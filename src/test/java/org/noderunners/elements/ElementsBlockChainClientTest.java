package org.noderunners.elements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.noderunners.elements.model.BlockchainInfo;
import org.noderunners.elements.transport.OkHttpTransport;

import java.io.IOException;
import java.util.Map;

class ElementsBlockChainClientTest {

    private static MockWebServer mockWebServer;

    private static ElementsBlockChainClient elementsBlockChainClient;


    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        elementsBlockChainClient = new ElementsBlockChainClient(
                new OkHttpTransport(mockWebServer.url("/").toString(), "dXNlcjpwYXNzd29yZA=="));
//                new OkHttpTransport("http://192.168.178.153:7041", "ZWxlbWVudHM6OWNkYTY2Nzg0ZWFjMjU2MTU1YzZmYTEyNjhhMWM1YThkODFkYTFmMjM5YTYzM2VjYTY3MjY3ODQwNjJhNDVlMw=="));
//        client = new HttpClientBlockChainClient(mockWebServer.url("/").toString(), "dXNlcjpwYXNzd29yZA==");
    }

//    @Test
//    void getBestBlockhash() throws IOException {
//        //GIVEN
//        MockResponse mockedResponse = new MockResponse()
//                .setBody("""
//                {"result":"7ac81fb0c02910a702cae81d780254f08ae271d9ef20a0fd66178d30c47c4e4d","error":null,"id":"curltest"}
//                """) //Sample
//                .addHeader("Content-Type", "text/plain");
//        mockWebServer.enqueue(mockedResponse);
//
//        //WHEN
//        String blockHash = client.getBestBlockhash();
//
//        //THEN
//        //MatcherAssert.assertThat(blockHash, CoreMatchers.equalTo("hex"));
//        mockedResponse = new MockResponse()
//                .setBody("""
//                        {
//                          "result": {
//                            "hash": "8cc318b49c6b49d219f74227ac54d3aa40101cdf7b4fbd72ca4d9625efa6151d",
//                            "confirmations": 133,
//                            "strippedsize": 934,
//                            "size": 11245,
//                            "weight": 14047,
//                            "height": 2001329,
//                            "version": 536870912,
//                            "versionHex": "20000000",
//                            "merkleroot": "1e1053d3085cb6bebdc52d9f0fae1d7e367a78b6689111ff258a63708192a7b6",
//                            "tx": [
//                              "1c94d766dbbe5fc6b8eccb7820dc6948562eab3d3cce04f71eecba166222b75d",
//                              "c7732b766c88a6eb26438392177f0362e35ccef50b058e90186dec2ebb84dfeb"
//                            ],
//                            "time": 1662750111,
//                            "mediantime": 1662749828,
//                            "dynamic_parameters": {
//                              "current": {
//                                "signblockscript": "002080d146b4003a2f942bfc9a288c364cb3b7b0d72278d55f7fab93e1cad15331e6",
//                                "max_block_witness": 1325,
//                                "fedpegscript": "",
//                                "extension_space": []
//                              },
//                              "proposed": {
//                                "signblockscript": "",
//                                "max_block_witness": 0,
//                                "fedpegscript": "",
//                                "extension_space": []
//                              }
//                            },
//                            "nTx": 2,
//                            "previousblockhash": "f8f35af799d9b12a12f51cb4a0893e0852d9a4da2bb19a61fadd44d0738b1a9a",
//                            "nextblockhash": "a452dd8a4b8589b0dea021aab564a045e07867ea916a50d24def69169f703155"
//                          },
//                          "error": null,
//                          "id": "curltest"
//                        }
//                        """) //Sample
//                .addHeader("Content-Type", "text/plain");
//        mockWebServer.enqueue(mockedResponse);
//
//        Block block = client.getBlock("8cc318b49c6b49d219f74227ac54d3aa40101cdf7b4fbd72ca4d9625efa6151d");
//    }

    @Test
    public void realTests() throws JsonProcessingException {
//        ElementsBlockChainClient elementsBlockChainClient = new ElementsBlockChainClient(
//                new OkHttpTransport("http://192.168.178.153:7041", "ZWxlbWVudHM6OWNkYTY2Nzg0ZWFjMjU2MTU1YzZmYTEyNjhhMWM1YThkODFkYTFmMjM5YTYzM2VjYTY3MjY3ODQwNjJhNDVlMw=="));

        JsonRpcClient client = elementsBlockChainClient.getClient(); //for raw/non-type safe responses

//        Object uptime = client.onDemand(ControlApi.class).getrpcinfo();

        //GIVEN
        MockResponse mockedResponse = new MockResponse()
                .setBody("""
                {"result": {"chain":"mainnet", "bestblockhash":"7ac81fb0c02910a702cae81d780254f08ae271d9ef20a0fd66178d30c47c4e4d"},"error":null,"id":"curltest"}
                """) //Sample
                .addHeader("Content-Type", "text/plain");
        mockWebServer.enqueue(mockedResponse);

        //Type-safe
        BlockchainInfo blockchainInfo = elementsBlockChainClient.getBlockchainInfo(); // *Example* for type safe responses

        //Non Type-safe
        try {
            Map<String, String> getblockfilter = client.onDemand(BlockchainApi.class).getblockfilter("8cc318b49c6b49d219f74227ac54d3aa40101cdf7b4fbd72ca4d9625efa6151d", "");
            System.out.println(getblockfilter); //TODO
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

//        Map<String, Object> r = (client.onDemand(BlockchainApi.class).getblockstats(2003934, new String[] {"feerate_percentiles", "blockhash"}));

//        Object o = client.onDemand(BlockchainApi.class).getdifficulty(); //(2000000, null);
//        Object b = client.onDemand(BlockchainApi.class).getmempoolentry("4f7747a2739a19ec23c1baab9a9c039aa8a2c6703fcca406aec96e0a44ff8bc9");
        Object b = client.onDemand(BlockchainApi.class).gettxout("5c0014c61f7b2cd60634286b0fce471aebf2bb146e1ce6bb76464d55b358af70", 1, null);
    }
}