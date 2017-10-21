package a1506a4.bwie.com.bwapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import a1506a4.bwie.com.bwapp.R;

/**
 * Created by Shadow on 2017/10/13.
 */

public class ReportFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button reportButton;
    private Button selectButton;
    private CommitReport commitReport;
    private SelectReport selectReport;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.report_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        reportButton = (Button) view.findViewById(R.id.reportButton);
        selectButton = (Button) view.findViewById(R.id.selectButton);

        reportButton.setOnClickListener(this);
        selectButton.setOnClickListener(this);

        commitReport = new CommitReport();
        selectReport = new SelectReport();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragments, commitReport)
                .add(R.id.fragments, selectReport)
                .hide(selectReport)
                .show(commitReport)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reportButton://汇报
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .show(commitReport)
                        .hide(selectReport)
                        .commit();
                break;
            case R.id.selectButton://查询汇报
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .show(selectReport)
                        .hide(commitReport)
                        .commit();
                break;
        }
    }

}
