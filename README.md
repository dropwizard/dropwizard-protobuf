Dropwizard Protobuf
===================
[![Build Status](https://travis-ci.org/dropwizard/dropwizard-protobuf.svg?branch=master)](https://travis-ci.org/dropwizard/dropwizard-protobuf)
[![Coverage Status](https://img.shields.io/coveralls/dropwizard/dropwizard-protobuf.svg)](https://coveralls.io/r/dropwizard/dropwizard-protobuf)

`dropwizard-protobuf` is a [Jersey](https://jersey.java.net) [JAX-RS Entity Provider](https://jersey.java.net/documentation/latest/message-body-workers.html) that allows reading and writing messages in Google's [Protocol Buffers](https://developers.google.com/protocol-buffers/) format.


Usage
-----

Just add the `ProtocolBufferMessageBodyProvider` to your Dropwizard application inside the [`Application#run`](http://dropwizard.io/0.7.1/dropwizard-core/apidocs/io/dropwizard/Application.html#run(java.lang.String[])) method.

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
    <version>0.7.1-3</version>
</dependency>
```

Support
-------

Please file bug reports and feature requests in [GitHub issues](https://github.com/dropwizard/dropwizard-protobuf/issues).


License
-------

Copyright (c) 2014 Justin Plock

This library is licensed under the Apache License, Version 2.0.

See http://www.apache.org/licenses/LICENSE-2.0.html or the LICENSE file in this repository for the full license text.
