FROM openjdk:20-bullseye

# Uygulama WAR dosyasını konteynera kopyala
COPY /target/oauth.jar /oauth.jar

# Uygulama çalıştırma komutunu belirtin
CMD ["java", "-jar", "/oauth.jar"]