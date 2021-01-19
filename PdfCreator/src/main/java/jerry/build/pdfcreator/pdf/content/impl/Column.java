package jerry.build.pdfcreator.pdf.content.impl;

import android.graphics.Canvas;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.ColumnStyle;

/**
 * 列
 */
public class Column extends ContentGroup {

    public Column(ColumnStyle columnStyle, PageHandle pageHandle) {
        super(columnStyle);
    }


    @Override
    protected void measure(ContentStyle contentStyle) {

    }

    @Override
    protected void draw(Canvas canvas) {

    }
}
