package com.duyngn.videogamelist;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class GamesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_CONSOLE,
            MySQLiteHelper.COLUMN_COMPLETED, MySQLiteHelper.COLUMN_RATING, MySQLiteHelper.COLUMN_IMAGE};

    public GamesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public GameObject createGame(String title, String console, int completed, int rating, Bitmap image) {
        ContentValues values = new ContentValues();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImageString = Base64.encodeToString(b, Base64.DEFAULT);

        values.put(MySQLiteHelper.COLUMN_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_CONSOLE, console);
        values.put(MySQLiteHelper.COLUMN_COMPLETED, completed);
        values.put(MySQLiteHelper.COLUMN_RATING, rating);
        values.put(MySQLiteHelper.COLUMN_IMAGE, encodedImageString);

        long insertId = database.insert(MySQLiteHelper.TABLE_GAMES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_GAMES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        GameObject newGame = cursorToGame(cursor);
        cursor.close();
        return newGame;
    }

    public void deleteGame(GameObject game) {
        int id = game.getId();
        //System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_GAMES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void updateGame(GameObject game){
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_COMPLETED, game.getCompleted());
        values.put(MySQLiteHelper.COLUMN_RATING, game.getRating());

        database.update(MySQLiteHelper.TABLE_GAMES, values, "_id ="+game.getId(), null);
    }

    public List<GameObject> getAllGames() {
        List<GameObject> comments = new ArrayList<GameObject>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_GAMES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            GameObject comment = cursorToGame(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private GameObject cursorToGame(Cursor cursor) {
        GameObject game = new GameObject();
        game.setId(cursor.getInt(0));
        game.setTitle(cursor.getString(1));
        game.setConsole(cursor.getString(2));
        game.setCompleted(cursor.getInt(3));
        game.setRating(cursor.getInt(4));

        byte[] bytarray = Base64.decode(cursor.getBlob(5), Base64.DEFAULT);
        Bitmap bmimage = BitmapFactory.decodeByteArray(bytarray, 0, bytarray.length);
        game.setImage(bmimage);

        return game;
    }
}