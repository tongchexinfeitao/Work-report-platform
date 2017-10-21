package a1506a4.bwie.com.bwapp.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.view.activity.ReprotActivity;

import static android.R.attr.type;

/**
 * 作者: 赵虔
 * 时间: 2017/10/21
 * 类作用:
 */

public class CommitReport extends Fragment implements View.OnClickListener {
    private final String[] types = {"咨询", "讲座", "招聘", "出差", "家访", "帮扶"};
    private View view;
    private Button dayReport;
    private Button meetingReport;
    private Button actionReport;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.commitreport_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        dayReport = (Button) view.findViewById(R.id.dayReport);
        meetingReport = (Button) view.findViewById(R.id.meetingReport);
        actionReport = (Button) view.findViewById(R.id.actionReport);
        dayReport.setOnClickListener(this);
        meetingReport.setOnClickListener(this);
        actionReport.setOnClickListener(this);
    }

    //日常例会弹出的类型选择dialog
    private void showDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("请选择日汇报的类型:")
                .setSingleChoiceItems(types, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String type = types[which];
                        Intent intent = new Intent(getActivity(), ReprotActivity.class);
                        intent.putExtra("type", type);
                        startActivity(intent);
                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dayReport://日汇报
                showDialog();
                break;
            case R.id.meetingReport://例会汇报
                Intent intent = new Intent(getActivity(), ReprotActivity.class);
                intent.putExtra("type", "会议");
                startActivity(intent);
                break;
            case R.id.actionReport://活动汇报
                Intent intent2 = new Intent(getActivity(), ReprotActivity.class);
                intent2.putExtra("type", "会议");
                startActivity(intent2);
                break;
        }
    }
}
