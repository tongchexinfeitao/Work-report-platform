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
import android.widget.Toast;

import a1506a4.bwie.com.bwapp.R;

/**
 * Created by Shadow on 2017/10/13.
 */

public class PunchFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText remark;
    private Button Travel;
    private Button onAndOffDuty;

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
    }

    private void initView() {
        remark = (EditText) view.findViewById(R.id.remark);//备注
        Travel = (Button) view.findViewById(R.id.Travel);//出差打卡
        onAndOffDuty = (Button) view.findViewById(R.id.onAndOffDuty);//上下班打卡
        Travel.setOnClickListener(this);
        onAndOffDuty.setOnClickListener(this);
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
            case R.id.Travel://出差打卡
                Toast.makeText(getActivity(), "出差打卡——>成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.onAndOffDuty://上下班打卡
                Toast.makeText(getActivity(), "上下班打卡——>成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
