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

```
chmod +x gradlew
```

```
./gradlew clean build
```

```
./gradlew bootRun
```

### Build project with docker

```
sudo docker build -t simscale-rest-api:latest .
```
```
sudo docker run -it -p 9000:9000 simscale-rest-api:latest
```

### Deploy process

The deploy process is automated by a Jenkins CI job, so you just need to pull a Jenkins image and get it running in your own machine to have the access to deploy the simscale-catalog-rest-api on production environment
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

* create-infrastructure - This one will set up a number of EC2 for you. To choice how many EC2 machine will run just change the follow line (in the job configuration)

```
 aws ec2 run-instances --image-id ami-597c8236 --count <NUMB-OF-EC2> --instance-type t2.micro --key-name $KEYPAIR --security-groups $SG --user-data file://cloudinit.txt > /tmp/created-infrastructure.info
```

The hosts names will be printed on the console like this ...

IPS:
ec2-54-93-32-158.eu-central-1.compute.amazonaws.com ec2-52-59-238-209.eu-central-1.compute.amazonaws.com

* deploy - This one will checkout from git repository the branch which you have provided and build a docker image for simscale-catalog-rest-api. Finally it will deploy the container ONLY in machines you created in the previous job.
