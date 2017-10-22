package a1506a4.bwie.com.bwapp.view.activity;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.wxn.locationutil.LocationUtil;
import com.wxn.locationutil.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

import a1506a4.bwie.com.bwapp.BuildConfig;
import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.view.adapter.MyViewPagerAdapter;
import a1506a4.bwie.com.bwapp.view.fragment.MineFragment;
import a1506a4.bwie.com.bwapp.view.fragment.NotificationFragment;
import a1506a4.bwie.com.bwapp.view.fragment.PunchFragment;
import a1506a4.bwie.com.bwapp.view.fragment.ReportFragment;
import a1506a4.bwie.com.bwapp.view.widget.NoScrollViewPager;

import static a1506a4.bwie.com.bwapp.R.color.firebrick;
import static a1506a4.bwie.com.bwapp.R.color.gray;
import static com.baidu.location.h.k.P;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_punch_clock;
    private Button btn_report;
    private Button btn_notification;
    private Button btn_mine;
    private NoScrollViewPager viewpager;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        initView();


        list = new ArrayList<>();
        initData();
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        viewpager.setScroll(false);

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
                } else {
                    // 没有获取到权限，做特殊处理
                    if (BuildConfig.DEBUG) Log.d("MainActivity", "请求权限失败");
                    Toast.makeText(this, "请求权限失败", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
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

        btn_notification.setOnClickListener(this);
        btn_report.setOnClickListener(this);
        btn_punch_clock.setOnClickListener(this);
        btn_mine.setOnClickListener(this);
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
        }
    }


}
