package com.example.edwardnguyentran.assignmented;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */

//Provides questions with answers at the end of the quiz for review
public class QuizFeedback extends Fragment {


    public QuizFeedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz_feedback, container, false);
        TextView feedbackinfo = v.findViewById(R.id.info);
        feedbackinfo.setText("Question: What are the three factors when deciding bootstrapping vs external financing?\n" +

                "Answer: Underlying Profitability, Asset Intensity, Pace of Growth\n" +

                "\n" +
                "Question: What is a debt investor?\n" +

                "Answer:Lend a fixed sum of money for a specified period \n at a given interest rate\n" +
                "\n" +

                "Question: What is a Equity investor?\n" +

                "Answer: Receive long-term ownership stake in a venture in exchange for capital\n" +
                "\n" +
                "Question: What is a angel investor?\n" +

                "Answer: Receive long-term ownership stake in a venture in exchange for capital\n" +
                "\n" +
                "Question: What is a factor that DOES NOT impact capital requirement?\n" +

                "Answer: Overall Profit\n" +
                "\n" +
                "Question: What is NOT a category of crowdfunding?\n" +

                "Answer: A/C Crowdfunding\n" +
                "\n" +

                "Question: What is the Equity Division formula?\n" +

                "Answer: Pre-money valuation + investment amount = post-money valuation\n" +
                "\n" +

                "Question: What is strategic investors?\n" +

                "Answer: Larger firms directly investing in external ventures\n" +

                "\n" +
                "Question: What is the formula for Expected value of investment?\n" +
                "Answer: Probability of success x value if successful \n" +
                "\n" +

                "Question: What do VC firms do?\n" +

                "Answer: Invest in new ventures using funds raised from limited partners such as pension funds, endowments and wealthy individuals \n\n");

        //Creates button to return to Home Page
        Button button = v.findViewById(R.id.goHome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment,new HomePage());
                getActivity().setTitle("Home Page");
                fr.commit();

            }
        });
    return v;
    }

}
