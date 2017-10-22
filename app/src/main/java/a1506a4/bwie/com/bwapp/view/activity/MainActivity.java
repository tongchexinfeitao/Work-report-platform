package a1506a4.bwie.com.bwapp.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wxn.locationutil.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.view.adapter.MyViewPagerAdapter;
import a1506a4.bwie.com.bwapp.view.fragment.MineFragment;
import a1506a4.bwie.com.bwapp.view.fragment.NotificationFragment;
import a1506a4.bwie.com.bwapp.view.fragment.PunchFragment;
import a1506a4.bwie.com.bwapp.view.fragment.ReportFragment;
import a1506a4.bwie.com.bwapp.view.widget.NoScrollViewPager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_punch_clock;
    private Button btn_report;
    private Button btn_notification;
    private Button btn_mine;
    private NoScrollViewPager viewpager;
    private List<Fragment> list;
    //用来保存网络状态的SharedPreferences()的名字
    public static final String NETWORK_STATE_NAME = "NETWORK_STATE_NAME";
    //保存是否有网络的boolean值
    public static final String NETWORK_STATE_BOOLEAN = "NETWORK_STATE_BOOLEAN";
    //跳转网络设置的请求码
    public static final int SET_NETWORK_REQUEST = 106;
    private RelativeLayout networkLayout;
    private SharedPreferences networkSP;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        getWindow().setStatusBarColor(getResources().getColor(R.color.firebrick));
        setContentView(R.layout.activity_main);
        initView();
        //保存网络状态的SharedPreferences
        networkSP = getSharedPreferences(NETWORK_STATE_NAME, MODE_PRIVATE);
        list = new ArrayList<>();
        initData();
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        viewpager.setScroll(false);
        //注册监听网络状态的广播
        IntentFilter intent = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(new NetworkBroadcastReceiver(), intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case PermissionUtil.LOCATION_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 第一次获取到权限，请求定位
                    Toast.makeText(this, "定位中...", Toast.LENGTH_SHORT).show();
                    //重启页面,完成定位
                    recreate();
                    //重启页面的动画,避免用户看到重启时的闪屏
                    overridePendingTransition(R.anim.enter_alpha, R.anim.exit_alpha);
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断是不是跳转网络设置的请求码
        if (requestCode == SET_NETWORK_REQUEST) {
            //判断当前是否有网
            if (networkSP.getBoolean(NETWORK_STATE_BOOLEAN, false)) {
                //重启页面,完成定位
                recreate();
                //重启页面的动画,避免用户看到重启时的闪屏
                overridePendingTransition(R.anim.enter_alpha, R.anim.exit_alpha);
            }
        }
    }

    private void initData() {
        list.add(new PunchFragment());
        list.add(new ReportFragment());
        list.add(new NotificationFragment());
        list.add(new MineFragment());
    }

    private void initView() {
        btn_punch_clock = (Button) findViewById(R.id.btn_punch_clock);
        btn_report = (Button) findViewById(R.id.btn_report);
        btn_notification = (Button) findViewById(R.id.btn_notification);
        btn_mine = (Button) findViewById(R.id.btn_mine);
        viewpager = (NoScrollViewPager) findViewById(R.id.viewpager);
        //没有网络的时候显示的提示栏
        networkLayout = (RelativeLayout) findViewById(R.id.networkLayout);
        btn_notification.setOnClickListener(this);
        btn_report.setOnClickListener(this);
        btn_punch_clock.setOnClickListener(this);
        btn_mine.setOnClickListener(this);
        networkLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_punch_clock:
                viewpager.setCurrentItem(0);
                break;
            case R.id.btn_report:
                viewpager.setCurrentItem(1);
                break;
            case R.id.btn_notification:
                viewpager.setCurrentItem(2);
                break;
            case R.id.btn_mine:
                viewpager.setCurrentItem(3);
                break;
            case R.id.networkLayout://显示没有网络的横幅,点击跳转设置页面
                Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                startActivityForResult(intent, SET_NETWORK_REQUEST);
                break;
        }
    }

    //用来检测网络的广播
    class NetworkBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    networkSP.edit().putBoolean(NETWORK_STATE_BOOLEAN, true).commit();
                    //有网的时候提示没网的标题影藏
                    networkLayout.setVisibility(View.GONE);
                } else {
                    networkSP.edit().putBoolean(NETWORK_STATE_BOOLEAN, false).commit();
                    //没网的时候显示没网的标题栏
                    networkLayout.setVisibility(View.VISIBLE);
                }

            }
        }
    }


}
