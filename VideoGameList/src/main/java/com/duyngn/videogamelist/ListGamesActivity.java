package com.duyngn.videogamelist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class ListGamesActivity extends ActionBarActivity implements GameListFragment.OnFragmentInteractionListener, AddGameFragment.OnFragmentInteractionListener {

    private static final int SELECT_PHOTO = 100;

    private GameListFragment gameFrag;
    private AddGameFragment addFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_games);

        this.setupActionbar();

        gameFrag = GameListFragment.newInstance("check");
        getSupportFragmentManager().beginTransaction().add(R.id.list_games_view, gameFrag).commit();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////        if (id == R.id.action_add) {
////
////            this.showForm();
////            return true;
////        }
//        return super.onOptionsItemSelected(item);
//    }

    private void setupActionbar()
    {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayUseLogoEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        //mActionBar.setDisplayShowCustomEnabled(true);
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
        startActivity(myIntent);
    }
    public void addBtn(View v){
        this.showForm();
    }

    private void showForm(){
        if(gameFrag != null) {
            getSupportFragmentManager().beginTransaction().remove(gameFrag).commit();
        }
        addFrag = AddGameFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.list_games_view, addFrag).commit();
    }

    private void submitForm(){

    }

    public void startPhotoIntent(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        Bitmap yourSelectedImage = this.decodeUri(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
//                    InputStream imageStream = null;
//                    try {
//                        imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                }
        }
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

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
