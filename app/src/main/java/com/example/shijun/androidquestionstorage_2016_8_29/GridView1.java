package com.example.shijun.androidquestionstorage_2016_8_29;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.Question;

public class GridView1 extends AppCompatActivity implements View.OnClickListener {

    private String url1 = "http://115.29.136.118:8080/web-question/app/question?method=list";
    private List<Question> list = new ArrayList<Question>();
    private ListView lv_02;
    private RelativeLayout relative1;
    private String[] type = {"单选","多选","判断","简答"};
    private int totalElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view1);
        x.view().inject(this);

        initView();

        initHttp();

        relative1.setOnClickListener(this);

    }

    private void initView() {
        relative1 = (RelativeLayout) findViewById(R.id.relative1);
        lv_02 = (ListView) findViewById(R.id.lv_02);
    }

    private void initHttp() {
        RequestParams params = new RequestParams(url1);
        params.addParameter("catalogId",1);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) throws JSONException {
                JSONObject json = new JSONObject(result);
                JSONArray jsonarray = json.getJSONArray("content");
                totalElements = json.getInt("totalElements");
                for (int i =0;i<jsonarray.length();i++){
                    Question question = new Question();
                    JSONObject js = jsonarray.getJSONObject(i);
                    String content = js.getString("content");
                    int id = js.getInt("id");
                    int pubTime = js.getInt("pubTime");
                    int typeid = js.getInt("typeid");
                    String answer = js.getString("answer");
                    if (typeid==1||typeid==2){
                        String options = js.getString("options");
                        question.setOptions(options);
                    }


                    question.setId(id);
                    question.setAnswer(answer);
                    question.setContent(content);
                    question.setPubTime(pubTime);

                    question.setTypeid(typeid);

                    System.out.println(id+answer+content+pubTime+typeid+"==============");
                    list.add(question);
                }

                initListView();

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                System.out.println("errorquququququ"+"========== ");
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {

            }
        });
    }

    private void initListView() {
        List<Map<String,Object>> maplist = new ArrayList<Map<String,Object>>();
        for (Question temp:list){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("text1",temp.getContent());
            if (temp.getTypeid()==1){
                map.put("text2",type[0]);
            }else if (temp.getTypeid()==2){
                map.put("text2",type[1]);
            }else if (temp.getTypeid()==3){
                map.put("text2",type[2]);
            }else if (temp.getTypeid()==4){
                map.put("text2",type[3]);
            }
            int pubTime = temp.getPubTime();
            Date date = new Date(pubTime);
            SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
            String format1 = format.format(date);
            map.put("text3",format1);
            maplist.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,maplist,R.layout.menu,new String[]{"text1","text2","text3"},new int[]{R.id.menu_txt1,R.id.menu_txt2,R.id.menu_txt3});
        lv_02.setAdapter(adapter);

        lv_02.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), Works.class);
                intent.putExtra("flag",i);
                intent.putExtra("total",totalElements);
                intent.putExtra("question",list.get(i));
                intent.putExtra("id",list.get(i).getId());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
