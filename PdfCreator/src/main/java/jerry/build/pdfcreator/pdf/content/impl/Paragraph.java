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
    private int w;
    private int h;

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
        this.w = Float.valueOf(paint.measureText(paragraphStyle.getText())).intValue();
        this.h = Double.valueOf((Math.abs(metrics.top)+Math.abs(metrics.bottom))).intValue();

        setMeasureStyle(getParentWidth(),getParentHeight());
    }

    @Override
    protected void draw(Canvas canvas) {
        super.draw(canvas);

        int parentWidth = getParentWidth();
        int parentHeight = getParentHeight();



        int textCenterLiner = Float.valueOf((Math.abs(metrics.top)+Math.abs(metrics.bottom))/2).intValue();
        int textBaseLine = Float.valueOf((((Math.abs(metrics.top)+Math.abs(metrics.bottom))/2)+((Math.abs(metrics.top)+Math.abs(metrics.bottom))/2-Math.abs(metrics.bottom)))).intValue();
        int textCenterToBase = Float.valueOf((Math.abs(metrics.top)+Math.abs(metrics.bottom))/2-Math.abs(metrics.bottom)).intValue();

        int x = 0;
        int y = 0;
        switch(paragraphStyle.getFont().getFontAlign()){
            case ParagraphStyle.ParagraphFont.TopLeft:
                x = getMarginLeft();
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.TopCenter:
                x = parentWidth/2;
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.TopRight:
                x = parentWidth-w/2-getMarginRight();
                y = textBaseLine;
                break;
            case ParagraphStyle.ParagraphFont.CenterLeft:
                x = getMarginLeft();
                y = parentHeight/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.Center:
                x = parentWidth/2;
                y = parentHeight/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.CenterRight:
                x = parentWidth-w/2-getMarginRight();
                y = parentHeight/2+textCenterToBase;
                break;
            case ParagraphStyle.ParagraphFont.BottomLeft:
                x = getMarginLeft();
                y = parentHeight - (h-textBaseLine);
                break;
            case ParagraphStyle.ParagraphFont.BottomCenter:
                x = parentWidth/2;
                y = parentHeight - (h-textBaseLine);
                break;
            case ParagraphStyle.ParagraphFont.BottomRight:
                x = parentWidth-w/2-getMarginRight();
                y = parentHeight - (h-textBaseLine);
                break;
        }


        canvas.drawText(paragraphStyle.getText(),x,y,paint);
    }

    public int getTextWidth() {
        return w;
    }

    public int getTextHeight() {
        return h;
    }
}
