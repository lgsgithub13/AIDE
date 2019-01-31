package demo.and.note.er;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import okhttp3.*;
import java.io.*;

public class ServerRequestAc extends Activity implements View.OnClickListener
{
	TextView tv;
	String url="https://m.hao123.com?vit=h123&from=3w123";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.server_request_ac);
		tv=(TextView)findViewById(R.id.serverrequestacTextView1);
		Button b1,b2,b3,b4;
		b1=(Button)findViewById(R.id.serverrequestacButton1);
		b2=(Button)findViewById(R.id.serverrequestacButton2);
		b3=(Button)findViewById(R.id.serverrequestacButton3);
		b4=(Button)findViewById(R.id.serverrequestacButton4);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
	}
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId())
		{
			case R.id.serverrequestacButton1:
				break;
			case R.id.serverrequestacButton2:
				break;
			case R.id.serverrequestacButton3:
				try{useOkHttpClient(url);}
				catch (IOException e){e.printStackTrace();}
				break;
			case R.id.serverrequestacButton4:
				break;
		}
	}
	protected void useOkHttpClient(String url) throws IOException
	{
		OkHttpClient ohc=new OkHttpClient();
		Request request=new Request.Builder()
			.url(url)
			.build();
		Response response=ohc.newCall(request).execute();
		String shuju=response.body().string();
		upDateTextView(shuju);
	}
	protected void upDateTextView(final String textView)
	{
		runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				tv.setText(textView);
			}
		});
	}
}
