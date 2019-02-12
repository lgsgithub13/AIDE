package demo.and.note.er;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.graphics.drawable.*;
public class ShowInstalledAc extends Activity implements AdapterView.OnItemClickListener,SimpleAdapter.ViewBinder
{
	SimpleAdapter sa;
	PackageManager pm;
	List<Map<String,Object>>list=new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_installed_ac);
		ListView lv=(ListView)findViewById(R.id.showinstalledacListView1);
		sa=new SimpleAdapter
		(
		ShowInstalledAc.this
		,list
		,R.layout.list_item_of_show_installed
		,new String[]{"icon","app_name","package_name"}
		,new int[]{R.id.listitemofshowinstalledImageView1,R.id.listitemofshowinstalledTextView1,R.id.listitemofshowinstalledTextView2}
		);
		lv.setAdapter(sa);
		lv.setOnItemClickListener(this);
		sa.setViewBinder(this);
		show();
	}
	protected void show()
	{
		pm=getPackageManager();
		List<PackageInfo> pi=pm.getInstalledPackages(0);
		Iterator<PackageInfo>it=pi.iterator();
		while(it.hasNext())
		{
			PackageInfo app=it.next();
			Map<String,Object>map=new HashMap<>();
			map.put("icon",app.applicationInfo.loadIcon(pm));
			map.put("app_name",app.applicationInfo.loadLabel(pm));
			map.put("package_name",app.packageName);
			list.add(map);
		}
	}
	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
	{
		String packageNames=(String)list.get(p3).get("package_name");
		Intent i=pm.getLaunchIntentForPackage(packageNames);
		startActivity(i);
	}
	@Override
	public boolean setViewValue(View p1, Object p2, String p3)
	{
		if(p1 instanceof ImageView&&p2 instanceof Drawable)
		{
			ImageView im=(ImageView)p1;
			im.setImageDrawable((Drawable)p2);
			return true;
		}
		else if(p1 instanceof TextView&&p2 instanceof String)
		{
			TextView tv=(TextView)p1;
			tv.setText((String)p2);
			return true;
		}
		else{return false;}
	}
}
