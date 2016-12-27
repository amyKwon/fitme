package final_project.mobile.lecture.ma01_20141025.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.dto.HealthCenterData;

/**
 * Created by cooling on 2016-10-09.
 */

public class MyHealthAdapter extends BaseAdapter  {

    LayoutInflater inflater;
    Context context;
    int layout;
    ArrayList<HealthCenterData> list;


    public MyHealthAdapter(Context context, int resource, ArrayList<HealthCenterData> list) {
        this.context = context;
        this.layout = resource;
        this.list = list;
//        check
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
//        check
        return list.size();
    }

    @Override
    public HealthCenterData getItem(int position) {
//        check
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
//        check
        return (long) position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

//        현재 위치에 대응하는 list 의 객체 준비
        HealthCenterData dto = list.get(position);

//        각 view 에 dto 에 저장되어 있는 값 설정
        TextView textName = (TextView) convertView.findViewById(R.id.hTitle);
        TextView textAddress = (TextView) convertView.findViewById(R.id.hAddrr);
        ImageView ivimage = (ImageView) convertView.findViewById(R.id.ivImage);

        textName.setText(dto.getName());
        textAddress.setText(dto.getAddress());
        ivimage.setImageResource(R.mipmap.ic_launcher);


//        GetImageAsyncTask 를 사용하여 dto 에 저장한 이미지의 주소에서 image 가져오기 실행


        return convertView;
    }

    public void setList(ArrayList<HealthCenterData> list) {
        this.list = list;
    }


    class GetImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap bitmap = null;
            String imageAddress = params[0];

            try {
                URL Url = new URL(imageAddress);
                URLConnection imageConn = Url.openConnection();
                imageConn.connect();

//                이미지 크기 확인
                int imageLength = imageConn.getContentLength();
//                InputStream 을 가져와 BufferedInputStream 으로 변환
                BufferedInputStream bis = new BufferedInputStream(imageConn.getInputStream(), imageLength);
//                Stream 을 Bitmap 으로 변환
                bitmap = BitmapFactory.decodeStream(bis);

                bis.close();
            }
            // 파일이 없을 경우를 고려할 필요
            catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

    }

}
