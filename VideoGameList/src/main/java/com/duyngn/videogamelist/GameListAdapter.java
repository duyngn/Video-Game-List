package com.duyngn.videogamelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

class GameListAdapter extends ArrayAdapter<GameObject>
{
    Context context;
    String layoutStyle;

    private GameObject[] allGames;

    GameListAdapter(Context c, GameObject[] gamesArr, String style)
    {
        super(c, R.layout.list_game_row, R.id.textView, gamesArr);
        this.context = c;
        this.allGames = gamesArr;
        this.layoutStyle = style;
    }

    static class MyViewHolder {
        TextView myGameTitle;
        TextView myGameConsole;
        ImageView myGameIcon;
        CheckBox myGameCompleted;
        RatingBar myGameRating;

        MyViewHolder(View v, String style){
            myGameIcon = (ImageView) v.findViewById(R.id.imageView);
            myGameTitle = (TextView) v.findViewById(R.id.textView);
            myGameConsole = (TextView) v.findViewById(R.id.textView2);
            myGameCompleted = (CheckBox) v.findViewById(R.id.checkBox);
            myGameRating = (RatingBar) v.findViewById(R.id.ratingBar);

            if(style.equals("check")){
                myGameCompleted.setVisibility(View.VISIBLE);
                myGameRating.setVisibility(View.GONE);
            }
            else{
                myGameCompleted.setVisibility(View.GONE);
                myGameRating.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        final MyViewHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_game_row,parent, false);

            holder = new MyViewHolder(row, layoutStyle);
            row.setTag(holder);

            if(layoutStyle.equals("check")){
                holder.myGameCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        GameObject game = (GameObject) holder.myGameCompleted.getTag();
                        if(buttonView.isChecked()) {
                            game.setCompleted(1);
                        }
                        else{
                            game.setCompleted(0);
                        }

                        GamesDataSource datasource = new GamesDataSource(getContext());
                        datasource.open();
                        datasource.updateGame(game);
                        datasource.close();
                    }
                });

            }
            else {
                holder.myGameRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    public void onRatingChanged(RatingBar ratingBar,
                                                float rating, boolean fromTouch) {
                        GameObject game = (GameObject) holder.myGameRating.getTag();
                        game.setRating(Math.round(rating));

                        GamesDataSource datasource = new GamesDataSource(getContext());
                        datasource.open();
                        datasource.updateGame(game);
                        datasource.close();
                    }
                });
            }
        }
        else
        {
            holder = (MyViewHolder) row.getTag();
        }

        holder.myGameIcon.setImageBitmap(allGames[position].getImage());
        holder.myGameTitle.setText(allGames[position].getTitle());
        holder.myGameConsole.setText(allGames[position].getConsole());

        if(layoutStyle.equals("check")) {
            boolean flag = false;
            if(allGames[position].getCompleted() == 1){ flag = true; }

            holder.myGameCompleted.setTag(allGames[position]);
            holder.myGameCompleted.setChecked(flag);
        }
        else{
            holder.myGameRating.setTag(allGames[position]);
            holder.myGameRating.setRating(allGames[position].getRating());
        }

        return row;
    }
}