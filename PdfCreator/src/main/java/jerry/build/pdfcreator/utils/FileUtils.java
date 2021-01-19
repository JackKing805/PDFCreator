package jerry.build.pdfcreator.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static File createFile(String path){
        File file = new File(path);
        createFile(file);
        if (file.isDirectory()) {
            file.mkdir();
            return file;
        } else {
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static void createFile(File file) {
        if (!file.exists()) {
            File parent = file.getParentFile();
            createFile(parent.getParentFile());
            if (!parent.exists()) {
                parent.mkdir();
            }
        }
    }
}




