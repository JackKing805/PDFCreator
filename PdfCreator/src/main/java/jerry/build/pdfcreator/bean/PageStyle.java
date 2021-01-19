package jerry.build.pdfcreator.bean;

public class PageStyle {
    private int width;
    private int height;
    private int pageSum;
    private String savePath;

    public PageStyle(int width, int height, int pageSum, String savePath) {
        this.width = width;
        this.height = height;
        if(pageSum<1){
            this.pageSum = 1;
        }else{
            this.pageSum = pageSum;
        }
        this.savePath = savePath;
    }

    public PageStyle(PageSize pageSize, int pageSum, String savePath) {
        this.width = pageSize.getWidth();
        this.height = pageSize.getHeight();
        if(pageSum<1){
            this.pageSum = 1;
        }else{
            this.pageSum = pageSum;
        }
        this.savePath = savePath;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPageSum() {
        return pageSum;
    }

    public String getSavePath() {
        return savePath;
    }
}
