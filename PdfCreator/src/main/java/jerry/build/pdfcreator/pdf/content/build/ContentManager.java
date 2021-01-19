package jerry.build.pdfcreator.pdf.content.build;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.impl.Row;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;

public class ContentManager {
    //最底层的视图
    private final ContentGroup rootContent;

    public ContentManager(PageHandle pageHandle) {
        ContentStyle contentStyle = new ContentStyle(pageHandle.getPageStyle().getWidth(), pageHandle.getPageStyle().getHeight());
        contentStyle.setHeightMode(ContentStyle.MATCH_PARENT);
        contentStyle.setWidthMode(ContentStyle.MATCH_PARENT);
        rootContent = new ContentGroup(contentStyle);
    }

    public ContentGroup getRootContent() {
        return  rootContent;
    }
}
