package jerry.build.pdfcreater.pdf.content.impl;

import android.graphics.Canvas;

import jerry.build.pdfcreater.bean.PageHandle;
import jerry.build.pdfcreater.pdf.content.base.ContentGroup;
import jerry.build.pdfcreater.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreater.pdf.content.bean.ColumnStyle;

/**
 * 列
 */
public class Column extends ContentGroup {

    public Column(ColumnStyle columnStyle, PageHandle pageHandle) {
        super(columnStyle,pageHandle);
    }


    @Override
    protected void measure(ContentStyle contentStyle) {

    }

    @Override
    protected void draw(Canvas canvas) {

    }
}
