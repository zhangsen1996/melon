import io.github.zhangsen.melon.core.connector.impl.NettyConnector;
import io.github.zhangsen.melon.core.route.DefaultRouteManager;
import io.github.zhangsen.melon.core.session.SessionManager;

public class ServerTest {

    private static int count = 0;

    public static void main(String[] args) {
        NettyConnector nettyConnector = new NettyConnector(new DefaultRouteManager(), new SessionManager());
        nettyConnector.start();
/*        try {
            Thread.sleep(1000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
