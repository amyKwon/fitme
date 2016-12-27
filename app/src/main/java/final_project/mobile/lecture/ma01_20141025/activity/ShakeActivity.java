package final_project.mobile.lecture.ma01_20141025.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import final_project.mobile.lecture.ma01_20141025.R;

public class ShakeActivity extends Activity implements SensorEventListener{

    private long lastTime;
    private float speed, lastX, lastY, lastZ;
    private float x, y, z;

    private static final int SHAKE_THRESHOLD = 4000;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;
    LinearLayout shakeLayout;
    Drawable sdrawable;
    //###########oncreate #####################
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        shakeLayout  = (LinearLayout)findViewById(R.id.slayout);

        Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.drawable.q_mark); // 비트맵 이미지를 만든다.
        int width=(int)(getWindowManager().getDefaultDisplay().getWidth()); // 가로 사이즈 지정
        int height=(int)(getWindowManager().getDefaultDisplay().getHeight() * 0.8); // 세로 사이즈 지정
        Bitmap resizedbitmap =Bitmap.createScaledBitmap(bmp, width, height, true); // 이미지 사이즈 조정

        sdrawable = new BitmapDrawable(getResources(),resizedbitmap);
        shakeLayout.setBackgroundDrawable(sdrawable); // 이미지뷰에 조정한 이미지 넣기

        this.setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    }
    @Override
    public void onStart()
    {
        super.onStart();
        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    public void onStop()
    {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);

            if (gabOfTime > 100)
            {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD)
                {
                    Toast.makeText(this,"shake it!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,CourseDetailActivity.class);            //intent 생성
                    startActivity(i);                                        //내가 만든 activity 실행
                }
                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data )
    {
    }
}