package demo.and.note.er;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

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
		list.add("一个简单的悬浮窗-190118");
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
		}
	}
}
