package a1506a4.bwie.com.bwapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import a1506a4.bwie.com.bwapp.R;

public class ReprotActivity extends AppCompatActivity {
    private final String[] types = {"咨询", "讲座", "招聘", "出差", "家访", "帮扶"};
    //    type6=会议
    private final String[] type0 = {"学生姓名", "学生联系电话", "性别", "效果", "证明人及联系电话", "接到人电话", "备注"};
    private final String[] type1 = {"参加人数", "地点", "效果", "证明人及联系电话", "讲座人电话", "备注"};
    private final String[] type2 = {"参加人数", "地点", "效果", "证明人及联系电话", "招聘主管人电话", "备注"};
    private final String[] type3 = {"需用时长", "地点", "目的", "证明人及联系电话", "主管人电话", "备注"};
    private final String[] type4 = {"家长姓名", "家长联系电话", "学生姓名", "效果", "证明人及联系电话", "负责家访领导电话", "备注"};
    private final String[] type5 = {"学生姓名", "学生联系电话", "性别", "效果", "证明人及联系电话", "备注"};
    private final String[] type6 = {"学生姓名", "学生联系电话", "性别", "效果", "证明人及联系电话", "备注"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprot);
    }
}
