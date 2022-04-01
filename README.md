`micronaut-discovery-client`, Spring Cloud Config, and GraalVM interaction issue.

## What is expected

When used with Spring Cloud Config, Micronaut Spring Config Client should resolve configuration as expected. To check that it works, run:

```bash
gradle clean dockerBuild
docker-compose up -d config
sleep 5
docker-compose up
```

When the server is up, hitting `http://localhost:8080` should yield `my-value`, which is resolved through the config client from file `./cloufconfig-files/native-config-demo-production.yml`.

## Breaking it

Simply run the same steps again, but with a native image.

```bash
gradle clean dockerBuildNative
docker-compose up -d config
sleep 5
docker-compose up
```

Micronaut is able to resolve config from the server, but breaks since `ConfigServerResponse#propertySources` is modified reflectively when parsing JSON.

```
Caused by: io.micronaut.http.client.exceptions.HttpClientResponseException: Error decoding HTTP response body: Error decoding stream for type [class io.micronaut.discovery.spring.config.client.ConfigServerResponse]: Cannot construct instance of `io.micronaut.discovery.spring.config.client.ConfigServerResponse` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
```

---

[micronaut-discovery-client#334](https://github.com/micronaut-projects/micronaut-discovery-client/issues/334)

[clowre/muicronaut-discovery-client@c32a49](https://github.com/clowre/micronaut-discovery-client/commit/c323a49e4c28c9ea26c918a40ee6d306f9f9afe4)