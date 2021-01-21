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
    protected void measure(int widthMode, int heightMode, int height, int width) {
        super.measure(widthMode, heightMode, height, width);

        int measureWidth = width;
        int measureHeight = height;

        if (widthMode == ContentStyle.WRAP_CONTENT) {
            measureWidth = measureChildren()[0];
        }

        if (heightMode == ContentStyle.WRAP_CONTENT) {
            measureHeight = measureChildren()[1];
        }

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
