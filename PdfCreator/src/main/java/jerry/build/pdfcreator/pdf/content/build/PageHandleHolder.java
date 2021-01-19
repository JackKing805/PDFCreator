package jerry.build.pdfcreator.pdf.content.build;

import android.graphics.Canvas;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.bean.PageStyle;

public class PageHandleHolder {
    private static PageHandleHolder instance;

    public static synchronized PageHandleHolder newInstance(){
        if(instance == null){
            instance = new PageHandleHolder();
        }
        return instance;
    }

    private PageHandle pageHandle;


    public void setPageHandle(PageHandle pageHandle){
        this.pageHandle = pageHandle;
    }

    public PageHandle getPageHandle(){
        if(pageHandle==null){
            throw new NullPointerException("PageHandle can't be null");
        }
        return pageHandle;
    }

    public Canvas getCanvas(){
        if(pageHandle==null){
            throw new NullPointerException("PageHandle can't be null");
        }
        return pageHandle.getCanvas();
    }

    public PageStyle getPageStyle(){
        if(pageHandle==null){
            throw new NullPointerException("PageHandle can't be null");
        }
        return pageHandle.getPageStyle();
    }
}
