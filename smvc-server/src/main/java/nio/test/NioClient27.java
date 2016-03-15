package nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by pei.xu on 2015/10/27.
 */
public class NioClient27 {


    private Selector selector = null;

    public static void main(String[] args) throws IOException {
        new NioClient27().init();
    }

    public void init() throws IOException {

        selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8088));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_WRITE);

        while (true) {
            execute();
        }

    }


    public void execute() throws IOException {
        selector.select();
        Iterator<SelectionKey> it = selector.selectedKeys().iterator();
        while (it.hasNext()) {
            SelectionKey sk = it.next();
            it.remove();
            if ((sk.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE) {
                SocketChannel socketChannel = (SocketChannel) sk.channel();
                ByteBuffer byteBuffer = ByteBuffer.wrap("hellow".getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
                byteBuffer = null;
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if ((sk.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                SocketChannel socketChannel = (SocketChannel) sk.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                socketChannel.write(byteBuffer);
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), "utf-8"));
                byteBuffer = null;
            }
        }
    }


}
