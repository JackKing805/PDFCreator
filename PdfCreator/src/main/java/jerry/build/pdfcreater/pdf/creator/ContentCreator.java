package jerry.build.pdfcreater.pdf.creator;

import jerry.build.pdfcreater.bean.PageHandle;
import jerry.build.pdfcreater.model.DefaultTemplate;

public class ContentCreator {
    private PageHandle pageHandle;

    public ContentCreator(PageHandle pageHandle) {
        this.pageHandle = pageHandle;
    }

    public void startCreateContent(DefaultTemplate defaultTemplate){
        defaultTemplate.createHeader();
        defaultTemplate.createBody();
        defaultTemplate.createFooter();
    }

}
