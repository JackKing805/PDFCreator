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
        RowStyle rowStyle = (RowStyle) getContentStyle();
        List<Content> children = getChildren();
        if(rowStyle.getOrientation()==RowStyle.horizontal){
            int marginLeft = 0;
            for (int i = 0; i < children.size(); i++) {
                if(i==0){
                    marginLeft = marginLeft + children.get(i).getMarginLeft();
                }else{
                    marginLeft = marginLeft + children.get(i).getMarginLeft() + children.get(i-1).getMarginRight() + children.get(i-1).getWidth();
                }
                children.get(i).setMarginLeft(marginLeft);
            }
        }else{
            int marginTop = 0;
            for (int i = 0; i < children.size(); i++) {
                if(i==0){
                    marginTop = marginTop + children.get(i).getMarginTop();
                }else{
                    marginTop = marginTop + children.get(i).getMarginTop() + children.get(i-1).getMarginBottom() + children.get(i-1).getHeight();
                }
                children.get(i).setMarginTop(marginTop);
            }
        }
    }

    @Override
    protected void measure(int widthMode, int heightMode, int height, int width) {
        super.measure(widthMode, heightMode, height, width);
        RowStyle rowStyle = (RowStyle) getContentStyle();
        if(rowStyle.isHaveHorizontalBorder()){
            getContentStyle().setPaddingLeft(rowStyle.getBorderWidth());
            getContentStyle().setPaddingRight(rowStyle.getBorderWidth());
        }

        if(rowStyle.isHaveVerticalBorder()){
            getContentStyle().setPaddingTop(rowStyle.getBorderWidth());
            getContentStyle().setPaddingBottom(rowStyle.getBorderWidth());
        }
    }


    @Override
    protected int[] measureChildren() {

        return super.measureChildren();
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
