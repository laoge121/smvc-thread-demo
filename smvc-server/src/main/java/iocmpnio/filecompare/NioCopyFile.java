package iocmpnio.filecompare;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by pei.xu on 2016/6/7.
 */
public class NioCopyFile {

    public static void main(String[] args) throws IOException {

        long s = System.currentTimeMillis();

        System.out.println(s);

        File file = new File("E:/*****.dat");

        FileInputStream fileInputStream = new FileInputStream(file);
        //RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        FileChannel fileChannel = fileInputStream.getChannel();
        byte[] b = new byte[fileInputStream.available()];
        //ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileInputStream.available());
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        file = new File("F:/****.dat");
        if (file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileOutChannel = fileOutputStream.getChannel();
        while (-1 != fileChannel.read(byteBuffer)) {
            byteBuffer.flip();
            fileOutChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        fileOutChannel.force(true);

        fileOutChannel.close();

        fileOutputStream.flush();

        fileOutputStream.close();
        fileChannel.close();
        fileInputStream.close();
        long e = System.currentTimeMillis();

        System.out.println(e);

        System.out.println("共用时：" + (e - s));

    }
}
