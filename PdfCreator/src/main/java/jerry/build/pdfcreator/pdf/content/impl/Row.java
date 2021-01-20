package jerry.build.pdfcreator.pdf.content.impl;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import java.util.List;

import jerry.build.pdfcreator.pdf.content.base.Content;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;

/**
 * 行
 */
public class Row extends ContentGroup {

    public Row(RowStyle contentStyle) {
        super(contentStyle);
    }

    @Override
    public void layout() {
        List<Content> children = getChildren();
        int marginTop = 0;
        for (int i = 0; i < children.size(); i++) {
            if(i==0){
                marginTop = marginTop + children.get(i).getMarginTop();
            }else{
                marginTop = marginTop + children.get(i).getMarginTop() + children.get(i).getMarginBottom() + children.get(i-1).getHeight();
            }
            children.get(i).setMarginTop(marginTop);
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        super.draw(canvas);
        RowStyle rowStyle = (RowStyle) getContentStyle();
        Paint paint = createPaint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(rowStyle.getBorderWidth());
        if(rowStyle.isHaveHorizontalBorder()){
            canvas.drawLine(0,rowStyle.getBorderWidth()/2,getWidth(),rowStyle.getBorderWidth()/2,paint);
            canvas.drawLine(0,getHeight()-rowStyle.getBorderWidth()/2,getWidth(),getHeight()-rowStyle.getBorderWidth()/2,paint);
        }

        if(rowStyle.isHaveVerticalBorder()){
            canvas.drawLine(rowStyle.getBorderWidth()/2,0,rowStyle.getBorderWidth()/2,getHeight(),paint);
            canvas.drawLine(getWidth()-rowStyle.getBorderWidth()/2,0,getWidth()-rowStyle.getBorderWidth()/2,getHeight(),paint);
        }
    }
}
