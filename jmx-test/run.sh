#!/usr/bin/env bash
root_dir=$(dirname $0)
/usr/bin/nohup /usr/bin/java \
	       -Dcom.sun.management.jmxremote.ssl=false \
	       -Dcom.sun.management.jmxremote.port=9012 \
	       -Dcom.sun.management.jmxremote.authenticate=false \
	       -javaagent:/opt/dd-java-agent.jar \
	       -Ddd.profiling.enabled=true -XX:FlightRecorderOptions=stackdepth=256 \
	       -Ddd.logs.injection=true \
	       -Ddd.trace.debug=true \
	       -Ddd.trace.config=${root_dir}/datadog.properties \
	       Main >/dev/null 2>&1 & echo $!>${root_dir}/pid.txt
