package com.mytest.fastdev.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mytest.fastdev.FunModule;
import com.mytest.fastdev.R;
import com.mytest.fastdev.recycleview.functions.ScrollablePanelTestActivity;
import com.mytest.fastdev.recycleview.functions.TableViewTestActivity;
import com.mytest.fastdev.recycleview.functions.TransformRecycleView;

import java.util.ArrayList;
import java.util.List;

public class FunctionListActivity_RecycleView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<FunModule> funModuleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_list);

        initDatas();

        recyclerView = (RecyclerView) findViewById(R.id.id_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View rootView = LayoutInflater.from(FunctionListActivity_RecycleView.this).inflate(R.layout.item_function, parent, false);
                return new MyViewHolder(rootView);
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {
                final FunModule m = funModuleList.get(position);
                holder.textView.setText(m.getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(FunctionListActivity_RecycleView.this, m.getActivityClass()));
                    }
                });
            }

            @Override
            public int getItemCount() {
                return funModuleList.size();
            }
        });
    }

    private void initDatas() {
        funModuleList.add(new FunModule("TransformRecycleView", TransformRecycleView.class));
        funModuleList.add(new FunModule("ScrollablePanelTestActivity", ScrollablePanelTestActivity.class));
        funModuleList.add(new FunModule("TableViewTestActivity", TableViewTestActivity.class));
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.section_label);
        }
    }
}
