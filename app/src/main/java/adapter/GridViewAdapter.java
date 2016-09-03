package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shijun.androidquestionstorage_2016_8_29.R;

import org.xutils.x;

import java.util.List;

import data.Data;

/**
 * Created by shijun on 2016/8/31.
 */
public class GridViewAdapter extends BaseAdapter{
    Context context;
    List<Data> list;
    String url = "http://115.29.136.118:8080/web-question/";
    public GridViewAdapter(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        System.out.println("getVIEW=============");
        Data data = list.get(i);
        ViewHolder holder= null;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.grid_img = (ImageView) view.findViewById(R.id.img_grid);
            holder.grid_txt = (TextView) view.findViewById(R.id.txt_grid);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.grid_txt.setText(data.getName());
        x.image().bind(holder.grid_img,url+data.getIcon());
        System.out.println(url+data.getIcon()+data.getName()+"==========");
        return view;
    }

    class ViewHolder{
        private ImageView grid_img;
        private TextView grid_txt;
    }
}
