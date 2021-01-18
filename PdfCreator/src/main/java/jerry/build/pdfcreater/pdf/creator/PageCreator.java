package jerry.build.pdfcreater.pdf.creator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jerry.build.pdfcreater.bean.PageHandle;
import jerry.build.pdfcreater.bean.PageStyle;
import jerry.build.pdfcreater.utils.FileUtils;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PageCreator {
    private PageStyle pageStyle;
    private PdfDocument document;
    private PdfDocument.Page page;

    public PageCreator(PageStyle pageStyle) {
        this.pageStyle = pageStyle;
    }

    public PageHandle createPage(){
        document = new PdfDocument();
        PdfDocument.PageInfo pageInfo =  new PdfDocument.PageInfo.Builder(pageStyle.getWidth(),pageStyle.getHeight(), pageStyle.getPageSum()).create();
        page = document.startPage(pageInfo);
        return new PageHandle(pageStyle,page.getCanvas());
    }

    public File savePage(){
        document.finishPage(page);
        File file = FileUtils.createFile(pageStyle.getSavePath());
        try {
            document.writeTo(new FileOutputStream(file));
            document.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
