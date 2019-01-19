package demo.and.note.er;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class YingYongBaoGuanLiQiAc extends Activity implements View.OnClickListener
{
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ying_yong_bao_guan_li_qi);
		tv=(TextView)findViewById(R.id.yingyongbaoguanliqiTextView1);
		Button b1,b2,b3,b4;
		b1=(Button)findViewById(R.id.yingyongbaoguanliqiButton1);
		b2=(Button)findViewById(R.id.yingyongbaoguanliqiButton2);
		b3=(Button)findViewById(R.id.yingyongbaoguanliqiButton3);
		b4=(Button)findViewById(R.id.yingyongbaoguanliqiButton4);
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
			case R.id.yingyongbaoguanliqiButton1:
				Intent i=new Intent(YingYongBaoGuanLiQiAc.this,ApkSelectErAc.class);
				startActivityForResult(i,7);
				break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==7&&resultCode==RESULT_OK)
		{
			tv.setText("APK安装包来源于："+"\n"+data.getStringExtra("APKPATH"));
		}
	}
}
