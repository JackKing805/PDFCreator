package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;

public class ContentGroup extends Content{
    //包含的子Content
    private final List<Content> children;


    public ContentGroup(ContentStyle contentStyle) {
        super(contentStyle);
        children = new ArrayList<>();
    }


    @Override
    protected void measureDefault() {
        super.measureDefault();

    }

    @Override
    protected void drawDefault(Canvas canvas) {
        super.drawDefault(canvas);
    }

    /**
     * 添加子Content
     */
    public void addContent(Content content){
        content.setParent(this);
        content.measureDefault();
        children.add(content);
    }
}
