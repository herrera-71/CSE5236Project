package com.project.cse5236.habitofgravity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class settingsActivity extends AppCompatActivity {

    //boolean sound=true;
    //boolean music=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //SharedPreferences settings = getSharedPreferences("HabitOfGravitySettings", 0);
        //sound = settings.getBoolean("sound", true);
        //music = settings.getBoolean("music", true);
        ((Switch)findViewById(R.id.switch1)).setChecked(audioAssets.getInstance().Sound);
        ((Switch)findViewById(R.id.switch2)).setChecked(audioAssets.getInstance().Music);

        //Sound
        ((Switch)findViewById(R.id.switch1)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Switch OnOff = (Switch)findViewById(R.id.switch1);
                        if(OnOff.isChecked())
                        {
                            Toast.makeText(settingsActivity.this, "Switch (sound) is on", Toast.LENGTH_LONG).show();
                            audioAssets.getInstance().Sound = true;
                        }
                        else {
                            Toast.makeText(settingsActivity.this, "Switch (sound) is Off", Toast.LENGTH_LONG).show();
                            audioAssets.getInstance().Sound = false;
                        }
                    }
                }
        );

        //Music
        ((Switch)findViewById(R.id.switch2)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Switch OnOff = (Switch)findViewById(R.id.switch2);
                        if(OnOff.isChecked())
                        {
                            Toast.makeText(settingsActivity.this, "Switch (Music) is on", Toast.LENGTH_LONG).show();
                            audioAssets.getInstance().Music = true;
                        }
                        else {
                            Toast.makeText(settingsActivity.this, "Switch (Music) is Off", Toast.LENGTH_LONG).show();
                            audioAssets.getInstance().Music = false;
                        }
                        audioAssets.getInstance().ToggleAudio();
                    }
                }
        );
    }

    Intent intent = getIntent();

    public void returnButton(View v)
    {
        finish();
    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences settings = getSharedPreferences("HabitOfGravitySettings", 0);

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("sound", audioAssets.getInstance().Sound);
        editor.putBoolean("music", audioAssets.getInstance().Music);

        // Commit the edits!
        editor.commit();
    }
}
