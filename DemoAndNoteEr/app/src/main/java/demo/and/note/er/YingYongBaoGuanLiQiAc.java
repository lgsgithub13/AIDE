package demo.and.note.er;
import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import android.text.*;

public class YingYongBaoGuanLiQiAc extends Activity implements View.OnClickListener,Runnable
{
	String apkPath;
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
			//文件选择器
			case R.id.yingyongbaoguanliqiButton1:
				Intent i=new Intent(YingYongBaoGuanLiQiAc.this,ApkSelectErAc.class);
				startActivityForResult(i,7);
				break;
			//ROOT安装
			case R.id.yingyongbaoguanliqiButton2:
				if(!isRoot())
				{
					Toast.makeText(YingYongBaoGuanLiQiAc.this,"该设备未ROOT",Toast.LENGTH_SHORT).show();
					return;
				}
				//TextUtils.isEmpty()用于判断字符串是否为空
				if(TextUtils.isEmpty(apkPath))
				{
					Toast.makeText(YingYongBaoGuanLiQiAc.this,"没有安装包，请选择安装包。",Toast.LENGTH_SHORT).show();
					return;
				}
				//开启一个线程用于ROOT安装
				new Thread(this).start();
				break;
			//打开辅助安装服务
			case R.id.yingyongbaoguanliqiButton3:
				Intent ii=new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
				startActivity(ii);
				break;
			//辅助安装
			case R.id.yingyongbaoguanliqiButton4:
				if(TextUtils.isEmpty(apkPath))
				{
					Toast.makeText(YingYongBaoGuanLiQiAc.this,"没有安装包，请选择安装包。",Toast.LENGTH_SHORT).show();
					return;
				}
				Intent iii=new Intent();
				Uri uri=Uri.fromFile(new File(apkPath));
				iii.setDataAndType(uri,"application/vnd.android.package-archive");
				startActivity(iii);
				break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==7&&resultCode==RESULT_OK)
		{
			apkPath=data.getStringExtra("APKPATH");
			tv.setText("APK安装包来源于："+"\n"+data.getStringExtra("APKPATH"));
		}
	}
	//判断设备root情况
	private boolean isRoot()
	{
		boolean result=false;
		try
		{
			//检查su文件是否存在，无代表root反之亦然。
			result=!new File("/system/bin/su").exists()|!new File("/system/xbin/su").exists();
		}
		catch(Exception e){e.getStackTrace();}
		return result;
	}
	//实现run方法
	@Override
	public void run()
	{
		RootInstaller ri=new RootInstaller();
		boolean result=ri.install(apkPath);
		if(result){Toast.makeText(YingYongBaoGuanLiQiAc.this,"安装完毕",Toast.LENGTH_LONG).show();}
		else{Toast.makeText(YingYongBaoGuanLiQiAc.this,"安装失败",Toast.LENGTH_LONG).show();}
	}
}
