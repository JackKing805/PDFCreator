package jerry.build.pdfcreator;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.RequiresApi;

import java.io.File;

import jerry.build.pdfcreator.bean.PageHandle;
import jerry.build.pdfcreator.bean.PageStyle;
import jerry.build.pdfcreator.listener.CreateListener;
import jerry.build.pdfcreator.model.DefaultTemplate;
import jerry.build.pdfcreator.pdf.creator.ContentCreator;
import jerry.build.pdfcreator.pdf.creator.PageCreator;

/**
 * 创建入口
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PDFCreator {

    /**
     * 整个创建的起点
     */
    public static void create(PageStyle pageStyle, DefaultTemplate defaultTemplate, Context context){
        create(pageStyle,defaultTemplate,context,null);
    }

    public static void create(PageStyle pageStyle, DefaultTemplate defaultTemplate, Context context, CreateListener createListener){
        new Thread(() -> {
            boolean hasListener = createListener!=null;
            //开始创建
            if(hasListener){
                new Handler(Looper.getMainLooper()).post(createListener::createStart);
            }

            //创建页面
            PageCreator pageCreator = new PageCreator(pageStyle);
            pageCreator.startCreate(context);

            //添加内容
            ContentCreator contentCreator = new ContentCreator();
            contentCreator.startCreateContent(defaultTemplate);

            //保存pdf
            File saveFile = pageCreator.savePage();
            if(saveFile!=null){
                //创建成功
                if(hasListener){
                    new Handler(Looper.getMainLooper()).post(() -> createListener.createSuccess(saveFile));
                }
            }else{
                //创建失败
                if(hasListener){
                    new Handler(Looper.getMainLooper()).post(() -> createListener.createError(new NullPointerException("Error in creation")));
                }
            }
        }).start();
    }

}
