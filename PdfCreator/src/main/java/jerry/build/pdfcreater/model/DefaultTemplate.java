package jerry.build.pdfcreater.model;

import jerry.build.pdfcreater.bean.PageHandle;
import jerry.build.pdfcreater.pdf.content.base.ContentManager;

/**
 * 内容创建模板
 */
public abstract class DefaultTemplate {
    private PageHandle pageHandle;
    protected ContentManager contentManager;

    public void setPageHandle(PageHandle pageHandle) {
        this.pageHandle = pageHandle;
        contentManager = new ContentManager(pageHandle);
    }

    /**
     * 添加头部
     */
    public abstract void createHeader();

    /**
     * 添加内容
     */
    public abstract void createBody();

    /**
     * 添加底部
     */
    public abstract void createFooter();
}
