package com.ssq.democircleprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


/**
 * Author : Mr.Shen
 * Date : 2019/9/23 17:44
 * Description : Canvas 简单使用
 */
public class DrawTest extends View {

    private Context mContext;
    private Paint mPaint;//定义一个画笔

    public DrawTest(Context context) {
        super(context);
    }

    public DrawTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DrawTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawNormal(canvas);
        drawTest(canvas);
    }

    /**
     * 常规绘制  以(0,0)作为坐标原点参考点
     */
    private void drawNormal(Canvas canvas) {
        mPaint = new Paint();
//        canvas.drawColor(Color.GRAY);// 绘制画布背景
        mPaint.setColor(Color.BLUE);//设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔为空心(如果将这里改为Style.STROKE  这个图中的实线圆柱体就变成了空心的圆柱体)
//        canvas.drawLine(50, 50, 450, 50, mPaint);//绘制直线
//        canvas.drawRect(100, 100, 200, 400, mPaint);//绘制矩形
        //绘制矩形
        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(100, 100, 200, 300, mPaint);
//        mPaint.setColor(Color.YELLOW);
        RectF rectF = new RectF(150, 500, 270, 600);
//        canvas.drawRect(rectF, mPaint);// 画矩形
//        canvas.drawCircle(50, 500, 50, mPaint);
//        canvas.drawOval(rectF, mPaint);// 画椭圆
//        canvas.drawRoundRect(rectF, 30, 20, mPaint);// 画圆角矩形
        //绘制圆弧 绘制弧形
//        RectF re1 = new RectF(500, 50, 1000, 200);
//        canvas.drawArc(re1, 10, 270, false, mPaint);//boolean useCenter 是否连接到圆心 true为连接圆心，false为不连接圆心
//
//        RectF re2 = new RectF(500, 300, 1000, 500);
//        canvas.drawArc(re2, 10, 270, true, mPaint);

        //设置Path路径
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        Path path = new Path();
//        path.moveTo(500, 100);
//        path.lineTo(920, 80);
//        path.lineTo(720, 200);
//        path.lineTo(600, 400);
//        path.close();
//        mPaint.setTextSize(50);
//        canvas.drawPath(path, mPaint);
//        canvas.drawTextOnPath("绘制path文本", path, -20, -20, mPaint);

        //三角形
//        path.moveTo(10, 330);
//        path.lineTo(70, 330);
//        path.lineTo(40, 270);
//        path.close();
//        canvas.drawPath(path, mPaint);

        //把开始的点和最后的点连接在一起，构成一个封闭梯形
//        path.moveTo(10, 410);//绘画基点
//        path.lineTo(70, 410);
//        path.lineTo(55, 350);
//        path.lineTo(25, 350);
//        //如果是Style.FILL的话，不设置close,也没有区别，可是如果是STROKE模式， 如果不设置close,图形不封闭。当然，你也可以不设置close，再添加一条线，效果一样。
//        path.close();
//        canvas.drawPath(path, mPaint);

        //参数一为渐变起初点坐标x位置，参数二为y轴位置，参数三和四分辨对应渐变终点,其中参数new int[]{startColor, midleColor,endColor}是参与渐变效果的颜色集合，
        // 其中参数new float[]{0 , 0.5f, 1.0f}是定义每个颜色处于的渐变相对位置， 这个参数可以为null，如果为null表示所有的颜色按顺序均匀的分布
        // Shader.TileMode三种模式
        // REPEAT:沿着渐变方向循环重复
        // CLAMP:如果在预先定义的范围外画的话，就重复边界的颜色
        // MIRROR:与REPEAT一样都是循环重复，但这个会对称重复
        Shader mShader = new LinearGradient(0, 0, 100, 100,
                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW},
                null, Shader.TileMode.REPEAT);
        mPaint.setShader(mShader);// 用Shader中定义定义的颜色来话
        mPaint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        path1.moveTo(170, 810);
        path1.lineTo(510, 810);
        path1.lineTo(460, 350);
        path1.lineTo(185, 350);
        path1.close();
        canvas.drawPath(path1, mPaint);
        canvas.save();

    }

    /**
     * 绘制方法练习
     *
     * @param canvas
     */
    private void drawTest(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        //平移测试
        canvas.translate(50, 900);
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);
        canvas.translate(50, 50);
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);

        //缩放测试
        canvas.translate(100, -50);
        canvas.drawRect(new Rect(0, 0, 300, 300), mPaint);

        // 保存画布状态
        canvas.save();
        canvas.scale(0.5f, 0.5f);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(0, 0, 300, 300), mPaint);

        // 画布状态回滚
        canvas.restore();
        // 先将画布平移到矩形的中心
        canvas.translate(400, -50);
        canvas.drawRect(new Rect(0, 0, 300, 300), mPaint);

        //旋转测试
        canvas.save();
        canvas.translate(350, 50);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
        mPaint.setColor(Color.RED);
        canvas.rotate(45, 200, 200);
        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
        canvas.restore();

        //画布错切 三角函数tan的值
        canvas.translate(350, 300);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
        // y 方向上倾斜45 度
        canvas.skew(0, 1);
        mPaint.setColor(0x8800ff00);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

    }
}
