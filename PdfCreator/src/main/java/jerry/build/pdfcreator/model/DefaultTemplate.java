package jerry.build.pdfcreator.model;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.base.ContentManager;

/**
 * 内容创建模板
 */
public abstract class DefaultTemplate {
    /**
     * 添加头部
     */
    public abstract void createHeader(ContentGroup content);

    /**
     * 添加内容
     */
    public abstract void createBody(ContentGroup content);

    /**
     * 添加底部
     */
    public abstract void createFooter(ContentGroup content);
}
