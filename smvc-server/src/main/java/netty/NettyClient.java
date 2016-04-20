package netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pei.xu on 2016/4/20.
 */
public class NettyClient extends SimpleChannelHandler {

    private BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

    public void nettyClientStart() {


        ExecutorService boss = Executors.newCachedThreadPool();

        ExecutorService worker = Executors.newCachedThreadPool();

        NioClientSocketChannelFactory nioClientSocketChannelFactory = new NioClientSocketChannelFactory(boss, worker, Runtime.getRuntime().availableProcessors() + 1);

        ClientBootstrap clientBootstrap = new ClientBootstrap(nioClientSocketChannelFactory);

        ChannelPipelineFactory channelPipelineFactory = new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new StringDecoder(), new StringEncoder(), NettyClient.this);
            }
        };

        clientBootstrap.setPipelineFactory(channelPipelineFactory);

        ChannelFuture channelFuture = clientBootstrap.connect(new InetSocketAddress("localhost", 8080));
        channelFuture.getChannel().getCloseFuture().awaitUninterruptibly();

        clientBootstrap.releaseExternalResources();
    }

    public static void main(String[] args) {
        new NettyClient().nettyClientStart();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        if (e.getMessage() instanceof String) {
            String message = (String) e.getMessage();

            System.out.println(message);

            System.out.println("\n等待客户端输入。。。");

            e.getChannel().write(sin.readLine());

        }

        super.messageReceived(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("已经与Server建立连接。。。。");
        System.out.println("\n请输入要发送的信息：");

        super.channelConnected(ctx, e);

        e.getChannel().write(sin.readLine());
    }
}
