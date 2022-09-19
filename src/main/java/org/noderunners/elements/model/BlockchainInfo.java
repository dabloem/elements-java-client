package org.noderunners.elements.model;

import java.util.ArrayList;

public class BlockchainInfo {
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
