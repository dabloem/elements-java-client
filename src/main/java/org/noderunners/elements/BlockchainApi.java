package org.noderunners.elements;

import com.github.arteam.simplejsonrpc.client.JsonRpcParams;
import com.github.arteam.simplejsonrpc.client.ParamsType;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcMethod;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcOptional;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcParam;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcService;
import org.noderunners.elements.model.Block;
import org.noderunners.elements.model.BlockHeader;
import org.noderunners.elements.model.BlockchainInfo;
import org.noderunners.elements.model.ChainTip;

import java.util.Map;

@JsonRpcService
@JsonRpcParams(ParamsType.MAP)
public interface BlockchainApi {

    @JsonRpcMethod("getbestblockhash")
    String getBestBlockhash();

    @JsonRpcMethod("getblock")
    Block getBlock(@JsonRpcParam("blockhash") String block);

    @JsonRpcMethod("getblockchaininfo")
    BlockchainInfo getBlockchainInfo();

    @JsonRpcMethod("getblockcount")
    Integer getBlockCount();

    @JsonRpcMethod
    Map<String, String> getblockfilter(@JsonRpcParam("blockhash") String blockhash, @JsonRpcParam("filtertype") @JsonRpcOptional String filtertype);

    @JsonRpcMethod
    String getblockhash(@JsonRpcParam("height") Integer height);

    @JsonRpcMethod
    BlockHeader getblockheader(@JsonRpcParam("blockhash") String blockhash, @JsonRpcParam("verbose") @JsonRpcOptional Boolean verbose);

    @JsonRpcMethod
    @JsonRpcParams(ParamsType.ARRAY)
    Map<String, Object> getblockstats(@JsonRpcParam("hash_or_height") Object hash_or_height, @JsonRpcParam("stats") @JsonRpcOptional String[] stats);

    @JsonRpcMethod
    ChainTip[] getchaintips();

    @JsonRpcMethod
    Map<String, Object> getchaintxstats(@JsonRpcParam("nblocks") @JsonRpcOptional Integer nblocks, @JsonRpcParam("blockhash") @JsonRpcOptional String  blockhash);

    @JsonRpcMethod
    Integer getdifficulty();        //TODO

    @JsonRpcMethod
    String getmempoolancestors(@JsonRpcParam("txid") String txid, @JsonRpcParam("verbose") @JsonRpcOptional Boolean verbose);
    //TODO no results

    @JsonRpcMethod
    String getmempooldescendants(@JsonRpcParam("txid") String txid, @JsonRpcParam("verbose") @JsonRpcOptional Boolean verbose);
    //TODO

    @JsonRpcMethod
    Object getmempoolentry(@JsonRpcParam("txid") String txid);

    @JsonRpcMethod
    Object getmempoolinfo();

    @JsonRpcMethod
    Object getrawmempool( @JsonRpcParam("verbose") @JsonRpcOptional Boolean verbose, @JsonRpcParam("mempool_sequence") @JsonRpcOptional String mempool_sequence);

    @JsonRpcMethod
    Object getsidechaininfo();

    @JsonRpcMethod
    Object gettxout(@JsonRpcParam("txid") String txid, @JsonRpcParam("n") Integer vout_number, @JsonRpcParam("include_mempool") @JsonRpcOptional Boolean include_mempool);

    @JsonRpcMethod
    String gettxoutproof();

    @JsonRpcMethod
    String gettxoutsetinfo();

    @JsonRpcMethod
    String preciousblock();

    @JsonRpcMethod
    String pruneblockchain();

    @JsonRpcMethod
    String savemempool();

    @JsonRpcMethod
    String scantxoutset();

    @JsonRpcMethod
    String verifychain();

    @JsonRpcMethod
    String verifytxoutproof();
}
