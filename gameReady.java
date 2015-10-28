package com.kennethJoseph92.ken.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 10/13/2015.
 */
public class gameReady extends Activity {

    private Spinner lv;
    List<Integer> listOfPNumbers = new ArrayList<Integer>();
    Boolean chosenSpeed = false;
    Boolean chosenSound = false;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_ready);

        final Button lol = (Button)findViewById(R.id.mainredt);
        final Button black = (Button)findViewById(R.id.mainblackt);
        final Button green = (Button)findViewById(R.id.maingreent);
        final Button blue = (Button)findViewById(R.id.mainbluet);
        final Button yellow = (Button)findViewById(R.id.mainyellowt);

        final Button lol2 = (Button)findViewById(R.id.mainredb);
        final Button black2 = (Button)findViewById(R.id.mainblackb);
        final Button green2 = (Button)findViewById(R.id.maingreenb);
        final Button blue2 = (Button)findViewById(R.id.mainblueb);
        final Button yellow2 = (Button)findViewById(R.id.mainyellowb);


        float lolx = lol.getX();
        float blackx = black.getX();
        float greenx = green.getX();
        float bluex = blue.getX();
        float yellowx = yellow.getX();

        float lol2x = lol2.getX();
        float black2x = black2.getX();
        float green2x = green2.getX();
        float blue2x = blue2.getX();
        float yellow2x = yellow2.getX();


        lol.setX(lol.getX() + 1500);
        black.setX(black.getX() + 1500);
        green.setX(green.getX() + 1500);
        blue.setX(blue.getX() + 1500);
        yellow.setX(yellow.getX() + 1500);

        float offset = 500;
        lol2.setX(lol2.getX() - 1500 - offset);
        black2.setX(black2.getX() - 1500 - offset);
        green2.setX(green2.getX() - 1500- offset);
        blue2.setX(blue2.getX() - 1500 - offset);
        yellow2.setX(yellow2.getX() - 1500 - offset);

        // fromx tox fromy toy
        TranslateAnimation animation = new TranslateAnimation(lolx + 1500, lolx-9000, lol.getY(), lol.getY());
        animation.setDuration(10000);
        animation.setFillAfter(false);
        animation.setRepeatCount(Animation.INFINITE);
        lol.startAnimation(animation);

        TranslateAnimation animation2 = new TranslateAnimation(blackx + 1500, blackx-9000, black.getY(), black.getY());
        animation2.setDuration(10000);
        animation2.setFillAfter(false);
        animation2.setRepeatCount(Animation.INFINITE);
        black.startAnimation(animation2);

        TranslateAnimation animation3 = new TranslateAnimation(greenx + 1500, greenx-9000, green.getY(), green.getY());
        animation3.setDuration(10000);
        animation3.setFillAfter(false);
        animation3.setRepeatCount(Animation.INFINITE);
        green.startAnimation(animation3);

        TranslateAnimation animation4 = new TranslateAnimation(bluex + 1500, bluex-9000, blue.getY(), blue.getY());
        animation4.setDuration(10000);
        animation4.setFillAfter(false);
        animation4.setRepeatCount(Animation.INFINITE);
        blue.startAnimation(animation4);

        TranslateAnimation animation5 = new TranslateAnimation(yellowx + 1500, yellowx-9000, yellow.getY(), yellow.getY());
        animation5.setDuration(10000);
        animation5.setFillAfter(false);
        animation5.setRepeatCount(Animation.INFINITE);
        yellow.startAnimation(animation5);


        // bottom yo
        TranslateAnimation animation6 = new TranslateAnimation(lol2x - 1500 - offset , lol2x+9000, lol2.getY(), lol2.getY());
        animation6.setDuration(10000);
        animation6.setFillAfter(false);
        animation6.setRepeatCount(Animation.INFINITE);
        lol2.startAnimation(animation6);

        TranslateAnimation animation7 = new TranslateAnimation(black2x - 1500- offset, black2x+9000, black2.getY(), black2.getY());
        animation7.setDuration(10000);
        animation7.setFillAfter(false);
        animation7.setRepeatCount(Animation.INFINITE);
        black2.startAnimation(animation7);

        TranslateAnimation animation8 = new TranslateAnimation(green2x - 1500- offset, green2x+9000, green2.getY(), green2.getY());
        animation8.setDuration(10000);
        animation8.setFillAfter(false);
        animation8.setRepeatCount(Animation.INFINITE);
        green2.startAnimation(animation8);

        TranslateAnimation animation9 = new TranslateAnimation(blue2x - 1500 -offset, blue2x+9000, blue2.getY(), blue2.getY());
        animation9.setDuration(10000);
        animation9.setFillAfter(false);
        animation9.setRepeatCount(Animation.INFINITE);
        blue2.startAnimation(animation9);

        TranslateAnimation animation10 = new TranslateAnimation(yellow2x - 1500 - offset, yellow2x+9000, yellow2.getY(), yellow2.getY());
        animation10.setDuration(10000);
        animation10.setFillAfter(false);
        animation10.setRepeatCount(Animation.INFINITE);
        yellow2.startAnimation(animation10);


        for(int i = 3; i < 11; ++i)
        {
            listOfPNumbers.add(i);
        }

        lv = (Spinner)findViewById(R.id.spinner1);
        lv.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1, listOfPNumbers));
        lv.setEnabled(true);

    }




    public void onButtonClick(View view) {

        final RadioButton fast = (RadioButton)findViewById(R.id.fast);
        final RadioButton med = (RadioButton)findViewById(R.id.med);
        final RadioButton slow = (RadioButton)findViewById(R.id.slow);

        final RadioButton piano = (RadioButton)findViewById(R.id.piano);
        final RadioButton guitar = (RadioButton)findViewById(R.id.guitar);

        final Button startGame = (Button)findViewById(R.id.startGame);


        int speed = 0;
        String sound = "";
        if(view.getId() == R.id.fast)
        {
            fast.setChecked(true);
            med.setChecked(false);
            slow.setChecked(false);
            chosenSpeed = true;

        }
        else if(view.getId() == R.id.med)
        {
            med.setChecked(true);
            fast.setChecked(false);
            slow.setChecked(false);
            chosenSpeed = true;
        }
        else if(view.getId() == R.id.slow)
        {
            slow.setChecked(true);
            med.setChecked(false);
            fast.setChecked(false);
            chosenSpeed = true;
        }

        if(view.getId() == R.id.guitar)
        {
            guitar.setChecked(true);
            piano.setChecked(false);
            chosenSound = true;
        }
        else if(view.getId() == R.id.piano)
        {
            piano.setChecked(true);
            guitar.setChecked(false);
            chosenSound = true;
        }

        if(view.getId() == R.id.startGame)
        {
            if(chosenSpeed == false || chosenSound == false)
            {
                Toast.makeText(this, "Please Fill in all fields",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                this.finish();
                Intent i = new Intent(this, Game.class);
                ArrayList<Integer> sounds = new ArrayList<Integer>();

                if (guitar.isChecked()) {
                    sounds.add(R.raw.red);
                    sounds.add(R.raw.yellow);
                    sounds.add(R.raw.black);
                    sounds.add(R.raw.blue);
                    sounds.add(R.raw.green);
                } else if (piano.isChecked()) {
                    sounds.add(R.raw.testgb);
                    sounds.add(R.raw.testcb);
                    sounds.add(R.raw.testeb);
                    sounds.add(R.raw.testfb);
                    sounds.add(R.raw.testbb);
                }

                if (fast.isChecked()) {
                    speed = 250;
                } else if (med.isChecked()) {
                    speed = 500;
                } else if (slow.isChecked()) {
                    speed = 1000;
                }
                String it = "";
                try {
                    it = lv.getSelectedItem().toString();
                } catch (Exception ex) {

                }
                i.putExtra("SPEED", speed);
                i.putExtra("SOUNDS", sounds);
                i.putExtra("numOfFlashes", it);
                startActivity(i);
            }
        }

    }



    }
