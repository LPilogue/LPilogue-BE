# Run Stage
FROM openjdk:17-jdk-slim

WORKDIR /app

# 빌드 결과물 복사
COPY  ./build/libs/*.jar lpilogue.jar

# 포트 노출 및 실행
EXPOSE 8080
ENV SPRING_DATASOURCE_URL=jdbc:mysql://lpilogue-db.c5msg0qqmmcw.ap-northeast-2.rds.amazonaws.com:3306/lpilogue?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=dnwnswls2501

CMD ["java", "-jar", "lpilogue.jar"]
