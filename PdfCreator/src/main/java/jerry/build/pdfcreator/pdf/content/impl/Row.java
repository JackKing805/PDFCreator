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
        int[] wh = new int[2];
        RowStyle rowStyle = (RowStyle) getContentStyle();
        List<Content> children = getChildren();
        if(rowStyle.getOrientation()==RowStyle.horizontal){
            //横向布局

            //算最大高度
            if(!children.isEmpty()){
                wh[1] = children.get(0).getHeight();
                for (int i = 0; i < children.size(); i++) {
                    if (wh[1]<children.get(i).getHeight()+children.get(i).getMarginTop()+children.get(i).getMarginBottom()) {
                        wh[1]=children.get(i).getHeight()+children.get(i).getMarginTop()+children.get(i).getMarginBottom();
                    }
                }
            }else{
                wh[1] = 20;
            }

            for (Content child:children) {
                wh[0] = wh[0]+child.getWidth()+child.getMarginRight()+child.getMarginLeft();
            }

        }

        if(rowStyle.getOrientation()==RowStyle.vertical){
            //竖向布局
            //算最大宽度
            if(!children.isEmpty()){
                wh[0] = children.get(0).getWidth();
                for (int i = 0; i < children.size(); i++) {
                    if (wh[0]<children.get(i).getWidth()+children.get(i).getMarginLeft()+children.get(i).getMarginRight()) {
                        wh[0]=children.get(i).getWidth()+children.get(i).getMarginLeft()+children.get(i).getMarginRight();
                    }
                }
            }else{
                wh[0] = 20;
            }

            for (Content child:children) {
                wh[1] = wh[1]+child.getHeight()+child.getMarginTop()+child.getMarginBottom();
            }
        }
        return wh;
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
