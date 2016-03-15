package nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * NIO 服务端
 * User: pei.xu
 * Date: 15-4-2
 * Time: 下午4:10
 */
public class NioService {

    private Selector selector = null;


    public void serverStart() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress(80));

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            this.execute();
        }
    }

    public void execute() throws IOException {

        //选中可用的通道
        selector.select();

        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

        while (iterator.hasNext()) {

            SelectionKey selectionKey = iterator.next();

            iterator.remove();
            if ((selectionKey.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                System.out.println("客户端请求链接!");
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null)
                    return;
                if (socketChannel.isConnected()) {
                    socketChannel.finishConnect();
                }
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);

            } else if ((selectionKey.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {

                System.out.println("获取客户端数据");

                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                socketChannel.read(byteBuffer);

                System.out.println(new String(byteBuffer.array()));

                System.out.println("设置服务端写入数据");
                byteBuffer = ByteBuffer.wrap("enen".getBytes());
                socketChannel.write(byteBuffer);
                //socketChannel.configureBlocking(false);
                //socketChannel.register(selector, SelectionKey.OP_WRITE);
//            } else if (selectionKey.isWritable()) {
//                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//                ByteBuffer byteBuffer = ByteBuffer.wrap("enen".getBytes());
//                socketChannel.write(byteBuffer);
//                //socketChannel.configureBlocking(false);
//                //socketChannel.register(selector, SelectionKey.OP_READ);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        new NioService().serverStart();
    }
}
