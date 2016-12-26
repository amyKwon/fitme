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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.activity.dao.MyDBHelper;
import final_project.mobile.lecture.ma01_20141025.adapter.FinishCustomAdapter;
import final_project.mobile.lecture.ma01_20141025.dto.fmyData;

public class FinishCourseActivity extends AppCompatActivity {

    RelativeLayout finishLayout;
    // 멤버 변수 선언
    MyDBHelper myDBHelper; //DB접근할 헬퍼 만들기 => onCreate 실행할 때 준비되야함


    private ArrayList<fmyData> fmyData;
    private FinishCustomAdapter fcustomAdapter;
    private ListView flistView;
    int updatePosition;
    TextView tv_courseName,tv_courseTime,tv_courseCnt;
    //iv_courseImg 처리해줄 부분 구현해야함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_course);

        finishLayout = (RelativeLayout)findViewById(R.id.fLayout);
        registerForContextMenu(finishLayout);

        flistView = (ListView)findViewById(R.id.flistView);
        myDBHelper = new MyDBHelper(this);
        fmyData = new ArrayList<fmyData>();

        tv_courseName = (TextView)findViewById(R.id.tv_courseName);
        tv_courseTime = (TextView)findViewById(R.id.tv_courseTime);
        tv_courseCnt = (TextView)findViewById(R.id.tv_courseCnt);

        readData();

        fcustomAdapter = new FinishCustomAdapter(this, R.layout.fcustom_view, fmyData);
        flistView.setAdapter(fcustomAdapter);



        flistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //원본데이터와 화면의 배치되는 순서는 정확히 일치하기 때문에 position으로 받을 수 있음.

                AlertDialog.Builder builder = new AlertDialog.Builder(FinishCourseActivity.this);

                builder.setTitle("삭제");
                builder.setMessage("해당 운동 기록을 정말로 삭제하시겠습니까?");
                builder.setIcon(R.mipmap.ic_launcher); //대화상자 아이콘
                builder.setPositiveButton("삭제버튼", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fmyData currentData = fmyData.get(position);
                        SQLiteDatabase db = myDBHelper.getWritableDatabase();
                        String whereClause = "_id=?";
                        //문자열로 만들어서 String 배열 ( 롱클릭한 아이디 )
                        String[] whereArgs = new String[] { Integer.valueOf(currentData.get_id()).toString()};
                        int result = db.delete(MyDBHelper.TABLE_NAME, whereClause, whereArgs);

                        myDBHelper.close();

                        if (result > 0)
                        {
                            Toast.makeText(FinishCourseActivity.this, "삭제하지 않습니다 ", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            fmyData.remove(position);//원본데이터 지움
                            fcustomAdapter.notifyDataSetChanged();// 데이터 바꼇다고 알려줌
                            Toast.makeText(FinishCourseActivity.this, "삭제하였습니다.", Toast.LENGTH_SHORT).show();
                        }

                        //Toast.makeText(ReadPattern.this, "삭제", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("취소버튼", null);
                builder.setCancelable(false);


                Dialog dlg = builder.create(); //대화상자 생성, 표시 X
                dlg.setCanceledOnTouchOutside(false); //대화상자 생성 후에만 설정 할 수 있음.
                dlg.show();

                //builder.show(); //대화상자 표시

                return true;

            }
        });

        flistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updatePosition = position;
                fmyData currentData =fmyData.get(position);

                Toast.makeText(FinishCourseActivity.this ,"OOO운동을 n회 완료하였습니다", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            switch (resultCode){
                case RESULT_OK:
                    fmyData mydata = fmyData.get(updatePosition);//클릭한 내용
                    fmyData updateData = (fmyData) data.getSerializableExtra("result_data");//바뀐내용
                    mydata.setF_courseName(updateData.getF_courseName());
                    mydata.setF_courseTime(updateData.getF_courseTime());
                    mydata.setF_courseCnt(updateData.getF_courseCnt());

                    fcustomAdapter.notifyDataSetChanged();

                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
    }

    private void readData() {

        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        Cursor cursor = db.query(MyDBHelper.TABLE_NAME, null, null, null, null, null, null, null);
        fmyData.clear(); //추가
        while (cursor.moveToNext()) {

            int _id = cursor.getInt(0);
            int iv_courseImg = cursor.getInt(1);
            String tv_courseName  = cursor.getString(2);
            String tv_courseTime = cursor.getString(3);
            String tv_courseCnt = cursor.getString(4);

            fmyData newItem = new fmyData();

            fmyData.add(newItem);


        }
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

                AlertDialog.Builder alt_bld = new AlertDialog.Builder(FinishCourseActivity.this);
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

    @Override
    public void onResume() {
        super.onResume();
        readData();
        fcustomAdapter.notifyDataSetChanged();
    }

}



/*

public class ReadPattern extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pattern);

        finishLayout = (RelativeLayout)findViewById(R.id.readlist);
        registerForContextMenu(readLayout);

        flist = (ListView)findViewById(R.id.flistView);
        myDBHelper = new MyDBHelper(this);
        fmyData = new ArrayList<fmyData>();

        exlovername = (TextView) findViewById(R.id.edit_exlover);
        loveperiod= (TextView)findViewById(R.id.edit_loveperiod);
        lovegage= (TextView)findViewById(R.id.edit_lovegage);

        readData();
        customAdapter = new CustomAdapter(this, R.layout.custom_view, myData);
        ablist.setAdapter(customAdapter);

//여기까지따라함

        ablist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //원본데이터와 화면의 배치되는 순서는 정확히 일치하기 때문에 position으로 받을 수 있음.

                AlertDialog.Builder builder = new AlertDialog.Builder(ReadPattern.this);

                builder.setTitle("삭제");
                builder.setMessage("정말로 삭제하시겠습니까?");
                builder.setIcon(R.mipmap.cardiogram); //대화상자 아이콘
                builder.setPositiveButton("삭제버튼", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyData currentData = myData.get(position);
                        SQLiteDatabase db = myDBHelper.getWritableDatabase();
                        String whereClause = "_id=?";
                        //문자열로 만들어서 String 배열 ( 롱클릭한 아이디 )
                        String[] whereArgs = new String[] { Integer.valueOf(currentData.get_id()).toString()};
                        int result = db.delete(MyDBHelper.TABLE_NAME, whereClause, whereArgs);

                        myDBHelper.close();
                        Log.d("TAG", ""+  myData.get(position).getLoveperiod()); // 데이터 잘 가져왔어.
                        //retult 는 0이다.

                        if (result > 0)
                        {
                            Toast.makeText(ReadPattern.this, "삭제 ㄴㄴ ", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                         myData.remove(position);//원본데이터 지움
                         customAdapter.notifyDataSetChanged();// 데이터 바꼇다고 알려줌
                         Toast.makeText(ReadPattern.this, "삭제하였습니다.", Toast.LENGTH_SHORT).show();
                        }

                        //Toast.makeText(ReadPattern.this, "삭제", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("취소버튼", null);
                builder.setCancelable(false);


                Dialog dlg = builder.create(); //대화상자 생성, 표시 X
                dlg.setCanceledOnTouchOutside(false); //대화상자 생성 후에만 설정 할 수 있음.
                dlg.show();

                //builder.show(); //대화상자 표시

                return true;

            }
        });

        ablist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updatePosition = position;
                MyData currentData =myData.get(position);

                Toast.makeText(ReadPattern.this , myData.get(position).getExlover().toString() + "님과의 " + myData.get(position).getLoveperiod().toString()
                        +"간의 사랑", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReadPattern.this, DetailActivity.class);
                intent.putExtra("DetailData", currentData);
                startActivityForResult(intent,ACT_EDIT);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            switch (resultCode){
                case RESULT_OK:
                    MyData mydata = myData.get(updatePosition);//클릭한 내용
                    MyData updateData = (MyData) data.getSerializableExtra("result_data");//바뀐내용
                    mydata.setAffection(updateData.getAffection());
                    mydata.setExlover(updateData.getExlover());
                    mydata.setLoveperiod(updateData.getLoveperiod());
                    mydata.setFightcause(updateData.getFightcause());
                    mydata.setEndcause(updateData.getEndcause());
                    mydata.setEnddate(updateData.getEnddate());

                    customAdapter.notifyDataSetChanged();

                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
    }

    private void readData() {

        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        Cursor cursor = db.query(MyDBHelper.TABLE_NAME, null, null, null, null, null, null, null);
        myData.clear(); //추가
        while (cursor.moveToNext()) {

            int _id = cursor.getInt(0);
            String exlover  = cursor.getString(1);
            String period = cursor.getString(2);
            String end_date = cursor.getString(3);
            String affection = cursor.getString(4);
            String end_cause = cursor.getString(5);
            String fight_cause = cursor.getString(6);

            MyData newItem = new MyData(_id, exlover, period, end_date, affection, end_cause, fight_cause);

            myData.add(newItem);


        }
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

                AlertDialog.Builder alt_bld = new AlertDialog.Builder(ReadPattern.this);
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
                Intent wintent = new Intent(this, WritePattern.class);
                startActivity(wintent);
                return true;
            case R.id.item_developer:
                Intent dintent = new Intent(this,DeveloperInfo.class);
                startActivity(dintent);
                return true;
            case R.id.item_finish:
                finish();
                return true;
        }
        return false;
    }
    //이미지 버튼 이밴트 처리
    public void mclick(View v){
        switch (v.getId()){
            case R.id.read_pattern:
                Intent intent = new Intent(this, ReadPattern.class);
                startActivity(intent);
                break;

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        readData();
        customAdapter.notifyDataSetChanged();
    }

}

 */