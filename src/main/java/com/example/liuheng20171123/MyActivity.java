package com.example.liuheng20171123;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liufan on 2017/11/23.
 */

public class MyActivity extends AppCompatActivity {

    private GridView gv;
List<String> list = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.my_itme);
        gv = findViewById(R.id.gv);
        gv.setAdapter(new MAadaapter());
        setString();
    }
    class MAadaapter extends BaseAdapter{

        private TextView tv_00;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=View.inflate(MyActivity.this,R.layout.gv_itme,null);
            }
            tv_00 = view.findViewById(R.id.tv_00);
            tv_00.setText(list.get(i));
            return view;
        }
    }
    //初始化数据
    public  void setString(){
        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("体育");
        list.add("军事");
        list.add("科技");
        list.add("财经");
        list.add("时尚");
        list.add("关注");
        list.add("推荐");
        list.add("数码");
        list.add("汽车");
        list.add("问答");
        list.add("教育");
    }
}
