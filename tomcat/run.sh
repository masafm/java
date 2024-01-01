#!/usr/bin/bash
( cd "$(dirname $0)"; /usr/bin/java \
			  -Dcom.sun.management.jmxremote.ssl=false \
			  -Dcom.sun.management.jmxremote.port=9012 \
			  -Dcom.sun.management.jmxremote.authenticate=false \
			  -javaagent:/opt/dd-java-agent.jar \
			  -Ddd.profiling.enabled=true -XX:FlightRecorderOptions=stackdepth=256 \
			  -Ddd.logs.injection=true \
			  -Ddd.trace.debug=false \
			  -Ddd.trace.config="$(dirname $0)/app/datadog.properties" \
			  -jar ./app/build/libs/app-1.0-SNAPSHOT-all.jar tomcat.Main
)
