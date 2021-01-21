package jerry.build.pdfcreator.pdf.content.build;

import android.app.AlertDialog;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.base.Content;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.impl.Row;
import jerry.build.pdfcreator.pdf.content.bean.RowStyle;

public class ContentManager {
    //最底层的视图
    private final ContentGroup rootContent;
    private final Row row;

    public ContentManager() {
        ContentStyle contentStyle = new ContentStyle.Builder()
                .setWidth(PageHandleHolder.newInstance().getPageStyle().getWidth())
                .setHeight(PageHandleHolder.newInstance().getPageStyle().getHeight())
                .setHeightMode(ContentStyle.MATCH_PARENT)
                .setWidthMode(ContentStyle.MATCH_PARENT)
                .setBackgroundColor(Color.TRANSPARENT)
                .create();
        rootContent = new ContentGroup(contentStyle);

        RowStyle rowStyle = new RowStyle.Builder()
                .setHeightMode(ContentStyle.MATCH_PARENT)
                .setWidthMode(ContentStyle.MATCH_PARENT)
                .setBackgroundColor(Color.parseColor("#f3ece3"))
                .setOrientation(RowStyle.vertical)
                .create();
        row = new Row(rowStyle);
        rootContent.addContent(row);
    }

    public void completeContent() {
        if (rootContent.haveChild()) {
            List<Content> children = new ArrayList<>();
            children.add(rootContent);
            measureChildren(children);
            layoutChildren(children);
            drawChildren(children);
        }
    }

    private void measureChildren(List<Content> children) {
        for (Content child : children) {
            child.measureDefault();
            try{
                ContentGroup content = (ContentGroup) child;
                measureChildren(content.getChildren());
            }catch (ClassCastException e){
                e.printStackTrace();
            }
        }
    }


    private void layoutChildren(List<Content> children) {
        for (Content child : children) {
            try{
                ContentGroup content = (ContentGroup) child;
                layoutChildren(content.getChildren());
                content.layout();
            }catch (ClassCastException e){
                e.printStackTrace();
            }
        }
    }

    private void drawChildren(List<Content> children) {
        for (Content child : children) {
            child.drawDefault(PageHandleHolder.newInstance().getCanvas());
            try{
                ContentGroup content = (ContentGroup) child;
                drawChildren(content.getChildren());
            }catch (ClassCastException e){
                e.printStackTrace();
            }
        }
    }



    public Row getRootContent() {
        return row;
    }
}
