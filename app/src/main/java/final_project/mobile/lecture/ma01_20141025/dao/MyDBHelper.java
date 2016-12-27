package final_project.mobile.lecture.ma01_20141025.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cooling on 2016-05-26.
 */

//DB손봐야함
public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "fitme_db";
    public static final String MTABLE_NAME = "member";
    public static final String RTABLE_NAME = "r_exercise";
    public static final String WTABLE_NAME = "walknride";


    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        member 데이터베이스 테이블 생성
        String mcreateTable = "create table " +
                MTABLE_NAME+ " ( _id integer primary key autoincrement, name text, age text, gender text, height text, " +
                "weight text, g_weight text, ckind text);";
        sqLiteDatabase.execSQL(mcreateTable);

//        샘플 데이터 추가
//        sqLiteDatabase.execSQL("insert into " + MTABLE_NAME + " values (null, '류준열', '30', 'm', '180', '78', '70', '하체운동');");
        sqLiteDatabase.execSQL("insert into " + MTABLE_NAME + " values (null, '권예진', '23', 'f', '165', '52', '50', '복부운동');");
//        sqLiteDatabase.execSQL("insert into " + MTABLE_NAME + " values (null, '이제훈', '33', 'm', '178', '65', '63', '상체운동');");
//        sqLiteDatabase.execSQL("insert into " + MTABLE_NAME + " values (null, '김지현', '22', 'f', '160', '48', '45', '하체운동' );");


        //        r_exercise 데이터베이스 테이블 생성
        String rcreateTable = "create table " +
                RTABLE_NAME+ " ( _id integer primary key autoincrement, img text, ename text, time text, complete text, " +
                "exeercise1 text, exercise2 text, exercise3 text, exercise4 text, ckind text);";
        sqLiteDatabase.execSQL(rcreateTable);
//        샘플 데이터 추가
        sqLiteDatabase.execSQL("insert into " + RTABLE_NAME + " values (null, null, '아이돌 몸매 만들기', '1시간40분', '미완료', '유산소런닝머신40분', '랫플다운머신15분', '삼두근운동10분', '데드리프트덤벨20분');");
        sqLiteDatabase.execSQL("insert into " + RTABLE_NAME + " values (null, null, '복부타파 운동', '약 1시간', '완료', '윗몸일으키기10분', '플랭크10분', '하체들고유지하기20분', '훌라후프20분');");
        sqLiteDatabase.execSQL("insert into " + RTABLE_NAME + " values (null, null, '예쁜 어깨라인 만들기', '45분', '미완료', '승모근스트레칭10분', '덤벨숄더스트레칭10분', '필라테스20분', '팔스트레칭5분');");

        //        walknride 데이터베이스 테이블 생성
        String wcreateTable = "create table " +
                WTABLE_NAME+ " ( _id integer primary key autoincrement, date sysdate, step text, distance text, kcal text); ";
        sqLiteDatabase.execSQL(wcreateTable);

//        샘플 데이터 추가
        sqLiteDatabase.execSQL("insert into " + WTABLE_NAME + " values (null, null, '3200보', '2.3km', '100kcal');");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
