# Jobs Watcher

Queries the Indeed API and puts the data in an elastic instance that is assumed to be running on your localhost.  The description below has information on the details of making this happen.

## Requirements

1. [git](https://git-scm.com/downloads)
2. [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
3. [Maven](http://maven.apache.org/download.cgi)
4. [Docker](https://docs.docker.com/installation/)
5. [Mongo](https://www.mongodb.com/)

## CI Configuration / GitLab Pipelines


## Dependencies Setup

_See below for additional setup options (Docker)_

1. `docker-compose --file docker/docker-compose-dependencies.yml up` creates Elasticsearch and Kibana linked Docker containers
1. `. bin/index-create.sh` this will setup an elastic index called jobs.
1. `. bin/mongo-config-create.sh` will create the initial job scraping configuration

Please note that this will expose the following ports on your host:

1. 9200 - Default port for the Elastic API
2. 9300 - Communication channel between Elastic and Kibana
3. 5601 - Default port for the Kibana service
1. 27017 - Mongo

## Seed the data

You must first create an index by running the following script: ``

So there are a couple different ways to seed the data.  You can import this project into intellij or Eclipse and run the main line (com.labs1904.jobs.watch.App).  Right now the api key is hard coded along with the indeed api, it also assumes the elastic endpoint is configured to hit the ports suggested above.  There are plans to provide all of this via configuration or service discovery.

### Running via Eclipse

1. Install [eclipse](https://www.eclipse.org/downloads).
2. right click in package explorer -> import.
3. Existing maven projects
4. Browse to `<jobswatcher>`
5. right click on pom.xml -> run as : mvn clean
6. right click on pom.xml -> run as : mvn install
7. right click on App.java -> run as : java Application

## Docker

### Run the Entire Ecosystem

_Still under development, jobswatcher does not wait for healthy elk, or an index to have been created_

1. `mvn clean package`
1. `cd docker`
1. `docker-compose build`
1. `docker-compose up`

### Run the Dependencies Only (Elasticsearch and Kibana)

_See above Elasticsearch & Kibana setup_

### Run the Jobswatcher App Only

1. `mvn clean package`
1. `docker-compose --file docker/docker-compose-jobswatcher.yml build`
1. `docker-compose --file docker/docker-compose-jobswatcher.yml up`
1. `docker-compose --file docker/docker-compose.yml build`
1. `docker-compose --file docker/docker-compose.yml up`

## Viewing the data within Kibana

When you ran the docker command above that should have populated a kibana index called __jobs__.  If you have elastic and kibana running you can travel to the kibana [homepage](http://localhost:5601/).  There you will be asked to provide a default index.  Put in jobs and you should be able to browse the data.  
