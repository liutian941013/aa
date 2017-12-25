package com.example.liuheng20171123.Frag;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liuheng20171123.R;
import com.example.liuheng20171123.bean.Bean;
import com.example.liuheng20171123.uite.Dao;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liufan on 2017/11/23.
 */

public class Frag6 extends Fragment {
    int s = 25;
    String str="http://www.93.gov.cn/93app/data.do?channelId=2&startNum="+s;
    List<Bean.DataBean> list = new ArrayList<>();
    private Madaadpter m;
    private PullToRefreshListView p;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        //获取PullToRefreshListView控件
        p = view.findViewById(R.id.pu);
        p.setMode(PullToRefreshBase.Mode.BOTH);
        //添加适配器
        m = new Madaadpter();
        p.setAdapter(m);
        //获取网络数据
        new MyAsynt().execute(str+s);
        p.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                new MyAsynt().execute(str+s);
            }
            //加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                s++;
                new MyAsynt().execute(str+s);
            }
        });
        return view;
    }
    //添加适配器
    class Madaadpter extends BaseAdapter {

        private ImageView iv;
        private TextView tv;
        private TextView tv1;
        private TextView tv_0;
        private TextView tv1_0;

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
            int pa = getItemViewType(i);
            switch (pa){
                case 0:
                    if (view==null){
                        view=View.inflate(getActivity(),R.layout.frag1_1,null);
                    }
                    iv = view.findViewById(R.id.iv);
                    tv = view.findViewById(R.id.tv);
                    tv1 = view.findViewById(R.id.tv1);
                    tv.setText(list.get(i).getTITLE());
                    tv1.setText(list.get(i).getFROMNAME());
                    ImageLoader.getInstance().displayImage(list.get(i).getIMAGEURL(),iv,Dao.getDisplayImageOption());
                    break;
                case 1:
                    if (view==null){
                        view=View.inflate(getActivity(), R.layout.frag1_2,null);
                    }
                    tv_0 = view.findViewById(R.id.tv_0);
                    tv1_0 = view.findViewById(R.id.tv1_0);
                    tv_0.setText(list.get(i).getTITLE());
                    tv1_0.setText(list.get(i).getFROMNAME());
                    break;
            }


            return view;
        }

        @Override
        public int getItemViewType(int position) {
            int d =0;
            if (list.get(position).getIMAGEURL()==null){
                d=1;
            }
            if (list.get(position).getIMAGEURL()!=null){
                d=0;
            }
            return d;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }
    //获取网络数据
    class MyAsynt extends AsyncTask<String,Void,String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson g = new Gson();
            Bean bean = g.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
            //刷新适配器
            m.notifyDataSetChanged();
            //停止刷新
            p.onRefreshComplete();
        }

        @Override
        protected String doInBackground(String... strings) {
            return Dao.setText(strings[0]);
        }
    }

}
