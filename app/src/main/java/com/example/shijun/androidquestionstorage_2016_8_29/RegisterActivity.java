package com.example.shijun.androidquestionstorage_2016_8_29;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;
@ContentView(value = R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText register_account,register_password,register_name,register_phone;
    private Button btn_register;
    private String url = "http://115.29.136.118:8080/web-question/app/registe";
    private RequestParams params;

    //    @ViewInject(value = R.id.btn_register)
//    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        x.view().inject(this);

        System.out.println("========");

        initview();

    }
    private void initview() {
        register_account = (EditText) findViewById(R.id.register_account);
        register_password = (EditText) findViewById(R.id.register_password);
        register_name = (EditText) findViewById(R.id.register_name);
        register_phone = (EditText) findViewById(R.id.register_phone);
        btn_register = (Button) findViewById(R.id.btn_register);

        System.out.println("+++++++++");
       btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String account = register_account.getText().toString();
        String password = register_password.getText().toString();
        String name = register_name.getText().toString();
        String phone = register_phone.getText().toString();

//        System.out.println(account+password+name+phone+"=====");

//        JSONObject json = new JSONObject();
//        try {
//            json.put("username",account);
//            json.put("password",password);
//            json.put("nickname",name);
//            json.put("telephone",phone);

        params = new RequestParams(url);
        params.addBodyParameter("username",account);
        params.addBodyParameter("password",password);
        params.addBodyParameter("nickname",name);
        params.addBodyParameter("telephone",phone);

//            params.addBodyParameter("params",json.toString());

            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) throws JSONException {
                    JSONObject jsonObject = new JSONObject(result);
                    String success = jsonObject.getString("success");
                    System.out.println(success+"=====");
                    if (success.equals("true")){
                        System.out.println("注册成功"+"===");
                        Intent it = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(it);
                    }else {
                        String reason = jsonObject.getString("reason");
                        System.out.println(reason+"===");
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    System.out.println("1111111111");
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
