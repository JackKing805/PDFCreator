package jerry.build.pdfcreator.pdf.content.base;

import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;

public class ContentParent {
    //每个content的基本属性
    private final ContentStyle contentStyle;

    public ContentParent(ContentStyle contentStyle) {
        this.contentStyle = contentStyle;
    }


    /**
     * 测量自己真实宽高
     */
    public void measureDefault() {
    }

    /**
     * 画出默认视图
     *
     * @return
     */
    public void drawDefault() {
    }

    public ContentStyle getContentStyle() {
        return contentStyle;
    }

    public int getHeight() {
        return contentStyle.getHeight();
    }

    public int getWidth() {
        return contentStyle.getWidth();
    }

    public int getMarginTop() {
        return contentStyle.getMarginTop();
    }

    public int getMarginLeft() {
        return contentStyle.getMarginLeft();
    }

    public int getMarginRight() {
        return contentStyle.getMarginRight();
    }

    public int getMarginBottom() {
        return contentStyle.getMarginBottom();
    }

    public int getPaddingTop() {
        return contentStyle.getPaddingTop();
    }

    public int getPaddingLeft() {
        return contentStyle.getPaddingLeft();
    }

    public int getPaddingRight() {
        return contentStyle.getPaddingRight();
    }

    public int getPaddingBottom() {
        return contentStyle.getPaddingBottom();
    }

    public void setMeasureStyle(int width,int height){
        contentStyle.setHeight(height);
        contentStyle.setWidth(width);
    }
}
