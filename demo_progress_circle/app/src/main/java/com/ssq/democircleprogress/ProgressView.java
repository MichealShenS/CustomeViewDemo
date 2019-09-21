package com.ssq.democircleprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Author : Mr.Shen
 * Date : 2019/9/21 15:44
 * Description : 自定义圆环进度条带文本
 */
public class ProgressView extends View {

    private Paint mBackgroundPaint;//背景色画笔
    private Paint mArcPaint;//进度画笔
    private Paint mTextViewPaint;//进度文本画笔

    private int mProgress = 0;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 初始化
     * Paint.Style.FILL设置只绘制图形内容
     * Paint.Style.STROKE设置只绘制图形的边
     * Paint.Style.FILL_AND_STROKE设置都绘制
     */
    private void initPaint() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.BLACK);
        mBackgroundPaint.setAntiAlias(true);//设置抗锯齿
        mBackgroundPaint.setStrokeWidth(5);
        mBackgroundPaint.setStyle(Paint.Style.STROKE);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.RED);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStrokeWidth(10);
        mArcPaint.setStyle(Paint.Style.STROKE);

        mTextViewPaint = new Paint();
        mTextViewPaint.setColor(Color.RED);
        mTextViewPaint.setAntiAlias(true);
        mTextViewPaint.setTextSize(30);
        mTextViewPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制底部圆圈
        /**
         * cx：圆心的x坐标
         * cy：圆心的y坐标
         * radius：圆半径
         * paint：绘制用的画笔
         */
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 150, mBackgroundPaint);

        //绘制进度的圆弧
        canvas.drawArc(new RectF(getMeasuredWidth() / 2 - 150, getMeasuredHeight() / 2 - 150, getMeasuredWidth() / 2 + 150, getMeasuredHeight() / 2 + 150),
                0, 360 * mProgress / 100, false, mArcPaint);

        //绘制中间的进度文本
        String text = mProgress + " %";
        //使文本居中摆放
        Rect rect = new Rect();
        mTextViewPaint.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text, getMeasuredWidth() / 2 - rect.width() / 2, getMeasuredHeight() / 2 + rect.height() / 2, mTextViewPaint);
    }

    /**
     * 开始进度
     */
    public void startProgress() {
        System.out.println("startProgress========" + Thread.currentThread());
        mProgress = 0;
        mHandler.sendEmptyMessageDelayed(0, 10);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("mHandler========" + Thread.currentThread());
            if (msg.what == 0) {
                if (mProgress < 100) {
                    mProgress++;
                    invalidate();
                    sendEmptyMessageDelayed(0, 10);
                }
            }
        }
    };
}
