FROM openjdk:8
ADD target/achat-*.jar monAchat.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "monAchat.jar"]