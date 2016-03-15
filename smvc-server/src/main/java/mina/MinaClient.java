package mina;

import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IoFuture;
import org.apache.mina.api.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.nio.NioTcpClient;
import org.apache.mina.transport.nio.NioTcpSession;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;

/**
 * 客户端
 * User: pei.xu
 * Date: 15-4-13
 * Time: 下午3:55
 */
public class MinaClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        NioTcpClient nioTcpClient = new NioTcpClient();
        nioTcpClient.setFilters(new LoggingFilter("测试client"));
        nioTcpClient.setIoHandler(new PersonIOHandler());

        IoFuture ioFuture = nioTcpClient.connect(new InetSocketAddress(8083));
        IoSession ioSession = (IoSession) ioFuture.get();
        ByteBuffer bf = ByteBuffer.wrap("nihao".getBytes());
        ioSession.write(bf);
        while (true){}
    }

    static class PersonIOHandler extends AbstractIoHandler {
        @Override
        public void messageReceived(IoSession session, Object message) {
            if (message instanceof ByteBuffer) {
                ByteBuffer b = (ByteBuffer) message;
                System.out.println("messageReceived:" + new String(b.array()));
            }
            System.out.println("messageReceived:" + message);
            session.close(true);
        }

        @Override
        public void messageSent(IoSession session, Object message) {
            System.out.println("messageSent:" + message);
            if (message instanceof ByteBuffer) {
                ByteBuffer b = (ByteBuffer) message;
                System.out.println("message:" + new String(b.array()));
            }
            NioTcpSession nioTcpSession = (NioTcpSession) session;
          //  nioTcpSession.ready(false, false, true, null, false);
        }

        @Override
        public void sessionOpened(IoSession session) {
            System.out.println("sessionOpened:" + session);
        }

        @Override
        public void sessionClosed(IoSession session) {
            System.out.println("sessionClosed:" + session);
        }
    }
}
