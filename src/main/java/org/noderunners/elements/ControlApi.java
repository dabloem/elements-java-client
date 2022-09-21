package org.noderunners.elements;

import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcMethod;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcParam;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcService;

@JsonRpcService
public interface ControlApi {

    @JsonRpcMethod
    Object getmemoryinfo(@JsonRpcParam("mode") String mode);

    @JsonRpcMethod
    Object getrpcinfo();

//    @JsonRpcMethod
//    Object help();

    @JsonRpcMethod
    Object logging(@JsonRpcParam("include") String[] include, @JsonRpcParam("exlude") String[] exclude);

    @JsonRpcMethod
    Object stop();

    @JsonRpcMethod
    Object uptime();

}
