# url-process-web-api

## Description
`url-process-web-api` is a web API that processes URLs.

The API is written in Java using the Spring Boot framework.

## Scalability Design
We store the requested URLs in a database, so that we can. 
1. completely decouple of the processing of the URLs from the POST API call, to have workers process the URLs in the background. 
2. scale the number of workers to process the URLs in parallel.
3. spreading the works out can also resolve compute and network IO bottleneck issue.
4. if database becomes a bottleneck, we can shard the database, or use a different database (e.g. NoSQL).
5. when the processing is complete, the results are stored in the database, and can be queried anytime.
6. the results can be cached in a cache server (e.g. Redis/Memcache) to improve performance.

## Installation and execution

### Requirements
Java 17 or later.
Maven 3 or later.

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

curl --location 'localhost:8080/url/process?processId='$ID
```

### API Notes
The API is available at http://localhost:8080
1. POST: `localhost:8080/url/process` -- return processId.
2. GET: `localhost:8080/url/process?processId=<processId>` -- processId returned above.

### Other Notes
We use H2 in memory database for persistence. It can be accessed at: `http://localhost:8080/h2-console` (password: sa).
<br/>The database will reset between restarts.
<br/>Replace the in memory is a simple swap to a different database (and connection info) in the application.properties file, and the appropriate driver dependency in the pom.xml file