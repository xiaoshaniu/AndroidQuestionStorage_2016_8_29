package fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shijun.androidquestionstorage_2016_8_29.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import data.Choice;
import data.Question;

/**
 * Created by shijun on 2016/9/2.
 */
@SuppressLint("ValidFragment")
public class Fragment_work2 extends Fragment {
    private TextView txt_content1;
    private CheckBox checkbox1;
    private TextView txt_check1;
    private CheckBox checkbox2;
    private TextView txt_check2;
    private CheckBox checkbox3;
    private TextView txt_check3;
    private CheckBox checkbox;
    private TextView txt_check;
    public View view;
    private Question question;
    private List<Choice> list;
    @SuppressLint("ValidFragment")
    public Fragment_work2(Question question) {
        this.question=question;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.work_fragment2, null);


//        Fragment_work2 tag = (Fragment_work2) manager.findFragmentByTag("apple");
            txt_content1 = (TextView) view.findViewById(R.id.txt_content1);
            txt_content1.setText(question.getContent());

            checkbox = (CheckBox) view.findViewById(R.id.checkbox);
            txt_check = (TextView) view.findViewById(R.id.txt_check);
            checkbox1 = (CheckBox) view.findViewById(R.id.checkbox1);
            txt_check1 = (TextView) view.findViewById(R.id.txt_check1);
            checkbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
            txt_check2 = (TextView) view.findViewById(R.id.txt_check2);
            checkbox3 = (CheckBox) view.findViewById(R.id.checkbox3);
            txt_check3 = (TextView) view.findViewById(R.id.txt_check3);



        String options = question.getOptions();
        JSONArray json = null;
        try {
            json = new JSONArray(options);
            for (int j = 0;j <json.length();j++){
                JSONObject jsonObject = json.getJSONObject(j);
                String title = jsonObject.getString("title");
                String checked = jsonObject.getString("checked");

                 if (j==0){
                     txt_check.setText(title);
                     if (checked.equals("true")){
                         checkbox.setChecked(true);
                     }
                 }else if(j==1){
                     txt_check1.setText(title);
                     if (checked.equals("true")){
                         checkbox1.setChecked(true);
                     }
                 }else if(j==2){
                     txt_check2.setText(title);
                     if (checked.equals("true")){
                         checkbox2.setChecked(true);
                     }
                 }else if(j==3){
                     txt_check3.setText(title);
                     if (checked.equals("true")){
                         checkbox3.setChecked(true);
                     }
                 }

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }



        return view;
    }
}
