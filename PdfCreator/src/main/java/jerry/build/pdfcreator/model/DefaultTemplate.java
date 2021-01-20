package jerry.build.pdfcreator.model;

import android.content.Context;

import jerry.build.pdfcreator.pdf.content.base.ContentGroup;
import jerry.build.pdfcreator.pdf.content.impl.Row;

/**
 * 内容创建模板
 */
public abstract class DefaultTemplate {
    /**
     * 添加头部
     */
    public abstract void createHeader(Row content);

    /**
     * 添加内容
     */
    public abstract void createBody(Row content);

    /**
     * 添加底部
     */
    public abstract void createFooter(Row content);
}
