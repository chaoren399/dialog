package cn.itcast.dialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CustomListDialog extends Activity
{
	private static final String TAG = "CustomListDialog";
	final int LIST_DIALOG = 0x113;
	//定义3个列表项的名称
	private String[] names = new String[]
	{ "风筝", "完美的一天", "是时候"};
	//定义3个列表项对应的图标
	private int[] imageIds = new int[]
	{ R.drawable.ic_launcher , R.drawable.ic_launcher
		, R.drawable.ic_launcher
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i(TAG, "oncreate");
		Button bn = (Button)findViewById(R.id.bn);
		//为按钮绑定事件监听器
		bn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View source)
			{
				//显示对话框
				showDialog(LIST_DIALOG);
			}
		});
	}
	//重写onCreateDialog方法创建对话框
	@Override
	public Dialog onCreateDialog(int id, Bundle state)
	{
		//判断需要生成哪种类型的对话框
		switch (id)
		{
			case LIST_DIALOG:
				Builder b = new AlertDialog.Builder(this);
				// 设置对话框的图标
				b.setIcon(R.drawable.ic_launcher);
				// 设置对话框的标题
				b.setTitle("单选列表对话框");
				//创建一个List对象，List对象的元素是Map
				List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < names.length; i++)
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("CDs", imageIds[i]);
					listItem.put("CDname", names[i]);
					listItems.add(listItem);
				}
				//创建一个SimpleAdapter
				SimpleAdapter simpleAdapter = new SimpleAdapter(this
					, listItems 
					, R.layout.row
					, new String[]{ "CDname", "CDs" }
					, new int[]{R.id.name , R.id.header});
				
				// 为对话框设置多个列表
				b.setAdapter(simpleAdapter				
					//为列表项的单击事件设置监听器
					, new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog,
							int which)
						{
//							TextView show = (TextView) findViewById(R.id.show);
							// which代表哪个列表项被单击了
//							show.setText("您最喜欢的专辑为：" + names[which]);

						}
					});
				// 创建对话框
				return b.create();
		}
		return null;
	}
}