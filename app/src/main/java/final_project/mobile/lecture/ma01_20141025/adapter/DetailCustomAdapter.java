package final_project.mobile.lecture.ma01_20141025.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.dto.detailData;

/**
 * Created by KwonYeJin on 2016. 12. 26..
 */
public class DetailCustomAdapter extends BaseAdapter {

    private Context context; //inflater 사용등을 위해 필요
    private int layout; //항목 하나를 표현하는 Layout저장
    private ArrayList<final_project.mobile.lecture.ma01_20141025.dto.detailData> dData; // 원본데이터
    private LayoutInflater inflater; //inflater객체

    public DetailCustomAdapter(Context context, int layout, ArrayList<detailData> dData) {
        this.context=context;
        this.layout = layout;
        this.dData =dData;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position 위치에 해당하는 항목을 표현하는 View를 생성하여 반환
        final int pos = position;//외부변수 사용하기!(상수로 설정)


        if(convertView == null){
            convertView = inflater.inflate(layout,parent,false);
        }


        TextView detailName = (TextView)convertView.findViewById(R.id.detailName);
        CheckBox rbtn =  (CheckBox)convertView.findViewById(R.id.radioFinishExercise);


        detailData mdata =  dData.get(position); //한번에 끄집어 내서 결합시키기


        detailName.setText(mdata.getDetailName());

        return convertView;
    }
}
