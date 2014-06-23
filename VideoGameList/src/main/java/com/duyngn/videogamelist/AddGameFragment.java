package com.duyngn.videogamelist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddGameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AddGameFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment AddGameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddGameFragment newInstance() {
        AddGameFragment fragment = new AddGameFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }
    public AddGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_game, container, false);

        this.setSpinnerContent(v);

        return v;
    }

    private void setSpinnerContent( View view )
    {
        Spinner spinner = (Spinner) view.findViewById(R.id.console_spinner);
        ArrayAdapter<CharSequence> consoleAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.game_consoles, android.R.layout.simple_spinner_item);
        consoleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(consoleAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
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

    public void updateImageView(Bitmap image) {
        ImageView imageV = (ImageView) getView().findViewById(R.id.game_image);
        imageV.setImageBitmap(image);
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
