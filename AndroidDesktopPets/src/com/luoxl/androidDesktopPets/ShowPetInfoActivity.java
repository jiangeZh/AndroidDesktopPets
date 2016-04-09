package com.luoxl.androidDesktopPets;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShowPetInfoActivity extends Activity {

    private Button btnSetting,btnShow;
    private TextView name,sex,year,character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pet_info);
        initView();
        showSettingInfo();
    }

    private void initView() {
        btnSetting = (Button)findViewById(R.id.btn_setting);
        btnShow = (Button)findViewById(R.id.btn_show);
        btnSetting.setOnClickListener(buttonListener);
        btnShow.setOnClickListener(buttonListener);

        name = (TextView)findViewById(R.id.name);
        sex = (TextView)findViewById(R.id.sex);
        year = (TextView)findViewById(R.id.year);
        character = (TextView)findViewById(R.id.character);
    }

    private OnClickListener buttonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn_setting:
                	Intent settings = new Intent(ShowPetInfoActivity.this,Setting.class);
    				settings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
                    startActivity(settings);
                    break;
                case R.id.btn_show:
                    showSettingInfo();
                    break;
            }
        }
    };

    private void showSettingInfo() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        name.setText(settings.getString(Consts.NAME_KEY, ""));
        sex.setText(settings.getString(Consts.SEX_KEY, "None"));
        year.setText(settings.getString(Consts.YEAR_KEY, "None"));
        character.setText(settings.getString(Consts.CHARACTER_KEY, "None"));
        //settings.getBoolean(Consts.CHECKOUT_KEY, false)+"" for checkbox
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
