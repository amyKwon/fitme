package final_project.mobile.lecture.ma01_20141025.activity.dao;

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
//        데이터베이스 파일 생성
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
        sqLiteDatabase.execSQL("insert into " + MTABLE_NAME + " values (null, '류준열', '30', 'm', '180', '78', '70', '하체운동');");
        sqLiteDatabase.execSQL("insert into " + MTABLE_NAME + " values (null, '권예진', '23', 'f', '165', '52', '50', '복부운동');");
        sqLiteDatabase.execSQL("insert into " + MTABLE_NAME + " values (null, '이제훈', '33', 'm', '178', '65', '63', '팔운동');");
        sqLiteDatabase.execSQL("insert into " + MTABLE_NAME + " values (null, '김지현', '22', 'f', '160', '48', '45', '전신운' );");


        //        r_exercise 데이터베이스 테이블 생성
        String rcreateTable = "create table " +
                RTABLE_NAME+ " ( _id integer primary key autoincrement, name text, age number, gender text, height text, " +
                "weight text, g_weight text);";
        sqLiteDatabase.execSQL(rcreateTable);
//        샘플 데이터 추가
        sqLiteDatabase.execSQL("insert into " + RTABLE_NAME + " values (null, '류준열', '2년4개월', '2016년6월14일', '90%', '바쁜생활', '연락문제');");
        sqLiteDatabase.execSQL("insert into " + RTABLE_NAME + " values (null, '송중기', '6개월', '2013년12월20일', '70%', '이성문제', '바람');");
        sqLiteDatabase.execSQL("insert into " + RTABLE_NAME + " values (null, '이제훈', '1년8개월', '2013년3월5일', '80%', '권태기', '연락문제');");
        sqLiteDatabase.execSQL("insert into " + RTABLE_NAME + " values (null, '남주혁', '2개월', '2015년10월1일', '40%', '권태기', '연락문제');");


        //        walknride 데이터베이스 테이블 생성
        String wcreateTable = "create table " +
                WTABLE_NAME+ " ( _id integer primary key autoincrement, name text, age number, gender text, height text, " +
                "weight text, g_weight text);";
        sqLiteDatabase.execSQL(wcreateTable);

//        샘플 데이터 추가
        sqLiteDatabase.execSQL("insert into " + WTABLE_NAME + " values (null, '류준열', '2년4개월', '2016년6월14일', '90%', '바쁜생활', '연락문제');");
        sqLiteDatabase.execSQL("insert into " + WTABLE_NAME + " values (null, '송중기', '6개월', '2013년12월20일', '70%', '이성문제', '바람');");
        sqLiteDatabase.execSQL("insert into " + WTABLE_NAME + " values (null, '이제훈', '1년8개월', '2013년3월5일', '80%', '권태기', '연락문제');");
        sqLiteDatabase.execSQL("insert into " + WTABLE_NAME + " values (null, '남주혁', '2개월', '2015년10월1일', '40%', '권태기', '연락문제');");


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
