package jerry.build.pdfcreator.pdf.content.build;

import android.graphics.Color;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.base.Content;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.impl.Row;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;

public class ContentManager {
    //最底层的视图
    private final Content rootContent;

    public ContentManager() {
        ContentStyle contentStyle = new ContentStyle(PageHandleHolder.newInstance().getPageStyle().getWidth(), PageHandleHolder.newInstance().getPageStyle().getHeight());
        contentStyle.setHeightMode(ContentStyle.MATCH_PARENT);
        contentStyle.setWidthMode(ContentStyle.MATCH_PARENT);
        contentStyle.setBackgroundColor(Color.parseColor("#ede18d"));
        rootContent = new ContentGroup(contentStyle);
        rootContent.measureDefault();
    }

    public ContentGroup getRootContent() {
        return  rootContent;
    }
}
