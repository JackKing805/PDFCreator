package com.cdp.pdfdocumentdemo;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

import jerry.build.pdfcreator.PDFCreator;
import jerry.build.pdfcreator.bean.PageStyle;
import jerry.build.pdfcreator.listener.CreateListener;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    FloatingActionButton button;
    BaseDialog baseDialog;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        button = findViewById(R.id.button);
        initWebView();
        baseDialog = new BaseDialog.Builder(this,R.layout.loading_layout)
                .setCancelAble(false)
                .setCanTouchOutSide(false)
                .setGravity(Gravity.CENTER)
                .build();


        button.setOnClickListener(v -> webView.post(() -> PDFCreator.create(new PageStyle(
                        webView.getWidth(),
                        webView.getHeight(),
                        1,
                        getApplicationContext().getFilesDir().getAbsolutePath() + "/pdf/demo.pdf"),
                new TestTemplate(),
                this,
                new CreateListener() {
                    @Override
                    public void createStart() {
                        baseDialog.show();
                    }

                    @Override
                    public void createSuccess(File file) {
                        loadPdfUrl(file.getAbsolutePath());
                        baseDialog.dismiss();
                    }

                    @Override
                    public void createError(Exception e) {
                        baseDialog.dismiss();
                    }
                })));
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        /**
         * 简单来说，该项设置决定了JavaScript能否访问来自于任何源的文件标识的URL。
         * 比如我们把PDF.js放在本地assets里，然后通过一个URL跳转访问来自于任何URL的PDF，所以这里我们需要将其设置为true。
         * 而一般情况下，为了安全起见，是需要将其设置为false的。
         */
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);//不显示那个丑东西
    }

    private void loadPdfUrl(String pdfPath) {
        webView.loadUrl("file:///android_asset/pdf_display_config/index.html?"+pdfPath);
    }
}