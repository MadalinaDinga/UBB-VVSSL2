import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public final class TestUtils {
    private final static int ASCII_CODE_NEw_LINE = 10;

    public static void deleteFileContent(String filename){
        try {
            new PrintWriter(filename).close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLastRecord(String filename){
        try(RandomAccessFile f = new RandomAccessFile(filename, "rw")){
            long length = f.length() - 1;
            byte b;
            do {
                length -= 1;
                f.seek(length);
                b = f.readByte();
            } while(length>0 && b != ASCII_CODE_NEw_LINE);
            f.setLength(length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
