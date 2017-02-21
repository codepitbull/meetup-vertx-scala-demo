#!/bin/bash
java -jar target/scala-2.12/meetup-vertx-demo-assembly-0.1-SNAPSHOT.jar -Dvertx.metrics.options.enabled=true -Dvertx.metrics.options.jmxEnabled=true -Dvertx.metrics.options.registryName=exported

