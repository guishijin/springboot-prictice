package apache.math.fft;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by gsj on 2018/3/11.
 */

public class FileUtil {

    public static File CreateFile(String dirname, String filename) {
        File parent_path = new File("D://logs");

        // 可以建立一个子目录专门存放自己专属文件
        File dir = new File(parent_path.getAbsoluteFile(), dirname);
        dir.mkdir();

        File file = new File(dir.getAbsoluteFile(), filename);

        System.out.println("文件路径"+file.getAbsolutePath());

        // 创建这个文件，如果不存在
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
