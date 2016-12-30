package com.example.touchsimulation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

public class Draw extends View {
	private int mov_x;// �����������
	private int mov_y;
	private Paint paint;// ��������
	private Canvas canvas;// ����
	private Bitmap bitmap;// λͼ
	private int blcolor;

	public Draw(Context context) {
		super(context);
		paint = new Paint(Paint.DITHER_FLAG);// ����һ������
		bitmap = Bitmap.createBitmap(480, 854, Bitmap.Config.ARGB_8888); // ����λͼ�Ŀ��
		canvas = new Canvas();
		canvas.setBitmap(bitmap);

		paint.setStyle(Style.STROKE);// ���÷����
		paint.setStrokeWidth(5);// �ʿ�5����
		paint.setColor(Color.RED);// ����Ϊ���
		paint.setAntiAlias(true);// ��ݲ���ʾ

	}

	// ��λͼ
	@Override
	protected void onDraw(Canvas canvas) {
		// super.onDraw(canvas);
		canvas.drawBitmap(bitmap, 0, 0, null);
	}

	// �����¼�
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {// ����϶�
			canvas.drawLine(mov_x, mov_y, event.getX(), event.getY(), paint);// ����
			invalidate();
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) {// ������
			mov_x = (int) event.getX();
			mov_y = (int) event.getY();
			canvas.drawPoint(mov_x, mov_y, paint);// ����
			invalidate();
		}
		mov_x = (int) event.getX();
		mov_y = (int) event.getY();
		return true;
	}

	
}