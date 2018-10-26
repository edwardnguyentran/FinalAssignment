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
public class Topic2 extends Fragment {


    public Topic2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_topic2, container, false);

        //Button to change to Topic 3
        Button nextButton =v.findViewById(R.id.mNextbutton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment,new Topic3());
                getActivity().setTitle("Topic 3");
                fr.commit();

            }
        });

        //Inputs content into textviews in fragment
        TextView paragraph2 = v.findViewById(R.id.T2Paragraph2);
        paragraph2.setText("- Relying on personal resources to achieve early cash flow and become profitable (Using personal finances to fund venture)");
        TextView t2Hint = v.findViewById(R.id.T2Hint);
        t2Hint.setText("HINT: Consider this type of financing against other financing options.");
        TextView paragraph4 = v.findViewById(R.id.T2Paragraph4);
        paragraph4.setText("- Prevent dilution of owner’s equity \n     - Maintain full ownership and control \n - Instills discipline \n     - More accountable for each action while learning business management skills \n - Enforces a reasonable rate of growth \n     - Ready for heavy demands and expectations with high exposure");
        TextView paragraph6 = v.findViewById(R.id.T2Paragraph6);
        paragraph6.setText("- Limits size, sophistication and competitiveness of the venture and its market penetration \n - Sometimes it's not possible to bootstrap \n\n");
        return v;
    }

}
