package jerry.build.pdfcreater.listener;

import java.io.File;

public interface CreateListener {
    void createStart();

    void createSuccess(File file);

    void createError(Exception e);
}
