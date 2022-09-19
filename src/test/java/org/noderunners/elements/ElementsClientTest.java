//package org.noderunners.elements;
//
//import okhttp3.mockwebserver.MockResponse;
//import okhttp3.mockwebserver.MockWebServer;
//import okhttp3.mockwebserver.RecordedRequest;
//import org.hamcrest.CoreMatchers;
//import org.hamcrest.MatcherAssert;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
//import static org.hamcrest.number.OrderingComparison.greaterThan;
//
//class ElementsClientTest {
//
//    private static MockWebServer mockWebServer;
//
//    private static ElementsRPCClient client;
//
//
//    @BeforeAll
//    static void setUp() throws IOException {
//        mockWebServer = new MockWebServer();
//        mockWebServer.start();
//
//        client = new ElementsRPCClient(mockWebServer.url("/").toString(), "dXNlcjpwYXNzd29yZA==");
//    }
//
//    @Test
//    void getReceivedByAddress() throws IOException {
//        //GIVEN
//        MockResponse mockedResponse = new MockResponse()
//                .setBody("{\"result\": 0.5}") //Sample
//                .addHeader("Content-Type", "application/json");
//        mockWebServer.enqueue(mockedResponse);
//
//        //WHEN
//        BigDecimal balance = client.getReceivedByAddress("vjTzxaiMicAMtFTZk8fkD6S2zyJWyb9ERN6yv1QNjphXM42rujg833ZzE7E8eZfu6KKaYZkyia42pE1p");
//
//        //THEN
//        MatcherAssert.assertThat(balance, CoreMatchers.is(greaterThan(new BigDecimal("0.1"))));
//    }
//
//    @Test
//    void getReceivedByLabel() throws IOException, InterruptedException {
//        MockResponse mockedResponse = new MockResponse()
//                .setBody("{\"result\": 0}")
//                .addHeader("Content-Type", "application/json");
//        mockWebServer.enqueue(mockedResponse);
//
//        BigDecimal balance = client.getReceivedByLabel("191463590");
//
//        RecordedRequest request1 = mockWebServer.takeRequest();
//        MatcherAssert.assertThat(request1.getHeader("Authorization"), CoreMatchers.equalTo("Basic " + "dXNlcjpwYXNzd29yZA=="));
//
//        MatcherAssert.assertThat(balance, CoreMatchers.is(closeTo(new BigDecimal("0"), new BigDecimal(0.01))));
//    }
//
//    @Test
//    void getAddressesByLabel() throws IOException {
//        MockResponse mockedResponse = new MockResponse()
//                .setBody(
//                        """
//                        {"result": {
//                            "vjTzxaiMicAMtFTZk8fkD6S2zyJWyb9ERN6yv1QNjphXM42rujg833ZzE7E8eZfu6KKaYZkyia42pE1p":\
//                                { "purpose" : "send" },
//                            "vjTzxaiMicAMtFTZk8fkD6S2zyJWyb9ERN6yv1QNjphXM42rujg833ZzE7E8eZfu6KKaYZkyia42pE1x":\
//                                { "purpose" : "send" }
//                            }
//                        }
//                        """)
//                .addHeader("Content-Type", "application/json");
//        mockWebServer.enqueue(mockedResponse);
//
//        List<String> addresses = client.getAddressByLabel("NodeRunner");
//        MatcherAssert.assertThat(addresses, hasSize(2));
//    }
//
//    @Test
//    void getBestBlockhash() throws IOException {
//        //GIVEN
//        MockResponse mockedResponse = new MockResponse()
//                .setBody("\"hex\"") //Sample
//                .addHeader("Content-Type", "text/plain");
//        mockWebServer.enqueue(mockedResponse);
//
//        //WHEN
//        String blockHash = client.getBestBlockhash();
//
//        //THEN
//        MatcherAssert.assertThat(blockHash, CoreMatchers.equalTo("hex"));
//    }
//}