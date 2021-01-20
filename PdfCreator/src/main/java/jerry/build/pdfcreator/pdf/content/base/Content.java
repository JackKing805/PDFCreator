package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;

public class Content extends ContentParent {
    private static final String TAG = "Content";
    //当前content的父亲
    private Content parent;

    public Content(ContentStyle contentStyle) {
        super(contentStyle);
    }


    @Override
    public void measureDefault() {
//        Log.e(TAG, "measureDefault: "+toString() );
        int widthMode = getWidthMode();
        int heightMode = getHeightMode();

        int measureWidth = 0;
        int measureHeight = 0;

        if (widthMode == ContentStyle.MATCH_PARENT) {
            measureWidth = getParentWidth() - getMarginLeft() - getMarginRight()- getParent().getPaddingLeft()-getParent().getPaddingRight();
        } else if (widthMode == ContentStyle.WRAP_CONTENT) {
            measureWidth = getWidth();
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
            measureHeight = getHeight();
        } else if (heightMode == ContentStyle.SELF) {
            if(getHeight()+getMarginTop()+getParent().getPaddingTop()>getParentHeight()-getMarginBottom()-getParent().getPaddingTop()){
                measureHeight = getParentHeight() - getMarginTop() - getMarginBottom()- getParent().getPaddingTop()-getParent().getPaddingBottom();
            }else{
                measureHeight = getHeight();
            }
        }
        setMeasureStyle(measureWidth, measureHeight);
        measure(widthMode,heightMode,getWidth(),getHeight());
        drawDefault(PageHandleHolder.newInstance().getCanvas());
    }

    @Override
    public void drawDefault(Canvas canvas) {
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(getContentStyle().getBackgroundColor());
        int left = getParentMPLeft()+getMarginLeft();
        int top = getParentMPTop()+getMarginTop();

        canvas.save();
        canvas.translate(left,top);

        int right = getWidth();
        int bottom = getHeight();

        Rect rect = new Rect(0,0,right,bottom);
        canvas.clipRect(rect);
        canvas.drawRect(rect,paint);
        draw(canvas);
        canvas.restore();
    }


    /**
     * 提供给子类自己测量的方法
     */
    protected void measure(int widthMode, int heightMode,int height,int width){

    }

    /**
     * 提供给子类自己绘制的方法
     */
    protected void draw(Canvas canvas){
    }

    /**
     * 判断是否是根节点
     *
     * @return true 是   ,false 不是
     */
    protected boolean isRootContent() {
        return parent == null;
    }

    /**
     * 拿到父节点
     */
    protected Content getParent() {
        if (isRootContent()) {
            return this;
        } else {
            return parent;
        }
    }

    /**
     * 获得父节点左边margin和padding
     */
    protected int getParentMPLeft() {
        if (isRootContent()) {
            return 0;
        } else {
            int length = 0;
            Content parent = getParent();
            while (!parent.isRootContent()) {
                length = length + parent.getContentStyle().getMarginLeft() + parent.getContentStyle().getPaddingLeft();
                parent = parent.getParent();
            }
            return length;
        }
    }

    /**
     * 获得所有父节点右边margin和padding
     */
    protected int getParentMPRight() {
        if (isRootContent()) {
            return 0;
        } else {
            int length = 0;
            Content parent = getParent();
            while (!parent.isRootContent()) {
                length = length + parent.getContentStyle().getMarginRight() + parent.getContentStyle().getPaddingRight();
                parent = parent.getParent();
            }
            return length;
        }
    }

    /**
     * 获得所有父节点顶部margin和padding
     */
    protected int getParentMPTop() {
        if (isRootContent()) {
            return 0;
        } else {
            int length = 0;
            Content parent = getParent();
            while (!parent.isRootContent()) {
                length = length + parent.getContentStyle().getMarginTop() + parent.getContentStyle().getPaddingTop();
                parent = parent.getParent();
            }
            return length;
        }
    }

    /**
     * 获得所有父节点底部margin和padding
     */
    protected int getParentMPBottom() {
        if (isRootContent()) {
            return 0;
        } else {
            int length = 0;
            Content parent = getParent();
            while (!parent.isRootContent()) {
                length = length + parent.getContentStyle().getMarginBottom() + parent.getContentStyle().getPaddingBottom();
                parent = parent.getParent();
            }
            return length;
        }
    }

    /**
     * 拿到父控件的宽度
     *
     * @return
     */
    protected int getParentWidth() {
        if (isRootContent()) {
            return getWidth();
        } else {
            return getParent().getWidth();
        }
    }

    /**
     * 拿到父控件的高度
     *
     * @return
     */
    protected int getParentHeight() {
        if (isRootContent()) {
            return getHeight();
        } else {
            return getParent().getHeight();
        }
    }

    /**
     * 为当前节点设置父节点
     */
    protected void setParent(Content parent) {
        this.parent = parent;
    }
}
