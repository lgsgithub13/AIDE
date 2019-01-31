package demo.and.note.er;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class NotificationContentAc extends Activity implements View.OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_content_ac);
		Button bn=(Button)findViewById(R.id.notificationcontentacButton1);
		bn.setOnClickListener(this);
	}
	@Override
	public void onClick(View p1)
	{
		NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		//关闭通知栏通知
		nm.cancel(1);
	}
}
