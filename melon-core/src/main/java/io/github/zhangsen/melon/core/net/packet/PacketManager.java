package io.github.zhangsen.melon.core.net.packet;

import io.github.zhangsen.melon.core.net.codec.Codec;

import java.util.HashMap;
import java.util.Map;

public class PacketManager {

    private Map<Integer, Class<? extends AbstractPacket>> packetId2Class = new HashMap<>();
    private Map<Integer, Codec> packetId2Codec = new HashMap<>();
    private String scanPackage;

    public PacketManager(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public Class<? extends AbstractPacket> getPacketClass(int packetId){
        return packetId2Class.get(packetId);
    }

    public Codec getPacketCodec(int packetId){
        return packetId2Codec.get(packetId);
    }
}
