package org.noderunners.elements;

import com.github.arteam.simplejsonrpc.client.Transport;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public class OkHttpTransport implements Transport {

    private final String BASE_URL;
    private final String AUTHENTICATION;

    // Apache HttpClient is used as an example
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    public OkHttpTransport(String base_url, String authentication) {
        BASE_URL = base_url;
        AUTHENTICATION = authentication;
    }


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
}
