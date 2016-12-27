package final_project.mobile.lecture.ma01_20141025.activity;

import android.app.Dialog;
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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.dao.MyDBHelper;
import final_project.mobile.lecture.ma01_20141025.adapter.DetailCustomAdapter;
import final_project.mobile.lecture.ma01_20141025.dto.detailData;

public class CourseDetailActivity extends AppCompatActivity {

    RelativeLayout detailLayout;
    // 멤버 변수 선언
    MyDBHelper myDBHelper; //DB접근할 헬퍼 만들기 => onCreate 실행할 때 준비되야함
    private ArrayList<detailData> dData;
    private DetailCustomAdapter dcustomAdapter;
    private ListView dlistView;
    int updatePosition;
    TextView detailName;
    RadioButton radioFinishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);


        detailLayout = (RelativeLayout)findViewById(R.id.fLayout);
        registerForContextMenu(detailLayout);

        dlistView = (ListView)findViewById(R.id.detailListView);
        myDBHelper = new MyDBHelper(this);
        dData = new ArrayList<detailData>();

        detailName = (TextView)findViewById(R.id.tv_courseName);

        readData();

        dcustomAdapter = new DetailCustomAdapter(this, R.layout.dlist_view, dData);
        dlistView.setAdapter(dcustomAdapter);



    }
    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.btn_finishCourse :
                AlertDialog.Builder builder = new AlertDialog.Builder(CourseDetailActivity.this);

                builder.setTitle("오늘의 운동을 완료하였습니다!");
                builder.setMessage("친구에게 자랑하시겠습니까?");
                builder.setIcon(R.mipmap.ic_launcher); //대화상자 아이콘
                builder.setPositiveButton("자랑할래요!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //sns공유
                        //Toast.makeText(ReadPattern.this, "삭제", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("다음에 할래요~", null);
                builder.setCancelable(false);


                Dialog dlg = builder.create(); //대화상자 생성, 표시 X
                dlg.setCanceledOnTouchOutside(false); //대화상자 생성 후에만 설정 할 수 있음.
                dlg.show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;
        }
    }


    //Back 버튼을 눌렀을 경우 종료 확인 대화상자 출력 후 종료 확인
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :

                AlertDialog.Builder alt_bld = new AlertDialog.Builder(CourseDetailActivity.this);
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
    private void readData() {

        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        Cursor cursor = db.query(MyDBHelper.RTABLE_NAME, null, null, null, null, null, null, null);
        dData.clear(); //추가
        while (cursor.moveToNext()) {

            int _id = cursor.getInt(0);
            int iv_courseImg = cursor.getInt(1);
            String tv_courseName = cursor.getString(2);
            String tv_courseTime = cursor.getString(3);
            String tv_courseCnt = cursor.getString(4);

            detailData newItem = new detailData();

            dData.add(newItem);


        }
    }

    private void getDataCount(){
//        Toast.makeText(ReadPattern.this,  , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        readData();
        dcustomAdapter.notifyDataSetChanged();
    }

}


