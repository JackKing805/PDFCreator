package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;

public class ContentGroup extends Content{
    private static final String TAG = "ContentGroup";
    //包含的子Content
    private final List<Content> children;


    public ContentGroup(ContentStyle contentStyle) {
        super(contentStyle);
        children = new ArrayList<>();
    }

    @Override
    public void measureDefault() {
        Log.e(TAG, "measureDefault: "+toString() );
        int widthMode = getWidthMode();
        int heightMode = getHeightMode();

        int measureWidth = 0;
        int measureHeight = 0;

        if (widthMode == ContentStyle.MATCH_PARENT) {
            measureWidth = getParentWidth() - getMarginLeft() - getMarginRight()- getParent().getPaddingLeft()-getParent().getPaddingRight();
        } else if (widthMode == ContentStyle.WRAP_CONTENT) {

        } else if (widthMode == ContentStyle.SELF) {
            if(getWidth()+getMarginLeft()+getParent().getPaddingLeft()>getParentWidth()-getMarginRight()-getParent().getPaddingRight()){
                measureWidth = getParentWidth() - getMarginLeft() - getMarginRight()- getParent().getPaddingLeft()-getParent().getPaddingRight();
            }else{
                measureWidth = getWidth();
            }
        }


        if (heightMode == ContentStyle.MATCH_PARENT) {
            measureHeight = getParentHeight() - getMarginTop() - getMarginBottom() - getParent().getPaddingTop()-getParent().getPaddingBottom();
        } else if (heightMode == ContentStyle.WRAP_CONTENT) {


        } else if (heightMode == ContentStyle.SELF) {
            if(getHeight()+getMarginTop()+getParent().getPaddingTop()>getParentHeight()-getMarginBottom()-getParent().getPaddingTop()){
                measureHeight = getParentHeight() - getMarginTop() - getMarginBottom()- getParent().getPaddingTop()-getParent().getPaddingBottom();
            }else{
                measureHeight = getHeight();
            }
        }
        setMeasureStyle(measureWidth, measureHeight);
        measure();
        layout();
        drawDefault(PageHandleHolder.newInstance().getCanvas());
    }

    public void layout(){}

    /**
     * 添加子Content
     */
    public void addContent(Content content){
        content.setParent(this);
        children.add(content);
    }

    /**
     * 判断是否有子content
     */
    public boolean haveChild(){
        return !children.isEmpty();
    }


    public List<Content> getChildren(){
        return children;
    }
}
