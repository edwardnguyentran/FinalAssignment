package com.example.edwardnguyentran.assignmented;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesNav extends Fragment {


    public NotesNav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notes_nav, container, false);

        //Creates button and navigates to Topic 1
        Button buttonT1 =v.findViewById(R.id.buttonT1);
        buttonT1.setText("Topic 1 \n Financial Implications");
        buttonT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment,new Topic1());
                getActivity().setTitle("Topic 1");
                fr.commit();

            }
        });

        //Creates button and navigates to Topic 2
        Button buttonT2 = v.findViewById(R.id.buttonT2);
        buttonT2.setText("Topic 2 \n Bootstrapping");
        buttonT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment,new Topic2());
                getActivity().setTitle("Topic 2");
                fr.commit();

            }
        });

        //Creates button and navigates to Topic 3
        Button buttonT3 =v.findViewById(R.id.buttonT3);
        buttonT3.setText("Topic 3 \n Types of Equity Investors");
        buttonT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment,new Topic3());
                getActivity().setTitle("Topic 3");
                fr.commit();

            }
        });
        return v;
    }

}
