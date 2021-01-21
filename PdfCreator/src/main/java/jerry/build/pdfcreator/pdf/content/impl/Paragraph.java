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
        int measureWidth = 0;
        int measureHeight = 0;

        if(widthMode==ContentStyle.WRAP_CONTENT && width == 0){
            width = Double.valueOf(Math.ceil(getParentWidth() * paragraphStyle.getWeight())).intValue();
            setMeasureStyle(width,height);
        }
        else if(widthMode==ContentStyle.WRAP_CONTENT){
            measureWidth = textWidth;
        }else if(widthMode==ContentStyle.MATCH_PARENT){
            measureWidth = getParentWidth();
        }else{
            measureWidth = getWidth();
        }

        if(heightMode==ContentStyle.WRAP_CONTENT && height == 0){
            height = Double.valueOf(Math.ceil(getParentHeight() * paragraphStyle.getWeight())).intValue();
            setMeasureStyle(width,height);
        } else if(heightMode==ContentStyle.WRAP_CONTENT){
            measureHeight = textHeight;
        }else if(heightMode==ContentStyle.MATCH_PARENT){
            measureHeight = getParentHeight();
        }else{
            measureHeight = getHeight();
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
                x = textWidth/2;
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.TopCenter:
                x = w/2;
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.TopRight:
                x = w-textWidth/2-getMarginRight();
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.CenterLeft:
                x = textWidth/2;
                y = h/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.Center:
                x = w/2;
                y = h/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.CenterRight:
                x = w-textWidth/2-getMarginRight();
                y = h/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.BottomLeft:
                x = textWidth/2;
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
