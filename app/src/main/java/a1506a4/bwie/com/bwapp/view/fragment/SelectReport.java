package a1506a4.bwie.com.bwapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1506a4.bwie.com.bwapp.R;

/**
 * 作者: 赵虔
 * 时间: 2017/10/21
 * 类作用:
 */

public class SelectReport extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.select_report_fragment, null);
        return view;
    }
}
