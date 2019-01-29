package demo.and.note.er;
import android.database.sqlite.*;
import android.content.*;
public class SimpleDataBase extends SQLiteOpenHelper
{
	//两个(抽象、构造器、实例)方法
	//封装好的固定代码块：注意添加类型、()、,
	//autoincrement写法、final等、integer(interger)
	public static final String command="create table book("+"id integer primary key autoincrement,"+"name text,"+"price real,"+"zuozhe text,"+"chubanshang text)";
	private Context c;
	public SimpleDataBase(Context c,String fileName,SQLiteDatabase.CursorFactory factory,int version)
	{
		super(c,fileName,factory,version);
		this.c=c;
	}
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		p1.execSQL(command);
	}
	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3){}
}
