package io.dropwizard.jersey.protobuf;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ProtobufBundle implements Bundle {

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        // nothing to initialize
    }

    @Override
    public void run(Environment environment) {
        environment.jersey().register(new ProtocolBufferMessageBodyProvider());
        environment.jersey()
                .register(new InvalidProtocolBufferExceptionMapper());
    }
}
