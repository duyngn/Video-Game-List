package com.duyngn.videogamelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hiding the action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);
    }

    /**
     * Responds to press from List Games and Rate Games buttons.
     * Determine which button and start appropriate activity.
     *
     * @param view the button that triggered the click event
     */
    public void mainBtnAction(View view) {
        String id_txt = getResources().getResourceEntryName(view.getId());
        Intent myIntent = null;

        if (id_txt.equals("list_games_btn")) {
            myIntent = new Intent(this, ListGamesActivity.class);
        } else if (id_txt.equals("rate_games_btn")) {
            myIntent = new Intent(this, RateGamesActivity.class);
        }
        startActivity(myIntent);
    }
}