package jerry.build.pdfcreator.pdf.content.base;

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
    public void measureDefault() {
        super.measureDefault();

    }

    @Override
    public void drawDefault() {
        super.drawDefault();
    }

    /**
     * 添加子Content
     */
    public void addContent(Content content){
        content.setParent(this);
        content.measureDefault();
        System.out.println("_________________________length:"+content.getParentMPTop());
        children.add(content);
    }
}
