package jerry.build.pdfcreator.pdf.content.bean;

public class ColumnStyle extends ContentStyle {
    public ColumnStyle(int width, int height, int marginTop, int marginLeft, int marginRight, int marginBottom, int paddingTop, int paddingLeft, int paddingRight, int paddingBottom, int backgroundColor) {
        super(width, height, marginTop, marginLeft, marginRight, marginBottom,paddingTop,paddingLeft,paddingRight,paddingBottom,backgroundColor);
    }

    public ColumnStyle(int width, int height,int backgroundColor) {
        super(width, height, backgroundColor);
    }

    public ColumnStyle(int width, int height,int margin,int padding,int backgroundColor) {
        super(width, height,margin,padding, backgroundColor);
    }

    public ColumnStyle(int width, int height,int margin,int padding) {
        super(width, height,margin,padding);
    }

    public ColumnStyle(int width, int height) {
        super(width, height);
    }}
