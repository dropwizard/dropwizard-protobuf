Dropwizard Protobuf
===================
[![Build Status](https://travis-ci.org/dropwizard/dropwizard-protobuf.svg?branch=master)](https://travis-ci.org/dropwizard/dropwizard-protobuf)
[![Maven Central](https://img.shields.io/maven-central/v/io.dropwizard.modules/dropwizard-protobuf.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/io.dropwizard.modules/dropwizard-protobuf/)
[![GitHub license](https://img.shields.io/github/license/dropwizard/dropwizard-protobuf.svg?style=flat-square)](https://github.com/dropwizard/dropwizard-protobuf/tree/master)


`dropwizard-protobuf` is a [Jersey](https://eclipse-ee4j.github.io/jersey/) [JAX-RS Entity Provider](https://www.oracle.com/technical-resources/articles/java/jax-rs.html) that allows reading and writing messages in Google's [Protocol Buffers](https://developers.google.com/protocol-buffers/) format.


Usage
-----

Just add the `ProtocolBundle` to your Dropwizard application inside the [`Application#initialize`](https://javadoc.io/static/io.dropwizard/dropwizard-project/2.0.7/io/dropwizard/Application.html#initialize-io.dropwizard.setup.Bootstrap-) method.

```java
@Override
public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    bootstrap.addBundle(new ProtobufBundle());
}
```

Maven Artifacts
---------------

This project is available on Maven Central. To add it to your project simply add the following dependencies to your `pom.xml`:

```xml
<dependency>
    <groupId>io.dropwizard.modules</groupId>
    <artifactId>dropwizard-protobuf</artifactId>
    <version>2.0.7-1</version>
</dependency>
```

Support
-------

Please file bug reports and feature requests in [GitHub issues](https://github.com/dropwizard/dropwizard-protobuf/issues).


License
-------

Copyright (c) 2020 Smoke Turner, LLC

This library is licensed under the Apache License, Version 2.0.

See http://www.apache.org/licenses/LICENSE-2.0.html or the LICENSE file in this repository for the full license text.
