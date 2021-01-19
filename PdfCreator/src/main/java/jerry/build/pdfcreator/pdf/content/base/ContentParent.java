package jerry.build.pdfcreator.pdf.content.base;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;

public class ContentParent {
    private ContentStyle contentStyle;
    private PageHandle pageHandle;

    public ContentParent(ContentStyle contentStyle){
        this.contentStyle = contentStyle;
    }
}
