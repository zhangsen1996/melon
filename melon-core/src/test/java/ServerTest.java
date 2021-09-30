import io.github.zhangsen.melon.core.connector.impl.NettyConnector;

import java.util.concurrent.ConcurrentSkipListSet;

public class ServerTest {

    public static void main(String[] args) {
        NettyConnector nettyConnector = new NettyConnector();
        nettyConnector.start();

    }
}
