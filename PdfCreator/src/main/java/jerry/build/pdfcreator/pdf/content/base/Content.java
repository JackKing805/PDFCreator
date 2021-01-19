package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;

import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;

public class Content extends ContentGroup{
    public Content(ContentStyle contentStyle) {
        super(contentStyle);
    }

    @Override
    protected void measureDefault(ContentStyle contentStyle) {
       super.measureDefault(contentStyle);
    }

    @Override
    protected void drawDefault(Canvas canvas) {

    }

    @Override
    protected void measure(ContentStyle contentStyle) {

    }

    @Override
    protected void draw(Canvas canvas) {

    }
}
