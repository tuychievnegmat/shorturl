FROM openjdk:17
COPY /out/artifacts/shorturl_jar/shorturl.jar shorturl.jar
ENTRYPOINT ["java", "-jar", "/shorturl.jar"]
#CMD["java", "-jar", "/shorturl.jar"]
ENV PORT=8090
EXPOSE $PORT
