package nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * User: pei.xu
 * Date: 15-5-18
 * Time: 下午4:58
 */
public class NioServerTest {


    private static Selector selector = null;

    public static void startServer() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(80));

        selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        execute();
    }

    /**
     * 业务执行
     */
    public static void execute() throws IOException {

        System.out.println("Server start ok！");
        while (true) {

            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端事件建立链接!");
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    try {
                        socketChannel.read(byteBuffer);
                        byte[] b = byteBuffer.array();
                        System.out.println("client sms info:" + new String(b, "utf-8"));
                        socketChannel.write(ByteBuffer.wrap("数据已收到处理成功!".getBytes()));
                    } catch (IOException e) {
                        System.out.println("异常信息:" + e.getMessage());
                        //socketChannel.finishConnect();
                        socketChannel.close();
                    }

                }
            }

        }
    }

    public static void main(String[] args) throws IOException {

        NioServerTest.startServer();

    }
}
