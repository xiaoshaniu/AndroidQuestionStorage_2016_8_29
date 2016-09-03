package fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shijun.androidquestionstorage_2016_8_29.R;

import data.Question;

/**
 * Created by shijun on 2016/9/2.
 */
@SuppressLint("ValidFragment")
public class Fragment_work1 extends Fragment {
    public View view;
    private Question datas;
    @SuppressLint("ValidFragment")
    public Fragment_work1(Question datas) {
        this.datas = datas;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.work_fragment1, null);


        String content = datas.getContent();
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        TextView txt_daan = (TextView) view.findViewById(R.id.txt_daan);
        TextView txt_answer = (TextView) view.findViewById(R.id.txt_answer);

        txt_content.setText(content);
        txt_daan.setText("答案");
        txt_answer.setText(datas.getAnswer());

        return view;
    }
}
