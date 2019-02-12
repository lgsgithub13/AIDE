package demo.and.note.er;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import java.util.*;

import android.support.v7.appcompat.R;

public class DaKaiYanShiBiJiAc extends Activity implements AdapterView.OnItemClickListener
{
	ArrayList<String> list=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.da_kai_yan_shi_bi_ji);
		ListView lv=(ListView)findViewById(R.id.dakaiyanshibijiListView1);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(DaKaiYanShiBiJiAc.this,android.R.layout.simple_list_item_1,list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		list.add("应用包安装管理器MiNi-190119");
		list.add("一个简单的悬浮窗-190118");
		list.add("一个简单的数据库-190129");
		list.add("通知栏示例演示-190131");
		list.add("向服务器请求数据方式-190131");
		list.add("显示已安装全部应用-190212");
	}
	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
	{
		switch((String)p1.getItemAtPosition(p3))
		{
			case "一个简单的悬浮窗-190118":
				Intent i=new Intent(DaKaiYanShiBiJiAc.this,SimpleXFCActivity.class);
				startActivity(i);
				break;
			case "应用包安装管理器MiNi-190119":
				Intent ii=new Intent(DaKaiYanShiBiJiAc.this,YingYongBaoGuanLiQiAc.class);
				startActivity(ii);
				break;
			case "一个简单的数据库-190129":
				//int 不能为null
				SimpleDataBase sdb=new SimpleDataBase(this,"data.db",null,1);
				//错误写法sdb.getSQLiteWriteable();
				sdb.getWritableDatabase();
				Toast.makeText(DaKaiYanShiBiJiAc.this,R.string.da_kai_yan_shi_bi_ji,Toast.LENGTH_LONG).show();
				break;
			case "通知栏示例演示-190131":
				showNotify();
				break;
			case "向服务器请求数据方式-190131":
				Intent iii=new Intent(DaKaiYanShiBiJiAc.this,ServerRequestAc.class);
				startActivity(iii);
				break;
			case "显示已安装全部应用-190212":
				Intent iiii=new Intent(DaKaiYanShiBiJiAc.this,ShowInstalledAc.class);
				startActivity(iiii);
				break;
		}
	}
	//显示通知栏通知
	protected void showNotify()
	{
		//通知意图PendingIntent(不是Pading)
		Intent i=new Intent(DaKaiYanShiBiJiAc.this,NotificationContentAc.class);
		PendingIntent pi=PendingIntent.getActivity(DaKaiYanShiBiJiAc.this,1,i,1);
		NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification nn=new Notification.Builder(DaKaiYanShiBiJiAc.this)
		//setSmallcon必须有、、、
		.setSmallIcon(R.drawable.unknown)
		.setContentIntent(pi)
		.setContentTitle("通知标题")
		.setContentText("通知内容")
		.build();
		nm.notify(1,nn);
	}
}
