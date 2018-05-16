package com.example.jun.bisaixiangmu.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class ZXingTestActivity extends BaseActivity {

    //定时器更新二维码，并不好使
    private Timer timer ;

    private ImageView imageView;
    private ImageView imageView_big;
    private TextView textView;

    private String yuan = "0";
    private int miao = 0;
    private int carId = 1;
    private String erWeiMaString = "";
    private Bitmap bitmap;

    @Override
    protected String getLayoutTitle() {
        return "付款信息";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_zxing_test;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer!=null&&miao!=0){
            timer.schedule(new TestTimer(), miao*1000, miao*1000);
        }
    }

    @Override
    protected void initView() {
        timer = new Timer();
        Intent intent = getIntent();
        intent.getIntExtra("carId", 0);
        String zhouqi = intent.getStringExtra("zhouqi");
        miao = Integer.parseInt(zhouqi);
        int jine = Integer.parseInt(intent.getStringExtra("jine"));
        erWeiMaString = "车辆编号=" + carId + ",付费金额=" + jine + "元";
        imageView = findViewById(R.id.zxing_imageview);
        imageView_big = findViewById(R.id.quan_ping);
        textView = findViewById(R.id.tv_zxing_show);
        //bitmap = createBitmap(erWeiMaString);
        bitmap = createTest(erWeiMaString);
        imageView.setImageBitmap(bitmap);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("", "setOnClickListener");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        imageView.setClickable(false);
                        imageView_big.setImageBitmap(bitmap);
                        imageView_big.setVisibility(View.VISIBLE);
                    }
                });

            }
        });
        imageView_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_big.setVisibility(View.GONE);
                imageView.setClickable(true);
                imageView.setImageBitmap(bitmap);
                Log.e("", "setOnClickListener");
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(erWeiMaString);
                    }
                });
                return true;
            }
        });
    }
    /**
     * 自己练习二维码的生成
     * 这里解决了乱码
     * @param
     * @return
     */
    public Bitmap createBitmap(String str) {
        Bitmap bitmap1 = null;
        try {
            Map<EncodeHintType, String> hints = new HashMap<>();//也可以new一个hashtable
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//解决乱码
            hints.put(EncodeHintType.ERROR_CORRECTION, "H");//容错率，也就是 黑白点 密集
            //两种方式生成二维码

            BitMatrix bitMatrix = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, 400, 400, hints);
            //以前zxing版本
            // BitMatrix bitMatrix = new MultiFormatWriter().encode(str,BarcodeFormat.QR_CODE, 400, 400,hints);
            //对BitMatrix操作
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = bitMatrix.get(x, y) ? BLACK : WHITE;
                }
            }
            //这是创建位图  Bitmap.Config.ARGB_8888 格式比较好，常用
            bitmap1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap1.setPixels(pixels, 0, width, 0, 0, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return bitmap1;
    }


    //练习二维码生成
    public Bitmap createTest(String s){
       Bitmap bitmap=null;

        Map<EncodeHintType, String> hints=new HashMap<>();
        try {
            hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION,"H");
            BitMatrix bitMatrix = new QRCodeWriter().encode(s, BarcodeFormat.QR_CODE, 400, 400, hints);

        } catch (WriterException e) {
            e.printStackTrace();
        }


        return bitmap;
    }



    class TestTimer extends TimerTask {

        @Override
        public void run() {
            Log.e("TestTimer","=--=-==-=-");
            //final Bitmap bitmap = createBitmap(erWeiMaString);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //imageView.setImageBitmap(createBitmap(erWeiMaString));
                    imageView.setImageBitmap(createTest(erWeiMaString));
                    Toast.makeText(ZXingTestActivity.this, "二维码已更新，周期"+miao+"秒", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void startSelfActivity(Context context, int carId, String yuan, String miao) {
        Intent intent = new Intent(context, ZXingTestActivity.class);
        intent.putExtra("carId", carId);
        intent.putExtra("jine", yuan);
        intent.putExtra("zhouqi", miao);
        context.startActivity(intent);
    }
}
