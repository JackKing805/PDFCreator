package jerry.build.pdfcreator.pdf.content.impl;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.ColumnStyle;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;

/**
 * 列
 */
public class Column extends ContentGroup {


    public Column(ColumnStyle contentStyle) {
        super(contentStyle);
    }


    @Override
    protected void draw(Canvas canvas) {
        super.draw(canvas);

        ColumnStyle columnStyle = (ColumnStyle) getContentStyle();
        Paint paint = createPaint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(columnStyle.getBorderWidth());
        if(columnStyle.isHaveVerticalBorder()){
            canvas.drawLine(columnStyle.getBorderWidth()/2,0,columnStyle.getBorderWidth()/2,getHeight(),paint);
            canvas.drawLine(getWidth()-columnStyle.getBorderWidth()/2,0,getWidth()-columnStyle.getBorderWidth()/2,getHeight(),paint);
        }

    }
}
