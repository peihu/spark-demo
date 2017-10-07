import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileTest {
    @Test
    public void testPrint() throws IOException {
        File   dir   = new File("D:\\tmp\\sf\\td\\file");
        File[] files = dir.listFiles();
        assert files != null;
        FileOutputStream output = new FileOutputStream(new File("d:\\tmp\\output"));
        for (File file : files) {
            FileInputStream inputStream = new FileInputStream(file);
            byte[]          b           = new byte[2048];
            while (b.length != 0) {
                inputStream.read(b);
                output.write(inputStream.read(b));
            }
            inputStream.close();
        }
        output.close();
    }
}
