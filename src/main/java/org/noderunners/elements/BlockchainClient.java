package org.noderunners.elements;

import java.io.IOException;

public interface BlockchainClient {

    String getBestBlockhash() throws IOException;
}
