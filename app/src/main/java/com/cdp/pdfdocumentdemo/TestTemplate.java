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
        Row row = new Row(new RowStyle(ContentStyle.MATCH_PARENT, ContentStyle.MATCH_PARENT,0,50, Color.parseColor("#e9e5d4"),0,false,false));
        content.addContent(row);
        RowStyle rowStyle1 = new RowStyle(ContentStyle.MATCH_PARENT, 50,Color.parseColor("#d1cfc7"), 5, false, true);
        rowStyle1.setMarginLeft(10);
        rowStyle1.setMarginRight(10);
        rowStyle1.setMarginTop(5);
        RowStyle rowStyle2 = new RowStyle(ContentStyle.MATCH_PARENT, 50,Color.parseColor("#ffffff"), 5, false, true);
        rowStyle2.setMarginLeft(10);
        rowStyle2.setMarginRight(10);
        rowStyle2.setMarginTop(5);

        Row row1 = new Row(rowStyle1);
        Row row2 = new Row(rowStyle2);
        Row row3 = new Row(rowStyle1);
        Row row4 = new Row(rowStyle2);
        Row row5 = new Row(rowStyle1);
        row.addContent(row1);
        row.addContent(row2);
        row.addContent(row3);
        row.addContent(row4);
        row.addContent(row5);

        row5.addContent(new Row(new RowStyle(ContentStyle.MATCH_PARENT, 20,10,0,Color.RED, 5, false, false   )));
    }

    @Override
    public void createBody(ContentGroup content) {

    }

    @Override
    public void createFooter(ContentGroup content) {

    }
}
