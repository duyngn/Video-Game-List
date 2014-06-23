package com.duyngn.videogamelist;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RateGamesActivity extends ActionBarActivity implements GameListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_games);

        this.setupActionbar();

        GameListFragment gameFrag = GameListFragment.newInstance("star");
        getSupportFragmentManager().beginTransaction().add(R.id.rate_games_view, gameFrag).commit();
    }

    private void setupActionbar()
    {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayUseLogoEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setDisplayShowHomeEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar_layout, null);

        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.activity_title);
        mTitleTextView.setText("Rate Games");

        Button mAddButton = (Button) mCustomView.findViewById(R.id.add_button);
        mAddButton.setVisibility(View.INVISIBLE);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    public void backBtn(View v){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myIntent);
    }
    public void addBtn(View v){
        // Do nothing
    }

    public void onFragmentInteraction(String id){

    }

}
