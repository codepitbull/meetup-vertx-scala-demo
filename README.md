All code in this repo is subject to Apache 2.0 License.

#Start Prometheus
Use config from configs/prometheus.yml

#Run the application
java -jar target/scala-2.12/meetup-vertx-demo-assembly-0.1-SNAPSHOT.jar -Dvertx.metrics.options.enabled=true -Dvertx.metrics.options.jmxEnabled=true -Dvertx.metrics.options.registryName=exported


#Work with this project

Create a runnable fat-jar
```
sbt clean compile assembly
```

play around in sbt
```
sbt
> console
scala> vertx.deployVerticle(s"scala:${classOf[DemoVerticle].getName}")
scala> vertx.deploymentIDs
```

From here you can freely interact with the Vertx-API inside the sbt-scala-shell.
