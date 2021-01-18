package jerry.build.pdfcreater.pdf.content.base;

import android.graphics.Canvas;

import jerry.build.pdfcreater.pdf.content.bean.ContentStyle;

public class Content extends ContentGroup{
    public Content(ContentStyle contentStyle) {
        super(contentStyle);
    }

    @Override
    protected void measureDefault(ContentStyle contentStyle) {
       super.measureDefault(contentStyle);
    }

    @Override
    protected void drawDefault() {

    }

    @Override
    protected void measure(ContentStyle contentStyle) {

    }

    @Override
    protected void draw(Canvas canvas) {

    }
}
