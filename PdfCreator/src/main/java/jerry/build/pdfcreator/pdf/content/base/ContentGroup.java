package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import jerry.build.pdfcreator.bean.PageStyle;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.bean.PageHandle;

public class ContentGroup {
    private final List<ContentGroup> contents = new ArrayList<>();
    private final ContentStyle contentStyle;
    private PageHandle pageHandle;
    private ContentGroup parent;

    private boolean isMeasure = false;

    public ContentGroup(ContentStyle contentStyle) {
        this.contentStyle = contentStyle;
    }

    public ContentGroup(ContentStyle contentStyle,PageHandle pageHandle) {
        this.contentStyle = contentStyle;
        this.pageHandle = pageHandle;
        measureDefault(contentStyle);
    }

    public void setParent(ContentGroup parent) {
        this.parent = parent;
        measureDefault(getContentStyle());
    }

    protected void measureDefault(ContentStyle contentStyle) {
        boolean haveParent = parent != null;

        int widthMode = contentStyle.getWidthMode();
        int heightMode = contentStyle.getHeightMode();

        int measureWidth = 0;
        int measureHeight = 0;
        //测量实际宽度
        if (widthMode == ContentStyle.SELF && !isMeasure) {
            if (haveParent) {
                measureWidth = Math.max(getWidth() - (contentStyle.getMarginLeft() + contentStyle.getMarginRight() + parent.getContentStyle().getPaddingRight() + parent.getContentStyle().getPaddingLeft()), 0);
            } else {
                measureWidth = Math.max(getWidth() - (contentStyle.getMarginLeft() + contentStyle.getMarginRight()), 0);
            }
        } else if (widthMode == ContentStyle.WRAP_CONTENT) {
            measureWidth = measureChildrenInt()[0];
        } else if (widthMode == ContentStyle.MATCH_PARENT&& !isMeasure) {
            if (haveParent) {
                measureWidth = Math.max(parent.getWidth() - (contentStyle.getMarginLeft() + contentStyle.getMarginRight() + parent.getContentStyle().getPaddingRight() + parent.getContentStyle().getPaddingLeft()), 0);
            } else {
                measureWidth = Math.max(getWidth() - (contentStyle.getMarginLeft() + contentStyle.getMarginRight()), 0);
            }
        }


        //测量实际高度
        if (heightMode == ContentStyle.SELF&& !isMeasure) {
            if (haveParent) {
                measureHeight = Math.max(getHeight() - (contentStyle.getMarginTop() + contentStyle.getMarginBottom() + parent.getContentStyle().getPaddingLeft() + parent.getContentStyle().getPaddingRight()), 0);
            } else {
                measureHeight = Math.max(getHeight() - (contentStyle.getMarginTop() + contentStyle.getMarginBottom()), 0);
            }
        } else if (heightMode == ContentStyle.WRAP_CONTENT) {
            measureHeight = measureChildrenInt()[1];
        } else if (widthMode == ContentStyle.MATCH_PARENT&& !isMeasure) {
            if (haveParent) {
                measureHeight = Math.max(parent.getHeight() - (contentStyle.getMarginTop() + contentStyle.getMarginBottom() + parent.getContentStyle().getPaddingTop() + parent.getContentStyle().getPaddingBottom()), 0);
            } else {
                measureHeight = Math.max(getHeight() - contentStyle.getMarginTop() - contentStyle.getMarginBottom(), 0);
            }
        }

        measureChildrenUnit();
        setMeasureValue(measureWidth, measureHeight);
        measure(contentStyle);
        drawDefault(getCanvas());
    }

    private int[] measureChildrenInt() {
        int width = 0;
        int height = 0;

        for (ContentGroup content : contents) {
            content.measureDefault(content.getContentStyle());
            width = width + content.getWidth();
            height = height + content.getHeight();
        }
        return new int[]{width, height};
    }

    private void measureChildrenUnit(){
        for (ContentGroup content : contents) {
            content.measureDefault(content.getContentStyle());
        }
    }

    protected void drawDefault(Canvas canvas) {
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
            left = contentStyle.getMarginLeft() + parent.getContentStyle().getMarginLeft() + parent.getContentStyle().getPaddingLeft();
            top = contentStyle.getMarginTop() + parent.getContentStyle().getMarginTop() + parent.getContentStyle().getPaddingTop();
        } else {
            left = contentStyle.getMarginLeft();
            top = contentStyle.getMarginTop();
        }
        right = contentStyle.getWidth() + left;
        bottom = contentStyle.getHeight() + top;

        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, paint);
    }

    protected void measure(ContentStyle contentStyle) {
    }

    protected void draw(Canvas canvas) {
    }

    protected void setMeasureValue(int width, int height) {
        contentStyle.setWidth(width);
        contentStyle.setHeight(height);
        isMeasure = true;
    }

    public void addContent(ContentGroup content) {
        content.setParent(this);
        contents.add(content);
    }

    protected int getWidth() {
        return getContentStyle().getWidth();
    }

    protected int getHeight() {
        return getContentStyle().getHeight();
    }

    public ContentStyle getContentStyle() {
        return contentStyle;
    }

    public ContentGroup getParent() {
        return parent;
    }

    public PageHandle getRootPageHandle() {
        ContentGroup content = this;
        while (true){
            if (content.getParent() == null) {
                return content.getPageHandle();
            } else {
                content = content.getParent();
            }
        }
    }

    public PageHandle getPageHandle() {
        return pageHandle;
    }

    public Canvas getCanvas() {
        return getRootPageHandle().getCanvas();
    }

    public PageStyle getRootPageStyle() {
        return getRootPageHandle().getPageStyle();
    }

    public int getRootHeight() {
        return getRootPageHandle().getHeight();
    }

    public int getRootWidth() {
        return getRootPageHandle().getWidth();
    }
}
