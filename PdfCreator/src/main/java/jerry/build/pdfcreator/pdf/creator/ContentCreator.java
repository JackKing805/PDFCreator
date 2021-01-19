package jerry.build.pdfcreator.pdf.creator;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.model.DefaultTemplate;
import jerry.build.pdfcreator.pdf.content.build.ContentManager;

public class ContentCreator {
    private ContentManager contentManager;

    public ContentCreator(PageHandle pageHandle){
        contentManager = new ContentManager(pageHandle);
    }

    public void startCreateContent(DefaultTemplate defaultTemplate){
        defaultTemplate.createHeader(contentManager.getRootContent());
        defaultTemplate.createBody(contentManager.getRootContent());
        defaultTemplate.createFooter(contentManager.getRootContent());
    }
}
