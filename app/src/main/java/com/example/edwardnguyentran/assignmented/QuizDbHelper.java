package com.example.edwardnguyentran.assignmented;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//Creates table and stores data
public class QuizDbHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "QUIZ.db";
    private static final int DATABASE_VERSION = 5;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creates database
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( " +
                QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    //Update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    //Inputting questions into database
    private void fillQuestionsTable() {
        Question q1 = new Question("What are the three factors when deciding bootstrapping vs external financing?", "A.Underlying Profitability, Asset Intensity, Pace of Growth", "B.Rate of customer uptake, Wages and benefits per employee, Revenue per customer", "C.Asset Intensity, Pace of Growth, Rate of customer uptake","D.Underlying Profitability, Wages and benefits per employee, Revenue per customer",1);
        addQuestion(q1);
        Question q2 = new Question("What is a debt investor?", "A.Individuals or group of individuals who invest their own personal money into startup ventures", "B.Receive long-term ownership stake in a venture in exchange for capital", "C.Lend a fixed sum of money for a specified period at a given interest rate","D.Larger firms directly investing in external ventures",3);
        addQuestion(q2);
        Question q3 = new Question("What is a Equity investor?", "A.Larger firms directly investing in external ventures", "B.Lend a fixed sum of money for a specified period at a given interest rate", "C.Individuals or group of individuals who invest their own personal money into startup ventures","D. Receive long-term ownership stake in a venture in exchange for capital",4);
        addQuestion(q3);
        Question q4 = new Question("What is a angel investor?", "A.Larger firms directly investing in external ventures", "B.Lend a fixed sum of money for a specified period at a given interest rate", "C.Individuals or group of individuals who invest their own personal money into startup ventures","D. Receive long-term ownership stake in a venture in exchange for capital",3);
        addQuestion(q4);
        Question q5 = new Question("What is a factor that DOES NOT impact capital requirement?", "A.Rate of customer uptake", "B.Number of personnel required at different stages", "C.Revenue per customer","D.Overall Profit",4);
        addQuestion(q5);
        Question q6 = new Question("What is NOT a category of crowdfunding?", "A.Token CrowdFunding", "B.CrowdFunding Investing", "C.A/C Crowdfunding","D.Regulation D Crowdfunding",3);
        addQuestion(q6);
        Question q7 = new Question("What is the Equity Division formula?", "A.Pre-money valuation + investment amount = post-money valuation", "B.Pre-money valuation / investment amount = post-money valuation", "C.Pre-money valuation - investment amount = post-money valuation","D. Investment amount - pre-money valuation= post-money valuation",1);
        addQuestion(q7);
        Question q8 = new Question("What is strategic investors?", "A.Larger firms directly investing in external ventures", "B.Lend a fixed sum of money for a specified period at a given interest rate", "C.Individuals or group of individuals who invest their own personal money into startup ventures","D. Receive long-term ownership stake in a venture in exchange for capital",1);
        addQuestion(q8);
        Question q9 = new Question("What is the formula for Expected value of investment?", "A.Probability of success x value if successful", "B.Pre-money valuation / investment amount", "C.Pre-money valuation - investment amount","D.Probability of success / value if successful",1);
        addQuestion(q9);
        Question q10 = new Question("What do VC firms do?", "A.Invest in new ventures using funds raised from limited partners such as pension funds, endowments and wealthy individuals", "B.Individuals or group of individuals who invest their own personal money into startup ventures", "C.Larger firms directly investing in external ventures","D.Lend a fixed sum of money for a specified period at a given interest rate",1);
        addQuestion(q10);
    }

    //Add new question
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionTable.TABLE_NAME, null, cv);
    }

    //Shuffles database questions
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}


