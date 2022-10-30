FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/Springfeatures-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM tomcat:8.5.82-jdk11-corretto-al2
#RUN rm -rf /usr/local/tomcat/webapps/*
#COPY target/Springfeatures-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
#EXPOSE 8080
#ENV _JAVA_OPTIONS="-Xmx2g -Xms2g"
#ENV JAVA_TOOL_OPTIONS="-Xmx2g -Xms2g"
#ENV CATALINA_OPTS="-Xms2G -Xmx2G"
#CMD ["catalina.sh", "run"]
