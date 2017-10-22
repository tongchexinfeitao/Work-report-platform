package a1506a4.bwie.com.bwapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import a1506a4.bwie.com.bwapp.R;

import static android.R.attr.name;
import static android.R.attr.type;

public class ReprotActivity extends AppCompatActivity implements View.OnClickListener {
    private final String[] types = {"咨询", "讲座", "招聘", "出差", "家访", "帮扶", "会议"};

    private final String[] type0 = {"学生姓名:", "学生联系电话:", "性别:", "效果:", "证明人及联系电话:", "接待人电话:", "备注:"};//7
    private final String[] type1 = {"参加人数:", "地点:", "效果:", "证明人及联系电话:", "讲座人电话:", "备注:"};//6
    private final String[] type2 = {"参加人数:", "地点:", "效果:", "证明人及联系电话:", "招聘主管人电话:", "备注:"};//6
    private final String[] type3 = {"需用时长:", "地点:", "目的:", "证明人及联系电话:", "主管人电话:", "备注:"};//6
    private final String[] type4 = {"家长姓名:", "家长联系电话:", "学生姓名:", "效果:", "证明人及联系电话:", "负责家访领导电话:", "备注:"};//7
    private final String[] type5 = {"地点:", "具体内容:", "效果:", "证明人及联系电话:", "帮扶总领导电话:", "备注:"};//6
    private final String[] type6 = {"参加人数:", "地点:", "具体内容:", "效果:", "证明人及联系电话:", "会议主持人电话:", "备注:"};//7

    private ArrayList<String[]> list;//保存七种汇报类型的集合
    private String[] strings;
    private LinearLayout layout6;
    private TextView name0;
    private TextView name1;
    private TextView name2;
    private TextView name3;
    private TextView name4;
    private TextView name5;
    private TextView name6;
    private EditText content0;
    private EditText content1;
    private EditText content2;
    private EditText content3;
    private EditText content4;
    private EditText content5;
    private EditText content6;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.firebrick));
        setContentView(R.layout.activity_reprot);
        initView();
        //跳转传过来的标题
        String Intent_title = getIntent().getStringExtra("title");
        //跳转传过来的汇报类型
        String Intent_type = getIntent().getStringExtra("type");
        //设置标题内容
        this.title.setText(Intent_title + "→" + Intent_type);
        for (int i = 0; i < types.length; i++) {
            if (Intent_type.equals(types[i])) {
                strings = list.get(i);
            }
        }
        //判断type传过来的类型是哪个,然后根据类型来设置要显示输入框的个数
        if (strings.length == types.length - 1) {//6个内容
            layout6.setVisibility(View.GONE);
            name0.setText(strings[0]);
            name1.setText(strings[1]);
            name2.setText(strings[2]);
            name3.setText(strings[3]);
            name4.setText(strings[4]);
            name5.setText(strings[5]);
        } else if (strings.length == types.length) {//7个内容
            layout6.setVisibility(View.VISIBLE);
            name0.setText(strings[0]);
            name1.setText(strings[1]);
            name2.setText(strings[2]);
            name3.setText(strings[3]);
            name4.setText(strings[4]);
            name5.setText(strings[5]);
            name6.setText(strings[6]);
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
        Button commit = (Button) findViewById(R.id.commit);//提交按钮
        Button gouBack = (Button) findViewById(R.id.gouBack);//返回按钮
        commit.setOnClickListener(this);
        gouBack.setOnClickListener(this);
        //标题名字
        title = (TextView) findViewById(R.id.title);
        //输入框
        content0 = (EditText) findViewById(R.id.content0);
        content1 = (EditText) findViewById(R.id.content1);
        content2 = (EditText) findViewById(R.id.content2);
        content3 = (EditText) findViewById(R.id.content3);
        content4 = (EditText) findViewById(R.id.content4);
        content5 = (EditText) findViewById(R.id.content5);
        content6 = (EditText) findViewById(R.id.content6);

        name0 = (TextView) findViewById(R.id.name0);
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        name4 = (TextView) findViewById(R.id.name4);
        name5 = (TextView) findViewById(R.id.name5);
        name6 = (TextView) findViewById(R.id.name6);
        /*LinearLayout layout0 = (LinearLayout) findViewById(R.id.layout0);
        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
        LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
        LinearLayout layout5 = (LinearLayout) findViewById(R.id.layout5);*/
        layout6 = (LinearLayout) findViewById(R.id.layout6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit://提交

                break;
            case R.id.gouBack://返回
                finish();
                break;
        }
    }
}
