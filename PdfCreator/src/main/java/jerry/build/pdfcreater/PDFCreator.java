package jerry.build.pdfcreater;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.RequiresApi;

import java.io.File;

import jerry.build.pdfcreater.bean.PageHandle;
import jerry.build.pdfcreater.bean.PageStyle;
import jerry.build.pdfcreater.listener.CreateListener;
import jerry.build.pdfcreater.model.DefaultTemplate;
import jerry.build.pdfcreater.pdf.creator.ContentCreator;
import jerry.build.pdfcreater.pdf.creator.PageCreator;

/**
 * 创建入口
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PDFCreator {

    /**
     * 整个创建的起点
     */
    public static void create(PageStyle pageStyle, DefaultTemplate defaultTemplate){
        create(pageStyle,defaultTemplate,null);
    }

    public static void create(PageStyle pageStyle, DefaultTemplate defaultTemplate, CreateListener createListener){
        new Thread(() -> {
            boolean hasListener = createListener!=null;
            //开始创建
            if(hasListener){
                new Handler(Looper.getMainLooper()).post(createListener::createStart);
            }

            //创建页面
            PageCreator pageCreator = new PageCreator(pageStyle);
            PageHandle pageHandle = pageCreator.createPage();

            //添加内容
            ContentCreator contentCreator = new ContentCreator(pageHandle);
            defaultTemplate.setPageHandle(pageHandle);
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
