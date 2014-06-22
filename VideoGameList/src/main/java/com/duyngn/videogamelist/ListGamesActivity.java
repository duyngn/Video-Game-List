package com.duyngn.videogamelist;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

public class ListGamesActivity extends ActionBarActivity implements GamesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_games);

        //this.setupActionBar();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Intent intent = getIntent();
//        String value = intent.getStringExtra("key"); //if it's a string you stored.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_games, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void setupActionBar(){
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(R.layout.actionbar_layout);
//
//        View v = getSupportActionBar().getCustomView();
//        TextView titleTxtView = (TextView) v.findViewById(R.id.actionbarTitle);
//        titleTxtView.setText(actionBar.getTitle());
//    }


    public void onFragmentInteraction(String id){

        //CheckBox game_check = findViewById(R.id.id);


//        GamesFragment gamesFrag = (GamesFragment)
//                getSupportFragmentManager().findFragmentById(R.id.games_fragment);
//
//        if (gamesFrag != null) {
//            // If article frag is available, we're in two-pane layout...
//
//            // Call a method in the ArticleFragment to update its content
//            //gamesFrag.updateArticleView(position);
//        } else {
//            // Otherwise, we're in the one-pane layout and must swap frags...
//
//            // Create fragment and give it an argument for the selected article
//            GamesFragment newFragment = new GamesFragment();
//            Bundle args = new Bundle();
//            //args.putInt(GamesFragment.ARG_POSITION, position);
//            newFragment.setArguments(args);
//
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//            // Replace whatever is in the fragment_container view with this fragment,
//            // and add the transaction to the back stack so the user can navigate back
//            transaction.replace(R.id.games_fragment, newFragment);
//            transaction.addToBackStack(null);
//
//            // Commit the transaction
//            transaction.commit();
//        }
    }

}
