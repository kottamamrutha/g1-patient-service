FROM openjdk:17
EXPOSE 9006
COPY target/g1-patient-service.jar g1-patient-service.jar
CMD [ "java","-jar","g1-patient-service.jar" ]