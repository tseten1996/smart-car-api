# smart-car-api

1. Download Java 11 https://www.oracle.com/java/technologies/downloads/#java11-mac
2. Type in command line : `echo $JAVA_HOME` . Verify path looks like `/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home`
3. If doesn't look like that go to .bash_profile : `vim ~/.bash_profile` and add to the file `export JAVA_HOME=$(/usr/libexec/java_home)`
4. Download Docker https://docs.docker.com/desktop/mac/install/
5. Go to the smart-car-api main directory and call `mvn install`
6. Once mvn installation is complete call `docker build -f Dockerfile -t smart-car-api .` on the command line to create a docker image. 
7. Once the docker build is complete call `docker run -p 8085:8085 smart-car-api` on the command line to run the docker image on docker container. 
