package com.example.jun.bisaixiangmu;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.activity.AnimTestActivity;
import com.example.jun.bisaixiangmu.activity.BaseMainActivity;
import com.example.jun.bisaixiangmu.activity.ChongZhi1Activity;
import com.example.jun.bisaixiangmu.activity.ChuXingActivity;
import com.example.jun.bisaixiangmu.activity.ETC9Activity;
import com.example.jun.bisaixiangmu.activity.ErWeiMa37Activity;
import com.example.jun.bisaixiangmu.activity.HongDengActivity;
import com.example.jun.bisaixiangmu.activity.HuanJing5Activity;
import com.example.jun.bisaixiangmu.activity.ImageTouchTestActivity;
import com.example.jun.bisaixiangmu.activity.IpSetActivity;
import com.example.jun.bisaixiangmu.activity.TestActivity;
import com.example.jun.bisaixiangmu.activity.TestChar;
import com.example.jun.bisaixiangmu.activity.TestExpandableListActivity;
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
import com.example.jun.bisaixiangmu.activity.TiKu45Activity;
import com.example.jun.bisaixiangmu.activity.WeiZhang4Activity;
import com.example.jun.bisaixiangmu.activity.XianShi6Activity;
import com.example.jun.bisaixiangmu.activity.YuZhi7Activity;
import com.example.jun.bisaixiangmu.activity.ZhangDan3Activity;
import com.example.jun.bisaixiangmu.bean.MenuList;
import com.example.jun.bisaixiangmu.db.YiJian31DB;
import com.example.jun.bisaixiangmu.dialog.MyReceiver;
import com.example.jun.bisaixiangmu.http.HttpUrl;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_ADDRESS;
import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_HTTP;

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
    private MyPagerAdapter adapter;
    private List<ImageView> mDots;//定义一个集合存储三个dot
    private int oldPosition;//记录当前点的位置。
    private Intent intent;
    private MyConn conn;
    private MyApp7Service.MyBinder binder;
    private CalendarView calendarView;
    private Timer timer;
    private NavigationView navView;
    private NetUtil1 netUtil1 = new NetUtil1();

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
        adapter = new MyPagerAdapter(mViews);
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

    //这是弹出选中的时间，小模拟
    public void timedialog(final String strTime) {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                Toast.makeText(MainActivity.this, "" + strTime + "  " + time, Toast.LENGTH_SHORT).show();
            }
        }, 0, 0, true).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                startActivitMenu(item);
                break;
        }
        return true;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_format_list_bulleted_black_24dp);
        }
        initMenu();

        timer = new Timer();
        MyReceiver receiver = new MyReceiver(this);
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


//        receiver = new DialogDome(this);
//        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        intent = new Intent(MainActivity.this, MyApp7Service.class);
        conn = new MyConn();
        startService(intent);
        bindService(intent, conn, BIND_AUTO_CREATE);
//        calendarView = findViewById(R.id.calendar_view);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                String strTime = "你选择的时间：" + year + "年" + month + "月" + dayOfMonth + "日";
//                Log.e("calendarView", "" + strTime);
//                //timedialog(strTime);
//
//            }
//        });
        // mTvMenu = findViewById(R.id.menu);
//        listView = findViewById(R.id.menu_list);
        layout = findViewById(R.id.layout_shezhi);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, IpSetActivity.class));
                Toast.makeText(MainActivity.this, "我点击了设置按钮", Toast.LENGTH_SHORT).show();
            }
        });
        //menuinit();
        testHttp();
    }

    private void testHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = getSharedPreferences("ipset", 0).getString("ip", "192.168.1.106");
                final String address = TEST_HTTP + ip + TEST_ADDRESS;
                JSONObject jsonObject7 = new JSONObject();
                String result7 = HttpUrl.posthttp2(address + "GetAllSense.do", jsonObject7.toString());
            }
        }).start();
    }

    private void initMenu() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivitMenu(item);
                return true;
            }
        });
    }

    private void startActivitMenu(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tiku_1:
                startActivity(new Intent(MainActivity.this, ChongZhi1Activity.class));
                break;
            case R.id.tiku_2:
                startActivity(new Intent(MainActivity.this, HongDengActivity.class));
                break;
            case R.id.tiku_3:
                startActivity(new Intent(MainActivity.this, ZhangDan3Activity.class));
                break;
            case R.id.tiku_4:
                startActivity(new Intent(MainActivity.this, WeiZhang4Activity.class));
                break;
            case R.id.tiku_5:
                startActivity(new Intent(MainActivity.this, HuanJing5Activity.class));
                break;
            case R.id.tiku_6:
                startActivity(new Intent(MainActivity.this, XianShi6Activity.class));
                break;
            case R.id.tiku_7:
                startActivity(new Intent(MainActivity.this, YuZhi7Activity.class));
                break;
            case R.id.tiku_8:
                startActivity(new Intent(MainActivity.this, ChuXingActivity.class));
                break;
            case R.id.tiku_9:
                startActivity(new Intent(MainActivity.this, ETC9Activity.class));
                break;
            case R.id.tiku_11:
                startActivity(new Intent(MainActivity.this, TiKu11Activity.class));
                break;
            case R.id.tiku_14:
                startActivity(new Intent(MainActivity.this, TiKu14Activity.class));
                break;
            case R.id.tiku_16:
                startActivity(new Intent(MainActivity.this, TiKu16Activity.class));
                break;
            case R.id.tiku_17:
                startActivity(new Intent(MainActivity.this, TiKu17Activity.class));
                break;
            case R.id.tiku_20:
                startActivity(new Intent(MainActivity.this, TiKu20Activity.class));
                break;
            case R.id.tiku_21:
                startActivity(new Intent(MainActivity.this, TiKu21Activity.class));
                break;
            case R.id.tiku_22:
                startActivity(new Intent(MainActivity.this, TiKu22Activity.class));
                break;
            case R.id.tiku_23:
                startActivity(new Intent(MainActivity.this, TiKu23Activity.class));
                break;
            case R.id.tiku_24:
                startActivity(new Intent(MainActivity.this, TiKu24Activity.class));
                break;
            case R.id.tiku_25:
                startActivity(new Intent(MainActivity.this, TiKu25Activity.class));
                break;
            case R.id.tiku_27:
                startActivity(new Intent(MainActivity.this, TiKu27Activity.class));
                break;
            case R.id.tiku_31:
                startActivity(new Intent(MainActivity.this, TiKu31Activity.class));
                break;
            case R.id.tiku_32:
                startActivity(new Intent(MainActivity.this, TiKu32Activity.class));
                break;
            case R.id.tiku_33:
                startActivity(new Intent(MainActivity.this, TiKu33Activity.class));
                break;
            case R.id.tiku_35:
                startActivity(new Intent(MainActivity.this, TiKu35Activity.class));
                break;
            case R.id.tiku_36:
                startActivity(new Intent(MainActivity.this, TiKu36ctivity.class));
                break;
            case R.id.tiku_37:
                startActivity(new Intent(MainActivity.this, ErWeiMa37Activity.class));
                break;
            case R.id.tiku_38:
                startActivity(new Intent(MainActivity.this, TiKu38Activity.class));
                break;
            case R.id.tiku_39:
                startActivity(new Intent(MainActivity.this, TiKu39Activity.class));
                break;
            case R.id.tiku_image_test:
                startActivity(new Intent(MainActivity.this, ImageTouchTestActivity.class));
                break;
            case R.id.tiku_test_chart:
                startActivity(new Intent(MainActivity.this, TestChar.class));
                break;
            case R.id.test_expandablelist:
                startActivity(new Intent(MainActivity.this, TestExpandableListActivity.class));
                break;
            case R.id.test_anim:
                startActivity(new Intent(MainActivity.this, AnimTestActivity.class));
                break;
            case R.id.tiku_45:
                startActivity(new Intent(MainActivity.this, TiKu45Activity.class));
                break;
            case R.id.test:
                startActivity(new Intent(MainActivity.this, TestActivity.class));
                break;
            default:
                break;
        }
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

    private String postJson(String url, String json) {
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json;charset=utf-8"), json);
            Request request = new Request.Builder()
                    .url(url)
                    .header("accept", "*/*")
                    .header("connection", "Keep-Alive")
                    .header("Content-Type", "text/html;charset=utf-8")
                    .build();
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause", "======================");
        if (timer != null) {
            timer.purge();
            timer.cancel();
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

    //两次退出test
    //event.getRepeatCount()点后退键的时候，为了防止点得过快，触发两次后退事件，故做此设置
    private long time;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        } else {
            finish();
            System.exit(0);
        }
    }

    private class TimerTaskTest3 extends TimerTask {
        @Override
        public void run() {
            //postHttp();
            //postJson();
            Log.e("MainActtivity", "TimerTaskTest3-----------");
        }
    }
}
