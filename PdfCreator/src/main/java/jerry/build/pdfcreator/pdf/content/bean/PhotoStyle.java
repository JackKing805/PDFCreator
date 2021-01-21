package jerry.build.pdfcreator.pdf.content.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntDef;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;

public class PhotoStyle extends ContentStyle{
    private Bitmap src;
    private int ScaleType = CenterCrop;

    public static final int CenterCrop = 0x01;
    public static final int Src = 0x02;
    public static final int FitCenter = 0x03;


    @IntDef({CenterCrop, Src,FitCenter})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @interface ScaleType {
    }


    public static class Builder {
        PhotoStyle contentStyle = new PhotoStyle();

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


        public Builder setSrc(Bitmap src) {
            contentStyle.setSrc(src);
            return this;
        }

        public Builder setSrc(int resID) {
            Drawable drawable = PageHandleHolder.newInstance().getContext().getResources().getDrawable(resID);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            return setSrc(bitmap);
        }

        public Builder setSrc(File file) {
            return setSrc(file.getAbsolutePath());
        }

        public Builder setSrc(String path) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            return setSrc(bitmap);
        }

        public Builder setScaleType(@ScaleType int scaleType) {
            contentStyle.setScaleType(scaleType);
            return this;
        }

        public PhotoStyle create() {
            return contentStyle;
        }
    }

    public Bitmap getSrc() {
        return src;
    }

    public void setSrc(Bitmap src) {
        this.src = src;
    }

    public int getScaleType() {
        return ScaleType;
    }

    public void setScaleType(@ScaleType int scaleType) {
        ScaleType = scaleType;
    }
}
