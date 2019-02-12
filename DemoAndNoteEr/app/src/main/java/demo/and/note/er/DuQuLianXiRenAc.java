package demo.and.note.er;
import android.app.*;
import android.content.*;
import android.database.*;
import android.os.*;
import android.provider.*;
import android.widget.*;
import java.util.*;

public class DuQuLianXiRenAc extends Activity
{
	ListView lv;
	List<String>lianxiren=new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.du_qu_lian_xi_ren_ac);
		lv=(ListView)findViewById(R.id.duqulianxirenacListView1);
		ArrayAdapter<String> adapter=new ArrayAdapter(DuQuLianXiRenAc.this,android.R.layout.simple_list_item_1,lianxiren);
		lv.setAdapter(adapter);
		ContentResolver cr=getContentResolver();
		//ContactsContract
		Cursor cursor=cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
		if(cursor!=null)
		{
			try
			{
				//最好放在异常处理代码块不要忘记了关闭Cursor
				while(cursor.moveToNext())
				{
					//getString
					String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
					String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					String s=name+"\n"+number;
					lianxiren.add(s);
				}
				//不要也能运行adapter.notifyDataSetChanged();
			}
			catch(Exception e){e.printStackTrace();}
			finally
			{
				if(cursor!=null)
				{
					cursor.close();
				}
			}
		}
	}
}
