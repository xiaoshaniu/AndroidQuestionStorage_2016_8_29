package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shijun.androidquestionstorage_2016_8_29.R;

/**
 * Created by shijun on 2016/8/29.
 */
public class Fragment_guide3 extends Fragment {


    public static Button btn_use;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.guide3, null);


//        btn_use = (Button) view.findViewById(R.id.guide_use);
//        btn_use.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(getActivity(), MainActivity.class);
//                startActivity(it);
//            }
//        });
        return view;
    }


}
