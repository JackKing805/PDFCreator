package com.cdp.pdfdocumentdemo;

import android.graphics.Color;

import jerry.build.pdfcreater.model.DefaultTemplate;
import jerry.build.pdfcreater.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreater.pdf.content.bean.RowStyle;
import jerry.build.pdfcreater.pdf.content.impl.Row;

public class TestTemplate extends DefaultTemplate {
    @Override
    public void createHeader() {
        Row row = contentManager.addRow(new RowStyle(ContentStyle.MATCH_PARENT, ContentStyle.MATCH_PARENT, Color.GRAY));
        row.addContent(new Row(new RowStyle(ContentStyle.MATCH_PARENT, 20,10,0, Color.RED)));
    }

    @Override
    public void createBody() {

    }

    @Override
    public void createFooter() {

    }
}
