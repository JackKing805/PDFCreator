package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;

public class ContentParent {
    private static final String TAG = "ContentParent";
    //每个content的基本属性
    private final ContentStyle contentStyle;

    private boolean isMeasure = false;

    public ContentParent(ContentStyle contentStyle) {
        this.contentStyle = (ContentStyle) contentStyle.clone();
    }

    /**
     * 测量自己真实宽高
     */
    public void measureDefault() { }

    /**
     * 画出默认视图
     *
     * @return
     */
    public void drawDefault(Canvas canvas) { }

    public ContentStyle getContentStyle() {
        return contentStyle;
    }

    public int getHeight() {
        return getContentStyle().getHeight();
    }

    public int getWidth() {
        return getContentStyle().getWidth();
    }

    public int getMarginTop() {
        return getContentStyle().getMarginTop();
    }

    public int getMarginLeft() {
        return getContentStyle().getMarginLeft();
    }

    public int getMarginRight() {
        return getContentStyle().getMarginRight();
    }

    public int getMarginBottom() {
        return getContentStyle().getMarginBottom();
    }

    public int getPaddingTop() {
        return getContentStyle().getPaddingTop();
    }

    public int getPaddingLeft() {
        return getContentStyle().getPaddingLeft();
    }

    public int getPaddingRight() {
        return getContentStyle().getPaddingRight();
    }

    public int getPaddingBottom() {
        return getContentStyle().getPaddingBottom();
    }

    public int getWidthMode(){
        return getContentStyle().getWidthMode();
    }

    public int getHeightMode(){
        return getContentStyle().getHeightMode();
    }

    protected void setMeasureStyle(int width,int height){
        if(isMeasure)return;
        isMeasure = true;
        getContentStyle().setHeight(height);
        getContentStyle().setWidth(width);
    }

    protected void setMeasureStyle(float width,float height){
        setMeasureStyle(Double.valueOf(Math.ceil(width)).intValue(),Double.valueOf(Math.ceil(height)).intValue());
    }

    /**
     * 设置mariginTop
     */
    public void setMarginTop(int marginTop) {
        Log.e(TAG, "setMarginTop: "+marginTop +",getContentStyle"+getContentStyle().hashCode());
        getContentStyle().setMarginTop(marginTop);
    }

    public void setMarginLeft(int marginLeft) {
        Log.e(TAG, "setMarginTop: "+marginLeft +",getContentStyle"+getContentStyle().hashCode());
        getContentStyle().setMarginLeft(marginLeft);
    }

    public Paint createPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        return paint;
    }
}
