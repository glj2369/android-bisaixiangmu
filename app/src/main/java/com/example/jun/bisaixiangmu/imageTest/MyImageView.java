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

//网上实现图片手势放大MyImageView.class-----不好使
@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener{
    private Matrix matrix=new Matrix();
    private ScaleGestureDetector detector;
    private boolean once=true;
    public MyImageView(Context context) {
        this(context,null);
    }



    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }
    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.MATRIX);
        detector=new ScaleGestureDetector(context,this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();
        if (getDrawable()==null)
            return true;
        matrix.postScale(scaleFactor,scaleFactor,getWidth()/2,getHeight()/2);
        setImageMatrix(matrix);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
    private float spacing(MotionEvent event) {
        if (event.getPointerCount() >= 2) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        } else return 0;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        if (!once)
            return;
        Drawable drawable = getDrawable();
        if (drawable==null)
            return;
        int imageWidth = drawable.getIntrinsicWidth();
        int imageHeight = drawable.getIntrinsicHeight();

        int width = getWidth();
        int height = getHeight();
        float scale=1.0f;
        matrix.postTranslate((width-imageWidth)/2,(height-imageHeight)/2);
        matrix.postScale(scale,scale,getWidth()/2, getHeight()/2);
        setImageMatrix(matrix);
        once=false;
    }

}
