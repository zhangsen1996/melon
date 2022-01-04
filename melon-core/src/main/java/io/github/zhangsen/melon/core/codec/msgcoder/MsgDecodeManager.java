package io.github.zhangsen.melon.core.codec.msgcoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MsgDecodeManager {

    public static IDecode getMsgDecode(String type){
        return decodeMap.get(type);
    }

    private static Map<String, IDecode> decodeMap = new ConcurrentHashMap<>();

    static {
        JsonCoder jsonDecode = new JsonCoder();
        decodeMap.put(jsonDecode.type(),jsonDecode);
    }
}
