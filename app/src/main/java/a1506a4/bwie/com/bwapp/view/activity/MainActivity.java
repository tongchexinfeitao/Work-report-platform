package a1506a4.bwie.com.bwapp.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        list = new ArrayList<>();
        initData();
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        viewpager.setScroll(false);
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
