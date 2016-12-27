package final_project.mobile.lecture.ma01_20141025;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import final_project.mobile.lecture.ma01_20141025.activity.BlogActivity;
import final_project.mobile.lecture.ma01_20141025.adapter.MyHealthAdapter;
import final_project.mobile.lecture.ma01_20141025.dto.HealthCenterData;

public class Fragment2 extends Fragment {
    final static  int ACT_EDIT = 200;
    public static final String TAG = "MainActivity";
    String apiurl;

    EditText etTargetCategory;
    ListView lvList;
    Button btnDownHtml;

    MyHealthAdapter adapter;
    private ArrayList<HealthCenterData> list;
    int updatePosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etTargetCategory = (EditText) view.findViewById(R.id.etTargetCategory);
        lvList = (ListView)view.findViewById(R.id.lvList);
        btnDownHtml = (Button)view.findViewById(R.id.btnDownHtml);

        list = new ArrayList<HealthCenterData>();
        adapter = new MyHealthAdapter(getActivity(), R.layout.health_item, list);

        lvList.setAdapter(adapter);
        apiurl = getResources().getString(R.string.apiurl1);
        btnDownHtml.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new NaverAsyncTask().execute(apiurl);
            }
        });
    }

    class NaverAsyncTask extends AsyncTask<String, Integer, String> {

        String query;
        ProgressDialog progressDlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            query = etTargetCategory.getText().toString();
            progressDlg = ProgressDialog.show(getActivity(), "Wait", "Downloading...");
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer response = new StringBuffer();

            String clientId = getResources().getString(R.string.clientId);
            String clientSecret = getResources().getString(R.string.clientSecret);

            try {

                apiurl += URLEncoder.encode(query, "UTF8");

                //쿼리 결합 완료 => 던지기
                //네이버 AsyncTask사용할것임
                URL url = new URL(apiurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("X-Naver-Client-Id", clientId);
                conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
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

            Log.i(TAG, result);

            MyXmlParser parser = new MyXmlParser();
            list = parser.parse(result);

            for (HealthCenterData dto : list) {
                Log.i(TAG, dto.toString());
            }

            adapter.setList(list);
            //화면이 바꼈다는 것을 알려주기 notify
            adapter.notifyDataSetChanged();
            progressDlg.dismiss();

            lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    updatePosition = position;
                    HealthCenterData hData = list.get(position);

                    Intent intent = new Intent(getActivity(), BlogActivity.class);
                    intent.putExtra("DetailBlogData", hData);
                    startActivity(intent);
                }
            });

        }
    }
}