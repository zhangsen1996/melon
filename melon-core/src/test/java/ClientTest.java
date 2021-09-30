import io.github.zhangsen.melon.core.connector.impl.NettyClient;

public class ClientTest {
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
    }
}
