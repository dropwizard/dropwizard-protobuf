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

import com.google.common.base.Converter;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Durations;
import com.google.protobuf.util.Timestamps;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Converters {

  public static final Converter<Timestamp, String> toStringUTC =
      Converter.from(
          Timestamps::toString,
          s -> {
            try {
              return Timestamps.parse(s);
            } catch (ParseException e) {
              return null;
            }
          });

  public static final Converter<Timestamp, Instant> toInstantUTC =
      Converter.from(
          t -> Instant.ofEpochSecond(t.getSeconds(), t.getNanos()),
          i -> Timestamp.newBuilder().setSeconds(i.getEpochSecond()).setNanos(i.getNano()).build());

  public static final Converter<Timestamp, OffsetDateTime> toOffsetDateTimeUTC =
      Converter.from(
          t -> toInstantUTC.convert(t).atOffset(ZoneOffset.UTC),
          o -> toInstantUTC.reverse().convert(o.toInstant()));

  public static final Converter<Timestamp, ZonedDateTime> toZonedDateTimeUTC =
      Converter.from(
          t -> toOffsetDateTimeUTC.convert(t).toZonedDateTime(),
          z -> toOffsetDateTimeUTC.reverse().convert(z.toOffsetDateTime()));

  public static final Converter<com.google.protobuf.Duration, Duration> toDuration =
      Converter.from(
          d -> Duration.ofSeconds(d.getSeconds(), d.getNanos()),
          d ->
              com.google.protobuf.Duration.newBuilder()
                  .setSeconds(d.getSeconds())
                  .setNanos(d.getNano())
                  .build());

  public static final Converter<com.google.protobuf.Duration, String> toDurationString =
      Converter.from(
          Durations::toString,
          s -> {
            try {
              return Durations.parse(s);
            } catch (ParseException e) {
              return null;
            }
          });
}
