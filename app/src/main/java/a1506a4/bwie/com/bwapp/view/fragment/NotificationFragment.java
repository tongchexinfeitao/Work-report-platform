package a1506a4.bwie.com.bwapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import a1506a4.bwie.com.bwapp.R;

/**
 * Created by Shadow on 2017/10/13.
 */

public class NotificationFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button reportButton;
    private Button selectButton;
    private SendNotifyFragment sendNotifyFragment;
    private ReceiveNotifyFragment receiveNotifyFragment;
    private TextView tv_selectAll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notification_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(View view) {
        reportButton = (Button) view.findViewById(R.id.reportButton);
        selectButton = (Button) view.findViewById(R.id.selectButton);
        tv_selectAll = (TextView) view.findViewById(R.id.tv_selectAll);
        reportButton.setOnClickListener(this);
        selectButton.setOnClickListener(this);

        sendNotifyFragment = new SendNotifyFragment();
        receiveNotifyFragment = new ReceiveNotifyFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, sendNotifyFragment)
                .add(R.id.frameLayout, receiveNotifyFragment)
                .hide(receiveNotifyFragment)
                .show(sendNotifyFragment)
                .commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.reportButton://汇报
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .show(sendNotifyFragment)
                        .hide(receiveNotifyFragment)
                        .commit();
                tv_selectAll.setVisibility(View.VISIBLE);

                break;
            case R.id.selectButton://查询汇报
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .show(receiveNotifyFragment)
                        .hide(sendNotifyFragment)
                        .commit();
                tv_selectAll.setVisibility(View.GONE);
                break;
        }

    }
}
