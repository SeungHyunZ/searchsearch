package com.darkbluesharp.searchsearch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/*
 * 참고사이트1 : https://redmuffler.tistory.com/449
 * */

public class MainActivity extends AppCompatActivity {

    WebView webView, webView2;
    Button search_btn, up_btn, down_btn, back_btn;
    EditText search_text;
    LinearLayout adsLinear, adsLinear2;

    private long backBtnTime = 0;
    private int webviewStatus = 0; // 0:중립, 1:위로, 2:아래로
    private Boolean adStatus = false;

   // private View   decorView;
   // private int    uiOption;

    private AdView mAdview, mAdview2; //애드뷰 변수 선언 //

/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        switch(keyCode){


            case KeyEvent.KEYCODE_BACK :

                Log.e("뒤로가기 버튼 눌름",keyCode+"");

                break;


        }


        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SoftKeyboardDectectorView softKeyboardDecector = new SoftKeyboardDectectorView(this);
        addContentView(softKeyboardDecector, new FrameLayout.LayoutParams(-1, -1));

  /*      softKeyboardDecector.setOnShownKeyboard(new SoftKeyboardDectectorView.OnShownKeyboardListener() {
            @Override
            public void onShowSoftKeyboard() {
                //키보드 등장할 때 채팅
            }

        });*/

        softKeyboardDecector.setOnHiddenKeyboard(new SoftKeyboardDectectorView.OnHiddenKeyboardListener(){
            @Override
            public void onHiddenSoftKeyboard() {
                //키보드 등장할 때 채팅
                Log.e("keboard show", "haha");

                if(adStatus){
                    hideAd();
                }

            }
        });




        MobileAds.initialize(this, new OnInitializationCompleteListener() { //광고 초기화
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdview = findViewById(R.id.adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        mAdview2 = findViewById(R.id.adView2); //배너광고 레이아웃 가져오기
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdview2.loadAd(adRequest2);

     /*   AdView adView = new AdView(this);
        adView.setAdSize(AdSize.LARGE_BANNER); //광고 사이즈는 배너 사이즈로 설정
        adView.setAdUnitId("\n"+"ca-app-pub-3940256099942544/6300978111");*/

       //ca-app-pub-8558357952484896/6048077643 real
        //ca-app-pub-3940256099942544/6300978111 test




    //    doFullScreen();

      //  showSystemUI();

        adsLinear = findViewById(R.id.adsLinear);
        adsLinear2 = findViewById(R.id.adsLinear2);
        webView = (WebView) findViewById(R.id.webview1);
        search_text = (EditText) findViewById(R.id.search_text);


        WebSettings webSettings = webView.getSettings(); //websetting은 캐시, 자바스크립트 등의 기능 설정
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);


        //키보드 감추기
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }

/*        search_btn.setOnClickListener(new Button.OnClickListener() {
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
        });*/
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
        webSettings2.setJavaScriptEnabled(true);
        webSettings2.setBuiltInZoomControls(true);
        webSettings2.setSupportZoom(true);


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



        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TextView textView1 = (TextView) findViewById(R.id.textView1);
                switch (view.getId()) {
                    case R.id.search_btn :

                        searchButtonClick();

                        break ;
                    case R.id.up_btn :

                        if(webviewStatus==0){
                            webView.setVisibility(View.GONE);
                            webView2.setVisibility(View.VISIBLE);
                            webviewStatus=1;
                            up_btn.setVisibility(View.GONE);
                            down_btn.setVisibility(View.VISIBLE);
                        }else if(webviewStatus==1){

                        }else if(webviewStatus==2){
                            webView.setVisibility(View.VISIBLE);
                            webView2.setVisibility(View.VISIBLE);
                            webviewStatus=0;
                            up_btn.setVisibility(View.VISIBLE);
                            down_btn.setVisibility(View.VISIBLE);
                        }

                        break ;
                    case R.id.down_btn :

                        if(webviewStatus==0){
                            webView.setVisibility(View.VISIBLE);
                            webView2.setVisibility(View.GONE);
                            webviewStatus=2;
                            up_btn.setVisibility(View.VISIBLE);
                            down_btn.setVisibility(View.GONE);
                        }else if(webviewStatus==1){
                            webView.setVisibility(View.VISIBLE);
                            webView2.setVisibility(View.VISIBLE);
                            webviewStatus=0;
                            up_btn.setVisibility(View.VISIBLE);
                            down_btn.setVisibility(View.VISIBLE);
                        }else if(webviewStatus==2){

                        }

                        break ;
                    case R.id.back_btn :
                        onBackPressed();
                        break ;
                }
            }
        } ;

        search_btn = (Button) findViewById(R.id.search_btn);
        search_btn.setOnClickListener(onClickListener) ;

        up_btn = (Button) findViewById(R.id.up_btn);
        up_btn.setOnClickListener(onClickListener) ;

        down_btn = (Button) findViewById(R.id.down_btn);
        down_btn.setOnClickListener(onClickListener) ;

        back_btn = (Button) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(onClickListener) ;




        search_text.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        {
                            Log.e("onTouch","ACTION_DOWN");
                         if(!adStatus) {
                             //광고 올리기
                             openAd();
                         }
                            return false;
                    }

                    case MotionEvent.ACTION_MOVE:{
                        Log.e("onTouch","ACTION_MOVE");
                        if(!adStatus) {
                            //광고 올리기
                            openAd();
                        }

                        return false;
                    }

                }
                return false;
            }
        });

//adsLinear
        adsLinear.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    {
                        Log.e("onTouch","ACTION_DOWN");
                        if(adStatus) {
                            //광고 올리기
                            hideAd();
                        }
                        return false;
                    }

               /*     case MotionEvent.ACTION_MOVE:{
                        Log.e("onTouch","ACTION_MOVE");
                        if(!adStatus) {
                            //광고 올리기
                            hideAd();
                        }

                        return true;
                    }*/

                }
                return false;
            }
        });

        adsLinear2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    {
                        Log.e("onTouch","ACTION_DOWN");
                        if(adStatus) {
                            //광고 올리기
                            hideAd();
                        }
                        return false;
                    }

          /*          case MotionEvent.ACTION_MOVE:{
                        Log.e("onTouch","ACTION_MOVE");
                        if(!adStatus) {
                            //광고 올리기
                            hideAd();
                        }

                        return true;
                    }*/

                }
                return false;
            }
        });

      /*  webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    {
                        Log.e("onTouch","ACTION_DOWN");
                        if(adStatus) {
                            //광고 올리기
                            hideAd();
                        }
                        return false;
                    }

       *//*             case MotionEvent.ACTION_MOVE:{
                        Log.e("onTouch","ACTION_MOVE");
                        if(adStatus) {
                            //광고 올리기
                            hideAd();
                        }

                        return false;
                    }*//*

                }
                return false;
            }
        });*/

     /*   webView2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    {
                        Log.e("onTouch","ACTION_DOWN");
                        if(adStatus) {
                            //광고 올리기
                            hideAd();
                        }
                        return false;
                    }

   *//*                 case MotionEvent.ACTION_MOVE:{
                        Log.e("onTouch","ACTION_MOVE");
                        if(adStatus) {
                            //광고 올리기
                            hideAd();
                        }

                        return false;
                    }*//*

                }
                return false;
            }
        });*/

        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchButtonClick();
                    return true;
                }
                return false;
            }
        });

        //키보드 감추기
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }





    }

    public void hideAd(){
        adStatus = false;
        //Keyboard : Hide
        //광고뷰 닫기
        Log.e("keboard hide", "haha");
        adsLinear.setVisibility(View.GONE);
        adsLinear2.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        webView2.setVisibility(View.VISIBLE);
        //   webView2.setVisibility(View.VISIBLE);
    }

    public void  openAd(){
        adStatus = true;
        Log.e("onTouch", "ACTION_DOWN");
        //터치했을 때의 이벤트

        //광고뷰 열기
        adsLinear.setVisibility(View.VISIBLE);
        adsLinear2.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);
        webView2.setVisibility(View.GONE);
        //  webView2.setVisibility(View.GONE);

        //화면 가운데로 세팅
        //   webView.setVisibility(View.VISIBLE);
        //webView2.setVisibility(View.VISIBLE);
        webviewStatus = 0;
        up_btn.setVisibility(View.VISIBLE);
        down_btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;
        WebView backWebView;

        if(webviewStatus==0){
            if (0 <= gapTime && 1000 >= gapTime) {
                super.onBackPressed();
            } else {
                backBtnTime = curTime;
                Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            }
        }else{
            if(webviewStatus==1){
                backWebView = webView2;
            }else{
                backWebView = webView;
            }


            if (backWebView.canGoBack()) {
                backWebView.goBack();
            } else if (0 <= gapTime && 1000 >= gapTime) {
                super.onBackPressed();
            } else {
                backBtnTime = curTime;
                Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            }
        }


        //키보드 감지
       // setListenerToKeyboard();

    }

    private void searchButtonClick(){

        adStatus = false;
        //광고뷰 닫기
        adsLinear.setVisibility(View.GONE);
        adsLinear2.setVisibility(View.GONE);


        //화면 가운데로 세팅
        webView.setVisibility(View.VISIBLE);
        webView2.setVisibility(View.VISIBLE);
        webviewStatus=0;
        up_btn.setVisibility(View.VISIBLE);
        down_btn.setVisibility(View.VISIBLE);

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


    private void doFullScreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE|
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    /*public void setListenerToKeyboard()
    {
        final View activityRootView = getWindow().getDecorView().findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                if(!adStatus) {
                    int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();

                    Log.e("keboard heightDiff", heightDiff + "");

                    if (heightDiff > 400) {
                        //Keyboard : Show
                        Log.e("keboard show", "haha");
                    } else {
                        hideAd();
                    }
                }
            }
        });
    }*/
}
