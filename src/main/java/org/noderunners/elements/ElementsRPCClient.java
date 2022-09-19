package org.noderunners.elements;


import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.json.*;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.*;

//@ApplicationScoped
public class ElementsRPCClient implements ElementsClient, BlockchainClient {

    private String BASE_URL = "http://127.0.0.1:18891/";

    private String AUTHENTICATION;

    static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public ElementsRPCClient() {
    }
//
//    public ElementsRPCClient(String base_url) {
//        BASE_URL = base_url;
//    }

    public ElementsRPCClient(String base_url, String authentication) {
        BASE_URL = base_url;
        AUTHENTICATION = authentication;
    }

    @Override
    public List<String> getAddressByLabel(String label) throws IOException {
        Objects.requireNonNull(label, "Empty label is not allowed.");

        JsonArray params = Json.createArrayBuilder().add(label).build();
        JsonObject req = getJsonObject("getaddressesbylabel", params);

        Response response = getResponse(req);

        if (response.isSuccessful()) {
            Set<String> addresses = Json.createReader(response.body().byteStream()).readObject()
                    .getJsonObject("result")
                    .keySet();
            return new ArrayList<>(addresses);
        } else {
            JsonObject error = Json.createReader(response.body().byteStream())
                    .readObject()
                    .getJsonObject("error");
            if (error.getInt("code") == -11) {
                System.out.println("No addresses found.");
                return Collections.emptyList();
            } else {
                throw new IOException(error.getString("message"));
            }
        }
    }

    @Override
    public BigDecimal getReceivedByLabel(String label) throws IOException {
        JsonArray params = Json.createArrayBuilder().add(label).add(6).add("bitcoin").build();
        JsonObject req = getJsonObject("getreceivedbylabel", params);

        Response response = getResponse(req);

        if (response.isSuccessful()) {
            return Json.createReader(response.body().byteStream()).readObject()
                    .getJsonNumber("result").bigDecimalValue();
        } else {
            String message = Json.createReader(response.body().byteStream())
                    .readObject()
                    .getJsonObject("error")
                    .getString("message");

            throw new IOException(message);
        }
    }

    @Override
    public BigDecimal getReceivedByAddress(String address) throws IOException {
        JsonArray params = Json.createArrayBuilder().add(address).add(6).add("bitcoin").build();
        JsonObject req = getJsonObject("getreceivedbyaddress", params);

        Response response = getResponse(req);

        if (response.isSuccessful()) {
            return Json.createReader(response.body().byteStream()).readObject()
                    .getJsonNumber("result").bigDecimalValue();
        } else {
            String message = Json.createReader(response.body().byteStream())
                    .readObject()
                    .getJsonObject("error")
                    .getString("message");

            throw new IOException(message);
        }
    }

    @Override
    public String createNewAddress(String label) throws IOException {
        JsonArray params = Json.createArrayBuilder().add(label).build();
        JsonObject req = getJsonObject("getnewaddress", params);

        Response response = getResponse(req);

        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            String message = Json.createReader(response.body().byteStream())
                    .readObject()
                    .getJsonObject("error")
                    .getString("message");

            throw new IOException(message);
        }
    }

    @NotNull
    private Response getResponse(JsonObject request) throws IOException {
        StringWriter sw = new StringWriter(128);
        try (JsonWriter jw = Json.createWriter(sw)) {
            jw.write(request);
        }

        RequestBody body = RequestBody.create(sw.toString(), JSON);
        Request req = getRequest(body);

        Call call = client.newCall(req);
        return call.execute();
    }

    private JsonObject getJsonObject(String method, JsonArray jsonArray) {
        JsonObject req = Json.createObjectBuilder()
                .add("jsonrpc", "1.0")
                .add("id", "logbook")
                .add("method", method)
                .add("params", jsonArray)
                .build();
        return req;
    }

    private Request getRequest(RequestBody body) {
        return new Request.Builder()
                .url(BASE_URL)
                .addHeader("Authorization", "Basic "+AUTHENTICATION)
                .addHeader("Content-Type", "text/plain")
                .post(body)
                .build();

    }

    @Override
    public String getBestBlockhash() throws IOException {
        JsonArray params = Json.createArrayBuilder().build();
        JsonObject req = getJsonObject("getbestblockhash", params);

        Response response = getResponse(req);

        if (response.isSuccessful()) {
            return response.body().string().replaceAll("\"", "");
        } else {
            String message = Json.createReader(response.body().byteStream())
                    .readObject()
                    .getJsonObject("error")
                    .getString("message");

            throw new IOException(message);
        }
    }

    @Override
    public Block getBlock(String hash) throws IOException {
        return null;
    }

}
