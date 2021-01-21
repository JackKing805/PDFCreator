package jerry.build.pdfcreator.pdf.creator;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.bean.PageStyle;
import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;
import jerry.build.pdfcreator.utils.FileUtils;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PageCreator {
    private PageStyle pageStyle;
    private PdfDocument document;
    private PdfDocument.Page page;

    public PageCreator(PageStyle pageStyle) {
        this.pageStyle = pageStyle;
    }

    public void startCreate(Context context){
        document = new PdfDocument();
        PdfDocument.PageInfo pageInfo =  new PdfDocument.PageInfo.Builder(pageStyle.getWidth(),pageStyle.getHeight(), pageStyle.getPageSum()).create();
        page = document.startPage(pageInfo);
        PageHandleHolder.newInstance().setPageHandle(new PageHandle(pageStyle,page.getCanvas(),context));
    }

    public PdfDocument.Page createNewPage() {
        PdfDocument.PageInfo pageInfo =  new PdfDocument.PageInfo.Builder(pageStyle.getWidth(),pageStyle.getHeight(), pageStyle.getPageSum()).create();
        return document.startPage(pageInfo);
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
