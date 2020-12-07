package com.anyvision.facekeyexample.activities.face;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class FocusView extends View {
    private Paint mTransparentPaint;
    private Paint mSemiBlackPaint;
    private Path mPath = new Path();

    public FocusView(Context context) {
        super(context);
        initPaints();
    }

    public FocusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public FocusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    private void initPaints() {
        mTransparentPaint = new Paint();
        mTransparentPaint.setColor(Color.TRANSPARENT);
        mTransparentPaint.setStrokeWidth(10);

        mSemiBlackPaint = new Paint();
        mSemiBlackPaint.setColor(Color.TRANSPARENT);
        mSemiBlackPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();

//        mPath.addCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 550, Path.Direction.CW);
        mPath.addCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 300, Path.Direction.CCW);
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);

//        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 550, mTransparentPaint);
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 300, mTransparentPaint);

        canvas.drawPath(mPath, mSemiBlackPaint);
        canvas.clipPath(mPath);
        canvas.drawColor(Color.parseColor("#A6000000"));
    }
}
