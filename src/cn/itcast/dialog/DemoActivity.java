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
	//定义3个列表项的名称
		private String[] names = new String[]
		{ "风筝", "完美的一天", "是时候"};
		//定义3个列表项对应的图标
		private int[] imageIds = new int[]
		{ R.drawable.ic_launcher , R.drawable.ic_launcher
			, R.drawable.ic_launcher
		};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	// 当当前的activity失去焦点的时候调用的方法.
	@Override
	protected void onPause() {
		// 对话框 是activity的一部分,弹出对话框 不会让activity失去焦点.
		System.out.println("失去焦点");
		super.onPause();
	}

	public void click0(View view) {
		Intent intent = new Intent(this, DialogActivity.class);
		startActivity(intent);
	}

	public void click1(View view) {// 显示一个通知对话框
		// 创建一个对话框的构建者
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("我是对话框标题");
		builder.setMessage("打开黑马网站");
		builder.setPositiveButton("确定", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				intent.setData(Uri.parse("http://edu.csdn.net"));
				startActivity(intent);

			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// 用户点击取消 会关闭掉对话框
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void click2(View view) {// 显示单选对话框
		// 创建一个对话框的构建者
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("单选对话框");
		final String[] items = { "java", ".net", "php" };
		builder.setItems(items, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "选择了" + items[which], 0)
						.show();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
		
		
		
		/*Window window = dialog.getWindow();
		WindowManager dd = window.getWindowManager();
		Display p = dd.getDefaultDisplay();
		WindowManager.LayoutParams pp = window.getAttributes(); // 获取对话框当前的参数值
		pp.height = (int) (p.getHeight() * 0.75); // 高度设置为屏幕的0.6
		pp.width = (int) (p.getWidth() * 0.75); // 宽度设置为屏幕的0.65
		window.setAttributes(pp);
		window.setContentView(R.layout.fragment_result_choose);*/
	}

	public void click3(View view) {// 显示带按钮单选对话框
		// 创建一个对话框的构建者
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("单选对话框");
		final String[] items = { "java", ".net", "php" };
		builder.setSingleChoiceItems(items, 2, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "选择了" + items[which], 0)
						.show();
				dialog.dismiss();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
		
		
	}

	public void click4(View view) {// 显示带按钮多选对话框
		// 创建一个对话框的构建者
		AlertDialog.Builder builder = new Builder(this);
		
		builder.setIcon(R.drawable.ic_launcher);
		
		
		
		builder.setTitle("多选对话框");
		final String[] items = { "java", ".net", "php" };

		builder.setMultiChoiceItems(items,
				new boolean[] { false, true, false },
				new OnMultiChoiceClickListener() {

					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						if (isChecked) {
							Toast.makeText(getApplicationContext(),
									"选择了" + items[which], 0).show();
						} else {
							Toast.makeText(getApplicationContext(),
									"取消选择了" + items[which], 0).show();
						}

					}
				});
		builder.setNegativeButton("取消", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void click5(View view) {// 进度条对话框
		ProgressDialog pd = new ProgressDialog(this);
		// 默认是一个不显示具体进度的对话框
		pd.setMessage("正在下载数据....");
		pd.show();
	}

	public void click6(View view) {// 进度条对话框
		final ProgressDialog pd = new ProgressDialog(this);
		// 显示具体进度的对话框
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
					b.setIcon(R.drawable.navigation_button);
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
//								TextView show = (TextView) findViewById(R.id.show);
								// which代表哪个列表项被单击了
//								show.setText("您最喜欢的专辑为：" + names[which]);

							}
						});
					// 创建对话框
					return b.create();
			}
			return null;
		}
}