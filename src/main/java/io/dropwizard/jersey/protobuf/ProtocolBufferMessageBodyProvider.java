package io.dropwizard.jersey.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import com.google.protobuf.Message;
import com.google.protobuf.TextFormat;

/**
 * A Jersey provider which enables using Protocol Buffers to parse request
 * entities into objects and generate response entities from objects.
 */
@Provider
@Consumes(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
@Produces({ ProtocolBufferMediaType.APPLICATION_PROTOBUF,
        ProtocolBufferMediaType.APPLICATION_PROTOBUF_TEXT })
public class ProtocolBufferMessageBodyProvider
        implements MessageBodyReader<Message>, MessageBodyWriter<Message> {

    private final Map<Class<Message>, Method> methodCache = new ConcurrentHashMap<>();

    @Override
    public boolean isReadable(final Class<?> type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType) {
        return Message.class.isAssignableFrom(type);
    }

    @Override
    public Message readFrom(final Class<Message> type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType,
            final MultivaluedMap<String, String> httpHeaders,
            final InputStream entityStream) throws IOException {

        try {

            final Method newBuilder = methodCache.computeIfAbsent(type, t -> {
                try {
                    return t.getMethod("newBuilder");
                } catch (Exception e) {
                    return null;
                }
            });

            final Message.Builder builder = (Message.Builder) newBuilder
                    .invoke(type);
            return builder.mergeFrom(entityStream).build();
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }

    @Override
    public long getSize(final Message m, final Class<?> type,
            final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {

        if (mediaType.getSubtype().contains("text-format")) {
            final String formatted = TextFormat.printToUnicodeString(m);
            return formatted.getBytes(StandardCharsets.UTF_8).length;
        }

        return m.getSerializedSize();
    }

    @Override
    public boolean isWriteable(final Class<?> type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType) {
        return Message.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(final Message m, final Class<?> type,
            final Type genericType, final Annotation[] annotations,
            final MediaType mediaType,
            final MultivaluedMap<String, Object> httpHeaders,
            final OutputStream entityStream) throws IOException {

        if (mediaType.getSubtype().contains("text-format")) {
            entityStream.write(m.toString().getBytes(StandardCharsets.UTF_8));
        } else {
            entityStream.write(m.toByteArray());
        }
    }
}
