package demo.and.note.er;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.main);
		super.onCreate(savedInstanceState);
		Button b1,b2;
		b1=(Button)findViewById(R.id.mainButton1);
		b2=(Button)findViewById(R.id.mainButton2);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater mi=getMenuInflater();
		mi.inflate(R.menu.main_menu,menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.item1:
				Toast.makeText(MainActivity.this,R.string.main_activity_1,Toast.LENGTH_LONG).show();
				return true;
			case R.id.item2:
				new AlertDialog.Builder(this)
				.setTitle(R.string.main_menu_2)
				.setMessage(R.string.main_activity_2)
				.show();
				return true;
		}
		return true;
	}
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId())
		{
			case R.id.mainButton1:
				Intent i=new Intent(MainActivity.this,DaKaiYanShiBiJiAc.class);
				startActivity(i);
				break;
			case R.id.mainButton2:
				break;
		}
	}
}
