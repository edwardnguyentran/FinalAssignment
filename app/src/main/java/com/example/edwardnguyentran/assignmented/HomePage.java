package com.example.edwardnguyentran.assignmented;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePage extends Fragment {
        public static final String DEVELOPER_KEY = "AIzaSyCz6HrI6HvR_avsJb7pOysiU3A2VWw3Wdw";
        private static final String VIDEO_ID = "QChfZU8ig8w";
        private YouTubePlayer activePlayer;
        private static final int RECOVERY_DIALOG_REQUEST = 1;

        //Default constructor
        public HomePage() {
        }

        //Initiates view
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_home_page, container, false);

            YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.Video1, youTubePlayerFragment).commit();

            //Navigates to beginning of quiz
            Button buttonStartQuiz =v.findViewById(R.id.QuizButton);
            buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction fr=getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment,new StartQuiz());
                    getActivity().setTitle("Start Quiz");
                    fr.commit();

                }
            });

            //Navigates to Notes Navigation page
            Button notesButton =v.findViewById(R.id.NotesButton);
            notesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction fr=getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment,new NotesNav());
                    getActivity().setTitle("Notes Navigation");
                    fr.commit();

                }
            });

            //Initialises Youtube Player
            youTubePlayerFragment.initialize(DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        activePlayer = youTubePlayer;

                        activePlayer.cueVideo(VIDEO_ID);

                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                }
            });

            return v;
        }


        @Override
        public void onDetach() {
            super.onDetach();
        }
}
