package netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pei.xu on 2016/4/20.
 */
public class NettyServer extends SimpleChannelHandler {

    public void nettyStart() {

        ExecutorService bossExecutor = Executors.newCachedThreadPool();

        ExecutorService workExecutor = Executors.newCachedThreadPool();

        NioServerSocketChannelFactory nioServerSocketChannelFactory = new NioServerSocketChannelFactory(bossExecutor, workExecutor, Runtime.getRuntime().availableProcessors() + 1);

        ServerBootstrap serverBootstrap = new ServerBootstrap(nioServerSocketChannelFactory);

        ChannelPipelineFactory channelPipelineFactory = new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new StringDecoder(), new StringEncoder(), NettyServer.this);
            }
        };

        serverBootstrap.setPipelineFactory(channelPipelineFactory);
        serverBootstrap.bind(new InetSocketAddress(8080));

    }

    public static void main(String[] args) {
        new NettyServer().nettyStart();
    }


    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        if (e.getMessage() instanceof String) {
            String message = (String) e.getMessage();
            System.out.println("Client发来:" + message);

            e.getChannel().write("Server已收到刚发送的:" + message);

            System.out.println("\n等待客户端输入。。。");
        }

        super.messageReceived(ctx, e);
    }


    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("有一个客户端注册上来了。。。");
        System.out.println("Client:" + e.getChannel().getRemoteAddress());
        System.out.println("Server:" + e.getChannel().getLocalAddress());
        System.out.println("\n等待客户端输入。。。");
        super.channelConnected(ctx, e);
    }
}
