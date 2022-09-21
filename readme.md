Usage
--

Create the client:
```java
ElementsBlockChainClient elementsBlockChainClient = new ElementsBlockChainClient(
    new OkHttpTransport("URL", "BASIC_PASSWORD"));
```
ps, to use different http libraries (e.g. apache, okHttp, etc), select the appropriate Transport.   


Select a specific API:
```java
BlockchainApi blockchainApi = client.onDemand(BlockchainApi.class);
//or
ControlApi controlApi = client.onDemand(ControlApi.class);
//or
// Mining|Network|RawTransactions|Wallet...
```

Use the API:
```java
String hash = blockchainApi.getBestBlockhash();
```