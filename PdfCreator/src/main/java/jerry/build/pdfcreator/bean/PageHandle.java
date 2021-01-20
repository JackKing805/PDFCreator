package jerry.build.pdfcreator.bean;

import android.content.Context;
import android.graphics.Canvas;

public class PageHandle {
    private PageStyle pageStyle;
    private Canvas canvas;
    private Context context;

    public PageHandle(PageStyle pageStyle,Canvas canvas,Context context) {
        this.pageStyle = pageStyle;
        this.canvas = canvas;
        this.context = context;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Context getContext() {
        return context;
    }

    public PageStyle getPageStyle() {
        return pageStyle;
    }

    public int getWidth() {
        return  pageStyle.getWidth();
    }

    public int getHeight() {
        return pageStyle.getHeight();
    }
}
