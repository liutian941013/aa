package com.example.liuheng20171123;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liuheng20171123.Frag.Frag1;
import com.example.liuheng20171123.Frag.Frag10;
import com.example.liuheng20171123.Frag.Frag2;
import com.example.liuheng20171123.Frag.Frag3;
import com.example.liuheng20171123.Frag.Frag4;
import com.example.liuheng20171123.Frag.Frag5;
import com.example.liuheng20171123.Frag.Frag6;
import com.example.liuheng20171123.Frag.Frag7;
import com.example.liuheng20171123.Frag.Frag8;
import com.example.liuheng20171123.Frag.Frag9;
import com.example.liuheng20171123.uite.Dao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
List<String> list = new ArrayList<>();
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int netype = Dao.getNetype(this);
        if (netype==1){
            Toast.makeText(this,"当前是wif",Toast.LENGTH_LONG).show();
        }else if (netype==2){
            Toast.makeText(this,"当前是移动网络",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"当前无网络",Toast.LENGTH_LONG).show();

        }
        //获取控件
        tab = findViewById(R.id.Tab);
        vp = findViewById(R.id.vp);
        bt = findViewById(R.id.tb);
        settext();
        //设置模式
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //与ViewPager关联
        tab.setupWithViewPager(vp);
        //设置适配器
        vp.setAdapter(new MAadapter(getSupportFragmentManager()));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyActivity.class);
                startActivity(intent);
            }
        });
    }
    class MAadapter extends FragmentPagerAdapter{

        public MAadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f =null;
            switch (position){
                case 0:
                    f=new Frag1();
                    break;
                case 1:
                    f=new Frag2();
                    break;
                case 2:
                    f=new Frag3();
                    break;
                case 3:
                    f=new Frag4();
                    break;
                case 4:
                    f=new Frag5();
                    break;
                case 5:
                    f=new Frag6();
                    break;
                case 6:
                    f=new Frag7();
                    break;
                case 7:
                    f=new Frag8();
                    break;
                case 8:
                    f=new Frag9();
                    break;
                case 9:
                    f=new Frag10();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }
    public void settext(){
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
    }
}
