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

import com.google.protobuf.InvalidProtocolBufferException;
import io.dropwizard.jersey.protobuf.protos.DropwizardProtos.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class InvalidProtocolBufferExceptionMapper
    implements ExceptionMapper<InvalidProtocolBufferException> {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(InvalidProtocolBufferExceptionMapper.class);

  @Override
  public Response toResponse(InvalidProtocolBufferException exception) {
    final ErrorMessage message =
        ErrorMessage.newBuilder()
            .setMessage("Unable to process protocol buffer")
            .setCode(Response.Status.BAD_REQUEST.getStatusCode())
            .build();

    LOGGER.debug("Unable to process protocol buffer message", exception);
    return Response.status(Response.Status.BAD_REQUEST)
        .type(ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE)
        .entity(message)
        .build();
  }
}
