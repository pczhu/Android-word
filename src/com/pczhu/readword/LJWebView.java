package com.pczhu.readword;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * 
 * 备注:
 * 此LJWebView继承自Relativielayout,�?��会导致丢失一个WebView的属性，如果大家
 * 在项目中�?��用到，可是此类中加入，然后调用即可，可参�?
 * 	public void setClickable(boolean value){
		mWebView.setClickable(value);
	}
 * 这个方法的定义和调用
 * 
 * @author Administrator
 *
 */
public class LJWebView extends RelativeLayout{
	
	public static int Circle = 0x01;
	public static int Horizontal = 0x02;
	
	private Context context;
	
	public WebView getmWebView() {
		return mWebView;
	}

	public void setmWebView(WebView mWebView) {
		this.mWebView = mWebView;
	}

	private WebView mWebView = null;  //
	private ProgressBar progressBar = null;  //水平进度�?
	private RelativeLayout progressBar_circle = null;  //包含圆形进度条的布局
	private int barHeight = 8;  //水平进度条的�?
	private boolean isAdd = false;  //判断是否已经加入进度�?
	private int progressStyle = Horizontal;  //进度条样�?Circle表示为圆形，Horizontal表示为水�?
	

	
	public LJWebView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public LJWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public LJWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}
	
	private void init(){
		mWebView = new WebView(context);
		this.addView(mWebView, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		
		mWebView.setWebChromeClient(new WebChromeClient(){

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				if(newProgress == 100){
					if(progressStyle == Horizontal){
						progressBar.setVisibility(View.GONE);
					}else{
						progressBar_circle.setVisibility(View.GONE);
					}
				}else{
					if(!isAdd){
						if(progressStyle == Horizontal){
							progressBar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.progress_horizontal, null);
							progressBar.setMax(100);
							progressBar.setProgress(0);
							LJWebView.this.addView(progressBar, LayoutParams.FILL_PARENT, barHeight);
						}
						isAdd = true;
					}
					
					if(progressStyle == Horizontal){
						progressBar.setVisibility(View.VISIBLE);
						progressBar.setProgress(newProgress);
					}else{
						progressBar_circle.setVisibility(View.VISIBLE);
					}
				}
			}
		});
	}
	
	public void setBarHeight(int height){
		barHeight = height;
	}
	
	public void setProgressStyle(int style){
		progressStyle = style;
	}
	
	public void setClickable(boolean value){
		mWebView.setClickable(value);
	}
	
	public void setUseWideViewPort(boolean value){
		mWebView.getSettings().setUseWideViewPort(value);
	}
	
	public void setSupportZoom(boolean value){
		mWebView.getSettings().setSupportZoom(value);
	}
	
	public void setBuiltInZoomControls(boolean value){
		mWebView.getSettings().setBuiltInZoomControls(value);
	}
	
	public void setJavaScriptEnabled(boolean value){
		mWebView.getSettings().setJavaScriptEnabled(value);
	}
	
	public void setCacheMode(int value){
		mWebView.getSettings().setCacheMode(value);
	}

	public void setWebViewClient(WebViewClient value){
		mWebView.setWebViewClient(value);
	}
	
	public void loadUrl(String url){
		mWebView.loadUrl(url);
	}

	public void setLoadWithOverviewMode(boolean flag){
		mWebView.getSettings().setLoadWithOverviewMode(flag);
	}
}
