package jerry.build.pdfcreator.pdf.content.bean;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jerry.build.pdfcreator.pdf.content.base.ParagraphFontFamily;
import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;

public class ParagraphStyle extends ContentStyle {
    private String text;
    private ParagraphFont font;

    public static class Builder {
        ParagraphStyle contentStyle = new ParagraphStyle();

        public Builder() {
            contentStyle.setWidthMode(ContentStyle.MATCH_PARENT);
            contentStyle.setHeightMode(ContentStyle.WRAP_CONTENT);
        }


        public Builder setWeight(int weight) {
            contentStyle.setWeight(weight);
            return this;
        }

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

        public Builder setText(String text) {
            contentStyle.setText(text);
            return this;
        }

        public Builder setFont(ParagraphFont font) {
            contentStyle.setFont(font);
            return this;
        }

        public ParagraphStyle create() {
            return contentStyle;
        }
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setFont(ParagraphFont font) {
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public ParagraphFont getFont() {
        return font;
    }

    public static class ParagraphFont {
        private int fontSize = 12;
        @ColorInt
        private int fontColor = Color.BLACK;
        @FontStyle
        private int fontStyle = Normal;
        @FontAlign
        private int fontAlign = TopLeft;
        private String fontFamily = ParagraphFontFamily.font1;
        private Typeface typeface;

        public static final int Bold = 0x01;
        public static final int Normal = 0x02;

        @IntDef({Bold, Normal})
        @Target({ElementType.FIELD, ElementType.PARAMETER})
        @Retention(RetentionPolicy.SOURCE)
        @interface FontStyle {
        }

        public static final int TopCenter = 0x01;
        public static final int TopLeft = 0x02;
        public static final int TopRight = 0x03;
        public static final int BottomCenter = 0x04;
        public static final int BottomLeft = 0x05;
        public static final int BottomRight = 0x06;
        public static final int Center = 0x07;
        public static final int CenterLeft = 0x08;
        public static final int CenterRight = 0x09;

        @IntDef({TopCenter, TopLeft, TopRight, BottomCenter, BottomLeft, BottomRight, Center, CenterLeft, CenterRight})
        @Target({ElementType.FIELD, ElementType.PARAMETER})
        @Retention(RetentionPolicy.SOURCE)
        @interface FontAlign {
        }

        public ParagraphFont() {
        }

        public ParagraphFont(@ColorInt int fontColor, int fontSize, @FontStyle int fontStyle, @FontAlign int fontAlign, String fontFamily) {
            this.fontColor = fontColor;
            this.fontSize = fontSize;
            this.fontStyle = fontStyle;
            if (fontFamily != null) {
                this.fontFamily = fontFamily;
            }
            this.fontAlign = fontAlign;
        }

        public Typeface getFontFamily() {
            AssetManager assetManager = PageHandleHolder.newInstance().getContext().getAssets();
            if (typeface == null) {
                typeface = Typeface.createFromAsset(assetManager, "fonts/" + fontFamily);
            }
            return typeface;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public void setFontColor(int fontColor) {
            this.fontColor = fontColor;
        }

        public void setFontStyle(int fontStyle) {
            this.fontStyle = fontStyle;
        }

        public void setFontAlign(int fontAlign) {
            this.fontAlign = fontAlign;
        }

        public void setFontFamily(String fontFamily) {
            this.fontFamily = fontFamily;
        }

        public int getFontAlign() {
            return fontAlign;
        }

        public int getFontColor() {
            return fontColor;
        }

        public int getFontSize() {
            return fontSize;
        }

        public int getFontStyle() {
            return fontStyle;
        }


        public static class Builder {
            ParagraphFont font = new ParagraphFont();

            public Builder setFontSize(int fontSize) {
                font.setFontSize(fontSize);
                return this;
            }

            public Builder setFontColor(int fontColor) {
                font.setFontColor(fontColor);
                return this;
            }

            public Builder setFontStyle(int fontStyle) {
                font.setFontStyle(fontStyle);
                return this;
            }

            public Builder setFontAlign(int fontAlign) {
                font.setFontAlign(fontAlign);
                return this;
            }

            public Builder setFontFamily(String fontFamily) {
                font.setFontFamily(fontFamily);
                return this;
            }

            public ParagraphFont create() {
                return font;
            }
        }
    }
}
