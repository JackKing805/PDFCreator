package jerry.build.pdfcreater.pdf.content.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import jerry.build.pdfcreater.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreater.bean.PageHandle;

public class ContentGroup {
    private List<ContentGroup> contents = new ArrayList<>();
    private PageHandle pageHandle;
    private ContentGroup parent;
    private ContentStyle contentStyle;

    public ContentGroup(ContentStyle contentStyle) {
        this.contentStyle = contentStyle;
    }

    public ContentGroup(ContentStyle contentStyle, PageHandle pageHandle) {
        this.contentStyle = contentStyle;
        this.pageHandle = pageHandle;
        if(parent==null){
            measureDefault(contentStyle);
        }
    }

    private void setPageHandle(PageHandle pageHandle) {
        this.pageHandle = pageHandle;
    }

    private void setParent(ContentGroup parent){
        this.parent = parent;
    }

    protected void measureDefault(ContentStyle contentStyle){
        int widthMode = contentStyle.getWidthMode();
        int heightMode = contentStyle.getHeightMode();

        int width = 0;
        int height = 0;
        if(parent != null){
            width = parent.getWidth();
        }else{
            width = pageHandle.getPageStyle().getWidth();
        }

        if(parent != null){
            height = parent.getHeight();
        }else{
            height = pageHandle.getPageStyle().getHeight();
        }


        int measureWidth = 0;
        int measureHeight = 0;
        //测量实际宽度
        if(widthMode == ContentStyle.SELF){
            if(parent != null){
                if(contentStyle.getMarginLeft()
                        +contentStyle.getMarginRight()>width){
                    measureWidth = 0;
                }else{
                    measureWidth = width
                            -contentStyle.getMarginRight()
                            -contentStyle.getMarginLeft();
                }
            }else{
                if(contentStyle.getMarginLeft()
                        +contentStyle.getMarginRight()
                        +parent.getContentStyle().getPaddingLeft()
                        +parent.getContentStyle().getPaddingRight()>width){
                    measureWidth = 0;
                }else{
                    measureWidth = width
                            -contentStyle.getMarginRight()
                            -contentStyle.getMarginLeft()
                            -parent.getContentStyle().getPaddingLeft()
                            -parent.getContentStyle().getPaddingRight();
                }
            }

        }else if(widthMode == ContentStyle.WRAP_CONTENT){
            measureWidth = measureChildrenW();
        }else if(widthMode == ContentStyle.MATCH_PARENT){
            if(parent == null){
                if(contentStyle.getMarginLeft()
                        +contentStyle.getMarginRight()>width){
                    measureWidth = 0;
                }else{
                    measureWidth = width
                            -contentStyle.getMarginRight()
                            -contentStyle.getMarginLeft();
                }
            }else{
                if(contentStyle.getMarginLeft()
                        +contentStyle.getMarginRight()
                        +parent.getContentStyle().getPaddingLeft()
                        +parent.getContentStyle().getPaddingRight()>width){
                    measureWidth = 0;
                }else{
                    measureWidth = width
                            -contentStyle.getMarginRight()
                            -contentStyle.getMarginLeft()
                            -parent.getContentStyle().getPaddingLeft()
                            -parent.getContentStyle().getPaddingRight();
                }
            }
        }


        //测量实际高度
        if(heightMode == ContentStyle.SELF){
            measureHeight = height- contentStyle.getMarginTop()- contentStyle.getMarginBottom();
        }else if(heightMode == ContentStyle.WRAP_CONTENT){
            measureHeight = measureChildrenH();
        }else if(widthMode == ContentStyle.MATCH_PARENT){
            measureHeight = height- contentStyle.getMarginTop()- contentStyle.getMarginBottom();
        }

        setMeasureValue(measureWidth,measureHeight);
        measure(contentStyle);
        drawDefault();
        drawChildren();
    }

    private void drawChildren(){
        for (ContentGroup content:contents) {
            content.drawDefault();
        }
    }

    private int measureChildrenW(){
        int width = 0;
        for (ContentGroup content:contents) {
            content.measureDefault(contentStyle);
            width = width + content.getWidth();
        }
        return width;
    }

    private int measureChildrenH(){
        int height = 0;
        for (ContentGroup content:contents) {
            content.measureDefault(contentStyle);
            height = height + content.getHeight();
        }
        return height;
    }

    protected void drawDefault(){
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(contentStyle.getBackgroundColor());
        int left = contentStyle.getMarginLeft();
        int top = contentStyle.getMarginTop();
        int right = contentStyle.getWidth()+left;
        int bottom = contentStyle.getHeight()+top;

        Rect rect = new Rect(left, top, right, bottom);
        pageHandle.getCanvas().drawRect(rect,paint);
    }

    protected void measure(ContentStyle contentStyle){}
    protected void draw(Canvas canvas){}

    public void addContent(ContentGroup content){
        content.setParent(parent);
        content.setPageHandle(pageHandle);
        content.measureDefault(content.contentStyle);
        contents.add(content);
        measureDefault(contentStyle);
    }

    protected void setMeasureValue(int width, int height){
        contentStyle.setWidth(width);
        contentStyle.setHeight(height);
    }

    protected int getWidth(){
        return contentStyle.getWidth();
    }

    protected int getHeight(){
        return contentStyle.getHeight();
    }


    public ContentStyle getContentStyle(){
        return contentStyle;
    }

}
