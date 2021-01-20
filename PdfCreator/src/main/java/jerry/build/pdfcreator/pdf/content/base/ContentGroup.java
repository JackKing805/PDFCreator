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
        layout();
        super.measureDefault();
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
