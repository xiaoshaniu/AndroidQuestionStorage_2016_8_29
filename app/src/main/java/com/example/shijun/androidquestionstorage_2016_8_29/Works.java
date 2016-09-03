package com.example.shijun.androidquestionstorage_2016_8_29;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import data.Question;
import fragment.Fragment_work1;
import fragment.Fragment_work2;

public class Works extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_next;
    private TextView txt_store;
    private TextView txt_last;
    private TextView work_txt2;
    private TextView work_txt1;
    private int typeid;
    private int flag;
    private int total;
    private RelativeLayout work_title;
    private FrameLayout fl_01;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Question question;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works);
//获取传递过来的数据
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        flag = intent.getIntExtra("flag", 0);
        total = intent.getIntExtra("total", 0);
        question = (Question) intent.getSerializableExtra("question");
        typeid = question.getTypeid();

//

        initView();

        initEvent();

        initButton();

        work_title.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initButton() {


    }

    private void initEvent() {

        work_txt1.setText("第" + (flag + 1));
        work_txt2.setText("/" + total + "道题");
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = manager.beginTransaction();

        if (typeid == 3 || typeid == 4) {
            ft.replace(R.id.fl_01, new Fragment_work1(question));

        } else {
            ft.replace(R.id.fl_01, new Fragment_work2(question));
//            Fragment_work2 tag = (Fragment_work2) manager.findFragmentByTag("apple");
//            txt_content1 = (TextView) tag.view.findViewById(R.id.txt_content1);
        }

        ft.commit();
    }



    private void initView() {
        work_title = (RelativeLayout) findViewById(R.id.works_title);
        work_txt1 = (TextView) findViewById(R.id.work_txt1);
        work_txt2 = (TextView) findViewById(R.id.work_txt2);

        txt_last = (TextView) findViewById(R.id.txt_last);
        txt_store = (TextView) findViewById(R.id.txt_store);
        txt_next = (TextView) findViewById(R.id.txt_next);
//获取控件
        fl_01 = (FrameLayout) findViewById(R.id.fl_01);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
