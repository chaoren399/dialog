package cn.itcast.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DemoActivity extends Activity {
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	// ����ǰ��activityʧȥ�����ʱ����õķ���.
	@Override
	protected void onPause() {
		// �Ի��� ��activity��һ����,�����Ի��� ������activityʧȥ����.
		System.out.println("ʧȥ����");
		super.onPause();
	}

	public void click0(View view) {
		Intent intent = new Intent(this, DialogActivity.class);
		startActivity(intent);
	}

	public void click1(View view) {// ��ʾһ��֪ͨ�Ի���
		// ����һ���Ի���Ĺ�����
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("���ǶԻ������");
		builder.setMessage("�򿪺�����վ");
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				intent.setData(Uri.parse("http://edu.csdn.net"));
				startActivity(intent);

			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// �û����ȡ�� ��رյ��Ի���
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void click2(View view) {// ��ʾ��ѡ�Ի���
		// ����һ���Ի���Ĺ�����
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("��ѡ�Ի���");
		final String[] items = { "java", ".net", "php" };
		builder.setItems(items, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "ѡ����" + items[which], 0)
						.show();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
		
		
		
		/*Window window = dialog.getWindow();
		WindowManager dd = window.getWindowManager();
		Display p = dd.getDefaultDisplay();
		WindowManager.LayoutParams pp = window.getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
		pp.height = (int) (p.getHeight() * 0.75); // �߶�����Ϊ��Ļ��0.6
		pp.width = (int) (p.getWidth() * 0.75); // �������Ϊ��Ļ��0.65
		window.setAttributes(pp);
		window.setContentView(R.layout.fragment_result_choose);*/
	}

	public void click3(View view) {// ��ʾ����ť��ѡ�Ի���
		// ����һ���Ի���Ĺ�����
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("��ѡ�Ի���");
		final String[] items = { "java", ".net", "php" };
		builder.setSingleChoiceItems(items, 2, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "ѡ����" + items[which], 0)
						.show();
				dialog.dismiss();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
		
		
	}

	public void click4(View view) {// ��ʾ����ť��ѡ�Ի���
		// ����һ���Ի���Ĺ�����
		AlertDialog.Builder builder = new Builder(this);
		
		builder.setIcon(R.drawable.ic_launcher);
		
		
		
		builder.setTitle("��ѡ�Ի���");
		final String[] items = { "java", ".net", "php" };

		builder.setMultiChoiceItems(items,
				new boolean[] { false, true, false },
				new OnMultiChoiceClickListener() {

					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						if (isChecked) {
							Toast.makeText(getApplicationContext(),
									"ѡ����" + items[which], 0).show();
						} else {
							Toast.makeText(getApplicationContext(),
									"ȡ��ѡ����" + items[which], 0).show();
						}

					}
				});
		builder.setNegativeButton("ȡ��", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void click5(View view) {// �������Ի���
		ProgressDialog pd = new ProgressDialog(this);
		// Ĭ����һ������ʾ������ȵĶԻ���
		pd.setMessage("������������....");
		pd.show();
	}

	public void click6(View view) {// �������Ի���
		final ProgressDialog pd = new ProgressDialog(this);
		// ��ʾ������ȵĶԻ���
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(100);
		pd.setProgress(20);
		pd.show();
		new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					pd.setProgress(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	public void click7(View view){
		showDialog(LIST_DIALOG);
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
					b.setIcon(R.drawable.navigation_button);
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
//								TextView show = (TextView) findViewById(R.id.show);
								// which�����ĸ��б��������
//								show.setText("����ϲ����ר��Ϊ��" + names[which]);

							}
						});
					// �����Ի���
					return b.create();
			}
			return null;
		}
}