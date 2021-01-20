package jerry.build.pdfcreator.pdf.content.bean;

import androidx.annotation.ColorInt;

public class RowStyle extends ContentStyle {
    private int borderWidth = 0;
    private boolean haveVerticalBorder = false;
    private boolean haveHorizontalBorder = false;

    public RowStyle(int width, int height, int marginTop, int marginLeft, int marginRight, int marginBottom, int paddingTop, int paddingLeft, int paddingRight, int paddingBottom, int backgroundColor,
                    int borderWidth,
                    boolean haveVerticalBorder,
                    boolean haveHorizontalBorder) {
        super(width, height, marginTop, marginLeft, marginRight, marginBottom, paddingTop, paddingLeft, paddingRight, paddingBottom, backgroundColor);
        this.borderWidth = borderWidth;
        this.haveVerticalBorder = haveVerticalBorder;
        this.haveHorizontalBorder = haveHorizontalBorder;
    }

    public RowStyle(int width, int height, @ColorInt int backgroundColor,
                    int borderWidth,
                    boolean haveVerticalBorder,
                    boolean haveHorizontalBorder) {
        super(width, height, backgroundColor);
        this.borderWidth = borderWidth;
        this.haveVerticalBorder = haveVerticalBorder;
        this.haveHorizontalBorder = haveHorizontalBorder;
    }

    public RowStyle(int width, int height, int margin, int padding, int backgroundColor,
                    int borderWidth,
                    boolean haveVerticalBorder,
                    boolean haveHorizontalBorder) {
        super(width, height, margin, padding, backgroundColor);
        this.borderWidth = borderWidth;
        this.haveVerticalBorder = haveVerticalBorder;
        this.haveHorizontalBorder = haveHorizontalBorder;
    }


    public RowStyle(int width, int height, int margin, int padding,
                    int borderWidth,
                    boolean haveVerticalBorder,
                    boolean haveHorizontalBorder) {
        super(width, height, margin, padding);
        this.borderWidth = borderWidth;
        this.haveVerticalBorder = haveVerticalBorder;
        this.haveHorizontalBorder = haveHorizontalBorder;
    }

    public RowStyle(int width, int height,
                    int borderWidth,
                    boolean haveVerticalBorder,
                    boolean haveHorizontalBorder) {
        super(width, height);
        this.borderWidth = borderWidth;
        this.haveVerticalBorder = haveVerticalBorder;
        this.haveHorizontalBorder = haveHorizontalBorder;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public boolean isHaveVerticalBorder() {
        return haveVerticalBorder;
    }

    public boolean isHaveHorizontalBorder() {
        return haveHorizontalBorder;
    }
}
