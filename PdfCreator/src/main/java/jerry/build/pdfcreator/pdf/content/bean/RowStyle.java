package jerry.build.pdfcreator.pdf.content.bean;

import androidx.annotation.ColorInt;

public class RowStyle extends ContentStyle {
    public RowStyle(int width, int height, int marginTop, int marginLeft, int marginRight, int marginBottom, int paddingTop, int paddingLeft, int paddingRight, int paddingBottom, int backgroundColor) {
        super(width, height, marginTop, marginLeft, marginRight, marginBottom,paddingTop,paddingLeft,paddingRight,paddingBottom,backgroundColor);
    }

    public RowStyle(int width, int height,@ColorInt int backgroundColor) {
        super(width, height, backgroundColor);
    }

    public RowStyle(int width, int height,int margin,int padding,int backgroundColor) {
        super(width, height,margin,padding, backgroundColor);
    }

    public RowStyle(int width, int height) {
        super(width, height);
    }
}
