package a1506a4.bwie.com.bwapp.view.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import a1506a4.bwie.com.bwapp.R;
import a1506a4.bwie.com.bwapp.view.adapter.MyRecyclerViewAdapter;

/**
 * Created by Shadow on 2017/10/13.
 */

public class SendNotifyFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RecyclerView recyclerView;
    private Button btn_send_notification;
    private Button btn_sendAll_notification;
    private EditText et_content;
    private TextView tv_warning;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.send_notification_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        btn_send_notification = (Button) view.findViewById(R.id.btn_send_notification);
        btn_sendAll_notification = (Button) view.findViewById(R.id.btn_sendAll_notification);
        et_content = (EditText) view.findViewById(R.id.et_content);
        tv_warning = (TextView) view.findViewById(R.id.tv_warning);

        btn_send_notification.setOnClickListener(this);
        btn_sendAll_notification.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MyRecyclerViewAdapter());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_notification:
                if (!TextUtils.isEmpty(et_content.getText().toString().trim())) {
                    tv_warning.setVisibility(View.GONE);
                    new AlertDialog.Builder(getContext())
                            .setTitle("发送通知")
                            .setMessage("你确定要发送通知吗")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create().show();
                } else {
                    tv_warning.setVisibility(View.VISIBLE);
                }


                break;
            case R.id.btn_sendAll_notification:
                if (!TextUtils.isEmpty(et_content.getText().toString().trim())) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("发送通知")
                            .setMessage("你确定要给所有人发送通知吗")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), "逐级发送成功", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create().show();
                } else {
                    tv_warning.setVisibility(View.VISIBLE);
                }


                break;
        }
    }

    private void submit() {
        // validate
        String content = et_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(getContext(), "content不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
