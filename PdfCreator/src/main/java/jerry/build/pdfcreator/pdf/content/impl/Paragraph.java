package jerry.build.pdfcreator.pdf.content.impl;

import android.graphics.Canvas;
import android.graphics.Paint;

import jerry.build.pdfcreator.pdf.content.base.Content;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.ParagraphStyle;

public class Paragraph extends Content {
    private Paint paint;
    private Paint.FontMetrics metrics;
    private ParagraphStyle paragraphStyle;

    private int textWidth = 0;
    private int textHeight = 0;

    public Paragraph(ParagraphStyle contentStyle) {
        super(contentStyle);
    }

    @Override
    protected void measure(int widthMode, int heightMode, int height, int width) {
        super.measure(widthMode, heightMode, height, width);
        paragraphStyle = (ParagraphStyle) getContentStyle();
        paint = createPaint();
        paint.setTextSize(paragraphStyle.getFont().getFontSize());
        paint.setColor(paragraphStyle.getFont().getFontColor());
        paint.setFakeBoldText(paragraphStyle.getFont().getFontStyle()==ParagraphStyle.ParagraphFont.Bold);
        paint.setTypeface(paragraphStyle.getFont().getFontFamily());
        paint.setTextAlign(Paint.Align.CENTER);
        metrics = paint.getFontMetrics();

        textWidth = Double.valueOf(Math.ceil(paint.measureText(paragraphStyle.getText()))).intValue();
        textHeight = Double.valueOf(Math.ceil((Math.abs(metrics.top)+Math.abs(metrics.bottom)+Math.abs(metrics.leading)))).intValue();
        int measureWidth;
        int measureHeight;

        if(widthMode==ContentStyle.WRAP_CONTENT){
            measureWidth = textWidth;
        }else if(widthMode==ContentStyle.MATCH_PARENT){
            measureWidth = getParentWidth();
        }else if(widthMode==ContentStyle.SELF){
            measureWidth = getWidth();
        }else{
            measureWidth = calculatingWeight(getParentWidth() - getMarginLeft() - getMarginRight() - getParent().getPaddingLeft() - getParent().getPaddingRight(), getCompanion(), 1);
        }



        if(heightMode==ContentStyle.WRAP_CONTENT){
            measureHeight = textHeight;
        }else if(heightMode==ContentStyle.MATCH_PARENT){
            measureHeight = getParentHeight();
        }else if(widthMode==ContentStyle.SELF){
            measureHeight = getHeight();
        }else{
            measureHeight = calculatingWeight(getParentHeight() - getMarginTop() - getMarginBottom() - getParent().getPaddingTop() - getParent().getPaddingBottom(), getCompanion(), 2);
        }
        setMeasureStyle(measureWidth,measureHeight);
    }

    @Override
    protected void draw(Canvas canvas) {
        super.draw(canvas);

        int w = getWidth();
        int h = getHeight();

        int textCenterLiner = Float.valueOf((Math.abs(metrics.top)+Math.abs(metrics.bottom))/2).intValue();
        int textBaseLine = Float.valueOf((((Math.abs(metrics.top)+Math.abs(metrics.bottom))/2)+((Math.abs(metrics.top)+Math.abs(metrics.bottom))/2-Math.abs(metrics.bottom)))).intValue();
        int textCenterToBase = Float.valueOf((Math.abs(metrics.top)+Math.abs(metrics.bottom))/2-Math.abs(metrics.bottom)).intValue();

        int x = 0;
        int y = 0;
        switch(paragraphStyle.getFont().getFontAlign()){
            case ParagraphStyle.ParagraphFont.TopLeft:
                x = getTextWidth()/2;
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.TopCenter:
                x = w/2;
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.TopRight:
                x = w-getTextWidth()/2-getMarginRight();
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.CenterLeft:
                x = getTextWidth()/2;
                y = h/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.Center:
                x = w/2;
                y = h/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.CenterRight:
                x = w-getTextWidth()/2-getMarginRight();
                y = h/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.BottomLeft:
                x = getTextWidth()/2;
                y = h - (h-textBaseLine);
                break;
            case ParagraphStyle.ParagraphFont.BottomCenter:
                x = w/2;
                y = h - (h-textBaseLine);
                break;
            case ParagraphStyle.ParagraphFont.BottomRight:
                x = w-w/2-getMarginRight();
                y = h - (h-textBaseLine);
                break;
        }


        canvas.drawText(paragraphStyle.getText(),x,y,paint);
    }

    public int getTextWidth() {
        return textWidth;
    }

    public int getTextHeight() {
        return textHeight;
    }
}
