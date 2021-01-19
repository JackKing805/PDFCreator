package jerry.build.pdfcreator.pdf.content.bean;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ContentStyle {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;
    public static final int SELF = -3;

    @IntDef({MATCH_PARENT,WRAP_CONTENT,SELF})
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @interface KV{}

    private int widthMode = SELF;
    private int heightMode = SELF;


    private int width = 0;
    private int height = 0;

    private int marginTop = 0;
    private int marginLeft = 0;
    private int marginRight = 0;
    private int marginBottom = 0;

    private int paddingTop = 0;
    private int paddingLeft = 0;
    private int paddingRight = 0;
    private int paddingBottom = 0;

    @ColorInt
    private int backgroundColor = Color.WHITE;

    public ContentStyle(int width, int height, int marginTop, int marginLeft, int marginRight, int marginBottom, int paddingTop, int paddingLeft, int paddingRight, int paddingBottom, @ColorInt int backgroundColor) {
        this(width, height);
        this.marginTop = marginTop;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
        this.paddingTop = paddingTop;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;
        this.backgroundColor = backgroundColor;
    }

    public ContentStyle(int rWidth, int rHeight) {
        if(rWidth<0){
            this.widthMode = rWidth;
        }else{
            this.width = rWidth;
        }

        if(rHeight<0){
            this.heightMode = rHeight;
        }else{
            this.height = rHeight;
        }

    }

    public ContentStyle(int width, int height, @ColorInt int backgroundColor) {
        this(width, height,0,0,0,0,0,0,0,0,backgroundColor);
    }

    public ContentStyle(int width, int height, int margin, int padding, @ColorInt int backgroundColor) {
        this(width, height,margin,margin,margin,margin,padding,padding,padding,padding,backgroundColor);
    }

    public ContentStyle() {
    }

    public void setMargin(int margin){
        this.marginTop = margin;
        this.marginLeft = margin;
        this.marginRight = margin;
        this.marginBottom = margin;
    }

    public void setPadding(int padding){
        this.paddingTop = padding;
        this.paddingLeft = padding;
        this.paddingRight = padding;
        this.paddingBottom = padding;
    }

    public void setWidthMode(int widthMode) {
        this.widthMode = widthMode;
    }

    public void setHeightMode(int heightMode) {
        this.heightMode = heightMode;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getWidthMode() {
        return widthMode;
    }

    public int getHeightMode() {
        return heightMode;
    }
}
