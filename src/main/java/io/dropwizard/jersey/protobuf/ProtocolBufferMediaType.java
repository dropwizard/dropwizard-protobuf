package io.dropwizard.jersey.protobuf;

import javax.ws.rs.core.MediaType;

public class ProtocolBufferMediaType extends MediaType {

    /** "application/x-protobuf" */
    public final static String APPLICATION_PROTOBUF = "application/x-protobuf";
    /** "application/x-protobuf" */
    public final static MediaType APPLICATION_PROTOBUF_TYPE = new MediaType(
            "application", "x-protobuf");

    /** "application/x-protobuf-text-format" */
    public final static String APPLICATION_PROTOBUF_TEXT = "application/x-protobuf-text-format";
    /** "application/x-protobuf-text-format" */
    public final static MediaType APPLICATION_PROTOBUF_TEXT_TYPE = new MediaType(
            "application", "x-protobuf-text-format");
}
