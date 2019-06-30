package com.darkbluesharp.searchsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 참고사이트1 : https://redmuffler.tistory.com/449
 * */

public class MainActivity extends AppCompatActivity {

    WebView webView, webView2;
    Button search_btn;
    EditText search_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview1);
        search_btn = (Button) findViewById(R.id.search_btn);
        search_text = (EditText) findViewById(R.id.search_text);

        WebSettings webSettings = webView.getSettings(); //websetting은 캐시, 자바스크립트 등의 기능 설정
        webSettings.setAppCacheEnabled(true);

        //키보드 감추기
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }

        search_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                webView.loadUrl( "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+search_text.getText().toString());
                webView2.loadUrl("https://www.google.com/search?q="+search_text.getText().toString());

                //키보드 감추기
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        });


        webView.setWebViewClient(new WebViewClient() { //새 창이 뜨는것을 방지
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

/*        webView.setOnTouchListener(new View.OnTouchListener(){ //터치이벤트 구현부
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getApplicationContext(), "click되었습니다.", Toast.LENGTH_LONG).show();
                return false;
            }

        });*/

        webView.loadUrl("https://m.naver.com/");//원하는 사이트 주소와 이름

        //키보드 감추기
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }


        webView2 = (WebView) findViewById(R.id.webview2);

        WebSettings webSettings2 = webView2.getSettings(); //websetting은 캐시, 자바스크립트 등의 기능 설정
        webSettings2.setAppCacheEnabled(true);


        webView2.setWebViewClient(new WebViewClient() { //새 창이 뜨는것을 방지
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

       /* webView2.setOnTouchListener(new View.OnTouchListener(){ //터치이벤트 구현부
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getApplicationContext(), "click되었습니다.", Toast.LENGTH_LONG).show();
                return false;
            }

        });*/

        webView2.loadUrl("https://www.google.com/");//원하는 사이트 주소와 이름

        //키보드 감추기
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
