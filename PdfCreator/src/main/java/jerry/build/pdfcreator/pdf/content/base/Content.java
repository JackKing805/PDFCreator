package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.pdf.content.build.PageHandleHolder;

public class Content extends ContentParent {
    private static final String TAG = "ContentParent";
    //当前content的父亲
    private Content parent;

    public Content(ContentStyle contentStyle) {
        super(contentStyle);
    }


    @Override
    protected void measureDefault() {
        int widthMode = getWidthMode();
        int heightMode = getHeightMode();

        int measureWidth = 0;
        int measureHeight = 0;

        if (widthMode == ContentStyle.MATCH_PARENT) {
            measureWidth = getParentWidth() - getMarginLeft() - getMarginRight();
        } else if (widthMode == ContentStyle.WRAP_CONTENT) {

        } else if (widthMode == ContentStyle.SELF) {
            if(getWidth()+getMarginLeft()+getMarginRight()>getParentWidth()){
                measureWidth = getParentWidth()-getMarginLeft()-getMarginRight();
            }else if(getWidth()+getMarginLeft()+getMarginRight()<getParentWidth()){
                measureWidth = getWidth();
            }else if(getWidth()+getMarginLeft()>getParentWidth()){
                measureWidth = getParentWidth() - getMarginLeft();
            }else{
                measureWidth = getWidth();
            }
        }


        if (heightMode == ContentStyle.MATCH_PARENT) {
            measureHeight = getParentHeight() - getMarginTop() - getMarginBottom();
        } else if (heightMode == ContentStyle.WRAP_CONTENT) {


        } else if (heightMode == ContentStyle.SELF) {
            if(getHeight()+getMarginTop()+getMarginBottom()>getParentHeight()){
                measureHeight = getParentHeight()-getMarginTop()-getMarginBottom();
            }else if(getHeight()+getMarginTop()+getMarginBottom()<getParentHeight()){
                measureHeight = getHeight();
            }else if(getWidth()+getMarginTop()>getParentHeight()){
                measureHeight = getParentHeight() - getMarginTop();
            }else{
                measureHeight = getHeight();
            }
        }
        measure();
        setMeasureStyle(measureWidth, measureHeight);
        drawDefault(PageHandleHolder.newInstance().getCanvas());
    }

    @Override
    protected void drawDefault(Canvas canvas) {
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(getContentStyle().getBackgroundColor());
        int left = getParentMPLeft()+getMarginLeft();
        int top = getParentMPTop()+getMarginTop();
        int right = left+getWidth();
        int bottom = top+getHeight();
        System.out.println("__________________");
        System.out.println("________left:"+left);
        System.out.println("________top:"+top);
        System.out.println("________right:"+right);
        System.out.println("________bottom:"+bottom);
        Rect rect = new Rect(left,top,right,bottom);
        canvas.save();
        canvas.clipRect(rect);
        canvas.drawRect(rect,paint);
        draw(canvas);
        canvas.restore();
    }


    /**
     * 提供给子类自己测量的方法
     */
    protected void measure(){

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
            throw new IllegalStateException("can't get parent at root content");
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
        if (this.parent == null) {
            this.parent = parent;
        } else {
            throw new IllegalArgumentException("already set parent");
        }
    }
}
