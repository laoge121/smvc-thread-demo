package iocmpnio.filecompare;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by pei.xu on 2016/6/7.
 */
public class IoCopyFile {
    public static void main(String[] args) throws IOException {

        long s = System.currentTimeMillis();

        System.out.println(s);

        File file = new File("E:/****.dat");

        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] b = new byte[1024];

        File file12 = new File("F:/***.dat");
        if (file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file12);
        while (-1 != fileInputStream.read(b)) {
            fileOutputStream.write(b);
        }
        fileOutputStream.flush();

        fileOutputStream.close();
        fileInputStream.close();
        long e = System.currentTimeMillis();

        System.out.println(e);

        System.out.println("共用时：" + (e - s));

    }
}
