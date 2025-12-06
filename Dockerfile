FROM tomcat:11-jre25
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY build/libs/testserverproperties-0.0.1.war /usr/local/tomcat/webapps/testserverproperties.war

EXPOSE 8080

CMD ["catalina.sh", "run"]