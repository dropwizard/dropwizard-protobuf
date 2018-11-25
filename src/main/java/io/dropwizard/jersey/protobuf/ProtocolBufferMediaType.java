/*
 * Copyright © 2018 Smoke Turner, LLC (github@smoketurner.com)
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

import javax.ws.rs.core.MediaType;

public class ProtocolBufferMediaType extends MediaType {

  /** "application/x-protobuf" */
  public static final String APPLICATION_PROTOBUF = "application/x-protobuf";
  /** "application/x-protobuf" */
  public static final MediaType APPLICATION_PROTOBUF_TYPE =
      new MediaType("application", "x-protobuf");

  /** "application/x-protobuf-text-format" */
  public static final String APPLICATION_PROTOBUF_TEXT = "application/x-protobuf-text-format";
  /** "application/x-protobuf-text-format" */
  public static final MediaType APPLICATION_PROTOBUF_TEXT_TYPE =
      new MediaType("application", "x-protobuf-text-format");
}
