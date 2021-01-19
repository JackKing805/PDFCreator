package com.cdp.pdfdocumentdemo;

import android.graphics.Color;

import jerry.build.pdfcreator.model.DefaultTemplate;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;
import jerry.build.pdfcreator.pdf.content.impl.Row;

public class TestTemplate extends DefaultTemplate {
    @Override
    public void createHeader(ContentGroup content) {
        Row row = new Row(new RowStyle(ContentStyle.MATCH_PARENT, ContentStyle.MATCH_PARENT, 0,20,Color.GRAY));
        content.addContent(row);
        Row row1 = new Row(new RowStyle(ContentStyle.MATCH_PARENT, 120, 0, 10, Color.RED));
        row.addContent(row1);
        row1.addContent(new Row(new RowStyle(ContentStyle.MATCH_PARENT, 130,0,0,Color.YELLOW)));
    }

    @Override
    public void createBody(ContentGroup content) {

    }

    @Override
    public void createFooter(ContentGroup content) {

    }
}
