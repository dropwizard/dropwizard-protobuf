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
    environment.jersey().register(new InvalidProtocolBufferExceptionMapper());
  }
}
