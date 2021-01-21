package jerry.build.pdfcreator.pdf.content.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import jerry.build.pdfcreator.pdf.content.base.Content;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.PhotoStyle;

public class Photo extends Content {
    public Photo(PhotoStyle contentStyle) {
        super(contentStyle);
    }

    @Override
    protected void draw(Canvas canvas) {
        super.draw(canvas);
        PhotoStyle photoStyle = (PhotoStyle) getContentStyle();
        if(photoStyle.getSrc()!=null){
            Paint paint = createPaint();
            switch(photoStyle.getScaleType()){
                case PhotoStyle.CenterCrop:
                    Bitmap bitmap = imageScale(photoStyle.getSrc(), getWidth(), getHeight());
                    canvas.drawBitmap(bitmap,new Rect(
                            0,0,bitmap.getWidth(),bitmap.getHeight()
                    ),new Rect(
                            0,0,getWidth(),getHeight()
                    ),paint);
                    break;
                case PhotoStyle.FitCenter:
                    Bitmap bitmap1 = photoStyle.getSrc();
                    canvas.drawBitmap(bitmap1,new Rect(
                            0,0,getWidth(),getHeight()
                    ),new Rect(
                            0,0,getWidth(),getHeight()
                    ),paint);
                    break;
                case PhotoStyle.Src:
                    Bitmap bitmap2 = photoStyle.getSrc();
                    canvas.drawBitmap(bitmap2,new Rect(
                            0,0,bitmap2.getWidth(),bitmap2.getHeight()
                    ),new Rect(
                            0,0,bitmap2.getWidth(),bitmap2.getHeight()
                    ),paint);
                    break;
            }
        }
    }


    public static Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        return Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                true);
    }
}
