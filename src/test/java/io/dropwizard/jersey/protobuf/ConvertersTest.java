/*
 * Copyright © 2018 Smoke Turner, LLC (contact@smoketurner.com)
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

import com.google.protobuf.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import org.junit.Test;

public class ConvertersTest {

  @Test
  public void testToInstant() {
    final Timestamp timestamp =
        Timestamp.newBuilder().setSeconds(1515761132).setNanos(123000000).build();

    final Instant actual = Converters.toInstantUTC.convert(timestamp);
    final Instant expected = Instant.parse("2018-01-12T12:45:32.123Z");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testFromInstant() {
    final Instant instant = Instant.parse("2018-01-12T12:45:32.123Z");

    final Timestamp actual = Converters.toInstantUTC.reverse().convert(instant);
    final Timestamp expected =
        Timestamp.newBuilder().setSeconds(1515761132).setNanos(123000000).build();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testToOffsetDateTime() {
    final Timestamp timestamp =
        Timestamp.newBuilder().setSeconds(1515761132).setNanos(123000000).build();

    final OffsetDateTime actual = Converters.toOffsetDateTimeUTC.convert(timestamp);
    final OffsetDateTime expected = OffsetDateTime.parse("2018-01-12T12:45:32.123Z");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testFromOffsetDateTime() {
    final OffsetDateTime offset = OffsetDateTime.parse("2018-01-12T12:45:32.123Z");

    final Timestamp actual = Converters.toOffsetDateTimeUTC.reverse().convert(offset);
    final Timestamp expected =
        Timestamp.newBuilder().setSeconds(1515761132).setNanos(123000000).build();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testToZonedDateTime() {
    final Timestamp timestamp =
        Timestamp.newBuilder().setSeconds(1515761132).setNanos(123000000).build();

    final ZonedDateTime actual = Converters.toZonedDateTimeUTC.convert(timestamp);
    final ZonedDateTime expected = ZonedDateTime.parse("2018-01-12T12:45:32.123Z");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testFromZonedDateTime() {
    final ZonedDateTime offset = ZonedDateTime.parse("2018-01-12T12:45:32.123Z");

    final Timestamp actual = Converters.toZonedDateTimeUTC.reverse().convert(offset);
    final Timestamp expected =
        Timestamp.newBuilder().setSeconds(1515761132).setNanos(123000000).build();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testToDuration() {
    final com.google.protobuf.Duration duration =
        com.google.protobuf.Duration.newBuilder().setSeconds(20).setNanos(345_000_000).build();

    final Duration actual = Converters.toDuration.convert(duration);
    final Duration expected = Duration.parse("PT20.345S");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testFromDuration() {
    final Duration duration = Duration.parse("PT20.345S");

    final com.google.protobuf.Duration actual = Converters.toDuration.reverse().convert(duration);
    final com.google.protobuf.Duration expected =
        com.google.protobuf.Duration.newBuilder().setSeconds(20).setNanos(345_000_000).build();

    assertThat(actual).isEqualTo(expected);
  }
}
