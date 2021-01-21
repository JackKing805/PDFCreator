package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;
import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;
import jerry.build.pdfcreator.pdf.content.impl.Row;

public class ContentGroup extends Content {
    private static final String TAG = "ContentGroup";
    //包含的子Content
    private final List<Content> children;


    public static final int vertical = 0x01;
    public static final int horizontal = 0x02;

    @IntDef({vertical, horizontal})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @interface SpaceOrientation {
    }

    public ContentGroup(ContentStyle contentStyle) {
        super(contentStyle);
        children = new ArrayList<>();
    }

    @Override
    public void measureDefault() {
        Log.e(TAG, "measureDefault: " + toString());
        int widthMode = getWidthMode();
        int heightMode = getHeightMode();

        int measureWidth = 0;
        int measureHeight = 0;

        if (widthMode == ContentStyle.MATCH_PARENT) {
            measureWidth = getParentWidth() - getMarginLeft() - getMarginRight() - getParent().getPaddingLeft() - getParent().getPaddingRight();
        } else if (widthMode == ContentStyle.WIGHT) {
            measureWidth = calculatingWeight(getParentWidth() - getMarginLeft() - getMarginRight() - getParent().getPaddingLeft() - getParent().getPaddingRight(), getCompanion(), 1);
        } else if (widthMode == ContentStyle.WRAP_CONTENT) {
            measureWidth = measureChildren()[0];
        } else if (widthMode == ContentStyle.SELF) {
            if (getWidth() + getMarginLeft() + getParent().getPaddingLeft() > getParentWidth() - getMarginRight() - getParent().getPaddingRight()) {
                measureWidth = getParentWidth() - getMarginLeft() - getMarginRight() - getParent().getPaddingLeft() - getParent().getPaddingRight();
            } else {
                measureWidth = getWidth();
            }
        }

        if (heightMode == ContentStyle.MATCH_PARENT) {
            measureHeight = getParentHeight() - getMarginTop() - getMarginBottom() - getParent().getPaddingTop() - getParent().getPaddingBottom();
        } else if (heightMode == ContentStyle.WIGHT) {
            measureHeight = calculatingWeight(getParentHeight() - getMarginTop() - getMarginBottom() - getParent().getPaddingTop() - getParent().getPaddingBottom(), getCompanion(), 2);
        } else if (heightMode == ContentStyle.WRAP_CONTENT) {
            measureHeight = measureChildren()[1];
        } else if (heightMode == ContentStyle.SELF) {
            if (getHeight() + getMarginTop() + getParent().getPaddingTop() > getParentHeight() - getMarginBottom() - getParent().getPaddingBottom()) {
                measureHeight = getParentHeight() - getMarginTop() - getMarginBottom() - getParent().getPaddingTop() - getParent().getPaddingBottom();
            } else {
                measureHeight = getHeight();
            }
        }
        measure(widthMode, heightMode, getWidth(), getHeight());
        setMeasureStyle(measureWidth, measureHeight);
    }


    public void layout() {
    }

    protected int[] measureChildren() {
        return new int[]{0, 0};
    }

    /**
     * 添加子Content
     */
    public void addContent(Content content) {
        content.setParent(this);
        children.add(content);
    }

    public void addSpace(@SpaceOrientation int spaceOrientation, int spaceDistance) {
        if (spaceOrientation == horizontal) {
            children.add(new Row(new RowStyle.Builder()
                    .setHeightMode(ContentStyle.MATCH_PARENT)
                    .setWidth(spaceDistance)
                    .setBackgroundColor(Color.TRANSPARENT)
                    .create()));
        } else {
            children.add(new Row(new RowStyle.Builder()
                    .setWidthMode(ContentStyle.MATCH_PARENT)
                    .setHeight(spaceDistance)
                    .setBackgroundColor(Color.TRANSPARENT)
                    .create()));
        }
    }

    /**
     * 判断是否有子content
     */
    public boolean haveChild() {
        return !children.isEmpty();
    }


    public List<Content> getChildren() {
        return children;
    }
}
