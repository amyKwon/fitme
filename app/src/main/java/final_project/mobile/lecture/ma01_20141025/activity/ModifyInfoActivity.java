package final_project.mobile.lecture.ma01_20141025.activity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.dao.MyDBHelper;
import final_project.mobile.lecture.ma01_20141025.dto.memberData;

public class ModifyInfoActivity extends AppCompatActivity {
    MyDBHelper myDBHelper;
    EditText input_name;
    EditText input_age;
    RadioGroup radioGroupGender;
    EditText input_height;
    EditText input_weight;
    EditText input_gweight;
    RadioGroup radioGroupKind;
    Button btn_save;
    int gender =0;
    int kind=0;
    LinearLayout mLayout;
    ArrayList<memberData> mlist ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        mLayout = (LinearLayout) findViewById(R.id.mLayout);
        registerForContextMenu(mLayout);

        myDBHelper= new MyDBHelper(this);
        input_name = ( EditText )this.findViewById(R.id.input_name);
        input_age = ( EditText )this.findViewById(R.id.input_age);
        input_height= ( EditText )this.findViewById(R.id.input_height);
        input_weight = ( EditText )this.findViewById(R.id.input_weight);
        input_gweight = ( EditText )this.findViewById(R.id.input_gweight);

        radioGroupGender =(RadioGroup)findViewById(R.id.radioGroupGender);
        radioGroupKind = (RadioGroup)findViewById(R.id.radioGroupKind);
        btn_save =(Button)findViewById(R.id.btn_save);


        mlist = readData();
        //db에서 꺼내오기
        input_name.setText(mlist.get(0).getName());
        input_age.setText(mlist.get(0).getAge());
        input_height.setText(mlist.get(0).getHeight());
        input_weight.setText(mlist.get(0).getWeight());
        input_gweight.setText(mlist.get(0).getGweight());

        if(mlist.get(0).getGender() == 0)
            radioGroupGender.check(R.id.input_gender_m);
        else if(mlist.get(0).getGender() == 1)
            radioGroupGender.check(R.id.input_gender_f);

        if(mlist.get(0).getKind() == 1)
            radioGroupKind.check(R.id.input_kind_bottom);
        else if(mlist.get(0).getKind() == 2)
            radioGroupKind.check(R.id.input_kind_top);
        else if(mlist.get(0).getKind() == 3)
            radioGroupKind.check(R.id.input_kind_valley);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:

                //항목을 입력하지 않은 상태에서 추가 버튼 등을 눌렀을 경우 오류 안내 대화상자 출력
                if (input_name.getText().length() == 0 || input_age.getText().length() == 0 || input_height.getText().length() == 0
                        || input_weight.getText().length() == 0 || input_gweight.getText().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ModifyInfoActivity.this);

                    builder.setTitle("WARNING");
                    builder.setMessage("필수 항목을 모두 입력해주세요.");
                    builder.setIcon(R.mipmap.ic_launcher); //대화상자 아이콘
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //클릭시 동작
                            Toast.makeText(ModifyInfoActivity.this, "다시 입력", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("취소", null);
                    builder.setCancelable(false);


                    Dialog dlg = builder.create(); //대화상자 생성, 표시 X
                    dlg.setCanceledOnTouchOutside(false); //대화상자 생성 후에만 설정 할 수 있음.
                    dlg.show();

                    //builder.show(); //대화상자 표시

                } else {

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

                    Toast.makeText(ModifyInfoActivity.this, input_name.getText().toString(), Toast.LENGTH_SHORT).show();

                    try {
                        db.insert(MyDBHelper.MTABLE_NAME, null, row);// null부분은 autoincrement 해 둔 부분이 자동으로 들어감.
                        myDBHelper.close();
                        Toast.makeText(this, "저장하였습니다.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
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

        }

    }
    private ArrayList<memberData> readData() {
        ArrayList<memberData> list = new ArrayList<memberData>();

        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        Cursor cursor = db.query(MyDBHelper.MTABLE_NAME, null, null, null, null, null, null, null);
//        mlist.clear(); //추가
        while (cursor.moveToNext()) {

            int _id = cursor.getInt(0);
            String name = cursor.getString(2);
            String age = cursor.getString(3);
            String gender = cursor.getString(4);
            String height = cursor.getString(5);
            String weight = cursor.getString(6);

            String gweight = cursor.getString(7);
            String ckind = cursor.getString(8);


            memberData newItem = new memberData(_id, name, age, Integer.parseInt(gender), height, weight, gweight, Integer.parseInt(ckind));

            list.add(newItem);
        }
        cursor.close();
        myDBHelper.close();

        return list;

    }

    private void getDataCount(){
//        Toast.makeText(ReadPattern.this,  , Toast.LENGTH_LONG).show();
    }

    //Back 버튼을 눌렀을 경우 종료 확인 대화상자 출력 후 종료 확인
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :

                AlertDialog.Builder alt_bld = new AlertDialog.Builder(ModifyInfoActivity.this);
                alt_bld
                        .setMessage("종료하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("예", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.cancel();
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //아니오 버튼
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = alt_bld.create();
                alert.show();


                break;

        }
        return super.onKeyDown(keyCode, event);
    }
    //메뉴 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_option_menu, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //메뉴 항목을 선택하면 배경화면 변경 및 현재 선택항목 기록
        switch(item.getItemId()) {
            case R.id.item_plus:
                Intent wintent = new Intent(this,MainActivity.class);
                startActivity(wintent);
                return true;
            case R.id.item_developer:
                Intent dintent = new Intent(this,SettingsActivity.class);
                startActivity(dintent);
                return true;
            case R.id.item_finish:
                finish();
                return true;
        }
        return false;
    }
}
