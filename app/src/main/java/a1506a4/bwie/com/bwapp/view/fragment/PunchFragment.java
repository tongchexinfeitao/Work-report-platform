package a1506a4.bwie.com.bwapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wxn.locationutil.LocationUtil;
import com.wxn.locationutil.PermissionUtil;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.view.MyCircleView;
import a1506a4.bwie.com.bwapp.view.activity.MainActivity;

/**
 * Created by Shadow on 2017/10/13.
 */

public class PunchFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText remark;
    private MyCircleView circleViewLeft;
    private MyCircleView circleViewRight;
    private TextView location;

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

        //定位
        boolean b = PermissionUtil.CanLocation(getActivity());
        if (b) {
            LocationUtil.getLocation(getContext());
            LocationUtil.setMyLocationListener(new LocationUtil.MyLocationListener() {
                @Override
                public void myLocatin(final double v, final double v1, final String s, final String s1, final String s2) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            location.setText("当前位置:" + s + "→" + s1 + "→" + s2);
                        }
                    });
                }
            });
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
    private String submit() {
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
                Toast.makeText(getActivity(), "打卡→成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.circleView2://出差打卡
                Toast.makeText(getActivity(), "打卡→成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
