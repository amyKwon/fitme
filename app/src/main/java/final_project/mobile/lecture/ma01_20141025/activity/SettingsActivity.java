package final_project.mobile.lecture.ma01_20141025.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;

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
}
