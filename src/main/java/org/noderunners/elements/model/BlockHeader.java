package org.noderunners.elements.model;

public class BlockHeader{
    public String hash;
    public int confirmations;
    public int height;
    public int version;
    public String versionHex;
    public String merkleroot;
    public int time;
    public int mediantime;
    public String signblock_witness_asm;
    public String signblock_witness_hex;
    public DynamicParameters dynamic_parameters;
    public int nTx;
    public String previousblockhash;
    public String nextblockhash;
}
