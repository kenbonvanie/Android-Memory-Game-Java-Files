package com.kennethJoseph92.ken.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;


public class MemoryGame extends Activity {


    SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.memory_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view) {

        // instructions
        if(view.getId() == R.id.instructions)
        {
            Intent i = new Intent(this, Instructions_About.class);
            startActivity(i);
        }
        // Exit
        else if(view.getId() == R.id.exit)
        {
            this.finish();
        }
        else if(view.getId() == R.id.newGame)
        {
            Intent i = new Intent(this, gameReady.class);
            startActivity(i);
        }
    }

    void makeSomeNoise(Button button, int noise)
    {
        sp.play(noise, 1, 1, 1, 0, 1);

        button.setAlpha(1.0f);
        button.setVisibility(button.VISIBLE);
        button.animate()
                .alpha(0.2f)
                .setDuration(500)
                .setListener(null)
                .setStartDelay(0);
    }
}
