package com.example.shijun.androidquestionstorage_2016_8_29;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
//    下一题的uri
    private String uri = "http://115.29.136.118:8080/web-question/app/question?method=next";
//上一题的uri
    private String url = "http://115.29.136.118:8080/web-question/app/question?method=prev";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Question question;
    private int id1;
    private FragmentManager manager;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works);

        x.view().inject(this);
//获取传递过来的数据
        Intent intent = getIntent();
        id1 = intent.getIntExtra("id", 0);
//        flag = intent.getIntExtra("flag", 0);
        total = intent.getIntExtra("total", 0);
        question = (Question) intent.getSerializableExtra("question");

//
        manager = getSupportFragmentManager();

        initView();

        initEvent(question);

        initButton();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initButton() {
        work_title.setOnClickListener(this);
        txt_last.setOnClickListener(this);
        txt_store.setOnClickListener(this);
        txt_next.setOnClickListener(this);
    }

    private void initEvent(Question quest) {
            ft = manager.beginTransaction();
           int  id = quest.getId();
        if (id>total){
            id= total;
        }
            work_txt1.setText("第" + id);
            work_txt2.setText("/" + total + "道题");

            typeid = quest.getTypeid();

            if (typeid == 3 || typeid == 4) {
                ft.replace(R.id.fl_01, new Fragment_work1(quest));

            } else {
                ft.replace(R.id.fl_01, new Fragment_work2(quest));
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

        switch (view.getId()) {
            case R.id.works_title: {
                finish();
            }
            break;
            case R.id.txt_last: {
                if (id1==1){
                    Toast.makeText(Works.this, "已经是第一题了", Toast.LENGTH_SHORT).show();
                }else {
                    RequestParams params = new RequestParams(url);
                    params.addParameter("id", id1);
                    params.addParameter("user_id", 2);
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) throws JSONException {
                            Question quest = new Question();
                            JSONObject js = new JSONObject(result);
//                     String content = json.getString("content");
//                     System.out.println(content);
//                     JSONObject js = jsonarray.getJSONObject(i);
                            String content = js.getString("content");
                            int id = js.getInt("id");
                            id1 = id;
                            int pubTime = js.getInt("pubTime");
                            int typeid = js.getInt("typeid");
                            String answer = js.getString("answer");
                            if (typeid == 1 || typeid == 2) {
                                String options = js.getString("options");
                                quest.setOptions(options);
                            }
                            quest.setId(id);
                            quest.setAnswer(answer);
                            quest.setContent(content);
                            quest.setPubTime(pubTime);
                            quest.setTypeid(typeid);

                            System.out.println(quest.toString());
                            initEvent(quest);

                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            ex.printStackTrace();
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {
                        }

                        @Override
                        public void onFinished() {
                        }
                    });
                }
            }
            break;
            case R.id.txt_store: {

            }
            break;
            case R.id.txt_next: {
                if (id1>=total){
                    Toast.makeText(Works.this, "已经是最后一题了", Toast.LENGTH_SHORT).show();
                }else{
                    RequestParams params = new RequestParams(uri);
//                params.addBodyParameter("id",String.valueOf(id));
//                params.addBodyParameter("user_id",String.valueOf(2));
                    params.addParameter("id",id1);
                    params.addParameter("user_id",2);
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) throws JSONException {
                            Question quest = new Question();
//                            JSONObject json = new JSONObject(result);
//                            String content = json.getString("content");
                            Toast.makeText(Works.this, "下一题", Toast.LENGTH_SHORT).show();
                            JSONObject js = new JSONObject(result);
//                     String content = json.getString("content");
//                     System.out.println(content);
//                     JSONObject js = jsonarray.getJSONObject(i);
                            String content = js.getString("content");
                            System.out.println(content);
                            int id = js.getInt("id");
                            id1=id;
                            int pubTime = js.getInt("pubTime");
                            int typeid = js.getInt("typeid");
                            String answer = js.getString("answer");
                            if (typeid==1||typeid==2){
                                String options = js.getString("options");
                                quest.setOptions(options);
                            }
                            quest.setId(id);
                            quest.setAnswer(answer);
                            quest.setContent(content);
                            quest.setPubTime(pubTime);
                            quest.setTypeid(typeid);
                            System.out.println(quest.toString());
                            initEvent(quest);
                        }
                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            System.out.println("有毒");
                            ex.printStackTrace();
                        }
                        @Override
                        public void onCancelled(CancelledException cex) {
                        }
                        @Override
                        public void onFinished() {
                        }
                    });
                }

            }
            break;
        }
    }
}
