package demo.and.note.er;
import android.app.*;
import android.content.*;
import android.os.*;
import android.webkit.*;

public class WebViewDemoAc extends Activity
{
	String url;
	/*
	无效声明
	Intent i=new Intent();
	url=i.getStringExtra("url");
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_demo);
		//获取网址
		//Intent i=new Intent();
		Intent i=getIntent();
		url=i.getStringExtra("url");
		WebView wv=(WebView)findViewById(R.id.webviewdemoWebView1);
		/*
		未知错误
		 wv.getSettings();
		 wv.setJavaScriptEnabled(true);
		*/
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient());
		wv.loadUrl(url);
	}
}
