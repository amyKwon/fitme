package final_project.mobile.lecture.ma01_20141025.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.dto.fmyData;

/**
 * Created by KwonYeJin on 2016. 12. 23..
 */
public class FinishCustomAdapter extends BaseAdapter {

    private Context context; //inflater 사용등을 위해 필요
    private int layout; //항목 하나를 표현하는 Layout저장
    private ArrayList<fmyData> fmyData; // 원본데이터
    private LayoutInflater inflater; //inflater객체

    public FinishCustomAdapter(Context context, int layout, ArrayList<fmyData> fmyData) {
        this.context=context;
        this.layout = layout;
        this.fmyData =fmyData;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return fmyData.size();
    }

    @Override
    public Object getItem(int position) {
        //position위치의 원본 데이터 반환
        return fmyData.get(position);
    }

    @Override
    public long getItemId(int position) {
        //position위치의 원본데이터 식별자 반환
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position 위치에 해당하는 항목을 표현하는 View를 생성하여 반환
        final int pos = position;//외부변수 사용하기!(상수로 설정)


        if(convertView == null){
            convertView = inflater.inflate(layout,parent,false);
        }

        ImageView iv_fImg = (ImageView)convertView.findViewById(R.id.iv_courseImg);
        TextView tv_courseName = (TextView)convertView.findViewById(R.id.tv_courseName);
        TextView tv_courseTime = (TextView)convertView.findViewById(R.id.tv_courseTime);
        TextView tv_courseCnt = (TextView) convertView.findViewById(R.id.tv_courseCnt);


        fmyData mdata =  fmyData.get(position); //한번에 끄집어 내서 결합시키기


        iv_fImg.setImageResource(mdata.getF_Img());
        tv_courseName.setText(mdata.getF_courseName());
        tv_courseTime.setText(mdata.getF_courseTime());
        tv_courseCnt.setText(mdata.getF_courseCnt());


        return convertView;
    }
}

/*

public class CustomAdapter extends BaseAdapter {



    private Context context; //inflater 사용등을 위해 필요
    private int layout; //항목 하나를 표현하는 Layout저장
    private ArrayList<MyData> myData; // 원본데이터
    private LayoutInflater inflater; //inflater객체
    Random random = new Random();
    public static final int[] icons = new int[] {
            R.mipmap.one, R.mipmap.two, R.mipmap.three,
            R.mipmap.four, R.mipmap.five, R.mipmap.six,
            R.mipmap.seven, R.mipmap.eight, R.mipmap.nine, R.mipmap.ten
    };

    public CustomAdapter(Context context, int layout, ArrayList<MyData> myData) {
        this.context=context;
        this.layout = layout;
        this.myData = myData;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        //원본 데이터의 전체 개수 반환
        return myData.size();
    }

    @Override
    public Object getItem(int position) {
        //position위치의 원본 데이터 반환
        return fmyData.get(position);
    }

    @Override
    public long getItemId(int position) {
        //position위치의 원본데이터 식별자 반환
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position 위치에 해당하는 항목을 표현하는 View를 생성하여 반환
        final int pos = position;//외부변수 사용하기!(상수로 설정)


        if(convertView == null){
            convertView = inflater.inflate(layout,parent,false);
        }

        ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
        TextView exlovername = (TextView)convertView.findViewById(R.id.exlovername);
        TextView loveperiod = (TextView)convertView.findViewById(R.id.loveperiod);
        TextView lovegage = (TextView) convertView.findViewById(R.id.lovegage);

//        EditText edit_exlover = (EditText)convertView.findViewById(R.id.edit_exlover);
//        EditText edit_loveperiod = (EditText)convertView.findViewById(R.id.edit_loveperiod);
//        EditText edit_enddate = (EditText)convertView.findViewById(R.id.edit_enddate);
//        EditText edit_lovegage = (EditText)convertView.findViewById(R.id.edit_lovegage);
//        EditText edit_endcause = (EditText)convertView.findViewById(R.id.edit_endcause);
//        EditText edit_fightcause = (EditText)convertView.findViewById(R.id.edit_fightcause);
//        ImageButton btnsave = (ImageButton)convertView.findViewById(R.id.btn_save);
//        ImageButton btnreset = (ImageButton)convertView.findViewById(R.id.btn_reset);
        MyData mdata =  myData.get(position); //한번에 끄집어 내서 결합시키기


        icon.setImageResource(icons[random.nextInt(10)]);
        exlovername.setText(mdata.getExlover());
        loveperiod.setText(mdata.getLoveperiod());
        lovegage.setText(mdata.getAffection());


        return convertView;
    }
}

 */