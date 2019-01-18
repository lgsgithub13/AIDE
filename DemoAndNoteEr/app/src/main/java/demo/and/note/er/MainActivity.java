package demo.and.note.er;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
}
