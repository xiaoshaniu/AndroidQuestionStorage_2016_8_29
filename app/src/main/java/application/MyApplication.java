package application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by shijun on 2016/8/30.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
