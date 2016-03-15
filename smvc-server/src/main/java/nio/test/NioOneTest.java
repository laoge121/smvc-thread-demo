package nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created with IntelliJ IDEA.
 * User: pei.xu
 * Date: 15-5-18
 * Time: 下午6:51
 * To change this template use File | Settings | File Templates.
 */
public class NioOneTest {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
            NioOneTest.execute(i);
        }
    }


    public static void execute(int i) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 80));
        socketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(("nihao" + i).getBytes());
        //byteBuffer.flip();
        socketChannel.write(ByteBuffer.wrap(("nihao" + i).getBytes()));
        boolean bool = false;

        while (true) {
            byteBuffer.clear();
            int b = socketChannel.read(byteBuffer);
            if (b > 0) {
                byteBuffer.flip();
                System.out.println("Client: data = " + new String(byteBuffer.array(), 0, b));
                bool = true;
            } else if (bool) {
                bool = false;
                socketChannel.close();
                break;
            }
        }
    }

}
