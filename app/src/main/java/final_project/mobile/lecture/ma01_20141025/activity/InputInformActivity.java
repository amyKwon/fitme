package final_project.mobile.lecture.ma01_20141025.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.activity.MainActivity;

public class InputInformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_inform);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
