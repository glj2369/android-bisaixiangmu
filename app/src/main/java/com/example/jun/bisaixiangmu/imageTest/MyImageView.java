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
public class MyImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    /**
     * 控件宽度
     */
    private int mWidth;
    /**
     * 控件高度
     */
    private int mHeight;

    /**
     * 拿到src的图片
     */
    private Drawable mDrawable;

    /**
     * 图片宽度（使用前判断mDrawable是否null）
     */
    private int mDrawableWidth;
    /**
     * 图片高度（使用前判断mDrawable是否null）
     */
    private int mDrawableHeight;

    /**
     * 初始化缩放值
     */
    private float mScale;

    /**
     * 双击图片的缩放值
     */
    private float mDoubleClickScale;

    /**
     * 最大的缩放值
     */
    private float mMaxScale;
    /**
     * 最小的缩放值
     */
    private float mMinScale;

    private ScaleGestureDetector scaleGestureDetector;
    /**
     * 当前有着缩放值、平移值的矩阵。
     */
    private Matrix matrix;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
        scaleGestureDetector = new ScaleGestureDetector(context, this);
        initListener();
    }


    /**
     * 初始化事件监听
     */
    private void initListener() {
        // 强制设置模式
        setScaleType(ScaleType.MATRIX);
        // 添加观察者
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 移除观察者
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // 获取控件大小
                mWidth = getWidth();
                mHeight = getHeight();

                //通过getDrawable获得Src的图片
                mDrawable = getDrawable();
                if (mDrawable == null)
                    return;
                mDrawableWidth = mDrawable.getIntrinsicWidth();
                mDrawableHeight = mDrawable.getIntrinsicHeight();
                initImageViewSize();
                moveToCenter();
            }
        });
    }

    /**
     * 初始化资源图片宽高
     */
    private void initImageViewSize() {
        if (mDrawable == null)
            return;

        // 缩放值
        float scale = 1.0f;
        // 图片宽度大于控件宽度，图片高度小于控件高度
        if (mDrawableWidth > mWidth && mDrawableHeight < mHeight)
            scale = mWidth * 1.0f / mDrawableWidth;
            // 图片高度度大于控件宽高，图片宽度小于控件宽度
        else if (mDrawableHeight > mHeight && mDrawableWidth < mWidth)
            scale = mHeight * 1.0f / mDrawableHeight;
            // 图片宽度大于控件宽度，图片高度大于控件高度
        else if (mDrawableHeight > mHeight && mDrawableWidth > mWidth)
            scale = Math.min(mHeight * 1.0f / mDrawableHeight, mWidth * 1.0f / mDrawableWidth);
            // 图片宽度小于控件宽度，图片高度小于控件高度
        else if (mDrawableHeight < mHeight && mDrawableWidth < mWidth)
            scale = Math.min(mHeight * 1.0f / mDrawableHeight, mWidth * 1.0f / mDrawableWidth);
        mScale = scale;
        mMaxScale = mScale * 8.0f;
        mMinScale = mScale * 0.5f;
    }

    /**
     * 移动控件中间位置
     */
    private void moveToCenter() {
        final float dx = mWidth / 2 - mDrawableWidth / 2;
        final float dy = mHeight / 2 - mDrawableHeight / 2;
        matrix = new Matrix();
        // 平移至中心
        matrix.postTranslate(dx, dy);
        // 以控件中心作为缩放
        matrix.postScale(mScale, mScale, mWidth / 2, mHeight / 2);
        setImageMatrix(matrix);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {

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
    public boolean onTouch(View v, MotionEvent event) {

        return scaleGestureDetector.onTouchEvent(event);
    }
}
