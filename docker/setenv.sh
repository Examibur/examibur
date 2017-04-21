CATALINA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
JAVA_OPTS="${JAVA_OPTS} -javaagent:/usr/local/tomcat/lib/jacocoagent.jar=append=false,destfile=/var/jacoco/jacocoUi.exec"

