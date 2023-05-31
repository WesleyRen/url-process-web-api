# url-process-web-api
`url-process-web-api` is a web API that processes URLs.

## Description
There are two directories in this repository.
1. `rest` - a REST api server takes a list of URLs, store them in a database, and returns a requestId, which can be used to query the status of the process.
2. `process` - a background process that processes the URLs, and stores the results in a database.

The API is written in Java using the Spring Boot framework.

## System Design
The system is designed to be scalable to handle large number of requests.

**Key words: sharding, decoupling frontend and backend.**
* REST APIs save the URLs.
* Background process processes the URLs.
* The REST APIs and background process scales independently.
* URLs are sharded, so that each worker only processes a subset of the URLs.

As a result, we can easily scale out the system by:
1. increasing the number of workers when load increase.
2. spreading the workers out geographically can also resolve compute and network IO bottleneck issues.
3. putting shards in different database servers.
4. caching results in cache servers (e.g. Redis/Memcache).

Also, since there is most likely a finite set of URLs interest us, 
1. we can normalize the tables, so that requestId and URL are in separate tables, and a given processed URL (with expiry setting) can serve multiple requests. 
2. we can even learn what are the most requested URLs, if they expire and we can pre-process them. This will allow us to reduce the number of workers needed and improve response time at the same time.


## Installation and execution

### Requirements
Java 17 or later.

```bash
./mvnw clean install
cd rest; ../mvnw spring-boot:run
```
On a separate terminal:
```bash
cd processor; ../mvnw spring-boot:run
```

### Usage
```bash
ID=$(curl --location 'localhost:8080/url/process' \
--header 'Content-Type: application/json' \
--data '["https://raptive.com", "https://google.com", "https://yahoo.com", "https://facebook.com", '\
'"https://blogger.com", "https://youtube.com", "https://microsoft.com", "https://apple.com", "https://linkedin.com", "https://whatsapp.com", "https://cloudflare.com"]'
)

echo "requestId: $ID"

i=0; 
while [ $i -lt 5 ]; do
curl --location 'localhost:8080/url/process/status?requestId='$ID
echo -n "\n"; i=$((i+1)); sleep 3
done
# To see the full detailed info:
curl --location 'localhost:8080/url/process?requestId='$ID
```

### API Notes
The API is available at http://localhost:8080
1. POST: `localhost:8080/url/process` -- return requestId.
2. GET: `localhost:8080/url/process?requestId=<requestId>` -- requestId returned above.

### Other Notes
We use H2 in memory database for persistence. It can be accessed at: `http://localhost:8080/h2-console` (password: sa).
<br/>The database will reset between restarts.
<br/>Replace H2 with other RDBMS is a simple swap to a different database (and connection info) in the application.properties file, and the appropriate driver dependency in the pom.xml file