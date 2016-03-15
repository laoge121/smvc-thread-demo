package nio;

import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by pei.xu on 2015/10/27.
 */
public class NioService27 {

    private ServerSocketChannel serverSocketChannel = null;

    private Selector selector = null;

    public static void main(String[] args) throws IOException {
        new NioService27().init();
    }


    public void init() throws IOException {

        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8088));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            this.execute();
        }

    }

    public void execute() throws IOException {
        selector.select();
        Iterator<SelectionKey> it = selector.selectedKeys().iterator();
        while (it.hasNext()) {

            SelectionKey sk = it.next();
            it.remove();
            if ((sk.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) sk.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (null == socketChannel)
                    return;

                if (socketChannel.isConnected()) {
                    socketChannel.finishConnect();
                }

                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if ((sk.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                SocketChannel socketChannel = (SocketChannel) sk.channel();
                ByteBuffer b = ByteBuffer.allocate(1024);
                socketChannel.read(b);
                b.flip();
                System.out.println(">>>>>>>结束客户端数据>>>>>>>>>" + new String(b.array(), "utf-8"));
                b.clear();
                b = null;
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            } else if ((sk.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE) {
                SocketChannel socketChannel = (SocketChannel) sk.channel();
                ByteBuffer b = ByteBuffer.wrap("nihao".getBytes());
                b.flip();
                socketChannel.write(b);
                System.out.println(">>>>>>>结束客户端数据>>>>>>>>>" + new String(b.array(), "utf-8"));
                b.clear();
                b = null;
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            }
        }
    }

}
