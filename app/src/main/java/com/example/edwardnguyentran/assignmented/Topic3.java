package com.example.edwardnguyentran.assignmented;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Topic3 extends Fragment {
    public static final String DEVELOPER_KEY = "AIzaSyCz6HrI6HvR_avsJb7pOysiU3A2VWw3Wdw";
    private static final String VIDEO_ID = "677ZtSMr4-4";
    private YouTubePlayer activePlayer;
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    public Topic3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_topic3, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        //Button to return to Home Page
        Button returnButton =v.findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment,new HomePage());
                getActivity().setTitle("Home Page");
                fr.commit();

            }
        });

        //Inputs content into textviews in fragment
        TextView t3Hint = v.findViewById(R.id.T3Hint);
        t3Hint.setText("HINT: The selection of investors are heavily dependent on the stage of the firm and its business needs, as discussed in Topic 1.");
        TextView paragraph2 = v.findViewById(R.id.T3Paragraph2);
        paragraph2.setText("- Individuals or group of individuals who invest their own personal money into startup ventures \n - Often local business leaders, wealthy individuals and former entrepreneurs within their communities \n - Typically for smaller investments less than $1 million to bridge the gap between the generation of raw idea and achievement of sufficient maturity and momentum for VC \n\n Strengths: \n - Deep expertise in domain \n - Large network of connections to talent pool and experts \n\n HOWEVER \n - Due diligence on the investor is required to select the ideal candidate");
        TextView paragraph4 = v.findViewById(R.id.T3Paragraph4);
        paragraph4.setText("- Invest in new ventures using funds raised from limited partners such as pension funds, endowments and wealthy individuals. These firms are run by professional investors (venture capitalists) \n\n Strengths: \n - Larger fundings \n - Can be staged, received financing in multiple rounds \n - Expertise in the domain and network of connections \n\n HOWEVER \n - VC must diversify their portfolio, limited capital investment \n - Result in a transfer of ownership through the financing round \n - May withhold future financing, as seen in Table 1 below");
        TextView paragraph6 = v.findViewById(R.id.T3Paragraph6);
        paragraph6.setText("- Larger firms directly investing in external ventures, in order to gain exposure to a new technology or product area that is important to the firm’s core operations or for financial reasons\n\n Strengths: \n - Able to provide key controlled resources and assets \n - Strategic investors have the funds required to invest in ventures \n - Protection of intellectual and property rights \n - Partial liquidity for owners and previous investors \n\n HOWEVER \n - May take over business \n - May acquire majority of shares and control the direction of the company \n\n");

        //Creates Youtube Player and accesses youtube video through Video ID
        transaction.add(R.id.Video2, youTubePlayerFragment).commit();
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


