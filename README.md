# buffer-limit

## Issue:
```
Caused by: org.springframework.web.reactive.function.client.WebClientResponseException: 200 OK from GET http://localhost:3001/demo; nested exception is org.springframework.core.io.buffer.DataBufferLimitException: Exceeded limit on max bytes to buffer : 204800
	at org.springframework.web.reactive.function.client.WebClientResponseException.create(WebClientResponseException.java:229) ~[spring-webflux-5.3.22.jar:5.3.22]
	at org.springframework.web.reactive.function.client.DefaultClientResponse.lambda$createException$1(DefaultClientResponse.java:207) ~[spring-webflux-5.3.22.jar:5.3.22]
	at reactor.core.publisher.FluxMap$MapSubscriber.onNext(FluxMap.java:106) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onNext(FluxOnErrorResume.java:79) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.Operators$ScalarSubscription.request(Operators.java:2398) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.Operators$MultiSubscriptionSubscriber.set(Operators.java:2194) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onSubscribe(FluxOnErrorResume.java:74) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.MonoJust.subscribe(MonoJust.java:55) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.Mono.subscribe(Mono.java:4397) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:103) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.Operators$MonoSubscriber.onError(Operators.java:1863) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxOnAssembly$OnAssemblySubscriber.onError(FluxOnAssembly.java:544) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onError(FluxMapFuseable.java:142) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxContextWrite$ContextWriteSubscriber.onError(FluxContextWrite.java:121) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxMapFuseable$MapFuseableConditionalSubscriber.onError(FluxMapFuseable.java:340) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxFilterFuseable$FilterFuseableConditionalSubscriber.onError(FluxFilterFuseable.java:382) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.MonoCollect$CollectSubscriber.onError(MonoCollect.java:145) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxMap$MapSubscriber.onError(FluxMap.java:134) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxPeek$PeekSubscriber.onError(FluxPeek.java:222) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.FluxMap$MapSubscriber.onError(FluxMap.java:134) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.core.publisher.Operators.error(Operators.java:198) ~[reactor-core-3.4.21.jar:3.4.21]
	at reactor.netty.channel.FluxReceive.startReceiver(FluxReceive.java:182) ~[reactor-netty-core-1.0.21.jar:1.0.21]
	at reactor.netty.channel.FluxReceive.subscribe(FluxReceive.java:143) ~[reactor-netty-core-1.0.21.jar:1.0.21]
	at reactor.core.publisher.InternalFluxOperator.subscribe(InternalFluxOperator.java:62) ~[reactor-core-3.4.21.jar:3.4.21]
```

## Reason: 

  All codecs are limited to 256K by default.
  
## Fix:
 
Configure codec with higher 
 
    @Value("${client.max-in-memory-size}")
    private DataSize dataSize;

    @Bean
    WebClient.Builder builder(){
        return WebClient.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize((int)dataSize.toBytes()))
                .baseUrl("http://localhost:3001")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

### Setup mock using:

- Tool: https://mockoon.com/
- Data: ./mock-data/
