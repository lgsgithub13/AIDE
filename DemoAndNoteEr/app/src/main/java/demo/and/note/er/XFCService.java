package demo.and.note.er;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.view.WindowManager.*;
import android.widget.*;
import java.text.*;
import java.util.*;

public class XFCService extends Service
{
	//申明相应变量
	WindowManager wm;
	LinearLayout lL;
	WindowManager.LayoutParams wmlP;
	Button b;
	//
	@Override
	public IBinder onBind(Intent p1)
	{return null;}
	@Override
	public void onCreate()
	{
		super.onCreate();
		//获取相应对象
		wm=(WindowManager)getSystemService(WINDOW_SERVICE);
		lL=(LinearLayout)LayoutInflater.from(getApplication()).inflate(R.layout.xfc_service,null);
		wmlP=new WindowManager.LayoutParams();
		//设置悬浮参数
		wmlP.type=LayoutParams.TYPE_PHONE;
		wmlP.flags=LayoutParams.FLAG_NOT_FOCUSABLE;
		wmlP.format=PixelFormat.RGBA_8888;
		wmlP.gravity=Gravity.TOP|Gravity.LEFT;
		wmlP.x=0;
		wmlP.y=0;
		wmlP.width=WindowManager.LayoutParams.WRAP_CONTENT;
		wmlP.height=WindowManager.LayoutParams.WRAP_CONTENT;
		//添加悬浮窗
		wm.addView(lL,wmlP);
		//悬浮按钮
		b=(Button)lL.findViewById(R.id.xfcserviceButton1);
		//点击悬浮按钮
		b.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//获取当前时间
				SimpleDateFormat sdf=new SimpleDateFormat("yy年MM月ˇdd号、HH:mm:ss");
				Date date=new Date(System.currentTimeMillis());
				String chiShi=sdf.format(date);
				String[] s=(chiShi.trim()).split("、");
				String ss=s[0];
				String[] sss=ss.split("ˇ");
				String ssss=sss[1];
				//复制当前时间到剪切板
				ClipboardManager cm=(ClipboardManager)getApplication().getSystemService(getApplication().CLIPBOARD_SERVICE);
				cm.setText("此时:"+ssss+"?☀"+s[1]+"🌙"+sss[0]);
				Toast.makeText(getApplication(),"当前时间为："+"\n"+chiShi+"\n"+"当前时间已复制到剪切板",Toast.LENGTH_LONG).show();
			}
		});
		//？lL.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED));
		//触摸悬浮按钮
		b.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v,MotionEvent me)
			{
				wmlP.x=(int)me.getRawX()-b.getMeasuredWidth()/2;
				wmlP.y=(int)me.getRawY()-b.getMeasuredHeight()/2-25;
				wm.updateViewLayout(lL,wmlP);
				return false;
			}
		});
	}
	@Override
	public void onDestroy()
	{
		//服务销毁移除悬浮窗
		super.onDestroy();
		wm.removeView(lL);
	}
}
