package com.kennethJoseph92.ken.memorygame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;


/**
 * Created by Ken on 10/26/2014.
 */
public class Instructions_About extends Activity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_about);


        ImageView iv= (ImageView)findViewById(R.id.imageView);
        // iv.setImageResource(R.drawable.apple);
        iv.setImageResource(R.drawable.one);

    }





}
