package io.dropwizard.jersey.protobuf;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import com.google.common.base.Converter;
import com.google.protobuf.Timestamp;

public class Converters {

    public static Converter<Timestamp, Instant> toInstantUTC = Converter.from(
            t -> Instant.ofEpochSecond(t.getSeconds(), t.getNanos()),
            i -> Timestamp.newBuilder().setSeconds(i.getEpochSecond())
                    .setNanos(i.getNano()).build());

    public static Converter<Timestamp, OffsetDateTime> toOffsetDateTimeUTC = Converter
            .from(t -> toInstantUTC.convert(t).atOffset(ZoneOffset.UTC),
                    o -> toInstantUTC.reverse().convert(o.toInstant()));

    public static Converter<Timestamp, ZonedDateTime> toZonedDateTimeUTC = Converter
            .from(t -> toOffsetDateTimeUTC.convert(t).toZonedDateTime(),
                    z -> toOffsetDateTimeUTC.reverse()
                            .convert(z.toOffsetDateTime()));

    public static Converter<com.google.protobuf.Duration, Duration> toDuration = Converter
            .from(d -> Duration.ofSeconds(d.getSeconds(), d.getNanos()),
                    d -> com.google.protobuf.Duration.newBuilder()
                            .setSeconds(d.getSeconds()).setNanos(d.getNano())
                            .build());
}
