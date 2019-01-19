package demo.and.note.er;
import android.accessibilityservice.*;
import android.view.accessibility.*;
import java.util.*;

public class FuZhuInstaller extends AccessibilityService
{
	@Override
	public void onInterrupt(){}
	@Override
	//监听窗口事件
	public void onAccessibilityEvent(AccessibilityEvent p1)
	{
		Map<Integer,Boolean>map=new HashMap<>();
		AccessibilityNodeInfo nodeInfo=p1.getSource();
		if(nodeInfo!=null)
		{
			int eventTypes=p1.getEventType();
			if(eventTypes==AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED||eventTypes==AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED)
			{
				if((map.get(p1.getWindowId()))==null)
				{
					boolean chulile=bianLiJieDian(nodeInfo);
					if(chulile)
					{
						map.put(p1.getWindowId(),true);
					}
				}
			}
		}
	}
	//遍历各个节点
	public boolean bianLiJieDian(AccessibilityNodeInfo nodeInfo)
	{
		if(nodeInfo!=null)
		{
			int childs=nodeInfo.getChildCount();
			if("android.widget.Button".equals(nodeInfo.getClassName()))
			{
				String nodeName=nodeInfo.getText().toString();
				if("安装".equals(nodeName)||"确定".equals(nodeName)||"完成".equals(nodeName))
				{
					nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
				}
			}
			else if("android.widget.ScrollView".equals(nodeInfo.getClassName()))
			{
				nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
			}
			for(int v=0;v<childs;v++)
			{
				AccessibilityNodeInfo nodeInfos=nodeInfo.getChild(v);
				if(bianLiJieDian(nodeInfos))
				{
					return true;
				}
			}
		}
		return false;
	}
}
