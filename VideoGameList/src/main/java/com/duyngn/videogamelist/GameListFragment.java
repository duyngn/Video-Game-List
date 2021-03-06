package com.duyngn.videogamelist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class GameListFragment extends ListFragment{

    private static final String LAYOUT_STYLE = "check";

    private GamesDataSource datasource;
    private String list_layout;

    private OnFragmentInteractionListener mListener;

    public static GameListFragment newInstance(String layout) {
        GameListFragment fragment = new GameListFragment();
        Bundle args = new Bundle();
        args.putString(LAYOUT_STYLE, layout);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GameListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            list_layout = getArguments().getString(LAYOUT_STYLE);
        }

        datasource = new GamesDataSource(getActivity());
        datasource.open();

        List<GameObject> values = datasource.getAllGames();

        GameObject[] allGamesArr = new GameObject[values.size()];
        values.toArray(allGamesArr); // fill the array
        setListAdapter(new GameListAdapter(getActivity(), allGamesArr, list_layout));

        datasource.close();
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


//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
////        if (null != mListener) {
////
////            //Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
////            // Notify the active callbacks interface (the activity, if the
////            // fragment is attached to one) that an item has been selected.
////            //mListener.onFragmentInteraction(getResources().getResourceEntryName(v.getId()));
////        }
//    }


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