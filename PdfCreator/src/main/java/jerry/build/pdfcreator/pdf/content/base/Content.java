package jerry.build.pdfcreator.pdf.content.base;

import android.util.Log;

import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;

public class Content extends ContentParent {
    private static final String TAG = "ContentParent";
    //当前content的父亲
    private Content parent;

    public Content(ContentStyle contentStyle) {
        super(contentStyle);
    }


    @Override
    public void measureDefault() {
        int widthMode = getWidthMode();
        int heightMode = getHeightMode();

        int measureWidth = 0;
        int measureHeight = 0;

        if (widthMode == ContentStyle.MATCH_PARENT) {
            measureWidth = getWidth() - getParentMPLeft() - getParentMPRight();
        } else if (widthMode == ContentStyle.WRAP_CONTENT) {

        } else if (widthMode == ContentStyle.SELF) {
            measureWidth = getWidth();
        }


        if (heightMode == ContentStyle.MATCH_PARENT) {
            measureHeight = getHeight() - getParentMPTop() - getParentMPBottom();
        } else if (heightMode == ContentStyle.WRAP_CONTENT) {


        } else if (heightMode == ContentStyle.SELF) {
            measureHeight = getHeight();
        }

        setMeasureStyle(measureWidth,measureHeight);
    }

    @Override
    public void drawDefault() {

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
