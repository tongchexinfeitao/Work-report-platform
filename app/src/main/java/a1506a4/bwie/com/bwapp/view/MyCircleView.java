package a1506a4.bwie.com.bwapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者: 赵虔
 * 时间: 2017/10/22
 * 类作用:
 */

public class MyCircleView extends View {

    private String name = null;

    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#87cefa"));
        int radius = (getWidth() / 2);
        canvas.drawCircle(radius, radius, radius, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(70);
        if (name.length() > 4)
            canvas.drawText(name, radius / 4 - 13, radius + 20, paint);
        else
            canvas.drawText(name, radius / 3 + 8, radius + 20, paint);
    }

    public void setText(String text) {
        name = text;
        invalidate();
    }
}
