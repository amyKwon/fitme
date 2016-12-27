package final_project.mobile.lecture.ma01_20141025.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.SharedPreferenceUtil;

public class SplashActivity extends AppCompatActivity {

    Drawable sdrawable;
    LinearLayout splashLayout;
    SharedPreferenceUtil pref = new SharedPreferenceUtil(this);
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashLayout = (LinearLayout)findViewById(R.id.splash);



        Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.drawable.splash ); // 비트맵 이미지를 만든다.
        int width=(int)(getWindowManager().getDefaultDisplay().getWidth()); // 가로 사이즈 지정
        int height=(int)(getWindowManager().getDefaultDisplay().getHeight() * 0.8); // 세로 사이즈 지정
        Bitmap resizedbitmap =Bitmap.createScaledBitmap(bmp, width, height, true); // 이미지 사이즈 조정

        sdrawable = new BitmapDrawable(getResources(),resizedbitmap);
        splashLayout.setBackgroundDrawable(sdrawable); // 이미지뷰에 조정한 이미지 넣기

        Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if( pref.getValue(SharedPreferenceUtil.PREF_USER_AGREEMENT, value) == 1 ){
                    Intent intent1 = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
                else
                {
                    Intent intent2 = new Intent(SplashActivity.this,BodyInfoActivity.class);
                    startActivity(intent2);
                    finish();
                }
            }
        };

        handler.sendEmptyMessageDelayed(0, 3000);

    } //end onCreate Method
}
