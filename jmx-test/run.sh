#!/usr/bin/bash
project_root=$(dirname $0)
( cd "${project_root}"; /usr/bin/java \
	       -Dcom.sun.management.jmxremote.ssl=false \
	       -Dcom.sun.management.jmxremote.port=9012 \
	       -Dcom.sun.management.jmxremote.authenticate=false \
	       -javaagent:/opt/dd-java-agent.jar \
	       -Ddd.profiling.enabled=true -XX:FlightRecorderOptions=stackdepth=256 \
	       -Ddd.logs.injection=true \
	       -Ddd.trace.debug=false \
	       -Ddd.trace.config=${project_root}/app/datadog.properties \
	       -jar ./app/build/libs/app-all.jar jmx.test.Main
)
