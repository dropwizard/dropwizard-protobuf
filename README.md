Dropwizard Protobuf
===================
[![Build Status](https://travis-ci.org/dropwizard/dropwizard-protobuf.svg?branch=master)](https://travis-ci.org/dropwizard/dropwizard-protobuf)
[![Coverage Status](https://coveralls.io/repos/dropwizard/dropwizard-protobuf/badge.svg?branch=master)](https://coveralls.io/r/dropwizard/dropwizard-protobuf?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/io.dropwizard.modules/dropwizard-protobuf.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/io.dropwizard.modules/dropwizard-protobuf/)
[![GitHub license](https://img.shields.io/github/license/dropwizard/dropwizard-protobuf.svg?style=flat-square)](https://github.com/dropwizard/dropwizard-protobuf/tree/master)


`dropwizard-protobuf` is a [Jersey](https://jersey.java.net) [JAX-RS Entity Provider](https://jersey.java.net/documentation/latest/message-body-workers.html) that allows reading and writing messages in Google's [Protocol Buffers](https://developers.google.com/protocol-buffers/) format.


Usage
-----

Just add the `ProtocolBufferMessageBodyProvider` to your Dropwizard application inside the [`Application#run`](http://dropwizard.io/1.0.5/dropwizard-core/apidocs/io/dropwizard/Application.html#run(java.lang.String[])) method.

```java
@Override
public void run(HelloWorldConfiguration config, Environment environment) throws Exception {
    environment.jersey().register(new ProtocolBufferMessageBodyProvider());
    environment.jersey().register(new InvalidProtocolBufferExceptionMapper());
}
```

Maven Artifacts
---------------

This project is available on Maven Central. To add it to your project simply add the following dependencies to your `pom.xml`:

```xml
<dependency>
    <groupId>io.dropwizard.modules</groupId>
    <artifactId>dropwizard-protobuf</artifactId>
    <version>1.0.5-1</version>
</dependency>
```

Support
-------

Please file bug reports and feature requests in [GitHub issues](https://github.com/dropwizard/dropwizard-protobuf/issues).


License
-------

Copyright (c) 2016 Justin Plock

This library is licensed under the Apache License, Version 2.0.

See http://www.apache.org/licenses/LICENSE-2.0.html or the LICENSE file in this repository for the full license text.
