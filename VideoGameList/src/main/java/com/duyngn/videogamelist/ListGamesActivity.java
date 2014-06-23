package com.duyngn.videogamelist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class ListGamesActivity extends ActionBarActivity implements GameListFragment.OnFragmentInteractionListener, AddGameFragment.OnFragmentInteractionListener {

    private static final int SELECT_PHOTO = 100;

    private GameListFragment gameFrag;
    private AddGameFragment addFrag;

    private GamesDataSource datasource;

    private Bitmap resizedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_games);

        this.setupActionbar();

        gameFrag = GameListFragment.newInstance("check");
        getSupportFragmentManager().beginTransaction().add(R.id.list_games_view, gameFrag).commit();
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
        mTitleTextView.setText("Game List");

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    public void onFragmentInteraction(String id){

//        GameListFragment gamesFrag = (GameListFragment)
//                getSupportFragmentManager().findFragmentById(R.id.games_fragment);

    }

    public void backBtn(View v){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myIntent);
    }
    public void addBtn(View v){
        if(addFrag == null) {
            this.showForm();
        }
    }

    private void showForm(){
        if(gameFrag != null) {
            getSupportFragmentManager().beginTransaction().remove(gameFrag).commit();
            gameFrag = null;
        }
        addFrag = AddGameFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.list_games_view, addFrag).commit();
    }

    public void submitForm(View view){

        String gTitle = ((EditText)addFrag.getView().findViewById(R.id.game_title)).getText().toString();
        String gConsole = ((Spinner)addFrag.getView().findViewById(R.id.console_spinner)).getSelectedItem().toString();

        if(gTitle != null
                && gConsole != null
                && !gTitle.equals("")
                && !gConsole.equals("")) {

            if(resizedImage == null){
                resizedImage = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            }

            datasource = new GamesDataSource(this);
            datasource.open();

            datasource.createGame(gTitle, gConsole, 0, 0, resizedImage);

            datasource.close();

            if (addFrag != null) {
                getSupportFragmentManager().beginTransaction().remove(addFrag).commit();
                addFrag = null;
            }
            gameFrag = GameListFragment.newInstance("check");
            getSupportFragmentManager().beginTransaction().add(R.id.list_games_view, gameFrag).commit();
        }
        else{
            Toast.makeText(this, "Title and Console is required",Toast.LENGTH_LONG).show();
        }
    }

    public void startPhotoIntent(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");

        photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), SELECT_PHOTO);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        resizedImage = this.decodeUri(selectedImage);

                        addFrag.updateImageView(resizedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 100;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
    }
}
