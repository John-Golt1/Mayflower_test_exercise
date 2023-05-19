FROM maven
COPY ./mayflower /app/mayflower
CMD cd /app/mayflower && mvn clean test
