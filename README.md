# Spring basic crm

# 1. Build the Docker image
docker build -t spring-crm .

# 2. Run the container, mapping port 8080 and setting datasource env vars
docker run --rm -p 8080:8080 \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/spring_crm \
-e SPRING_DATASOURCE_USERNAME=user \
-e SPRING_DATASOURCE_PASSWORD=password \
spring-crm