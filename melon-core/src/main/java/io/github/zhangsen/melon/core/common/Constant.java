package io.github.zhangsen.melon.core.common;

public interface Constant {

    int PACKET_HEAD_LENGTH = 6;




    String DECODE_JSON = "json";
    String DECODE_PROTOBUF = "protobuf";

    interface PacketType{
        byte HANDSHAKE = 1;
        byte HANDSHAKE_ACK = 2;
        byte BUSINESS_MSG = 3;
    }
}
