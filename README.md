# java
## jmx-test
### Prerequiesit
* Install openjdk: `sudo apt update && sudo apt install -y openjdk-17-jre`
* Install [gradle-8.5](https://gradle.org/)
* Download [dd-java-agent.jar](https://repo1.maven.org/maven2/com/datadoghq/dd-java-agent/) to `/opt/dd-java-agent.jar`
* Run Datadog Agent with the following logs config
```
logs:
  - type: tcp
    port: 10518
    service: jmx-test
    source: java
```

### How to run
```
gradle build
gradle run
```
or
```
gradle build
gradle shadowJar
./run.sh
```
### How to stop
```
Ctrl-c
```
