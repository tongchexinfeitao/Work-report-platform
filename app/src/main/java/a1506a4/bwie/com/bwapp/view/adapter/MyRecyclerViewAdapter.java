package a1506a4.bwie.com.bwapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import a1506a4.bwie.com.bwapp.R;

/**
 * Created by Shadow on 2017/10/23.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    List<String> list;

    public MyRecyclerViewAdapter() {
        initData();
    }

    private void initData() {
        list=new ArrayList<>();
        list.add("区域经理A");
        list.add("区域经理B");
        list.add("区域经理C");
        list.add("区域经理D");
        list.add("区域经理E");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理F");
        list.add("区域经理G");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.checkbox.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
