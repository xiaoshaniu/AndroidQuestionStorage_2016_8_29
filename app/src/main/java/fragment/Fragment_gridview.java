package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.shijun.androidquestionstorage_2016_8_29.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import adapter.GridViewAdapter;
import data.Data;

/**
 * Created by shijun on 2016/8/31.
 */
public class Fragment_gridview extends Fragment{

    private GridView gridview;
    private String url = "http://115.29.136.118:8080/web-question/app/catalog?method=list";
    List<Data> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gridview, null);
        gridview = (GridView) view.findViewById(R.id.gridview);
        initHttp();
        return view;
    }

    private void initEvent() {
        System.out.println("initEvent==========");
        gridview.setAdapter(new GridViewAdapter(getActivity(),list));

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
}
