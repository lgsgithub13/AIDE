package demo.and.note.er;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class ApkSelectErAc extends Activity implements AdapterView.OnItemClickListener
{
	SimpleAdapter adapter;
	List<Map<String,Object>>list= new ArrayList<>();
	String pathOfRoot=Environment.getExternalStorageDirectory().getPath();
	String MuQianLuJin=pathOfRoot;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apk_select_er);
		ListView lv=(ListView)findViewById(R.id.apkselecterListView1);
		//适配器
		adapter=new SimpleAdapter
		(
			ApkSelectErAc.this
			,list
			,R.layout.list_item_of_apk_select_er
			,new String[]{"filePic","fileName"}
			,new int[]{R.id.listitemofapkselecterImageView1,R.id.listitemofapkselecterTextView1}
		);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		//更新文件列表
		onListUpDate(MuQianLuJin);
	}
	private void onListUpDate(String path)
	{
		setTitle(MuQianLuJin);
		list.clear();
		File[] file=new File(path).listFiles();
		if(file!=null)
		{
			for(File files:file)
			{
				Map<String,Object>map=new HashMap<>();
				if(files.isDirectory()){map.put("filePic",R.drawable.wen_jian_jia);}
				else
				{
					String wenJianLuJin=files.getPath();
					String[] wenJianLuJinArray=wenJianLuJin.split("\\.");
					int len=wenJianLuJinArray.length;
					if("apk".equalsIgnoreCase(wenJianLuJinArray[len-1])){map.put("filePic",R.drawable.apk);}
					else{map.put("filePic",R.drawable.unknown);}
				}
				map.put("fileName",files.getName());
				map.put("PATH KEY",files.getPath());
				list.add(map);
			}
			adapter.notifyDataSetChanged();
		}
	}
	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
	{
		MuQianLuJin=(String)list.get(p3).get("PATH KEY");
		File file=new File(MuQianLuJin);
		//判断点击的是目录还是文件
		if(file.isDirectory())
		{
			onListUpDate(MuQianLuJin);
		}
		else
		{
			String wenJianLuJin=file.getPath();
			String[] wenJianLuJinArray=wenJianLuJin.split("\\.");
			int len=wenJianLuJinArray.length;
			//筛选文件后缀为apk的文件
			if("apk".equalsIgnoreCase(wenJianLuJinArray[len-1]))
			{
			    Intent i=new Intent();
			    String apkFilePath=file.getPath();
			    i.putExtra("APKPATH",apkFilePath);
			    setResult(RESULT_OK,i);
			    finish();
			}
			else
			{
				new AlertDialog.Builder(ApkSelectErAc.this)
				.setMessage(R.string.main_activity_2)
				.show();
			}
		}
	}
	//判断点击返回键所在目录
	@Override
	public void onBackPressed()
	{
		if(MuQianLuJin.equals(pathOfRoot))
		{
		    super.onBackPressed();
		}
		else
		{
			File file=new File(MuQianLuJin);
			MuQianLuJin=file.getParentFile().getPath();
			onListUpDate(MuQianLuJin);
		}
	}
}
