package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shijun.androidquestionstorage_2016_8_29.R;

/**
 * Created by shijun on 2016/8/31.
 */
public class Fragment_menu extends android.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, null);
        return view;
    }
}
