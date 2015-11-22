package com.example.aaaa;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	int[] location=new int[2];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		
		
		iv.setImageResource(R.drawable.start);
		
		iv.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				iv.measure(0, 0);
				
				Rect frame = new Rect();
				getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
				int statusBarHeight = frame.top;
				int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
				//statusBarHeight是上面状态栏的高度
				
				Log.d("MainActivity","titleBar::"+contentTop);
				
				iv.getLocationOnScreen(location);
				
				Log.d("MainActivity",""+location[0]);
				Log.d("MainActivity",""+location[1]);
				
				int measuredWidth = iv.getMeasuredWidth();
				int measuredHeight = iv.getMeasuredHeight();
				Log.d("MainActivity",""+measuredWidth);
				Log.d("MainActivity",""+measuredHeight);
				
				
				
				
				// 图片合成画布 先画图片A 再去画图片B
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.start);
				Bitmap alertBitmap = Bitmap.createBitmap(bitmap.getWidth(),
						bitmap.getHeight(), bitmap.getConfig());

				Canvas canvas = new Canvas(alertBitmap);
				Paint paint = new Paint();
				paint.setColor(Color.BLACK);

				canvas.drawBitmap(bitmap, new Matrix(), paint);
				Bitmap ic_launch = BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher);
				Log.d("MainActivity",""+ic_launch.getWidth());
				Log.d("MainActivity",""+ic_launch.getHeight());
				canvas.drawBitmap(ic_launch, location[0]+((measuredWidth-ic_launch.getWidth())/ 2),
						location[1]+((measuredHeight-ic_launch.getHeight())/2)-contentTop, paint);

				iv.setImageBitmap(alertBitmap);
				
				iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});

	}
}
