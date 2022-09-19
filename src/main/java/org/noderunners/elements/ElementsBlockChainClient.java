package org.noderunners.elements;

import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import com.github.arteam.simplejsonrpc.client.Transport;
import org.noderunners.elements.model.BlockchainInfo;


public class ElementsBlockChainClient {

    private static Class<BlockchainApi> CLAZZ = BlockchainApi.class;

    JsonRpcClient client;

    public ElementsBlockChainClient(Transport transport) {
        client = new JsonRpcClient(transport);
    }

    public JsonRpcClient getClient() {
        return client;
    }

    public BlockchainInfo getBlockchainInfo() {
        return client.onDemand(CLAZZ).getBlockchainInfo();
    }

}
