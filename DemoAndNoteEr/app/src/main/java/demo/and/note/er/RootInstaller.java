package demo.and.note.er;
import android.util.*;
import java.io.*;
import java.nio.charset.*;
//未解释
public class RootInstaller
{
	protected boolean install(String apkPath)
	{
		boolean result=false;
		DataOutputStream dops=null;
		BufferedReader br=null;
		try
		{
			Process process=Runtime.getRuntime().exec("su");
			String command="pm install -r"+apkPath+"\n";
			dops=new DataOutputStream(process.getOutputStream());
			dops.write(command.getBytes(Charset.forName("utf-8")));
			dops.flush();
			dops.writeBytes("exit/n");
			dops.flush();
			process.waitFor();
			br=new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String info="";
			String l;
			while((l=br.readLine())!=null)
			{
				info+=l;
			}
			if(!info.contains("Failure"))
			{
				result=true;
			}
		}
		catch(Exception e){Log.e("来自RootInstaller的installer方法",e.getMessage(),e);}
		finally
		{
			try
			{
				if(dops!=null){dops.close();}
				if(br!=null){br.close();}
			}
			catch(IOException e){Log.e("来自RootInstaller的installer方法",e.getMessage(),e);}
		}
		return result;
	}
}
