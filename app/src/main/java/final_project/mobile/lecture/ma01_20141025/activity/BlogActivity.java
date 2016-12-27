package final_project.mobile.lecture.ma01_20141025.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.BlogXmlParser;
import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.dto.BlogData;
import final_project.mobile.lecture.ma01_20141025.dto.HealthCenterData;

public class BlogActivity extends AppCompatActivity {

    public String name;
    TextView bTitle;
    TextView description;
    TextView link;
    Button btn_blog;
    private HealthCenterData hData;
    String apiUrl;
    String bloggerlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        btn_blog = (Button) findViewById(R.id.btn_goblg);
        bTitle = (TextView) findViewById(R.id.bTitle);
        description = (TextView) findViewById(R.id.description);
        link = (TextView) findViewById(R.id.link);

        Intent hIntent = getIntent();
        hData = (HealthCenterData) hIntent.getSerializableExtra("DetailBlogData");
        name = hData.getName();

        apiUrl = getResources().getString(R.string.apiurl2);


    }


    protected void onResume() {
        super.onResume();

        new NaverAsyncTask().execute(apiUrl);
    }

    class NaverAsyncTask extends AsyncTask<String, Integer, String> {

        public final static String TAG = "BlogAsyncTask";
        public final static int TIME_OUT = 10000;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer response = new StringBuffer();

            String clientId = getResources().getString(R.string.clientId);
            String clientSecret = getResources().getString(R.string.clientSecret);

            try {

                apiUrl += URLEncoder.encode(name, "UTF-8");

                //쿼리 결합 완료 => 던지기
                //네이버 AsyncTask사용할것임
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("X-Naver-Client-Id", clientId);
                conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                int responseCode = conn.getResponseCode();
                if (responseCode==200) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                } else {
                    Log.e(TAG, "API 호출 에러 발생 : 에러코드=" + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            BlogXmlParser parser = new BlogXmlParser();

            if (result != null) {
                Log.i("BlogActivity", result);
                ArrayList<BlogData> blist = parser.parse(result);

                BlogData bdata = blist.get(0);

                bTitle.setText(bdata.getTitle());
                link.setText(bdata.getLink());
                description.setText(bdata.getDescription());
                bloggerlink = bdata.getBloggerlink();

//                bTitle.setText(blist.get(0).toString());
//                link.setText(blist.get(1).toString());
//                description.setText(blist.get(2).toString());
//                bloggerlink  = blist.get(3).toString();
                btn_blog.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {

                        Uri uri = Uri.parse(bloggerlink);
                        Intent it  = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(it);

                    }
                });
            }
        }
    }


}
