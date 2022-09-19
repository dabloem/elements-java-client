package org.noderunners.elements;

import java.util.ArrayList;

public class BlockchainInfo {

    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    public static class Bip34{
        public String type;
        public boolean active;
        public int height;
    }

    public static class Bip65{
        public String type;
        public boolean active;
        public int height;
    }

    public static class Bip66{
        public String type;
        public boolean active;
        public int height;
    }

    public static class Bip9{
        public String status;
        public int start_time;
        public long timeout;
        public int since;
    }

    public static class Csv{
        public String type;
        public boolean active;
        public int height;
    }

    public static class Dynafed{
        public String type;
        public Bip9 bip9;
        public int height;
        public boolean active;
    }

    public static class Root{
        public String chain;
        public int blocks;
        public int headers;
        public String bestblockhash;
        public int mediantime;
        public int verificationprogress;
        public boolean initialblockdownload;
        public long size_on_disk;
        public boolean pruned;
        public String signblock_asm;
        public String signblock_hex;
        public String current_params_root;
        public String current_signblock_asm;
        public String current_signblock_hex;
        public int max_block_witness;
        public String current_fedpeg_program;
        public String current_fedpeg_script;
        public ArrayList<String> extension_space;
        public int epoch_length;
        public int total_valid_epochs;
        public int epoch_age;
        public Softforks softforks;
        public String warnings;
    }

    public static class Segwit{
        public String type;
        public boolean active;
        public int height;
    }

    public static class Softforks{
        public Bip34 bip34;
        public Bip66 bip66;
        public Bip65 bip65;
        public Csv csv;
        public Segwit segwit;
        public Dynafed dynafed;
        public Testdummy testdummy;
        public Taproot taproot;
    }

    public static class Taproot{
        public String type;
        public Bip9 bip9;
        public int height;
        public boolean active;
    }

    public static class Testdummy{
        public String type;
        public Bip9 bip9;
        public int height;
        public boolean active;
    }


}
