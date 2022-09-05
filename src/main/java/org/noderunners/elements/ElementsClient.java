package org.noderunners.elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ElementsClient {
    BigDecimal getReceivedByLabel(String label) throws IOException;

    BigDecimal getReceivedByAddress(String address) throws IOException;

    List<String> getAddressByLabel(String label) throws IOException;

    String createNewAddress(String label) throws IOException;
}
