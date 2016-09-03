package com.example.shijun.androidquestionstorage_2016_8_29;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_search;
    private Button btn_search;
    private ListView lv_03;
    private String url = "http://115.29.136.118:8080/web-question/app/question?method=list";
    private String[] type = {"单选","多选","判断","简答"};
    private String totalElements;
    private List<Question> list ;
    private RelativeLayout search_title;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        x.view().inject(this);

        initView();

        initEvent();

    }

    private void initEvent() {


      btn_search.setOnClickListener(this);
        search_title.setOnClickListener(this);

    }

    private void initView() {
        search_title = (RelativeLayout) findViewById(R.id.search_title);
        edt_search = (EditText) findViewById(R.id.edt_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        lv_03 = (ListView) findViewById(R.id.lv_03);
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
        lv_03.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        lv_03.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), Works.class);
                intent.putExtra("flag",i);
                intent.putExtra("total",totalElements);
                intent.putExtra("question",list.get(i));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        String search = edt_search.getText().toString();
        switch (view.getId()){
            case R.id.btn_search:{

                    list = new ArrayList<Question>();
                    RequestParams params = new RequestParams(url);
                    params.addParameter("questionName",search);
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) throws JSONException {

                            JSONObject json = new JSONObject(result);
                            JSONArray jsonarray = json.getJSONArray("content");
                            totalElements = json.getString("totalElements");
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

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });

            }break;
            case R.id.search_title:{
                finish();
            }break;
        }

    }
}
