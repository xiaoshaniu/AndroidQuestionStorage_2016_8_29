package com.example.shijun.androidquestionstorage_2016_8_29;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adapter.MyAdapter;
import fragment.Fragment_guide1;
import fragment.Fragment_guide2;
import fragment.Fragment_guide3;

public class GuideActivity extends AppCompatActivity implements  ViewPager.OnPageChangeListener, View.OnClickListener {
    /*
    * 底部小点图片
   */
    private ImageView[] img;

    /*当前下标值*/
    int current;
    private List<Fragment> list;
    private Button btn_use;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
//        比较进入的时间和第一次进入时间的差
        SharedPreferences you = getSharedPreferences("you", MODE_PRIVATE);
        long time = you.getLong("time", 0);
        if (time!=0) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(time));

            long l = calendar.getTimeInMillis() - instance.getTimeInMillis();
            if (l>100){

                SharedPreferences share = getSharedPreferences("test",MODE_PRIVATE);
                boolean flag = share.getBoolean("flag", true);
                if (!flag){
                    startActivity(new Intent(getBaseContext(),SecondActivity.class));
                }

                startActivity(new Intent(getBaseContext(),MainActivity.class));
                this.finish();
            }
        }

        viewpager = (ViewPager) findViewById(R.id.vp_default);
        list = new ArrayList<Fragment>();
        list.add(new Fragment_guide1());
        list.add(new Fragment_guide2());
        list.add(new Fragment_guide3());
        viewpager.setOnPageChangeListener(this);
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));

        initTime();

        initPoint();

        initFormer();



    }





    private void initTime() {
        //存储刚进入的时间
        SharedPreferences preferences = getSharedPreferences("you",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        Date date = new Date();
        long time = date.getTime();
        edit.putLong("time",time);
        edit.commit();
    }

    private void initFormer() {
        Intent intent = new Intent();
        intent.putExtra("xiaoshaniu",true);
//        intent.putExtra("xiaoshaniu","zhuyiyang");
        GuideActivity.this.setResult(RESULT_OK,intent);
    }

    private void initPoint() {

        LinearLayout point = (LinearLayout) findViewById(R.id.line_point);
        img = new ImageView[list.size()];

//        获取小点的图片
        for (int i = 0; i < list.size(); i++) {
            img[i] = (ImageView) point.getChildAt(i);
//            img[i].setEnabled(true);
//            img[i].setOnClickListener(this);
            img[i].setTag(i);   //设置位置tag，方便取出与当前位置对应
        }
        current = 0;
        img[current].setEnabled(false);

        System.out.println("initpoint======");
    }


//    @Override
//    public void onClick(View view) {
//        int position = (int) view.getTag();
//        setCurImg(position);
//
//        System.out.println("onclick=====");
//    }

    private void setCurImg(int position) {
        if (position < 0 || position > list.size() - 1 || position == current) {
            return;
        }
        img[position].setEnabled(false);
        img[current].setEnabled(true);
        current = position;

        System.out.println("setcurimg=======");
    }

    //当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //当新的页面被选中时调用
    @Override
    public void onPageSelected(int position) {
        setCurImg(position);
        if (position==2){
            View view3 = viewpager.getChildAt(position);
            btn_use = (Button) view3.findViewById(R.id.guide_use);
            btn_use.setOnClickListener(this);
        }
    }

    //当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Intent it = new Intent(getBaseContext(), MainActivity.class);
        startActivity(it);
        finish();
    }
}
