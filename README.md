### Status
[![Build Status](https://travis-ci.org/fabianopaes/simscale-catalog-rest-api.svg?branch=travis-ci)](https://travis-ci.org/fabianopaes/simscale-catalog-rest-api)

# simscale-catalog-rest-api

## Prerequisites

You will need the following things properly installed on your own machine.

* [Java 8](https://www.java.com)

## Installation

Used [Gradle](http://www.gradle.org), a cross-platform build automation tool.
If you prefer [install Gradle](http://www.gradle.org/installation) or use a [Gradle wrapper](http://www.gradle.org/docs/current/userguide/gradle_wrapper.html) inside this project.

* `git clone git@github.com:fabianopaes/simscale-catalog-rest-api.git` this repository
* change into the new directory `simscale-catalog-rest-api`

### Build project

If you do not have a Gradle installed you might use this commands

```
chmod +x gradlew
```

```
./gradlew clean build
```

But if you already do, you can run the follow one

```
gradle build
```

Finally you can build a docker image, so just use 

```
sudo docker build -t simscale-rest-api:latest .
```

### Running application on local environment

If you would like to run the simscale-catalog-rest-api on your own machine, you can do it using docker or gradle ...

```
./gradlew bootRun
```

```
gradle bootRun
```

```
sudo docker run -it -p 9000:9000 simscale-rest-api:latest
```

### Continuous Integration

You can check the history of compilation jobs on [TravisCI](https://travis-ci.org/fabianopaes/simscale-catalog-rest-api)
 
Every single time when a some contributors push a change into repo a job will start

### Endpoint documentation

You can find the endpoints documentation on [Apiary.io](http://docs.simscalecatalogrestapi.apiary.io/)

### Running application on AWS EC2 instances

The deploy process is automated by a Jenkins CI job, so you just need to pull a Jenkins image from [DockerHub](https://hub.docker.com/) and get it running in your own machine to have the access to deploy the simscale-catalog-rest-api on production environment

Execute the follow commands:

```
sudo docker pull fabianopaes/jenkins:1.8
```

```
sudo docker run -it -p 8080:8080 -e "AWSSECRET=<put here your aws secret key>" -e "AWSACCESS=<put here your aws access key>" fabianopaes/jenkins:1.8 /bin/bash
```

At this step you just need to go to your favorite browser and access http://localhost:8080 you will see a jenkins home page. Please sign in using this credentials

*login: jenkins

*password: jenkins

After that you will see two jobs

* create-infrastructure - This one will set up a number of EC2 for you. To choose how many EC2 machine will run just change the follow line (in the job configuration shell)

```
 aws ec2 run-instances --image-id ami-597c8236 --count <NUMBER-OF-EC2> --instance-type t2.micro --key-name $KEYPAIR --security-groups $SG --user-data file://cloudinit.txt > /tmp/created-infrastructure.info
```

The hosts names will be printed on the console like this ...

```
IPS:

ec2-54-93-32-158.eu-central-1.compute.amazonaws.com ec2-52-59-238-209.eu-central-1.compute.amazonaws.com
```

* deploy - This one will checkout from git repository the branch which you have provided and build a docker image for simscale-catalog-rest-api. Finally it will deploy the container ONLY in the servers you have created executing the previous job.
