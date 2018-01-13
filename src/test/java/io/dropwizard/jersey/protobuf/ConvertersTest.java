package io.dropwizard.jersey.protobuf;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import org.junit.Test;
import com.google.protobuf.Timestamp;

public class ConvertersTest {

    @Test
    public void testToInstant() {
        final Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(1515761132).setNanos(123000000).build();

        final Instant actual = Converters.toInstantUTC.convert(timestamp);
        final Instant expected = Instant.parse("2018-01-12T12:45:32.123Z");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFromInstant() {
        final Instant instant = Instant.parse("2018-01-12T12:45:32.123Z");

        final Timestamp actual = Converters.toInstantUTC.reverse()
                .convert(instant);
        final Timestamp expected = Timestamp.newBuilder().setSeconds(1515761132)
                .setNanos(123000000).build();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testToOffsetDateTime() {
        final Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(1515761132).setNanos(123000000).build();

        final OffsetDateTime actual = Converters.toOffsetDateTimeUTC
                .convert(timestamp);
        final OffsetDateTime expected = OffsetDateTime
                .parse("2018-01-12T12:45:32.123Z");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFromOffsetDateTime() {
        final OffsetDateTime offset = OffsetDateTime
                .parse("2018-01-12T12:45:32.123Z");

        final Timestamp actual = Converters.toOffsetDateTimeUTC.reverse()
                .convert(offset);
        final Timestamp expected = Timestamp.newBuilder().setSeconds(1515761132)
                .setNanos(123000000).build();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testToZonedDateTime() {
        final Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(1515761132).setNanos(123000000).build();

        final ZonedDateTime actual = Converters.toZonedDateTimeUTC
                .convert(timestamp);
        final ZonedDateTime expected = ZonedDateTime
                .parse("2018-01-12T12:45:32.123Z");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFromZonedDateTime() {
        final ZonedDateTime offset = ZonedDateTime
                .parse("2018-01-12T12:45:32.123Z");

        final Timestamp actual = Converters.toZonedDateTimeUTC.reverse()
                .convert(offset);
        final Timestamp expected = Timestamp.newBuilder().setSeconds(1515761132)
                .setNanos(123000000).build();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testToDuration() {
        final com.google.protobuf.Duration duration = com.google.protobuf.Duration
                .newBuilder().setSeconds(20).setNanos(345_000_000).build();

        final Duration actual = Converters.toDuration.convert(duration);
        final Duration expected = Duration.parse("PT20.345S");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFromDuration() {
        final Duration duration = Duration.parse("PT20.345S");

        final com.google.protobuf.Duration actual = Converters.toDuration
                .reverse().convert(duration);
        final com.google.protobuf.Duration expected = com.google.protobuf.Duration
                .newBuilder().setSeconds(20).setNanos(345_000_000).build();

        assertThat(actual).isEqualTo(expected);
    }
}
