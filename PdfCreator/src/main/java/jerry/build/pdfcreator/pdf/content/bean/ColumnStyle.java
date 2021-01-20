package jerry.build.pdfcreator.pdf.content.bean;

public class ColumnStyle extends ContentStyle {
    private int borderWidth = 0;
    private boolean haveVerticalBorder = false;

    public static class Builder {
        ColumnStyle contentStyle = new ColumnStyle();


        public Builder setWidthMode(int widthMode) {
            contentStyle.setWidthMode(widthMode);
            return this;
        }

        public Builder setHeightMode(int heightMode) {
            contentStyle.setHeightMode(heightMode);
            return this;
        }

        public Builder setWidth(int width) {
            contentStyle.setWidth(width);
            return this;
        }

        public Builder setHeight(int height) {
            contentStyle.setHeight(height);
            return this;
        }

        public Builder setMarginTop(int marginTop) {
            contentStyle.setMarginTop(marginTop);
            return this;
        }

        public Builder setMarginLeft(int marginLeft) {
            contentStyle.setMarginLeft(marginLeft);
            return this;
        }

        public Builder setMarginRight(int marginRight) {
            contentStyle.setMarginRight(marginRight);
            return this;
        }

        public Builder setMarginBottom(int marginBottom) {
            contentStyle.setMarginBottom(marginBottom);
            return this;
        }

        public Builder setPaddingTop(int paddingTop) {
            contentStyle.setPaddingTop(paddingTop);
            return this;
        }

        public Builder setPaddingLeft(int paddingLeft) {
            contentStyle.setPaddingLeft(paddingLeft);
            return this;
        }

        public Builder setPaddingRight(int paddingRight) {
            contentStyle.setPaddingRight(paddingRight);
            return this;
        }

        public Builder setPaddingBottom(int paddingBottom) {
            contentStyle.setPaddingBottom(paddingBottom);
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            contentStyle.setBackgroundColor(backgroundColor);
            return this;
        }

        private Builder setHaveVerticalBorder(boolean haveVerticalBorder) {
            contentStyle.setHaveVerticalBorder(haveVerticalBorder);
            return this;
        }

        private Builder setBorderWidth(int borderWidth) {
            contentStyle.setBorderWidth(borderWidth);
            return this;
        }


        public ColumnStyle create() {
            return contentStyle;
        }
    }

    private void setHaveVerticalBorder(boolean haveVerticalBorder) {
        this.haveVerticalBorder = haveVerticalBorder;
    }

    private void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }


    public int getBorderWidth() {
        return borderWidth;
    }

    public boolean isHaveVerticalBorder() {
        return haveVerticalBorder;
    }
}
