package com.cdp.pdfdocumentdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.fonts.Font;

import jerry.build.pdfcreator.model.DefaultTemplate;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.ParagraphStyle;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;
import jerry.build.pdfcreator.pdf.content.impl.Paragraph;
import jerry.build.pdfcreator.pdf.content.impl.Row;

public class TestTemplate extends DefaultTemplate {

    @Override
    public void createHeader(ContentGroup content) {
        Row row = new Row(new RowStyle(ContentStyle.MATCH_PARENT, ContentStyle.MATCH_PARENT,0,10, Color.parseColor("#e9e5d4"),0,false,false));
        content.addContent(row);
        RowStyle rowStyle1 = new RowStyle(ContentStyle.MATCH_PARENT, 150,Color.parseColor("#d1cfc7"), 5, false, true);
        rowStyle1.setMarginLeft(10);
        rowStyle1.setMarginRight(10);
        rowStyle1.setMarginTop(5);
        RowStyle rowStyle2 = new RowStyle(ContentStyle.MATCH_PARENT, 100,Color.parseColor("#ffffff"), 5, false, true);
        rowStyle2.setMarginLeft(10);
        rowStyle2.setMarginRight(10);
        rowStyle2.setMarginTop(5);


        for (int i = 0; i < 6; i++) {
            Row row1 = new Row(i%2==0?rowStyle1:rowStyle2);
            row.addContent(row1);
            row1.addContent(new Paragraph(new ParagraphStyle("Hard work never killed anybody. but why take the risk",
                    new ParagraphStyle.ParagraphFont(Color.BLACK,30, ParagraphStyle.ParagraphFont.Bold, ParagraphStyle.ParagraphFont.BottomRight,"app_font_bold.ttf"))));
        }

    }

    @Override
    public void createBody(ContentGroup content) {

    }

    @Override
    public void createFooter(ContentGroup content) {

    }
}
