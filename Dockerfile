FROM amazoncorretto:11-alpine3.17-jdk
COPY /secure-connect-cricfizz-app.zip /opt/apps/secure-connect-cricfizz-app.zip
COPY build/libs/LiveCricketScores-0.0.1-SNAPSHOT.jar /opt/apps/LiveCricketScores-1-SNAPSHOT.jar
WORKDIR /opt/apps
ENTRYPOINT ["java", "-jar", "LiveCricketScores-1-SNAPSHOT.jar"]