package com.example.shijun.androidquestionstorage_2016_8_29;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_account,edt_password;
    private Button btn_login,btn_forget,btn_register;
    private EditText[] edt;
    private int index= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        x.view().inject(this);

        initview();

        initEvent();

    }


    private void initEvent() {
        btn_login.setOnClickListener(this);
        btn_forget.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    private void initview() {
        edt_account = (EditText) findViewById(R.id.edt_account);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_forget = (Button) findViewById(R.id.btn_forget);
        btn_register = (Button) findViewById(R.id.btn_register);
    }

    @Override
    public void onClick(View view) {
        String url = "http://115.29.136.118:8080/web-question/app/login";
        select(view);
        switch (view.getId()){

            case R.id.btn_login:{
                String account = edt_account.getText().toString();
                String password = edt_password.getText().toString();

                RequestParams params = new RequestParams(url);
                params.addBodyParameter("username",account);
                params.addBodyParameter("password",password);

                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) throws JSONException {
                        JSONObject js = new JSONObject(result);
                        String success = js.getString("success");
                        if (success.equals("true")){
                            System.out.println("登入成功");
                            Toast.makeText(MainActivity.this,"登入成功",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(),SecondActivity.class));

                            finish();
                        }else {
                            System.out.println("登入失败"+js.getString("reason"));
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace();
                        System.out.println("有错误");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }break;
            case R.id.btn_forget:{

            }break;
            case R.id.btn_register:{
                Intent it = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(it);
            }break;
        }
    }

    private void select(View view) {

        btn_login.setSelected(view.getId()==R.id.btn_login);

    }
}
