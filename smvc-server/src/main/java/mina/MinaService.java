package mina;

import org.apache.mina.api.AbstractIoHandler;
import org.apache.mina.api.IdleStatus;
import org.apache.mina.api.IoService;
import org.apache.mina.api.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.nio.NioTcpServer;
import org.apache.mina.transport.nio.NioTcpSession;

import java.nio.ByteBuffer;

/**
 * mina 服务端
 * User: pei.xu
 * Date: 15-4-13
 * Time: 下午3:46
 */
public class MinaService {

    public static void main(String[] args) {

        NioTcpServer nioTcpServer = new NioTcpServer();

        nioTcpServer.setFilters(new LoggingFilter("测试Server"));

        nioTcpServer.setIoHandler(new PersonIOHandler());

        //绑定端口
        nioTcpServer.bind(8083);
        while (true) {
        }
    }

    static class PersonIOHandler extends AbstractIoHandler {
        @Override
        public void messageReceived(IoSession session, Object message) {
            if (message instanceof ByteBuffer) {
                ByteBuffer b = (ByteBuffer) message;
                System.out.println("messageReceived:" + new String(b.array()));
            }
            System.out.println("messageReceived:" + message);
            //收到消息后处理消息并且发送消息
            ByteBuffer byteBuffer = ByteBuffer.wrap("ok".getBytes());
            session.write(byteBuffer);
        }

        @Override
        public void messageSent(IoSession session, Object message) {
            System.out.println("messageSent:" + message);
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
