package com.example.shijun.androidquestionstorage_2016_8_29;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    Handler hd = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
              if (message.obj==1){
                  finish();
              }
            return false;
        }
    });
    private boolean you = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);

                    hd.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                           finish();
                            if (you){
                                Intent it = new Intent(getBaseContext(),MainActivity.class);
                                startActivity(it);
                            }
                            Intent it = new Intent(WelcomeActivity.this,GuideActivity.class);
                            startActivityForResult(it,1);


                            Message msg = new Message();
                            msg.obj = 1;
                            hd.sendMessage(msg);
                        }
                    },100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean me=data.getExtras().getBoolean("xiaoshaniu");
        you = me;
        System.out.println(you);
    }
}
