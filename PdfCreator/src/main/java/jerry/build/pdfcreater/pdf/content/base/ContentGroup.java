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
    private boolean isMeasure = false;

    public ContentGroup(ContentStyle contentStyle) {
        this.contentStyle = contentStyle;
    }

    public void setPageHandle(PageHandle pageHandle) {
        this.pageHandle = pageHandle;
        if (pageHandle != null && !isMeasure) {
            measureDefault(contentStyle);
        }
    }

    public void setParent(ContentGroup parent) {
        this.parent = parent;
        if (pageHandle != null && !isMeasure) {
            measureDefault(contentStyle);
        }
    }

    protected void measureDefault(ContentStyle contentStyle) {
        isMeasure = true;

        boolean haveParent = parent != null;

        int widthMode = contentStyle.getWidthMode();
        int heightMode = contentStyle.getHeightMode();

        int measureWidth = 0;
        int measureHeight = 0;
        //测量实际宽度
        if (widthMode == ContentStyle.SELF) {
            if (haveParent) {
                measureWidth = Math.max(getWidth() - (contentStyle.getMarginLeft() + contentStyle.getMarginRight() + parent.getContentStyle().getPaddingRight() + parent.getContentStyle().getPaddingLeft()), 0);
            } else {
                measureWidth = Math.max(getWidth() - (contentStyle.getMarginLeft() + contentStyle.getMarginRight()), 0);
            }
        } else if (widthMode == ContentStyle.WRAP_CONTENT) {
            measureWidth = measureChildren()[0];
        } else if (widthMode == ContentStyle.MATCH_PARENT) {
            if (haveParent) {
                measureWidth = Math.max(parent.getWidth() - (contentStyle.getMarginLeft() + contentStyle.getMarginRight() + parent.getContentStyle().getPaddingRight() + parent.getContentStyle().getPaddingLeft()), 0);
            } else {
                measureWidth = Math.max(pageHandle.getWidth() - (contentStyle.getMarginLeft() + contentStyle.getMarginRight()), 0);
            }
        }


        //测量实际高度
        if (heightMode == ContentStyle.SELF) {
            if (haveParent) {
                measureHeight = Math.max(getHeight() - (contentStyle.getMarginTop() + contentStyle.getMarginBottom() + parent.getContentStyle().getPaddingLeft() + parent.getContentStyle().getPaddingRight()), 0);
            } else {
                measureHeight = Math.max(getHeight() - (contentStyle.getMarginTop() + contentStyle.getMarginBottom()), 0);
            }
        } else if (heightMode == ContentStyle.WRAP_CONTENT) {
            measureHeight = measureChildren()[1];
        } else if (widthMode == ContentStyle.MATCH_PARENT) {
            if (haveParent) {
                measureHeight = parent.getHeight() - contentStyle.getMarginTop() - contentStyle.getMarginBottom();
            } else {
                measureHeight = pageHandle.getHeight() - contentStyle.getMarginTop() - contentStyle.getMarginBottom();
            }
        }

        setMeasureValue(measureWidth, measureHeight);
        measure(contentStyle);
        drawDefault();
    }

    private int[] measureChildren() {
        int width = 0;
        int height = 0;

        for (ContentGroup content : contents) {
            content.measureDefault(contentStyle);
            width = width + content.getWidth();
            height = height + content.getHeight();
        }
        return new int[]{width, height};
    }


    protected void drawDefault() {
        boolean haveParent = parent != null;

        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(contentStyle.getBackgroundColor());
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        if (haveParent) {
            left = contentStyle.getMarginLeft()+parent.getContentStyle().getMarginLeft()+parent.getContentStyle().getPaddingLeft();
            top = contentStyle.getMarginTop()+parent.getContentStyle().getMarginTop()+parent.getContentStyle().getPaddingTop();
            right = contentStyle.getWidth() + left;
            bottom = contentStyle.getHeight() + top;
        } else {

        }

        Rect rect = new Rect(left, top, right, bottom);
        pageHandle.getCanvas().drawRect(rect, paint);
    }

    protected void measure(ContentStyle contentStyle) {
    }

    protected void draw(Canvas canvas) {
    }

    public void addContent(ContentGroup content) {
        content.setParent(this);
        content.setPageHandle(pageHandle);
        contents.add(content);
    }

    protected void setMeasureValue(int width, int height) {
        contentStyle.setWidth(width);
        contentStyle.setHeight(height);
    }

    protected int getWidth() {
        return contentStyle.getWidth();
    }

    protected int getHeight() {
        return contentStyle.getHeight();
    }


    public ContentStyle getContentStyle() {
        return contentStyle;
    }

}
