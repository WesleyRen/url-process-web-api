# url-process-web-api

## Description
`url-process-web-api` is a web API that processes URLs.

The API is written in Java using the Spring Boot framework.

## System Design
The system is designed to be scalable, and to handle large number of requests.

* The POST (URL process request) is a fire and forget API. It returns a processId, and the processing of the URLs is done in the background.
* We store the requested URLs in a database.
* We store the process results in the database as well.
* We store the process status on each URL, to have a more granular view of the process.
* We can easily decouple processing of the URLs from the POST API calls.

As a result, we can easily scale out the system by:
1. having dedicated workers to process the URLs in the background.
2. increasing the number of workers when load increase.
3. spreading the workers out geographically can also resolve compute and network IO bottleneck issues.
4. if database becomes a bottleneck, sharding the database, or using a different database (e.g. NoSQL).
5. since results can be queried anytime, caching results in cache servers (e.g. Redis/Memcache) to improve performance.

Above all, since there is most likely a finite set of URLs interest us, 
1. we can normalize the tables, so that processId and URL are in separate tables, and a given processed URL (with expiry setting) can serve multiple requests. 
2. we can even learn what are the most requested URLs, if they expire and we can pre-process them. This will allow us to reduce the number of workers needed and improve response time at the same time.


## Installation and execution

### Requirements
Java 17 or later.

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### Usage
```bash
ID=$(curl --location 'localhost:8080/url/process' \
--header 'Content-Type: application/json' \
--data '["https://raptive.com", "https://google.com", "https://yahoo.com"]'
)

echo "processID: $ID"

sleep 3
curl --location 'localhost:8080/url/process?processId='$ID
```

### API Notes
The API is available at http://localhost:8080
1. POST: `localhost:8080/url/process` -- return processId.
2. GET: `localhost:8080/url/process?processId=<processId>` -- processId returned above.

### Other Notes
We use H2 in memory database for persistence. It can be accessed at: `http://localhost:8080/h2-console` (password: sa).
<br/>The database will reset between restarts.
<br/>Replace H2 with other RDBMS is a simple swap to a different database (and connection info) in the application.properties file, and the appropriate driver dependency in the pom.xml file