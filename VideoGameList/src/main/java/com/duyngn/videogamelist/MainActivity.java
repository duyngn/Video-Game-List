package com.duyngn.videogamelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hiding the action bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
    }

    /**
     * Responds to press from List Games and Rate Games buttons.
     * Determine which button and start appropriate activity.
     *
     * @param  view  the button that triggered the click event
     */
    public void mainBtnAction(View view){
        String id_txt = getResources().getResourceEntryName(view.getId());
        Intent myIntent = null;

        if(id_txt.equals("list_games_btn")){
            myIntent = new Intent(this, ListGamesActivity.class);
        }
        else if(id_txt.equals("rate_games_btn")) {
            myIntent = new Intent(this, RateGamesActivity.class);
        }
        startActivity(myIntent);
    }
}
