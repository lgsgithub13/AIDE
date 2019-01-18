package demo.and.note.er;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class SimpleXFCActivity extends Activity implements View.OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_xfc_activity);
		Button b1,b2;
		b1=(Button)findViewById(R.id.simplexfcactivityButton1);
		b2=(Button)findViewById(R.id.simplexfcactivityButton2);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
	}
	@Override
	public void onClick(View p1)
	{
		Intent i=new Intent(SimpleXFCActivity.this,XFCService.class);
		switch(p1.getId())
		{
			case R.id.simplexfcactivityButton1:
				startService(i);
				break;
			case R.id.simplexfcactivityButton2:
				stopService(i);
				break;
		}
	}
}
