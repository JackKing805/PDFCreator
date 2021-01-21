package com.cdp.pdfdocumentdemo;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.model.DefaultTemplate;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.base.ParagraphFontFamily;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.bean.ParagraphStyle;
import jerry.build.pdfcreator.pdf.content.bean.PhotoStyle;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;
import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;
import jerry.build.pdfcreator.pdf.content.impl.Paragraph;
import jerry.build.pdfcreator.pdf.content.impl.Photo;
import jerry.build.pdfcreator.pdf.content.impl.Row;

public class TestTemplate extends DefaultTemplate {


    @Override
    public void createHeader(Row content) {
        content.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setHeight(40)
                .setMarginTop(50)
                .setText("Invoice")
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 40, ParagraphStyle.ParagraphFont.Bold, ParagraphStyle.ParagraphFont.TopCenter, ParagraphFontFamily.font1))
                .create()));

        content.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setText("date:2021/1/19")
                .setHeight(20)
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 20, ParagraphStyle.ParagraphFont.Bold, ParagraphStyle.ParagraphFont.TopRight, "Anton-Regular.ttf"))
                .setMarginRight(20)
                .create()));

        content.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setText("author:jerry")
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 20, ParagraphStyle.ParagraphFont.Bold, ParagraphStyle.ParagraphFont.TopRight, "Anton-Regular.ttf"))
                .setMarginRight(20)
                .setMarginTop(10)
                .create()));

        content.addContent(new Row(new RowStyle.Builder()
                .setWidthMode(ContentStyle.MATCH_PARENT)
                .setHeight(20)
                .setMarginLeft(10)
                .setMarginRight(10)
                .setMarginTop(10)
                .setBorderWidth(2)
                .setHaveHorizontalBorder(true)
                .setHaveVerticalBorder(false)
                .setOrientation(RowStyle.vertical)
                .create()
        ));

        content.addSpace(ContentGroup.vertical,50);
    }

    @Override
    public void createBody(Row content) {
        Row titleLine = new Row(new RowStyle.Builder()
                .setWidthMode(ContentStyle.MATCH_PARENT)
                .setHeightMode(ContentStyle.WRAP_CONTENT)
                .setMarginLeft(10)
                .setMarginRight(10)
                .setOrientation(RowStyle.horizontal)
                .create()
        );

        titleLine.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setText("Name")
                .setWidth(0)
                .setWeight(1)
                .setWidthMode(ContentStyle.WIGHT)
                .setHeightMode(ContentStyle.WRAP_CONTENT)
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"),
                        20,
                        ParagraphStyle.ParagraphFont.Normal,
                        ParagraphStyle.ParagraphFont.Center,
                        "HanaleiFill-Regular.ttf"))
                .setMarginRight(20)
                .create()));

        titleLine.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setText("Money")
                .setWidth(0)
                .setWeight(1)
                .setWidthMode(ContentStyle.WIGHT)
                .setHeightMode(ContentStyle.WRAP_CONTENT)
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"),
                        20,
                        ParagraphStyle.ParagraphFont.Normal,
                        ParagraphStyle.ParagraphFont.Center,
                        "HanaleiFill-Regular.ttf"))
                .setMarginRight(20)
                .create()));

        titleLine.addContent(new Paragraph(new ParagraphStyle.Builder()
                .setText("Time")
                .setWeight(1)
                .setWidthMode(ContentStyle.WIGHT)
                .setHeightMode(ContentStyle.WRAP_CONTENT)
                .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 20, ParagraphStyle.ParagraphFont.Normal, ParagraphStyle.ParagraphFont.Center, "HanaleiFill-Regular.ttf"))
                .setMarginRight(20)
                .create()));
        content.addContent(titleLine);

        content.addSpace(ContentGroup.vertical,50);
        content.addContent(new Row(new RowStyle.Builder()
                .setWidthMode(ContentStyle.MATCH_PARENT)
                .setHeightMode(ContentStyle.SELF)
                .setHeight(2)
                .setBorderWidth(2)
                .setHaveHorizontalBorder(true)
                .setMarginLeft(10)
                .setMarginRight(10)
                .setOrientation(RowStyle.horizontal)
                .create()
        ));
        content.addSpace(ContentGroup.vertical,20);

        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd/hh:MM:ss");
        for (int i = 0; i < 10; i++) {
            Row row = new Row(new RowStyle.Builder()
                    .setWidthMode(ContentStyle.MATCH_PARENT)
                    .setOrientation(RowStyle.horizontal)
                    .setMarginRight(10)
                    .setMarginLeft(10)
                    .setBackgroundColor(i % 2 == 0 ? Color.parseColor("#d3d9da") : Color.parseColor("#ffffff"))
                    .setHeight(40)
                    .setMarginTop(5)
                    .create());

            row.addContent(new Paragraph(new ParagraphStyle.Builder()
                    .setText("Jerry"+i)
                    .setWidth(0)
                    .setWeight(1)
                    .setWidthMode(ContentStyle.WIGHT)
                    .setHeightMode(ContentStyle.MATCH_PARENT)
                    .setFont(new ParagraphStyle.ParagraphFont.Builder()
                            .setFontFamily(ParagraphFontFamily.font9)
                            .setFontColor(Color.BLACK)
                            .setFontSize(20)
                            .setFontAlign(ParagraphStyle.ParagraphFont.CenterRight)
                        .create())
                    .create()));

            row.addContent(new Paragraph(new ParagraphStyle.Builder()
                    .setText(""+((i+1)*20))
                    .setWidth(0)
                    .setWeight(1)
                    .setWidthMode(ContentStyle.WIGHT)
                    .setHeightMode(ContentStyle.MATCH_PARENT)
                    .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 20, ParagraphStyle.ParagraphFont.Normal, ParagraphStyle.ParagraphFont.Center, "app_font_regular.ttf"))
                    .create()));

            row.addContent(new Paragraph(new ParagraphStyle.Builder()
                    .setText(sp.format(new Date()))
                    .setWidth(0)
                    .setWeight(1)
                    .setMarginRight(20)
                    .setWidthMode(ContentStyle.WIGHT)
                    .setHeightMode(ContentStyle.MATCH_PARENT)
                    .setFont(new ParagraphStyle.ParagraphFont(Color.parseColor("#3498db"), 20, ParagraphStyle.ParagraphFont.Normal, ParagraphStyle.ParagraphFont.CenterRight, "app_font_regular.ttf"))
                    .create()));
            content.addContent(row);
        }

        content.addContent(new Photo(new PhotoStyle.Builder()
                .setSrc(PageHandleHolder.newInstance().getContext().getApplicationContext().getFilesDir().getAbsolutePath() + "/img/sign.png")
                .setMarginLeft(10)
                .setMarginTop(10)
                .setScaleType(PhotoStyle.Src)
                .setHeight(100)
                .setWidth(200)
                .create()));
    }

    @Override
    public void createFooter(Row content) {
        content.addSpace(ContentGroup.vertical,50);
        content.addContent(new Row(new RowStyle.Builder()
                .setWidthMode(ContentStyle.MATCH_PARENT)
                .setBorderWidth(2)
                .setOrientation(RowStyle.horizontal)
                .setHaveHorizontalBorder(true)
                .setMarginRight(10)
                .setMarginLeft(10)
                .setBackgroundColor(Color.BLACK)
                .setHeight(40)
                .setMarginTop(1)
                .create()));
    }
}
