package a1506a4.bwie.com.bwapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;

import a1506a4.bwie.com.bwapp.R;

public class ReprotActivity extends AppCompatActivity {
    private final String[] types = {"咨询", "讲座", "招聘", "出差", "家访", "帮扶", "会议"};

    private final String[] type0 = {"学生姓名", "学生联系电话", "性别", "效果", "证明人及联系电话", "接待人电话", "备注"};
    private final String[] type1 = {"参加人数", "地点", "效果", "证明人及联系电话", "讲座人电话", "备注"};
    private final String[] type2 = {"参加人数", "地点", "效果", "证明人及联系电话", "招聘主管人电话", "备注"};
    private final String[] type3 = {"需用时长", "地点", "目的", "证明人及联系电话", "主管人电话", "备注"};
    private final String[] type4 = {"家长姓名", "家长联系电话", "学生姓名", "效果", "证明人及联系电话", "负责家访领导电话", "备注"};
    private final String[] type5 = {"地点", "具体内容", "效果", "证明人及联系电话", "帮扶总领导电话", "备注"};
    private final String[] type6 = {"参加人数", "地点", "具体内容", "效果", "证明人及联系电话", "会议主持人电话", "备注"};
    private String type;
    private ListView listview;
    private ArrayList<String[]> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_reprot);
        initView();

        type = getIntent().getStringExtra("type");
        for (int i = 0; i < types.length; i++) {
            if (type.equals(types[i])) {
                String[] strings = list.get(i);
            }
        }
    }

    private void initView() {
        list = new ArrayList<>();
        list.add(type0);
        list.add(type1);
        list.add(type2);
        list.add(type3);
        list.add(type4);
        list.add(type5);
        list.add(type6);
        listview = (ListView) findViewById(R.id.listview);
    }
}
