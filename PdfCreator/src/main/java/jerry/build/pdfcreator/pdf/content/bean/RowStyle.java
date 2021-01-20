package jerry.build.pdfcreator.pdf.content.bean;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RowStyle extends ContentStyle {
    private int borderWidth = 0;
    private boolean haveVerticalBorder = false;
    private boolean haveHorizontalBorder = false;
    @Orientation
    private int orientation = vertical;

    public static final int vertical = 0x01;
    public static final int horizontal = 0x02;

    @IntDef({vertical, horizontal})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @interface Orientation {
    }


    public static class Builder {
        RowStyle contentStyle = new RowStyle();


        public Builder setWidthMode(int widthMode) {
            contentStyle.setWidthMode(widthMode);
            return this;
        }

        public Builder setHeightMode(int heightMode) {
            contentStyle.setHeightMode(heightMode);
            return this;
        }

        public Builder setWidth(int width) {
            contentStyle.setWidth(width);
            return this;
        }

        public Builder setHeight(int height) {
            contentStyle.setHeight(height);
            return this;
        }

        public Builder setMarginTop(int marginTop) {
            contentStyle.setMarginTop(marginTop);
            return this;
        }

        public Builder setMarginLeft(int marginLeft) {
            contentStyle.setMarginLeft(marginLeft);
            return this;
        }

        public Builder setMarginRight(int marginRight) {
            contentStyle.setMarginRight(marginRight);
            return this;
        }

        public Builder setMarginBottom(int marginBottom) {
            contentStyle.setMarginBottom(marginBottom);
            return this;
        }

        public Builder setPaddingTop(int paddingTop) {
            contentStyle.setPaddingTop(paddingTop);
            return this;
        }

        public Builder setPaddingLeft(int paddingLeft) {
            contentStyle.setPaddingLeft(paddingLeft);
            return this;
        }

        public Builder setPaddingRight(int paddingRight) {
            contentStyle.setPaddingRight(paddingRight);
            return this;
        }

        public Builder setPaddingBottom(int paddingBottom) {
            contentStyle.setPaddingBottom(paddingBottom);
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            contentStyle.setBackgroundColor(backgroundColor);
            return this;
        }

        public Builder setBorderWidth(int borderWidth) {
            contentStyle.setBorderWidth(borderWidth);
            return this;
        }

        public Builder setHaveVerticalBorder(boolean haveVerticalBorder) {
            contentStyle.setHaveVerticalBorder(haveVerticalBorder);
            return this;
        }

        public Builder setHaveHorizontalBorder(boolean haveHorizontalBorder) {
            contentStyle.setHaveHorizontalBorder(haveHorizontalBorder);
            return this;
        }

        public Builder setOrientation(int orientation) {
            contentStyle.setOrientation(orientation);
            return this;
        }

        public RowStyle create() {
            return contentStyle;
        }
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setHaveVerticalBorder(boolean haveVerticalBorder) {
        this.haveVerticalBorder = haveVerticalBorder;
    }

    public void setHaveHorizontalBorder(boolean haveHorizontalBorder) {
        this.haveHorizontalBorder = haveHorizontalBorder;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public static int getVertical() {
        return vertical;
    }

    public static int getHorizontal() {
        return horizontal;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public boolean isHaveVerticalBorder() {
        return haveVerticalBorder;
    }

    public boolean isHaveHorizontalBorder() {
        return haveHorizontalBorder;
    }
}
