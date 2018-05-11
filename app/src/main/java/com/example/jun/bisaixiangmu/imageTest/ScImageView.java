package com.example.jun.bisaixiangmu.imageTest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

//朴永亮给的实现图片手势放大ScImageView.class
@SuppressLint("AppCompatCustomView")
public class ScImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {

    //private static final String TAG = "ScImageView";
    //这个是为了第一次运行将图片放在中间位置
    boolean once = true;

    //手势检测类ScaleGestureDetector
    ScaleGestureDetector scaleGestureDetector = null;
    Matrix scaleMatrix = new Matrix();//初始化矩阵

    //构造方法
    public ScImageView(Context context) {
        this(context, null);
        // super(context);
    }

    public ScImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // super(context, attrs);
    }

    public ScImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.MATRIX);//矩阵类型缩放
        // 这里需要实现ScaleGestureDetector.OnScaleGestureListener缩放手势检测接口
        scaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
    }

    //onTouch接口：
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return scaleGestureDetector.onTouchEvent(event);
    }

    //以下为ScaleGestureDetector.OnScaleGestureListener接口
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();
        if (getDrawable() == null)
            return true;
        //设置缩放比例
        scaleMatrix.postScale(scaleFactor, scaleFactor, getWidth() / 2, getHeight() / 2);
        setImageMatrix(scaleMatrix);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }
    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 添加观察者
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 移除观察者
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        getViewTreeObserver().removeGlobalOnLayoutListener(this);

    }
    //布局树控制接口，让图片在中心
    @Override
    public void onGlobalLayout() {

        if (!once)
            return;
        Drawable d = getDrawable();
        if (d == null)
            return;
        //获取imageview宽高
        int width = getWidth();
        int height = getHeight();

        //获取图片宽高
        int imgWidth = d.getIntrinsicWidth();
        int imgHeight = d.getIntrinsicHeight();
        //缩放倍数
        float scale = 1.0f;
        //将图片移动至屏幕中心
        scaleMatrix.postTranslate((width - imgWidth) / 2, (height - imgHeight) / 2);
        scaleMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
        setImageMatrix(scaleMatrix);
        once = false;
    }
}
