package final_project.mobile.lecture.ma01_20141025.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import final_project.mobile.lecture.ma01_20141025.R;
import final_project.mobile.lecture.ma01_20141025.SharedPreferenceUtil;

public class PrefActivity extends Activity {

    public static SharedPreferences mPref;
    private TextView setsensitive, autoUpdateEnable,
            updateNofiti, notifiSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        System.out.println("PrefActivity");
        mPref = PreferenceManager.getDefaultSharedPreferences(this);

        setsensitive = (TextView) findViewById(R.id.set_sensitive);
        autoUpdateEnable = (TextView) findViewById(R.id.autoUpdateEnable);
        updateNofiti = (TextView) findViewById(R.id.updateNofiti);
        notifiSound = (TextView) findViewById(R.id.notifiSound);

        getPreferencesData();
        System.out.println(SharedPreferenceUtil.getSharedPreference(this, "index"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("onResume", "onResume()");

        getPreferencesData();
    }
    private void savePreferences(String key, int i) {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, i);
        editor.commit();
    }


    private void getPreferencesData() {

        autoUpdateEnable.setText("" + mPref.getBoolean("autoUpdate", false));
        updateNofiti.setText("" + mPref.getBoolean("useUpdateNofiti", false));

        //감도조절
        String[] array = getResources().getStringArray(R.array.setSensitive);
        int index = getArrayIndex(R.array.sensitive_values, mPref.getString("감도", "0"));

        if (index == 1)
        {
            // sIntet.SHAKE_THRESHOLD = 0;
            SharedPreferenceUtil.putSharedPreference(this, "index", "1");
            //savePreferences("index",1);

        }
        else if(index == 0)
        {
            // sIntet.SHAKE_THRESHOLD = 800;
            savePreferences("index",0);

            SharedPreferenceUtil.putSharedPreference(this, "index", "0");
        }
        else if(index == -1)
        {
            // sIntet.SHAKE_THRESHOLD = 3000;
            //savePreferences("index",-1);

            SharedPreferenceUtil.putSharedPreference(this, "index", "-1");
        }
        else{
            System.out.println("이거 어떻게 쓰지");
            SharedPreferenceUtil.putSharedPreference(this, "index", "default");
        }


        String Sound = mPref.getString("autoUpdate_ringtone", "설정되지 않음");
        if (TextUtils.isEmpty(Sound)) {
            notifiSound.setText("무음으로 설정됨?");

        } else {
            Ringtone ringtone = RingtoneManager.getRingtone(getBaseContext(),
                    Uri.parse(Sound));

            if (ringtone == null) {
                notifiSound.setText(null);

            } else {
                String name = ringtone.getTitle(this);
                notifiSound.setText(name);
            }
        }
    }

    private int getArrayIndex(int array, String findIndex) {
        String[] arrayString = getResources().getStringArray(array);
        for (int e = 0; e < arrayString.length; e++) {
            if (arrayString[e].equals(findIndex))
                return e;
        }
        return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_settings) {
            Intent SettingActivity = new Intent(this, SettingsActivity.class);
            startActivity(SettingActivity);
        }

        return super.onOptionsItemSelected(item);
    }

}
