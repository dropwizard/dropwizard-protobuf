/*
 * Copyright © 2019 Smoke Turner, LLC (github@smoketurner.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.dropwizard.jersey.protobuf;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import io.dropwizard.jersey.protobuf.protos.DropwizardProtosTest.Example;
import io.dropwizard.jersey.protobuf.protos.DropwizardProtosTest.Example2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.core.MultivaluedHashMap;
import org.glassfish.jersey.internal.util.collection.StringKeyIgnoreCaseMultivaluedMap;
import org.junit.Test;

public class ProtocolBufferMessageBodyProviderTest {
  private final Annotation[] NONE = new Annotation[0];
  private final ProtocolBufferMessageBodyProvider provider =
      new ProtocolBufferMessageBodyProvider();
  private final Example example = Example.newBuilder().setId(1337L).build();
  private final Example2 example2 = Example2.newBuilder().setName("example").build();

  @Test
  public void readsDeserializableTypes() throws Exception {
    assertThat(provider.isReadable(Example.class, null, null, null)).isTrue();
  }

  @Test
  public void writesSerializableTypes() throws Exception {
    assertThat(provider.isWriteable(Example.class, null, null, null)).isTrue();
  }

  @Test
  public void deserializesRequestEntities() throws Exception {
    final Object actualExample1 =
        readFrom(provider, Example.class, new ByteArrayInputStream(example.toByteArray()));
    assertThat(actualExample1).isInstanceOf(Example.class);
    assertThat(((Example) actualExample1).getId()).isEqualTo(1337L);

    final Object actualExample2 =
        readFrom(provider, Example.class, new ByteArrayInputStream(example.toByteArray()));
    assertThat(actualExample2).isInstanceOf(Example.class);
    assertThat(((Example) actualExample2).getId()).isEqualTo(1337L);

    final Object actualExample3 =
        readFrom(provider, Example2.class, new ByteArrayInputStream(example2.toByteArray()));
    assertThat(actualExample3).isInstanceOf(Example2.class);
    assertThat(((Example2) actualExample3).getName()).isEqualTo("example");
  }

  private Object readFrom(
      ProtocolBufferMessageBodyProvider provider, Class<?> clazz, ByteArrayInputStream entity)
      throws IOException {
    final Object obj =
        provider.readFrom(
            (Class<Message>) clazz,
            clazz,
            NONE,
            ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE,
            new MultivaluedHashMap<String, String>(),
            entity);
    return obj;
  }

  @Test
  public void deserializesRequestTextEntities() throws Exception {
    final Object actualExample1 =
        readStringFrom(
            provider,
            Example.class,
            new ByteArrayInputStream(example.toString().getBytes(StandardCharsets.UTF_8)));
    assertThat(actualExample1).isInstanceOf(Example.class);
    assertThat(((Example) actualExample1).getId()).isEqualTo(1337L);

    final Object actualExample2 =
        readFrom(provider, Example2.class, new ByteArrayInputStream(example2.toByteArray()));
    assertThat(actualExample2).isInstanceOf(Example2.class);
    assertThat(((Example2) actualExample2).getName()).isEqualTo("example");
  }

  private Object readStringFrom(
      ProtocolBufferMessageBodyProvider provider, Class<?> clazz, ByteArrayInputStream entity)
      throws IOException {
    final Object obj =
        provider.readFrom(
            (Class<Message>) clazz,
            clazz,
            NONE,
            ProtocolBufferMediaType.APPLICATION_PROTOBUF_TEXT_TYPE,
            new MultivaluedHashMap<String, String>(),
            entity);
    return obj;
  }

  @Test
  public void throwsInvalidProtocolBufferExceptionForMalformedRequestEntities() throws Exception {
    final ByteArrayInputStream entity =
        new ByteArrayInputStream("{\"id\":-1d".getBytes(StandardCharsets.UTF_8));

    try {
      final Class<?> klass = Example.class;
      provider.readFrom(
          (Class<Message>) klass,
          Example.class,
          NONE,
          ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE,
          new MultivaluedHashMap<String, String>(),
          entity);
      failBecauseExceptionWasNotThrown(InvalidProtocolBufferException.class);
    } catch (InvalidProtocolBufferException e) {
    }
  }

  @Test
  public void serializesResponseEntities() throws Exception {
    final ByteArrayOutputStream output = new ByteArrayOutputStream();

    final Example example = Example.newBuilder().setId(1L).build();

    provider.writeTo(
        example,
        Example.class,
        Example.class,
        NONE,
        ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE,
        new StringKeyIgnoreCaseMultivaluedMap<>(),
        output);

    assertThat(output.toByteArray()).isEqualTo(example.toByteArray());
  }

  @Test
  public void serializesResponseTextEntities() throws Exception {
    final ByteArrayOutputStream output = new ByteArrayOutputStream();

    final Example example = Example.newBuilder().setId(1L).build();

    provider.writeTo(
        example,
        Example.class,
        Example.class,
        NONE,
        ProtocolBufferMediaType.APPLICATION_PROTOBUF_TEXT_TYPE,
        new StringKeyIgnoreCaseMultivaluedMap<>(),
        output);

    assertThat(output.toString()).isEqualTo("id: 1\n");
  }

  @Test
  public void responseEntitySize() throws Exception {
    final Example example = Example.newBuilder().setId(1L).build();

    final long size =
        provider.getSize(
            example,
            Example.class,
            Example.class,
            NONE,
            ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE);

    assertThat(size).isEqualTo(2L);
  }

  @Test
  public void responseTextEntitySize() throws Exception {
    final Example example = Example.newBuilder().setId(1L).build();

    final long size =
        provider.getSize(
            example,
            Example.class,
            Example.class,
            NONE,
            ProtocolBufferMediaType.APPLICATION_PROTOBUF_TEXT_TYPE);

    assertThat(size).isEqualTo(6L);
  }
}
