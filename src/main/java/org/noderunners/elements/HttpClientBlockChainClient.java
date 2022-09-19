package org.noderunners.elements;

import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import com.github.arteam.simplejsonrpc.client.Transport;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static org.noderunners.elements.ElementsRPCClient.JSON;

public class HttpClientBlockChainClient implements BlockchainClient {

    private String BASE_URL;
    private String AUTHENTICATION;

    JsonRpcClient client = new JsonRpcClient(new Transport() {

        // Apache HttpClient is used as an example
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        @Override
        public String pass(String request) throws IOException {
            RequestBody body = RequestBody.create(request, MediaType.parse("text/plain"));
            Request req = new Request.Builder()
                    .url(BASE_URL)
                    .addHeader("Authorization", "Basic " + AUTHENTICATION)
//                    .addHeader("Content-Type", "text/plain")
                    .post(body)
                    .build();
            Call call = client.newCall(req);
            return call.execute().body().string();
        }
    });

    public HttpClientBlockChainClient(String BASE_URL, String AUTHENTICATION) {
        this.BASE_URL = BASE_URL;
        this.AUTHENTICATION = AUTHENTICATION;
    }

    @Override
    public String getBestBlockhash() throws IOException {
        return client.onDemand(BlockchainApi.class).getBestBlockhash();
    }

    @Override
    public Block getBlock(String hash) throws IOException {
        return client.onDemand(BlockchainApi.class).getBlock(hash);
    }

}
