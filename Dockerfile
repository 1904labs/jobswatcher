# Dockerfiles must live on the same directory as the root of the project
# All other docker related files can be found in the `docker` directory

# Based on the OpenJDK Alpine image of (currently) Java 8
FROM openjdk:alpine

# Add distributable files from /target to container root path
ADD target/jobswatcher.jar /

# Launch one-time job (TODO: Update to cron job)
ENTRYPOINT ["java", "-jar", "jobswatcher.jar"]
