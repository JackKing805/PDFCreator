package jerry.build.pdfcreater.pdf.content.base;

import jerry.build.pdfcreater.bean.PageHandle;
import jerry.build.pdfcreater.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreater.pdf.content.impl.Row;
import jerry.build.pdfcreater.pdf.content.bean.RowStyle;

public class ContentManager {
    private PageHandle pageHandle;
    //最底层的视图
    private final ContentGroup contentGroup;

    public ContentManager(PageHandle pageHandle) {
        this.pageHandle = pageHandle;
        ContentStyle contentStyle = new ContentStyle(pageHandle.getPageStyle().getWidth(), pageHandle.getPageStyle().getHeight());
        contentStyle.setHeightMode(ContentStyle.MATCH_PARENT);
        contentStyle.setWidthMode(ContentStyle.MATCH_PARENT);
        contentGroup = new ContentGroup(contentStyle);
        contentGroup.setPageHandle(pageHandle);
    }

    public Row addRow(RowStyle rowStyle) {
        Row row = new Row(rowStyle);
        contentGroup.addContent(row);
        return row;
    }

    public void addContent(ContentGroup content){
        contentGroup.addContent(content);
    }
}
