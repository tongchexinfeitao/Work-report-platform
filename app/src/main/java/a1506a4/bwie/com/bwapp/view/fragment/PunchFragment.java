package a1506a4.bwie.com.bwapp.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.app.MyApplication;
import a1506a4.bwie.com.bwapp.view.MyCircleView;
import a1506a4.bwie.com.bwapp.view.activity.MainActivity;

import static a1506a4.bwie.com.bwapp.view.activity.MainActivity.NETWORK_STATE_BOOLEAN;
import static a1506a4.bwie.com.bwapp.view.activity.MainActivity.NETWORK_STATE_NAME;
import static android.content.Context.MODE_PRIVATE;
import static com.baidu.location.h.k.s;

/**
 * Created by Shadow on 2017/10/13.
 */

public class PunchFragment extends Fragment implements View.OnClickListener {

    private static final int GO_TO_SETTING_REQUEST_CODE = 105;
    private View view;
    private EditText remark;
    private MyCircleView circleViewLeft;
    private MyCircleView circleViewRight;
    private TextView location;
    private boolean canLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.punch_fragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        //设置两个自定义圆里面文字的内容
        circleViewLeft.setText("上下班打卡");
        circleViewRight.setText("出差打卡");

        //查看是否获得定位的权限
        canLocation = PermissionUtil.CanLocation(getActivity());
        //检查网络并获取定位的位置
        checkNewwork();
    }

    //检查网络并获取定位的位置
    private void checkNewwork() {
        //先检查是否有定位的权限
        if (canLocation) {
            //检查是否有网络
            boolean aBoolean = getActivity().getSharedPreferences(NETWORK_STATE_NAME, MODE_PRIVATE)
                    .getBoolean(NETWORK_STATE_BOOLEAN, false);
            if (aBoolean) {
                LocationUtil.getLocation(MyApplication.getContext());
                LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
                    @Override
                    public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                location.setText("您的位置:" + s + "→" + s1 + "→" + s2);
                            }
                        });
                    }
                });
            }
        } else {
            location.setText("定位功能未开启。");
        }
    }

    private void initView() {
        remark = (EditText) view.findViewById(R.id.remark);//备注

        circleViewLeft = (MyCircleView) view.findViewById(R.id.circleView1);
        circleViewRight = (MyCircleView) view.findViewById(R.id.circleView2);
        circleViewLeft.setOnClickListener(this);
        circleViewRight.setOnClickListener(this);

        //定位成功以后显示的位置
        location = (TextView) view.findViewById(R.id.location);
    }


    //得到备注输入框的内容
    private String getSubmit() {
        String remarkString = remark.getText().toString().trim();
        if (TextUtils.isEmpty(remarkString)) {
            return null;
        }
        return remarkString;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circleView1://上下班打卡
                punch();
                break;
            case R.id.circleView2://出差打卡
                punch();
                break;
        }
    }

    //定位,打卡
    private void punch() {
        //先检查是否有定位的权限
        if (canLocation) {
            //检查是否有网络
            boolean aBoolean = getActivity().getSharedPreferences(NETWORK_STATE_NAME, MODE_PRIVATE)
                    .getBoolean(NETWORK_STATE_BOOLEAN, false);
            if (aBoolean) {
                LocationUtil.getLocation(MyApplication.getContext());
                LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
                    @Override
                    public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                location.setText("您的位置:" + s + "→" + s1 + "→" + s2);
                            }
                        });
                    }
                });
                Toast.makeText(getActivity(), "打卡成功!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "请检查您的网络", Toast.LENGTH_LONG).show();
            }
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("定位权限未打开:")
                    .setMessage("为了正常使用打卡功能,请打开定位权限!")
                    .setPositiveButton("打开", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goToSetting();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断回传吗是否一致
        if (requestCode == GO_TO_SETTING_REQUEST_CODE) {
            //检查用户是否打开定位权限
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //重启页面,完成定位
                getActivity().recreate();
                //重启页面的动画,避免用户看到重启时的闪屏
                getActivity().overridePendingTransition(R.anim.enter_alpha, R.anim.exit_alpha);
            } else {
                Toast.makeText(getActivity(), "定位权限未打开,请重新设置权限", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 跳转到设置权限页面
     */
    private void goToSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, GO_TO_SETTING_REQUEST_CODE);
    }
}
