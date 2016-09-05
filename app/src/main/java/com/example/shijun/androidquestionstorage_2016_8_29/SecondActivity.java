package com.example.shijun.androidquestionstorage_2016_8_29;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.GridViewAdapter;
import data.Data;
import fragment.Fragment_menu;

@ContentView(value = R.layout.activity_second)
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView gridview;
    private String url = "http://115.29.136.118:8080/web-question/app/catalog?method=list";

    private List<Data> list;
    private FragmentManager manager;
    private FragmentTransaction ft;
    boolean flag = true;
    private Fragment_menu menu;
    private LinearLayout linear_layout;
    private DrawerLayout drawer;
    private ListView lv_01;
    private int[] imgview = {R.drawable.home_nav_icon01,R.drawable.home_nav_icon02,R.drawable.home_nav_icon03,R.drawable.home_nav_icon04};
    private String[] text = {"分类练习","题目查找","我得成就","我得收藏"};
    private TextView txt_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);
        x.view().inject(this);

//        昵称
        txt_second = (TextView) findViewById(R.id.txt_second);
//九宫格视图
        gridview = (GridView) findViewById(R.id.gridview);
//listview
        lv_01 = (ListView) findViewById(R.id.lv_01);
        initListview();

//        设置和退出还有搜索
        ImageView search_img = (ImageView) findViewById(R.id.search_img);
        Button btn_change = (Button) findViewById(R.id.btn_change);
        Button btn_exit = (Button) findViewById(R.id.btn_exit);
        search_img.setOnClickListener(this);
        btn_change.setOnClickListener(this);
        btn_exit.setOnClickListener(this);

//侧滑菜单
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        RelativeLayout re_title = (RelativeLayout) findViewById(R.id.re_title);
        re_title.setOnClickListener(this);

//获取服务器数据
        initHttp();



    }

    private void initListview() {
        List<Map<String,Object>> viewlist = getdata();
        SimpleAdapter adapter = new SimpleAdapter(this,viewlist,R.layout.listitem,new String[]{"img","text"},new int[]{R.id.linea_img,R.id.linear_txt});
        lv_01.setAdapter(adapter);

        lv_01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        drawer.closeDrawer(Gravity.LEFT);
                        flag= true;
                    }break;
                    case 1:{
                         startActivity(new Intent(getBaseContext(),SearchActivity.class));
                    }break;
                    case 2:{
                        drawer.closeDrawer(Gravity.LEFT);
                        flag= true;
                    }break;
                    case 3:{
                        Toast.makeText(getBaseContext(),"此功能还未开通",Toast.LENGTH_SHORT).show();
                    }break;
                }
            }
        });
    }

    private List<Map<String, Object>> getdata() {
        List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
        for (int i = 0;i<text.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",imgview[i]);
            map.put("text",text[i]);
            mapList.add(map);
        }
        return mapList;
    }

    private void initEvent() {
        System.out.println("initEvent==========");
       gridview.setAdapter(new GridViewAdapter(getBaseContext(),list));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        startActivity(new Intent(getBaseContext(),GridView1.class));

                    }break;
                    case 1:{

                    }break;
                }
            }
        });
    }

    private void initHttp() {

        list = new ArrayList<Data>();
        RequestParams params = new RequestParams(url);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) throws JSONException {

                JSONArray json = new JSONArray(result);
               for (int i=0;i<json.length();i++){
                   Data data = new Data();
                   JSONObject jsonObject = json.getJSONObject(i);
                   int id = jsonObject.getInt("id");
                   String name = jsonObject.getString("name");
                   String icon = jsonObject.getString("icon");
                   data.setId(id);
                   data.setName(name);
                   data.setIcon(icon);
                   list.add(data);
                   System.out.println(name+icon+id+"=====");
               }
                initEvent();
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

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.re_title:{
                if (flag){
                    drawer.openDrawer(Gravity.LEFT);
                    flag = false;
                }else {
                    drawer.closeDrawer(Gravity.LEFT);
                    flag= true;
                }
            }break;
            case R.id.search_img:{
                startActivity(new Intent(getBaseContext(),SearchActivity.class));
            }break;
            case R.id.btn_change:{
//                startActivity();
                startActivityForResult(new Intent(getBaseContext(),ChangeActivity.class),0);

            }break;
            case R.id.btn_exit:{
//                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();
            }break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


//      通过requestcode来设置textview值，同时也可以通过resultcode来设置
//        if (requestCode==0){
//            txt_second.setText(nicheng);
//            System.out.println("zhuyiyang");
//        }
//        或者是使用resultcode来设置textview值
        if (resultCode==RESULT_OK){
            String nicheng = data.getStringExtra("nicheng");
            txt_second.setText(nicheng);
            System.out.println("shaniuyiyang");
        }


    }


    //    RelativeLayout re_title = (RelativeLayout) findViewById(R.id.re_title);
//        ImageView re_img = (ImageView) findViewById(R.id.re_img);
//        TextView re_txt = (TextView) findViewById(R.id.re_txt);
//        ImageView search_img = (ImageView) findViewById(R.id.search_img);
//        manager = getFragmentManager();
//        ft = manager.beginTransaction();
//        menu = new Fragment_menu();
//        re_title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ft.replace(R.id.frame_layout,menu);
//
//                if (!flag){
//                    flag = true;
//                    ft.commit();
//                }
//            }
//        });

}
