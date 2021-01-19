package jerry.build.pdfcreator.bean;


/**
 * pdf 宽高
 */
public class PageSize {
    private int width;
    private int height;

    public PageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public PageSize(PageSize pageSize) {
        this.width = pageSize.getWidth();
        this.height = pageSize.getHeight();
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    /**
     * 默认size配置
     */
    public static final PageSize A3 = new PageSize(420,297);
    public static final PageSize A4 = new PageSize(297,210);
    public static final PageSize A5 = new PageSize(210,148);
    public static final PageSize A6 = new PageSize(144,105);
}
