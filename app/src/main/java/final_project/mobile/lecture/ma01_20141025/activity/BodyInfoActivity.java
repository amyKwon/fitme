package final_project.mobile.lecture.ma01_20141025.activity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.SharedPreferenceUtil;
import final_project.mobile.lecture.ma01_20141025.dao.MyDBHelper;

public class BodyInfoActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    EditText input_name;
    EditText input_age;
    RadioGroup radioGroupGender;
    EditText input_height;
    EditText input_weight;
    EditText input_gweight;
    RadioGroup radioGroupKind;
    Button btn_start;
    int gender =0;
    int kind=0;
    SharedPreferenceUtil pref = new SharedPreferenceUtil(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_info);

        myDBHelper= new MyDBHelper(this);
        input_name = ( EditText )this.findViewById(R.id.input_name);
        input_age = ( EditText )this.findViewById(R.id.input_age);
        input_height= ( EditText )this.findViewById(R.id.input_height);
        input_weight = ( EditText )this.findViewById(R.id.input_weight);
        input_gweight = ( EditText )this.findViewById(R.id.input_gweight);

        radioGroupGender =(RadioGroup)findViewById(R.id.radioGroupGender);
        radioGroupGender.setOnCheckedChangeListener(mRadioChange);
        radioGroupKind = (RadioGroup)findViewById(R.id.radioGroupKind);
        radioGroupKind.setOnCheckedChangeListener(mRadioChange);
        radioGroupKind.check(R.id.input_kind_bottom);
        btn_start =(Button)findViewById(R.id.btn_start);
    }

    RadioGroup.OnCheckedChangeListener  mRadioChange = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(group.getId() == R.id.radioGroupGender) {
                switch (checkedId) {
                    case R.id.input_gender_f:
                        gender = 0;
                        break;
                    case R.id.input_gender_m:
                        gender =1;
                        break;
                }
            }
                else if(group.getId() == R.id.radioGroupKind)
            {
                    switch(checkedId ) {
                        case R.id.input_kind_bottom:
                            kind = 1;
                            break;
                        case R.id.input_kind_top:
                            kind = 2;
                            break;
                        case R.id.input_kind_valley:
                            kind = 3;
                            break;
                    }
            }


        }


    };

    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.btn_start :

                //항목을 입력하지 않은 상태에서 추가 버튼 등을 눌렀을 경우 오류 안내 대화상자 출력
                if(input_name.getText().length() == 0 || input_age.getText().length() == 0  || input_height.getText().length() == 0
                        || input_weight.getText().length() == 0 || input_gweight.getText().length() == 0 )
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BodyInfoActivity.this);

                    builder.setTitle("WARNING");
                    builder.setMessage("필수 항목을 모두 입력해주세요.");
                    builder.setIcon(R.mipmap.ic_launcher); //대화상자 아이콘
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //클릭시 동작
                            Toast.makeText(BodyInfoActivity.this,"다시 입력",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("취소", null);
                    builder.setCancelable(false);


                    Dialog dlg = builder.create(); //대화상자 생성, 표시 X
                    dlg.setCanceledOnTouchOutside(false); //대화상자 생성 후에만 설정 할 수 있음.
                    dlg.show();

                    //builder.show(); //대화상자 표시

                }
                else
                {

                    SQLiteDatabase db = myDBHelper.getWritableDatabase();

//                1. DB 관련 메소드를 사용하여 데이터 추가
                    ContentValues row = new ContentValues();// row 생성
                    row.put("name", input_name.getText().toString());
                    row.put("age", input_age.getText().toString());
                    row.put("gender", Integer.toString(gender));
                    row.put("height", input_height.getText().toString());
                    row.put("weight", input_weight.getText().toString());
                    row.put("g_weight", input_gweight.getText().toString());
                    row.put("ckind", Integer.toString(kind));

                    Toast.makeText(BodyInfoActivity.this, input_name.getText().toString(), Toast.LENGTH_SHORT).show();

                    try {
                        db.insert(MyDBHelper.MTABLE_NAME, null, row);// null부분은 autoincrement 해 둔 부분이 자동으로 들어감.
                        myDBHelper.close();
                        Toast.makeText(this, "저장하였습니다.", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    input_name.setText("");
                    input_age.setText("");
                    input_height.setText("");
                    input_weight.setText("");
                    input_gweight.setText("");


                    //customAdapter.notifyDataSetChanged();

                    finish();
                }

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                pref.put(SharedPreferenceUtil.PREF_USER_AGREEMENT, 1);
                break;
        }
    }
}
