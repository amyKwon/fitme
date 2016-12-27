package final_project.mobile.lecture.ma01_20141025.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import final_project.mobile.lecture.ma01_20141025.R;

public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceClickListener {

    AudioManager mAudioManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
        mAudioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);

        Preference pAppVersion = (Preference) findPreference("appversion");
        SwitchPreference setAlarm = (SwitchPreference) findPreference("set_sound");

        pAppVersion.setTitle(getVersionName(this));
        setAlarm.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        // 어플리케이션 알림
        if (preference.getKey().equals("set_sound") == true) {

            if(mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE || mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT)//현재 진동상태이면
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);  //벨로 바꾸기
        }
        else if(preference.getKey().equals("set_sound") == false){
            if(mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL)
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);  //진동을 바꾸기
        }

        return false;
    }

    public static String getVersionName(Context context)
    {
        try {
            PackageInfo pi= context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }


    //Back 버튼을 눌렀을 경우 종료 확인 대화상자 출력 후 종료 확인
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :

                AlertDialog.Builder alt_bld = new AlertDialog.Builder(SettingsActivity.this);
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
