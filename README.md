# gd-plugin-integration
Integration with Google Documents via plugin. 

## intro


You need to have docker, docker-compose, mongodb, java, maven. Application works with MongoDB database. 
 
If you don't want to use docker and docker-compose you can start app directly from your IDE. You still need MongoDB. 

If you don't have MongodDB, but you have docker, you can start images with docker-compose.yml. If you have docker but no docker-compose addon ... you know how to start images ... right? :)

You will also need to have Google credentials for authentication. If you are starting app from IDE, you have to define them in application.yml file or pass it to jvm with environment variables. Variables are defined in docker-compose.yml file, so have a look there. 


## directories

application main directory is ./app1 so go there!

`cd ./app1`

 - ./docker directory is for Dockerfile used by maven plugin. 
 - ./docker-compose directory contains docker-compose.yml file used to start application as two docker machines
 - ./src ...well :)

## build

`mvn clean package docker:build`

or: 

`./mvnw clean package docker:build`

or if you do not have mongodb on building machine: 

`mvn clean package docker:build -DskipTests`

or: 

`./mvnw clean package docker:build -DskipTest`

## start

`mvn spring-boot:run` and of course environments. 

or

`docker-compose up` in docker-compose directory

