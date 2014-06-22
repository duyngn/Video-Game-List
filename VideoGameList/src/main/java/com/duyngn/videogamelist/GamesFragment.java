package com.duyngn.videogamelist;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class GamesFragment extends ListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    String[] gameTitles;
    String[] gameConsoles;
    int image = R.drawable.ic_launcher;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types of parameters
    public static GamesFragment newInstance(String param1, String param2) {
        GamesFragment fragment = new GamesFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GamesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Resources res = getResources();
        gameTitles = res.getStringArray(R.array.game_titles);
        gameConsoles = res.getStringArray(R.array.game_consoles);

        setListAdapter(new GameListAdapter(getActivity(),gameTitles,gameConsoles,image));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), getResources().getResourceEntryName(v.getId()), Toast.LENGTH_SHORT).show();
        if (null != mListener) {

            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(getResources().getResourceEntryName(v.getId()));
        }
    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}

class GameListAdapter extends ArrayAdapter<String>
{
    Context context;
    int image;
    String[] titleArray;
    String[] consoleArray;
    GameListAdapter(Context c, String[] titles, String[] consoles, int img)
    {
        super(c, R.layout.list_game_row, R.id.textView, titles);
        this.context = c;
        this.image = img;
        this.titleArray = titles;
        this.consoleArray = consoles;
    }

    static class MyViewHolder {
        TextView myGameTitle;
        TextView myGameConsole;
        ImageView myGameIcon;
        CheckBox myGameCompleted;

        MyViewHolder(View v){
            myGameIcon = (ImageView) v.findViewById(R.id.imageView);
            myGameTitle = (TextView) v.findViewById(R.id.textView);
            myGameConsole = (TextView) v.findViewById(R.id.textView2);
            myGameCompleted = (CheckBox) v.findViewById(R.id.checkBox);
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

            holder = new MyViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) row.getTag();
        }

        holder.myGameIcon.setImageResource(image);
        holder.myGameTitle.setText(titleArray[position]);
        holder.myGameConsole.setText(consoleArray[(int)(Math.random() * 10)]);

        holder.myGameCompleted.setChecked(true);

        return row;
    }
}