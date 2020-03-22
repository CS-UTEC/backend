git pull origin
kill -9 $(lsof -t -i:3031)
mvn install
mvn spring-boot:run &>~/backend_COVID.log &

