package jerry.build.pdfcreator.pdf.content.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import jerry.build.pdfcreator.bean.PageStyle;
import jerry.build.pdfcreator.pdf.content.bean.ContentStyle;
import jerry.build.pdfcreator.bean.PageHandle;

public class ContentGroup extends Content{

    public ContentGroup(ContentStyle contentStyle) {
        super(contentStyle);
    }
}
