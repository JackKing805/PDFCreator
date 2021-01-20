package com.cdp.pdfdocumentdemo;

import android.graphics.Color;

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
        Row line1 = new Row(
                new RowStyle.Builder()
                        .setWidthMode(ContentStyle.MATCH_PARENT)
                        .setHeight(60)
                        .setMarginTop(10)
                        .setOrientation(RowStyle.vertical)
                        .create()
        );
        line1.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setText("Invoice")
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 40, ParagraphStyle.ParagraphFont.Bold, ParagraphStyle.ParagraphFont.TopCenter, "RuslanDisplay.ttf"))
                .create()));
        content.addContent(line1);

        Row line2 = new Row(
                new RowStyle.Builder()
                        .setWidthMode(ContentStyle.MATCH_PARENT)
                        .setHeight(30)
                        .setMarginTop(10)
                        .setOrientation(RowStyle.vertical)
                        .create()
        );
        line2.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setText("date:2021/1/19")
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 20, ParagraphStyle.ParagraphFont.Bold, ParagraphStyle.ParagraphFont.TopRight, "Anton-Regular.ttf"))
                .setMarginRight(20)
                .create()));
        content.addContent(line2);


        Row line3 = new Row(
                new RowStyle.Builder()
                        .setWidthMode(ContentStyle.MATCH_PARENT)
                        .setHeight(30)
                        .setMarginTop(5)
                        .setOrientation(RowStyle.vertical)
                        .create()
        );
        line3.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setText("author:jerry")
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 20, ParagraphStyle.ParagraphFont.Bold, ParagraphStyle.ParagraphFont.TopRight, "Anton-Regular.ttf"))
                .setMarginRight(20)
                .create()));
        content.addContent(line3);



    }

    @Override
    public void createBody(ContentGroup content) {

    }

    @Override
    public void createFooter(ContentGroup content) {

    }
}
