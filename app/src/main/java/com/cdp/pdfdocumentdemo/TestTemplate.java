package com.cdp.pdfdocumentdemo;

import android.graphics.Color;

import jerry.build.pdfcreater.model.DefaultTemplate;
import jerry.build.pdfcreater.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreater.pdf.content.bean.RowStyle;
import jerry.build.pdfcreater.pdf.content.impl.Row;

public class TestTemplate extends DefaultTemplate {
    @Override
    public void createHeader() {
        Row row = new Row(new RowStyle(ContentStyle.MATCH_PARENT, ContentStyle.MATCH_PARENT, 50,0,Color.GRAY));
        addContent(row);
        row.addContent(new Row(new RowStyle(120, 150,Color.RED)));
        row.addContent(new Row(new RowStyle(ContentStyle.MATCH_PARENT, ContentStyle.MATCH_PARENT,120,0,Color.YELLOW)));
    }

    @Override
    public void createBody() {

    }

    @Override
    public void createFooter() {

    }
}
