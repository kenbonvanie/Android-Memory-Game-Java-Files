package com.kennethJoseph92.ken.memorygame;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kennethJoseph92.ken.memorygame.R.id;
import static com.kennethJoseph92.ken.memorygame.R.layout;
/**
 * Created by Ken on 10/20/2014.
 */


public class Game extends Activity {

    // some global variables
    //TextView wel;
    TextView high;
    TextView totalScore_TEXT;
    int totalScore = 0;
    int oldScore = 0;
    int score = 0;
    String name = "";
    SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

    int k = 0;
    public List<Integer> numbers = new ArrayList<Integer>();
    Random rand = new Random();
    Integer randomNum = rand.nextInt((4 - 0) + 1) + 0;
    TextView tv = null;
    String word = "";
    int currentCircle = 0;
    int mover = 0;
    Paint paint;

    int speed = 0;
    ArrayList<Integer> sounds = new ArrayList<Integer>();
    String numOfFlashes = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(layout.game);
        //wel = (TextView)findViewById(id.welcome);
        high = (TextView)findViewById(id.highScoreText);
/*
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("STRING_I_NEED");
        score = bundle.getInt("INT_I_NEED");
        oldScore = score;
        wel.setText("Welcome: " + name);
        high.setText("Highest Score: " + score);
*/
        Bundle bundle = getIntent().getExtras();
        speed = bundle.getInt("SPEED");
        sounds = bundle.getIntegerArrayList("SOUNDS");
        numOfFlashes = bundle.getString("numOfFlashes");

        int numOfFlashesInt = Integer.parseInt(numOfFlashes);

        numOfFlashesInt = numOfFlashesInt - 1;

        //oldScore = score;
        DBAdapter db = new DBAdapter(this);
        db.open();

        Cursor c = db.getScoreByPlayersName();
        try
        {
            score = Integer.parseInt(c.getString(0));
        }
        catch (Exception ex)
        {
            db.insertPlayer();
            Cursor cTwo = db.getScoreByPlayersName();
            score = Integer.parseInt(cTwo.getString(0));
        }
        oldScore = score;
        high.setText("Highest Score: " + score);



        final Button lol = (Button)findViewById(id.kenred);
        final Button black = (Button)findViewById(id.black);
        final Button green = (Button)findViewById(id.green);
        final Button blue = (Button)findViewById(id.blue);
        final Button yellow = (Button)findViewById(id.yellow);



        // set all alphas to 0.2f
        lol.setAlpha(0.2f);
        black.setAlpha(0.2f);
        blue.setAlpha(0.2f);
        green.setAlpha(0.2f);
        yellow.setAlpha(0.2f);


        // insert 3 random numbers
        randomNum = rand.nextInt((4 - 0) + 1) + 0;
        numbers.add(randomNum);

        for(int i =0; i < numOfFlashesInt; ++i)
        {
            k++;
            do {
                randomNum = rand.nextInt((4 - 0) + 1) + 0;
            } while (numbers.get(k - 1) == randomNum);
            numbers.add(randomNum);
        }
        //k++;
        /*
        do
        {
            randomNum = rand.nextInt((4 - 0) + 1) + 0;
        } while (numbers.get(k - 1) == randomNum);
        numbers.add(randomNum);
*/
        k++;

    }

    protected void onDestroy()
    {
        super.onDestroy();
        sp.release();
    }

    protected void onPause()
    {
        super.onPause();
        sp.release();
        finish();
    }


    protected void onStart()
    {
        super.onStart();
        playAnimation();
    }

    protected void onResume()
    {
        super.onResume();

    }


    // this method plays the animation to fade in and out for each randomized button
    void playAnimation()
    {
        //initialize buttons
        final Button lol = (Button)findViewById(id.kenred);
        final Button black = (Button)findViewById(id.black);
        final Button green = (Button)findViewById(id.green);
        final Button blue = (Button)findViewById(id.blue);
        final Button yellow = (Button)findViewById(id.yellow);

        // making sure all buttons are an opacity of 0.2
        lol.setAlpha(0.2f);
        black.setAlpha(0.2f);
        green.setAlpha(0.2f);
        blue.setAlpha(0.2f);
        yellow.setAlpha(0.2f);


        CountDownTimer timer = null;
        timer = new CountDownTimer( (numbers.size() * speed) + 200, 1000 ) {
            @Override public void onTick( long millisUntilFinished ) {
                lol.setClickable(false);
                black.setClickable(false);
                green.setClickable(false);
                blue.setClickable(false);
                yellow.setClickable(false);
            }
            @Override public void onFinish() {
                lol.setClickable(true);
                black.setClickable(true);
                green.setClickable(true);
                blue.setClickable(true);
                yellow.setClickable(true);
            }
        };
        timer.start();

        long count = 0;
        for(int i = 0; i < numbers.size(); ++i)
        {

            if(numbers.get(i) == 0)
            {

                final int soundId = sp.load(this, sounds.get(2),1 );

                CountDownTimer timerblack = null;
                timerblack = new CountDownTimer( (i * speed) + 1000, speed ) {
                    @Override public void onTick( long millisUntilFinished ) {
                    }
                    @Override public void onFinish() {

                        sp.play(soundId, 1, 1, 1, 0, 1);

                        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(black, black.ALPHA, 1f, 0.2f);

                        fadeAnim.setDuration(speed);
                        fadeAnim.setStartDelay(0);
                        fadeAnim.start();

                    }
                };
                timerblack.start();


            }

            if(numbers.get(i) == 1)
            {
                final int soundId = sp.load(this, sounds.get(0),1 );
                CountDownTimer timerlol = null;
                timerlol = new CountDownTimer( (i * speed) + 1000, speed ) {
                    @Override public void onTick( long millisUntilFinished ) {
                    }
                    @Override public void onFinish() {
                        sp.play(soundId, 1, 1, 1, 0, 1);
                        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(lol, lol.ALPHA, 1f, 0.2f);

                        fadeAnim.setDuration(speed);
                        fadeAnim.setStartDelay(0);
                        fadeAnim.start();
                    }
                };
                timerlol.start();

            }


            if(numbers.get(i) == 2)
            {

                final int soundId = sp.load(this, sounds.get(3),1 );
                CountDownTimer timerblue = null;
                timerblue = new CountDownTimer( (i * speed) + 1000, speed ) {
                    @Override public void onTick( long millisUntilFinished ) {
                    }
                    @Override public void onFinish() {

                        sp.play(soundId, 1, 1, 1, 0, 1);

                        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(blue, blue.ALPHA, 1f, 0.2f);

                        fadeAnim.setDuration(speed);
                        fadeAnim.setStartDelay(0);
                        fadeAnim.start();
                    }
                };
                timerblue.start();

            }


            if(numbers.get(i) == 3)
            {

                final int soundId = sp.load(this, sounds.get(4),1 );
                CountDownTimer timergreen = null;
                timergreen = new CountDownTimer( (i * speed) + 1000, speed ) {
                    @Override public void onTick( long millisUntilFinished ) {
                    }
                    @Override public void onFinish() {
                        sp.play(soundId, 1, 1, 1, 0, 1);

                        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(green, green.ALPHA, 1f, 0.2f);

                        fadeAnim.setDuration(speed);
                        fadeAnim.setStartDelay(0);
                        fadeAnim.start();
                    }
                };
                timergreen.start();

            }


            if(numbers.get(i) == 4)
            {
                final int soundId = sp.load(this, sounds.get(1),1 );
                CountDownTimer timeryellow = null;
                timeryellow = new CountDownTimer( (i * speed) + 1000, speed ) {
                    @Override public void onTick( long millisUntilFinished ) {
                    }
                    @Override public void onFinish() {

                        sp.play(soundId, 1, 1, 1, 0, 1);
                        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(yellow, yellow.ALPHA, 1f, 0.2f);

                        fadeAnim.setDuration(speed);
                        fadeAnim.setStartDelay(0);
                        fadeAnim.start();
                    }
                };
                timeryellow.start();


            }
        }

    }

    public void onButtonClick(View view) {

        final Button lol = (Button)findViewById(id.kenred);
        final Button black = (Button)findViewById(id.black);
        final Button green = (Button)findViewById(id.green);
        final Button blue = (Button)findViewById(id.blue);
        final Button yellow = (Button)findViewById(id.yellow);
        randomNum = rand.nextInt((4 - 0) + 1) + 0;

        // plays a short animation for each button clicked, if it's the wrong button then its gameover and the updated score is inserted to the database if the new score is higher then the old score.
        if(view.getId() == id.kenred)
        {
            currentCircle = 1;
            if (currentCircle == numbers.get(mover))
            {
                addOnePoint();

                final int soundId = sp.load(this, sounds.get(0),1 );
                sp.play(soundId, 1, 1, 1, 0, 1);

                lol.setAlpha(1.0f);
                lol.setVisibility(lol.VISIBLE);
                lol.animate()
                        .alpha(0.2f)
                        .setDuration(500)
                        .setListener(null)
                        .setStartDelay(0);
            }
            else
            {
                EndTheGame();
            }
            mover++;
            if(numbers.size() == mover && currentCircle == numbers.get(mover-1))
            {
                // restart sequence
                restartSequence();
            }
        }
        else if(view.getId() == id.black)
        {
            currentCircle = 0;
            if(currentCircle == numbers.get(mover))
            {
                final int soundId = sp.load(this, sounds.get(2),1 );
                sp.play(soundId, 1, 1, 1, 0, 1);
                addOnePoint();

                black.setAlpha(1.0f);
                black.setVisibility(black.VISIBLE);
                black.animate()
                        .alpha(0.2f)
                        .setDuration(500)
                        .setListener(null)
                        .setStartDelay(0);
            }
            else
            {
                EndTheGame();
            }

            mover++;
            if(numbers.size() == mover && currentCircle == numbers.get(mover-1))
            {
                // restart sequence
                restartSequence();
            }

        }
        else if(view.getId() == id.yellow)
        {
            currentCircle = 4;
            if(currentCircle == numbers.get(mover))
            {
                addOnePoint();

                final int soundId = sp.load(this, sounds.get(1),1 );
                sp.play(soundId, 1, 1, 1, 0, 1);

                yellow.setAlpha(1.0f);
                yellow.setVisibility(yellow.VISIBLE);
                yellow.animate()
                        .alpha(0.2f)
                        .setDuration(500)
                        .setListener(null)
                        .setStartDelay(0);
            }
            else
            {
                EndTheGame();
            }

            mover++;
            if(numbers.size() == mover && currentCircle == numbers.get(mover-1))
            {
                // restart sequence
                restartSequence();
            }
        }
        else if(view.getId() == id.blue)
        {
            currentCircle = 2;
            if(currentCircle == numbers.get(mover))
            {
                addOnePoint();

                final int soundId = sp.load(this, sounds.get(3),1 );
                sp.play(soundId, 1, 1, 1, 0, 1);

                blue.setAlpha(1.0f);
                blue.setVisibility(blue.VISIBLE);
                blue.animate()
                        .alpha(0.2f)
                        .setDuration(500)
                        .setListener(null)
                        .setStartDelay(0);
            }
            else
            {
                EndTheGame();
            }
            mover++;
            if(numbers.size() == mover && currentCircle == numbers.get(mover-1))
            {
                // restart sequence
                restartSequence();
            }

        }
        else if(view.getId() == id.green)
        {
            currentCircle = 3;
            if(currentCircle == numbers.get(mover))
            {
                addOnePoint();

                final int soundId = sp.load(this, sounds.get(4),1 );
                sp.play(soundId, 1, 1, 1, 0, 1);

                green.setAlpha(1.0f);
                green.setVisibility(green.VISIBLE);
                green.animate()
                        .alpha(0.2f)
                        .setDuration(500)
                        .setListener(null)
                        .setStartDelay(0);
            }
            else
            {
                // End the game
                EndTheGame();

            }

            mover++;

            if(numbers.size() == mover && currentCircle == numbers.get(mover-1))
            {
                // restart sequence
                restartSequence();
            }
        }
    }


    void restartSequence()
    {
        addTenPoints();

        do
        {
            randomNum = rand.nextInt((4 - 0) + 1) + 0;
        }
        while (numbers.get(k - 1).equals(randomNum));
        k++;

        numbers.add(randomNum);

        mover = 0;
        //play animation
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("AWESOME");
        alertDialogBuilder.setMessage("Good Job");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                playAnimation();

            }
        });
        alertDialogBuilder.show();

    }

    void addOnePoint()
    {
        high = (TextView)findViewById(id.highScoreText);

        totalScore_TEXT = (TextView)findViewById(id.totalScore);

        totalScore += 1;
        totalScore_TEXT.setText("Score: " + totalScore);

        if(totalScore > score)
        {
            score = totalScore;
            high.setText("Highest Score: " + score);

        }

    }

    void addTenPoints()
    {
        high = (TextView)findViewById(id.highScoreText);

        totalScore_TEXT = (TextView)findViewById(id.totalScore);

        totalScore += 10;
        totalScore_TEXT.setText("Score: " + totalScore);

        if(totalScore > score)
        {
            score = totalScore;
            high.setText("Highest Score: " + score);

        }
    }


    void EndTheGame()
    {
        String msg = "You Scored " + totalScore + " points! You are not that bad";

        if(score == totalScore) // player beat their high score
        {
            // do an update
            DBAdapter db = new DBAdapter(this);
            db.open();
            db.updatePlayersHighScore(oldScore, totalScore );

            db.close();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Game Over");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("NEW HIGH SCORE!!!! " + msg + ", You will be taken back to the home screen");
            alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    // if this button is clicked, close
                    // current activity
                    doit();
                    finish();
                    Game.this.finish();
                }
            });
            alertDialogBuilder.show();

        }
        else
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Game Over");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("Good Job!!!! " + msg + "! You will be taken back to the home screen.");
            alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    // if this button is clicked, close
                    // current activity
                    doit();
                    finish();
                    Game.this.finish();
                }
            });

            alertDialogBuilder.show();
        }


    }

    // back to the home screen after clicking ok
    void doit()
    {
        this.finish();

    }
}


