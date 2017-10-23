package a1506a4.bwie.com.bwapp.view.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import a1506a4.bwie.com.bwapp.R;

/**
 * Created by Shadow on 2017/10/13.
 */

public class SendNotifyFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RecyclerView recyclerView;
    private Button btn_send_notification;
    private Button btn_sendAll_notification;

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

        btn_send_notification.setOnClickListener(this);
        btn_sendAll_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_notification:

                new AlertDialog.Builder(getContext())
                        .setTitle("发送通知")
                        .setMessage("你确定要发送通知吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create().show();
                break;
            case R.id.btn_sendAll_notification:
                new AlertDialog.Builder(getContext())
                        .setTitle("发送通知")
                        .setMessage("你确定要给所有人发送通知吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "逐级发送成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create().show();
                break;
        }
    }
}
