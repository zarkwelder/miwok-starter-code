package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    /** Handles playback of all sound files */
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Direct volume key presses to control the app's sound effects
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.word_list);

        //create array of words to be translated
        final ArrayList<Word> wordsList = new ArrayList<>();

        wordsList.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        wordsList.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        wordsList.add(new Word("three", "tolookosu", R.drawable.number_three));
        wordsList.add(new Word("four", "oyyisa", R.drawable.number_four));
        wordsList.add(new Word("five", "massokka", R.drawable.number_five));
        wordsList.add(new Word("six", "temmokka", R.drawable.number_six));
        wordsList.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        wordsList.add(new Word("eight", "kawinta", R.drawable.number_eight));
        wordsList.add(new Word("nine", "wo'e", R.drawable.number_nine));
        wordsList.add(new Word("ten", "na'aacha", R.drawable.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter itemsAdapter = new WordAdapter(this, wordsList, R.color.category_numbers);

        //Get a reference to the ListView
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        //Attach an adapter to that ListView
        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(itemsAdapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = wordsList.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer.create(NumbersActivity.this, word.getSoundResourceId());

                //Start the audio file
                mMediaPlayer.start();
            }
        });

    }
}
