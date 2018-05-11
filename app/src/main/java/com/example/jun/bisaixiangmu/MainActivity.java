package com.example.jun.bisaixiangmu;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.activity.BaseMainActivity;
import com.example.jun.bisaixiangmu.activity.ChongZhi1Activity;
import com.example.jun.bisaixiangmu.activity.ChuXingActivity;
import com.example.jun.bisaixiangmu.activity.ETC9Activity;
import com.example.jun.bisaixiangmu.activity.ErWeiMa37Activity;
import com.example.jun.bisaixiangmu.activity.HongDengActivity;
import com.example.jun.bisaixiangmu.activity.HuanJing5Activity;
import com.example.jun.bisaixiangmu.activity.ImageTouchTestActivity;
import com.example.jun.bisaixiangmu.activity.IpSetActivity;
import com.example.jun.bisaixiangmu.activity.TestChar;
import com.example.jun.bisaixiangmu.activity.TiKu11Activity;
import com.example.jun.bisaixiangmu.activity.TiKu14Activity;
import com.example.jun.bisaixiangmu.activity.TiKu16Activity;
import com.example.jun.bisaixiangmu.activity.TiKu17Activity;
import com.example.jun.bisaixiangmu.activity.TiKu20Activity;
import com.example.jun.bisaixiangmu.activity.TiKu21Activity;
import com.example.jun.bisaixiangmu.activity.TiKu22Activity;
import com.example.jun.bisaixiangmu.activity.TiKu23Activity;
import com.example.jun.bisaixiangmu.activity.TiKu24Activity;
import com.example.jun.bisaixiangmu.activity.TiKu25Activity;
import com.example.jun.bisaixiangmu.activity.TiKu27Activity;
import com.example.jun.bisaixiangmu.activity.TiKu31Activity;
import com.example.jun.bisaixiangmu.activity.TiKu32Activity;
import com.example.jun.bisaixiangmu.activity.TiKu33Activity;
import com.example.jun.bisaixiangmu.activity.TiKu35Activity;
import com.example.jun.bisaixiangmu.activity.TiKu36ctivity;
import com.example.jun.bisaixiangmu.activity.TiKu38Activity;
import com.example.jun.bisaixiangmu.activity.TiKu39Activity;
import com.example.jun.bisaixiangmu.activity.WeiZhang4Activity;
import com.example.jun.bisaixiangmu.activity.XianShi6Activity;
import com.example.jun.bisaixiangmu.activity.YuZhi7Activity;
import com.example.jun.bisaixiangmu.activity.ZhangDan3Activity;
import com.example.jun.bisaixiangmu.bean.MenuList;
import com.example.jun.bisaixiangmu.http.NetUtil1;
import com.example.jun.bisaixiangmu.utils.DialogDome;
import com.example.jun.bisaixiangmu.utils.ListAdapterMenu;
import com.example.jun.bisaixiangmu.utils.MyPagerAdapter;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseMainActivity {
    private DrawerLayout mDrawerLayout;
    private DialogDome receiver;
    private ImageView mTvMenu;
    private ListView listView;
    private List<MenuList> list;
    private LinearLayout layout;

    private ViewPager mViewPager;
    private List<View> mViews;
    private LayoutInflater mInflater;
    private MyPagerAdapter myPagerAdapter;
    private List<ImageView> mDots;//定义一个集合存储三个dot
    private int oldPosition;//记录当前点的位置。
    private Intent intent;
    private MyConn conn;
    private MyApp7Service.MyBinder binder;
    private CalendarView calendarView;
    private Timer timer;

    @Override
    protected String getLayoutTitle() {
        return "主界面";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_main;
    }

    //这里初始化数据是初始化一个viewpager模拟 最外层Fragment 里面一个viewpager和几个小imageview
    @Override
    protected void initData() {

        mViewPager = (ViewPager) findViewById(R.id.viewpager_show);
        mInflater = getLayoutInflater();
        mViews = new ArrayList<>();
        View view1 = mInflater.inflate(R.layout.view_pager_1, null);
        View view2 = mInflater.inflate(R.layout.view_pager_2, null);
        View view3 = mInflater.inflate(R.layout.view_pager_3, null);
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mDots = new ArrayList<>();
        ImageView dotFirst = (ImageView) findViewById(R.id.dot_first);
        ImageView dotFSecond = (ImageView) findViewById(R.id.dot_second);
        ImageView dotThrid = (ImageView) findViewById(R.id.dot_thrid);
        mDots.add(dotFirst);
        mDots.add(dotFSecond);
        mDots.add(dotThrid);
        oldPosition = 0;
        //mDots.get(oldPosition).setImageResource(R.drawable.dot_focused);
        //这里我们用颜色来代替 BLACK  RED
        mDots.get(oldPosition).setBackgroundColor(Color.RED);
        MyPagerAdapter adapter = new MyPagerAdapter(mViews);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mDots.get(oldPosition).setBackgroundColor(Color.BLACK);
                mDots.get(position).setBackgroundColor(Color.RED);
                oldPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void timedia(final String strTime) {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                Toast.makeText(MainActivity.this, "" + strTime + "  " + time, Toast.LENGTH_SHORT).show();
            }
        }, 0, 0, true).show();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {
        timer = new Timer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //postJson();
                //postHttp();
            }
        }).start();

        calendarView = findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String strTime = "你选择的时间：" + year + "年" + month + "月" + dayOfMonth + "日";
                Log.e("calendarView", "" + strTime);
                //timedia(strTime);

            }
        });
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mTvMenu = findViewById(R.id.menu);
        listView = findViewById(R.id.menu_list);
        layout = findViewById(R.id.layout_shezhi);
        receiver = new DialogDome(this);
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        menuinit();
        intent = new Intent(MainActivity.this, MyApp7Service.class);
        conn = new MyConn();
        startService(intent);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }


    private void menuinit() {
        mTvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IpSetActivity.class));
                Toast.makeText(MainActivity.this, "我点击了设置按钮", Toast.LENGTH_SHORT).show();
            }
        });
        list = new ArrayList<>();
        list.add(new MenuList(R.mipmap.ic_launcher_round, "红绿灯管理2题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "出行管理8题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "阈值设置7题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "MPAndroidChartTest"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "我的账户1题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "账单管理3题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "车辆违章4题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "二维码37题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "环境指标5题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "实时显示6题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "账户管理9题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "生活助手14题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "生活助手17题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "个人中心20题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "红绿灯管理21题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "账户管理22题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "账户设置23题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "生活助手24题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "路况查询25题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "生活助手27题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "意见反馈31题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "高速路况33题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "红绿灯管理11题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "个人中心16题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "旅游信息33题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "天气信息36题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "地铁查询32题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "定制班车38题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "新闻媒体39题"));
        list.add(new MenuList(R.mipmap.ic_launcher_round, "imageTest"));
        ListAdapterMenu adapter = new ListAdapterMenu(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, HongDengActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ChuXingActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, YuZhi7Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, TestChar.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ChongZhi1Activity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, ZhangDan3Activity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, WeiZhang4Activity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, ErWeiMa37Activity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, HuanJing5Activity.class));
                        break;
                    case 9://实时显示6题
                        startActivity(new Intent(MainActivity.this, XianShi6Activity.class));
                        break;
                    case 10://ETC账单管理9题
                        startActivity(new Intent(MainActivity.this, ETC9Activity.class));
                        break;
                    case 11://生活助手14题
                        startActivity(new Intent(MainActivity.this, TiKu14Activity.class));
                        break;
                    case 12://生活助手17题
                        startActivity(new Intent(MainActivity.this, TiKu17Activity.class));
                        break;
                    case 13://个人中心20题
                        startActivity(new Intent(MainActivity.this, TiKu20Activity.class));
                        break;
                    case 14://21题
                        startActivity(new Intent(MainActivity.this, TiKu21Activity.class));
                        break;
                    case 15://22题
                        startActivity(new Intent(MainActivity.this, TiKu22Activity.class));
                        break;
                    case 16://23题
                        startActivity(new Intent(MainActivity.this, TiKu23Activity.class));
                        break;
                    case 17://24题
                        startActivity(new Intent(MainActivity.this, TiKu24Activity.class));
                        break;
                    case 18://25题
                        startActivity(new Intent(MainActivity.this, TiKu25Activity.class));
                        break;
                    case 19://27题
                        startActivity(new Intent(MainActivity.this, TiKu27Activity.class));
                        break;
                    case 20://31题
                        startActivity(new Intent(MainActivity.this, TiKu31Activity.class));
                        break;
                    case 21://33题
                        startActivity(new Intent(MainActivity.this, TiKu33Activity.class));
                        break;
                    case 22://11题
                        startActivity(new Intent(MainActivity.this, TiKu11Activity.class));
                        break;
                    case 23://16题
                        startActivity(new Intent(MainActivity.this, TiKu16Activity.class));
                        break;
                    case 24://35题
                        startActivity(new Intent(MainActivity.this, TiKu35Activity.class));
                        break;
                    case 25://36题
                        startActivity(new Intent(MainActivity.this, TiKu36ctivity.class));
                        break;
                    case 26://32题
                        startActivity(new Intent(MainActivity.this, TiKu32Activity.class));
                        break;
                    case 27://38题
                        startActivity(new Intent(MainActivity.this, TiKu38Activity.class));
                        break;
                    case 28://新闻媒体39题
                        startActivity(new Intent(MainActivity.this, TiKu39Activity.class));
                        break;
                    case 29://imageTest
                        startActivity(new Intent(MainActivity.this, ImageTouchTestActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unbindService(conn);
        if (intent != null) {
            stopService(intent);
        }

    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyApp7Service.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    NetUtil1 netUtil1 = new NetUtil1();

    private void postHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("CarId", 1);
                    String urlString = "http://192.168.1.106:8080/transportservice/type/jason/action/GetCarAccountBalance.do";
                    URL mUrl = new URL(urlString);
                    HttpURLConnection mConnection = (HttpURLConnection) mUrl.openConnection();
                    // mConnection.setRequestProperty("accept", "*/*");
                    mConnection.setRequestProperty("connection", "Keep-Alive");
                    mConnection.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
                    mConnection.setRequestMethod("POST");
                    mConnection.setConnectTimeout(3000);
                    mConnection.setReadTimeout(3000);
                    mConnection.setDoOutput(true);
                    mConnection.setDoInput(true);
                    OutputStream os = mConnection.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
                    osw.write(jsonObject.toString());
                    osw.flush();
                    if (os != null) {
                        os.close();
                    }
                    if (osw != null) {
                        osw.close();
                    }
                    InputStream in = mConnection.getInputStream();
                    BufferedReader mReader = new BufferedReader(new InputStreamReader(in));
                    final StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = mReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (mReader != null) {
                        mReader.close();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "我接收到的数据" + stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void postJson() {
        //String weatherUrl = "https://free-api.heweather.com/s6/weather?";//location=" + weatherId + "&key=02a55672f62d4149bd2546f2ae665385";
        //String weatherUrl = "https://free-api.heweather.com/s6/weather?{"location":"beijing","key":"02a55672f62d4149bd2546f2ae665385"}";//location=" + weatherId + "&key=02a55672f62d4149bd2546f2ae665385";
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象

        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)"application/json; charset=utf-8"
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("carId", "1");
            jsonObject.put("name", "admin");
            jsonObject.put("UserName", "user1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("jsonObject", "" + jsonObject.toString());
        RequestBody requestBody2 = new FormBody.Builder().add("carId", "1").build();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),
                jsonObject.toString());
        Log.e("requestBody", "" + requestBody.toString());
        //创建一个请求对象
        Request request = new Request.Builder()
                //.url(weatherUrl) //
                .url("http://192.168.43.29:8090/TestJson/Test")
                .post(requestBody2)
                .build();
        //发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            String string = response.body().toString();
            Log.e("MainActivity", "---" + string);
            //判断请求是否成功
//            if(response.isSuccessful()){
//                //打印服务端返回结果
//                Log.e("MainActivity",""+response.toString());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause", "======================");
        if (timer != null) {
            timer.purge();
            timer.cancel();
            Log.e("timer", "======================");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume", "======================");
        if (timer != null) {
            timer = new Timer();
            timer.schedule(new TimerTaskTest3(), 3000, 5000);
        }
    }

    private class TimerTaskTest3 extends TimerTask {
        @Override
        public void run() {
            //postHttp();
            //postJson();
        }
    }
}