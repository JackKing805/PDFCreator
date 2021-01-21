package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;

public class Content extends ContentParent {
    private static final String TAG = "Content";
    //当前content的父节点
    private Content parent;

    public Content(ContentStyle contentStyle) {
        super(contentStyle);
    }


    @Override
    public void measureDefault() {
        Log.e(TAG, "measureDefault: "+toString() );
        int widthMode = getWidthMode();
        int heightMode = getHeightMode();

        int measureWidth = 0;
        int measureHeight = 0;

        if (widthMode == ContentStyle.MATCH_PARENT) {
            measureWidth = getParentWidth() - getMarginLeft() - getMarginRight() - getParent().getPaddingLeft() - getParent().getPaddingRight();
        } else if (widthMode == ContentStyle.WIGHT) {
            measureWidth = calculatingWeight(getParentWidth() - getMarginLeft() - getMarginRight() - getParent().getPaddingLeft() - getParent().getPaddingRight(), getCompanion(), 1);
        } else if (widthMode == ContentStyle.WRAP_CONTENT) {
            measureWidth = getWidth();
        } else if (widthMode == ContentStyle.SELF) {
            if (getWidth() + getMarginLeft() + getParent().getPaddingLeft() > getParentWidth() - getMarginRight() - getParent().getPaddingRight()) {
                measureWidth = getParentWidth() - getMarginLeft() - getMarginRight() - getParent().getPaddingLeft() - getParent().getPaddingRight();
            } else {
                measureWidth = getWidth()-getMarginLeft();
            }
        }

        if (heightMode == ContentStyle.MATCH_PARENT) {
            measureHeight = getParentHeight() - getMarginTop() - getMarginBottom() - getParent().getPaddingTop() - getParent().getPaddingBottom();
        } else if (heightMode == ContentStyle.WIGHT) {
            measureHeight = calculatingWeight(getParentHeight() - getMarginTop() - getMarginBottom() - getParent().getPaddingTop() - getParent().getPaddingBottom(), getCompanion(), 2);
        } else if (heightMode == ContentStyle.WRAP_CONTENT) {
            measureHeight = getHeight();
        } else if (heightMode == ContentStyle.SELF) {
            if (getHeight() + getMarginTop() + getParent().getPaddingTop() > getParentHeight() - getMarginBottom() - getParent().getPaddingBottom()) {
                measureHeight = getParentHeight() - getMarginTop() - getMarginBottom() - getParent().getPaddingTop() - getParent().getPaddingBottom();
            } else {
                measureHeight = getHeight()-getMarginTop();
            }
        }

        measure(widthMode, heightMode, measureHeight, measureWidth);
        setMeasureStyle(measureWidth, measureHeight);
    }


    protected int calculatingWeight(int totalWidth, List<Content> companion, int type) {
        if (totalWidth == 0) {
            return 0;
        }
        if (companion == null) {
            return totalWidth;
        }

        if (type == 1) {
            int totalWight = getContentStyle().getWeight();
            //width
            for (Content child : companion) {
                if (child.getWidthMode() == ContentStyle.WIGHT && child.getWidth() == 0) {
                    totalWight = totalWight + child.getContentStyle().getWeight();
                } else {
                    totalWidth = totalWidth - child.getWidth();
                }
            }
            if (totalWight == 0) {
                return 0;
            }

            float percentWidth = totalWidth / totalWight;
            return Double.valueOf(Math.ceil(getContentStyle().getWeight() * percentWidth)).intValue();
        } else if (type == 2) {
            //height
            int totalWight = getContentStyle().getWeight();
            //width
            for (Content child : companion) {
                if (child.getHeightMode() == ContentStyle.WIGHT && child.getHeight() == 0) {
                    totalWight = child.getContentStyle().getWeight();
                } else {
                    totalWidth = totalWidth - child.getHeight();
                }
            }
            if (totalWight == 0) {
                return 0;
            }

            float percentWHeight = totalWidth / totalWight;
            return Double.valueOf(Math.ceil(getContentStyle().getWeight() * percentWHeight)).intValue();
        } else {
            return 0;
        }
    }


    @Override
    public void drawDefault(Canvas canvas) {
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(getContentStyle().getBackgroundColor());
        int left = getParentMPLeft() + getMarginLeft();
        int top = getParentMPTop() + getMarginTop();

        canvas.save();
        canvas.translate(left, top);

        int right = getWidth();
        int bottom = getHeight();

        Rect rect = new Rect(0, 0, right, bottom);
        canvas.clipRect(rect);
        canvas.drawRect(rect, paint);
        draw(canvas);
        canvas.restore();
    }


    /**
     * 提供给子类自己测量的方法
     */
    protected void measure(int widthMode, int heightMode, int height, int width) {

    }

    /**
     * 提供给子类自己绘制的方法
     */
    protected void draw(Canvas canvas) {
    }

    /**
     * 判断是否是根节点
     *
     * @return true 是   ,false 不是
     */
    protected boolean isRootContent() {
        return parent == null;
    }

    /**
     * 拿到父节点
     */
    protected Content getParent() {
        if (isRootContent()) {
            return this;
        } else {
            return parent;
        }
    }

    /**
     * 获得父节点左边margin和padding
     */
    protected int getParentMPLeft() {
        if (isRootContent()) {
            return 0;
        } else {
            int length = 0;
            Content parent = getParent();
            while (!parent.isRootContent()) {
                length = length + parent.getContentStyle().getMarginLeft() + parent.getContentStyle().getPaddingLeft();
                parent = parent.getParent();
            }
            return length;
        }
    }

    /**
     * 获得所有父节点右边margin和padding
     */
    protected int getParentMPRight() {
        if (isRootContent()) {
            return 0;
        } else {
            int length = 0;
            Content parent = getParent();
            while (!parent.isRootContent()) {
                length = length + parent.getContentStyle().getMarginRight() + parent.getContentStyle().getPaddingRight();
                parent = parent.getParent();
            }
            return length;
        }
    }

    /**
     * 获得所有父节点顶部margin和padding
     */
    protected int getParentMPTop() {
        if (isRootContent()) {
            return 0;
        } else {
            int length = 0;
            Content parent = getParent();
            while (!parent.isRootContent()) {
                length = length + parent.getContentStyle().getMarginTop() + parent.getContentStyle().getPaddingTop();
                parent = parent.getParent();
            }
            return length;
        }
    }

    /**
     * 获得所有父节点底部margin和padding
     */
    protected int getParentMPBottom() {
        if (isRootContent()) {
            return 0;
        } else {
            int length = 0;
            Content parent = getParent();
            while (!parent.isRootContent()) {
                length = length + parent.getContentStyle().getMarginBottom() + parent.getContentStyle().getPaddingBottom();
                parent = parent.getParent();
            }
            return length;
        }
    }

    /**
     * 拿到父控件的宽度
     *
     * @return
     */
    protected int getParentWidth() {
        if (isRootContent()) {
            return getWidth();
        } else {
            return getParent().getWidth();
        }
    }

    /**
     * 拿到父控件的高度
     *
     * @return
     */
    protected int getParentHeight() {
        if (isRootContent()) {
            return getHeight();
        } else {
            return getParent().getHeight();
        }
    }

    /**
     * 为当前节点设置父节点
     */
    protected void setParent(Content parent) {
        this.parent = parent;
    }


    /**
     * 拿到同一级的其它控件
     *
     * @return
     */
    public List<Content> getCompanion() {
        if (getGroupParent() != null) {
            List<Content> children = getGroupParent().getChildren();
            List<Content> companion = new ArrayList<>(children);
            Iterator<Content> contentListIterator = companion.iterator();
            while (contentListIterator.hasNext()) {
                Content content = contentListIterator.next();
                if (content == this) {
                    contentListIterator.remove();
                }
            }
            return companion;
        }
        return null;
    }

    protected ContentGroup getGroupParent() {
        if (isRootContent()) {
            return null;
        } else {
            try {
                return (ContentGroup) getParent();
            } catch (Exception e) {
                return null;
            }
        }
    }
}
