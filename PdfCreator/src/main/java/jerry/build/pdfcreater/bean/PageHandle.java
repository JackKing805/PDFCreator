package jerry.build.pdfcreater.bean;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PageHandle {
    private PageStyle pageStyle;
    private Canvas canvas;

    public PageHandle(PageStyle pageStyle,Canvas canvas) {
        this.pageStyle = pageStyle;
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
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
