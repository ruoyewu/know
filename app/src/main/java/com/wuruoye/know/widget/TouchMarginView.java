package com.wuruoye.know.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.FloatValueHolder;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.wuruoye.know.R;
import com.wuruoye.library.util.DensityUtil;
import com.wuruoye.library.util.log.WLog;

import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created at 2019/3/18 18:30 by wuruoye
 * Description:
 */
public class TouchMarginView extends View {
    public static int MAX_TOP = 50;
    public static int MAX_LEFT = 30;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_TOP = 1;
    public static final int TYPE_BOTTOM = 2;
    public static final int TYPE_LEFT = 3;
    public static final int TYPE_RIGHT = 4;
    public static final float MIN_TIME = 0.001F;

    private float top;
    private float bottom;
    private float left;
    private float right;

    private int width;
    private int height;
    private int lineWidth;
    private int lineHeight;
    private int offset;

    private Paint mPaint;

    private int curType;
    private float startX;
    private float startY;
    private VelocityTracker mVelocityTracker;
    private FlingAnimation mAnimation;

    public TouchMarginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();

        mVelocityTracker = VelocityTracker.obtain();
        mAnimation = new FlingAnimation(new FloatValueHolder());
        mAnimation.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float v, float v1) {
                switch (curType) {
                    case TYPE_TOP: top = v; break;
                    case TYPE_BOTTOM: bottom = v; break;
                    case TYPE_LEFT: left = v; break;
                    case TYPE_RIGHT: right = v; break;
                }
                postInvalidate();
            }
        });
        MAX_TOP = (int) DensityUtil.dp2px(getContext(), 50);
        MAX_LEFT = (int) DensityUtil.dp2px(getContext(), 30);
    }

    public void setMargin(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        postInvalidate();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setAlpha(100);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(ActivityCompat.getColor(getContext(), R.color.mountain_mist));
    }

    private void initSize(int w, int h) {
        this.width = w;
        this.height = h;
        this.lineWidth = w / 3;
        this.lineHeight = h / 3;
        offset = 20;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw top
        float startX = lineWidth, endX = lineWidth << 1;
        float startY = lineHeight * top * 1F / MAX_TOP, endY = startY;
        canvas.drawLine(startX, startY + offset, endX, endY + offset, mPaint);

        // draw bottom
        startY = height - (lineHeight * bottom * 1F / MAX_TOP);
        endY = startY;
        canvas.drawLine(startX, startY - offset, endX, endY - offset, mPaint);

        // draw left
        startX = lineWidth * left * 1F / MAX_LEFT;
        endX = startX;
        startY = lineHeight;
        endY = lineHeight << 1;
        canvas.drawLine(startX + offset, startY, endX + offset, endY, mPaint);

        // draw right
        startX = width - (lineWidth * right * 1F / MAX_LEFT);
        endX = startX;
        canvas.drawLine(startX - offset, startY, endX - offset, endY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        mVelocityTracker.computeCurrentVelocity(1000);
        float velocityX = mVelocityTracker.getXVelocity();
        float velocityY = mVelocityTracker.getYVelocity();
        float x = event.getX(), y = event.getY();
        switch (event.getAction()) {
            case ACTION_DOWN:
                mAnimation.cancel();
                curType = TYPE_NONE;
                startX = x;
                startY = y;
                WLog.loge(this, "down");
                break;
            case ACTION_UP:
            case ACTION_CANCEL:
                float startValue = 0, minValue = 0, maxValue = 0, velocity = 0;
                switch (curType) {
                    case TYPE_TOP:
                        startValue = top;
                        maxValue = MAX_TOP;
                        velocity = velocityY;
                        break;
                    case TYPE_BOTTOM:
                        startValue = bottom;
                        maxValue = MAX_TOP;
                        velocity = -velocityY;
                        break;
                    case TYPE_LEFT:
                        startValue = left;
                        maxValue = MAX_LEFT;
                        velocity = velocityX;
                        break;
                    case TYPE_RIGHT:
                        startValue = right;
                        maxValue = MAX_LEFT;
                        velocity = -velocityX;
                        break;
                }
                mAnimation.setMinValue(minValue)
                        .setMaxValue(maxValue)
                        .setStartValue(startValue)
                        .setStartVelocity(velocity/10)
                        .start();
                break;
            case ACTION_MOVE:
                WLog.loge(this, "velocity x : " + velocityX
                        + ", velocity y : " + velocityY);
                if (curType == TYPE_NONE) {
                    if (Math.abs(velocityX) > Math.abs(velocityY)) {
                        if (startX > width / 2) curType = TYPE_RIGHT;
                        else curType = TYPE_LEFT;
                    } else {
                        if (startY > height / 2) curType = TYPE_BOTTOM;
                        else curType = TYPE_TOP;
                    }
                }
                float distance = (curType > 2 ? velocityX : velocityY) * MIN_TIME;
                switch (curType) {
                    case TYPE_TOP:
                        top = addWithLimit(top, distance, MAX_TOP);
                        break;
                    case TYPE_BOTTOM:
                        bottom = addWithLimit(bottom, -distance, MAX_TOP);
                        break;
                    case TYPE_LEFT:
                        left = addWithLimit(left, distance, MAX_LEFT);
                        break;
                    case TYPE_RIGHT:
                        right = addWithLimit(right, -distance, MAX_LEFT);
                        break;
                }
                postInvalidate();
                break;
        }
        super.onTouchEvent(event);
        return true;
    }

    private float addWithLimit(float distance, float offset, int max) {
        float result = distance + offset;
        if (result > max) return max;
        if (result < 0) return 0;
        return result;
    }

    private float getCurValue() {
        switch (curType) {
            case TYPE_TOP: return top;
            case TYPE_BOTTOM: return bottom;
            case TYPE_LEFT: return left;
            case TYPE_RIGHT: return right;
            default: return 0;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initSize(w, h);
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}
