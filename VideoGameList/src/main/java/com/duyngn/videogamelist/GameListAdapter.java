package com.duyngn.videogamelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

class GameListAdapter extends ArrayAdapter<GameObject>
{
    Context context;
    int image;
    String[] titleArray;
    String[] consoleArray;
    String layoutStyle;

    GameObject[] allGames;

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
        MyViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_game_row,parent, false);

            holder = new MyViewHolder(row, layoutStyle);
            row.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) row.getTag();
        }

        holder.myGameIcon.setImageResource(allGames[position].getImage());
        holder.myGameTitle.setText(allGames[position].getTitle());
        holder.myGameConsole.setText(allGames[position].getConsole());

        if(layoutStyle.equals("check")) {
            boolean flag = false;
            if(allGames[position].getCompleted() == 1){ flag = true; }
            holder.myGameCompleted.setChecked(flag);
        }
        else{
            holder.myGameRating.setRating(allGames[position].getRating());
        }

        return row;
    }
}