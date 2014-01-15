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
	//����3���б��������
	private String[] names = new String[]
	{ "����", "������һ��", "��ʱ��"};
	//����3���б����Ӧ��ͼ��
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
		//Ϊ��ť���¼�������
		bn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View source)
			{
				//��ʾ�Ի���
				showDialog(LIST_DIALOG);
			}
		});
	}
	//��дonCreateDialog���������Ի���
	@Override
	public Dialog onCreateDialog(int id, Bundle state)
	{
		//�ж���Ҫ�����������͵ĶԻ���
		switch (id)
		{
			case LIST_DIALOG:
				Builder b = new AlertDialog.Builder(this);
				// ���öԻ����ͼ��
				b.setIcon(R.drawable.ic_launcher);
				// ���öԻ���ı���
				b.setTitle("��ѡ�б�Ի���");
				//����һ��List����List�����Ԫ����Map
				List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < names.length; i++)
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("CDs", imageIds[i]);
					listItem.put("CDname", names[i]);
					listItems.add(listItem);
				}
				//����һ��SimpleAdapter
				SimpleAdapter simpleAdapter = new SimpleAdapter(this
					, listItems 
					, R.layout.row
					, new String[]{ "CDname", "CDs" }
					, new int[]{R.id.name , R.id.header});
				
				// Ϊ�Ի������ö���б�
				b.setAdapter(simpleAdapter				
					//Ϊ�б���ĵ����¼����ü�����
					, new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog,
							int which)
						{
//							TextView show = (TextView) findViewById(R.id.show);
							// which�����ĸ��б��������
//							show.setText("����ϲ����ר��Ϊ��" + names[which]);

						}
					});
				// �����Ի���
				return b.create();
		}
		return null;
	}
}