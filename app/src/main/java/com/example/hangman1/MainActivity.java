package com.example.hangman1;

import static java.sql.Types.NULL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText etLetter;
    ImageView ivHangman;
    int index = 0;
    String[] name = {"amos","idan","itay","tomer","nadav","robert","dolev","liav"};

    int randomwords = (int)(Math.random()*8);
    char checkname;
    TextView answer,checkwin,theword;
    String txt = "";
    TextView incorrectLetters;
    String str = "";
    boolean flag = false;
    boolean flag2 = true;
    boolean written = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etLetter = findViewById(R.id.editTextTextPersonName);
        ivHangman = findViewById(R.id.imageView);
        answer = findViewById(R.id.textView);
        incorrectLetters = findViewById(R.id.textView2);
        checkwin = findViewById(R.id.win);
        theword = findViewById(R.id.textView4);
        for (int i = 0; i < name[randomwords].length(); i++) {
            txt += "-";
        }
        answer.setText(txt);
        etLetter.addTextChangedListener(new TextWatcher() {
            boolean isClicked = true;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isClicked) {
                    checkname = etLetter.getText().toString().charAt(0);
                    changeWord();
                    flag2 = false;
                    checkname = etLetter.getText().toString().charAt(0);
                    changeWord();
                }
                isClicked = !isClicked;
                if (checkname != NULL)
                    etLetter.getText().clear();
                flag2 = true;
            }

        });
    }

    private void changeWord() {

        checkname = etLetter.getText().toString().charAt(0);
        Print();
        answer.setText(txt);
        answer.setVisibility(View.VISIBLE);
        if (flag != true&&flag2==true) {
            if (index < 6) {
                IncorrectLetter();
                incorrectLetters.setText(str);
                index++;
                int imageKey = getResources().getIdentifier("hangman" + index, "drawable", getPackageName());
                ivHangman.setImageResource(imageKey);
            }
        }
        flag = false;
        written = false;
        Checkwinorlose();

    }

    public void Print() {
        int co = 0;
        for (int i = 0; i < name[randomwords].length(); i++) {
            if (checkname == name[randomwords].charAt(i)) {
                txt = txt.substring(0, i) + name[randomwords].charAt(i) + txt.substring(i + 1);
                flag = true;
            }
        }
    }
    public void Checkwinorlose()
    {
        if(txt.equals(name[randomwords]))
        {
            checkwin.setVisibility(View.VISIBLE);
            checkwin.setText("you guessed the right number and won the game");
        }
        if(index==6)
        {
            checkwin.setVisibility(View.VISIBLE);
            checkwin.setText("you lost the game");
            theword.setVisibility(View.VISIBLE);
            theword.setText("the word is:"+" "+name[randomwords]);

        }
    }
    public void IncorrectLetter()
    {
        for(int j =0;j<str.length();j++)
        {
            if(checkname==str.charAt(j))
            {
                written = true;
            }
        }
        for(int i = 0;i<txt.length();i++)
        {
            if(checkname == txt.charAt(i))
            {
                written = true;
            }
        }
        if(written==false)
            str += checkname + " ";
    }

}