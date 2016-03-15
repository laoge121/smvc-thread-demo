package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * User: pei.xu
 * Date: 15-4-2
 * Time: 下午7:14
 */
public class NioClent1 {

    private Selector selector = null;

    public void clientStart() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("127.0.0.1", 80));

        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            this.execute();
        }

    }

    public void execute() throws IOException {

        selector.select();

        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();

            iterator.remove();

            if (selectionKey.isConnectable()) {

                System.out.println("数据连接建立发送数据到服务端!");
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                //如果正在连接结束链接
                if (socketChannel.isConnectionPending()) {
                    socketChannel.finishConnect();
                }

                ByteBuffer byteBuffer = ByteBuffer.wrap("nihao".getBytes());
                socketChannel.write(byteBuffer);
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                System.out.println("设置成接受数据发送数据到服务端!");
            } else if (selectionKey.isReadable()) {

                System.out.println("开始接收数据!");

                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                socketChannel.read(byteBuffer);
                System.out.println("接收到的数据:" + new String(byteBuffer.array()));

            }
        }
    }


    public static void main(String[] args) throws IOException {
        new NioClent1().clientStart();
    }
}
