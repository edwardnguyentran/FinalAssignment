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
public class Topic1 extends Fragment {

    public Topic1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_topic1, container, false);

        //Button to change to Topic 2
        Button nextButton =v.findViewById(R.id.NextTopicButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment,new Topic2());
                getActivity().setTitle("Topic 2");
                fr.commit();

            }
        });

        //Inputs content into textviews in fragment
        TextView paragraph1 = v.findViewById(R.id.T1Paragraph1);
        paragraph1.setText("There are THREE main factors when deciding the type of financing:");
        TextView paragraph2 = v.findViewById(R.id.T1Paragraph2);
        paragraph2.setText("HINT: These factors affect how much cash is generated in a given period, differentiate businesses and their varying financing needs");
        TextView paragraph4 = v.findViewById(R.id.T1Paragraph4);
        paragraph4.setText("- Value of the output relative to the value of the input (how profitable it is) \n\n Example: \n - Low profitability in a very competitive industry where there is a large number of options and customers seek lowest prices. \n - Consider EXTERNAL financing! \n If high profitability, consider INTERNAL financing.");
        TextView paragraph6 = v.findViewById(R.id.T1Paragraph6);
        paragraph6.setText("- Amount of assets that must be tied up in the business in order to generate sales (How long it takes to be cash flow positive after initial investments into assets) \n\n Example: \n Asset intensive means it requires large amounts of initial capital for purchase of stock or machinery for growth) \n - Consider EXTERNAL financing! \n If asset light, consider INTERNAL financing.");
        TextView paragraph8 = v.findViewById(R.id.T1Paragraph8);
        paragraph8.setText("- The speed at which a venture needs to grow \n\n Example: \n High growth requirements means you require more assets to grow or develop network effects \n Again, in this scenario, consider EXTERNAL financing \n If low growth requirements, consider INTERNAL financing. \n\n");
        return v;
    }

}
