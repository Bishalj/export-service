FROM maven:3.6.3-jdk-11-openj9
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp/
EXPOSE 9095
RUN mvn compile -x test
#RUN mvn spring-boot:run
CMD ["java", "-jar", ".usr/src/myapp/target/bulk-export-0.0.1-SNAPSHOT.jar"]
