package com.example.dragdropexample;

import android.content.ClipData;
import android.content.ClipDescription;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView fish;
    private ImageView banana;
    private ImageView cat;
    private ImageView monkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fish = (ImageView) findViewById(R.id.fish);
        this.banana = (ImageView) findViewById(R.id.banana);
        this.cat = (ImageView) findViewById(R.id.cat);
        this.monkey = (ImageView) findViewById(R.id.monkey);

        this.fish.setImageResource(R.drawable.fish);
        this.banana.setImageResource(R.drawable.banana);
        this.cat.setImageResource(R.drawable.praying_cat);
        this.monkey.setImageResource(R.drawable.praying_monkey);

        String fishMimeType = ClipDescription.MIMETYPE_TEXT_HTML;
        String bananaMimeType = ClipDescription.MIMETYPE_TEXT_PLAIN;

        this.fish.setOnLongClickListener(new FoodLongClickListener(fishMimeType));
        this.banana.setOnLongClickListener(new FoodLongClickListener(bananaMimeType));

        this.monkey.setOnDragListener(new FoodOnDragListener(R.drawable.praying_monkey,
                R.drawable.grumpy_monkey,
                R.drawable.reaching_monkey,
                R.drawable.relaxing_monkey,
                bananaMimeType));

        this.cat.setOnDragListener(new FoodOnDragListener(R.drawable.praying_cat,
                R.drawable.grumpy_cat,
                R.drawable.reaching_cat,
                R.drawable.relaxing_cat,
                fishMimeType));
    }
}
