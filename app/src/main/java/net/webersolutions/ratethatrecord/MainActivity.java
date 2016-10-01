package net.webersolutions.ratethatrecord;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

// ******************************************************
// Janet Weber   9/30/2016
// Java App Dev 1   CDM-290
//
// Assignment:  Bootcamp Chapter 6
// "Does it have a good beat and can you dance to it?" Create a app that plays 3 songs and allows
// you to rank them by your preference.
// ******************************************************
public class MainActivity extends AppCompatActivity {

    Button songBtn1, songBtn2, songBtn3, songBtn4, displayRankings; // 4 song buttons, show rankings button
    MediaPlayer mpLoveShack, mpThreeSteps, mpAndSheWas, mpBaby;     // Media players for each song (couldn't
                                                                    //   figure out how to use only one)
    RatingBar ratingBar;                                            // Rating bar for ranking songs
    int playing;                                                    // 0 - notplaying, 1-playing
    int num = -1;                                                   // song index 0-3
    double[] stars = {0.0,0.0,0.0,0.0};                             // array with song ratings
    String[] songs = {"Love Shack", "Gi'me Three Steps",
            "Stay With Me Tonight", "And She Was"};
    String ratedValue, tmpString;                                   // used to construct output strings
    boolean showRankingsBtn, showingRankings = false;               // used hide/show button and rankings
    TextView rankingsDisplay;                                       // contains rankings output
    int retainVis1=0, retainVis2=0, retainVis3=0, retainVis4=0;

    // *******************************************************
    // onCreate method
    // ****************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayRankings = (Button) findViewById(R.id.btnShowRankings);  // initialize show rankings button and
        displayRankings.setOnClickListener(btnShowRankings);            // it's listener method and hide it initially
        displayRankings.setVisibility(View.INVISIBLE);
        rankingsDisplay = (TextView) findViewById(R.id.txtRankings);    // initialize widget to hold rankings display
        rankingsDisplay.setVisibility(View.INVISIBLE);                  // and hide it initially

        songBtn1 = (Button) findViewById(R.id.btnSong1);            // initiaize 4 song buttons and
        songBtn2 = (Button) findViewById(R.id.btnSong2);            //   their listener method
        songBtn3 = (Button) findViewById(R.id.btnSong3);
        songBtn4 = (Button) findViewById(R.id.btnSong4);
        songBtn1.setOnClickListener(leclicke);
        songBtn2.setOnClickListener(leclicke);
        songBtn3.setOnClickListener(leclicke);
        songBtn4.setOnClickListener(leclicke);

        mpLoveShack = new MediaPlayer();                            // declare/initialize 4 media players
        mpLoveShack = MediaPlayer.create(this, R.raw.loveshack);
        mpThreeSteps = new MediaPlayer();
        mpThreeSteps = MediaPlayer.create(this, R.raw.threesteps);
        mpBaby = new MediaPlayer();
        mpBaby = MediaPlayer.create(this, R.raw.staywithmetonight);
        mpAndSheWas = new MediaPlayer();
        mpAndSheWas = MediaPlayer.create(this, R.raw.andshewas);

        playing = 0;                                                // set playing indicator to 0 (not playing)

        // *   *   *   *   *   *   *   *   *   *   *   *   *   *
        // This code section implements the rating bar.
        // *   *   *   *   *   *   *   *   *   *   *   *   *   *
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);                           // initialize rating bar and
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() { // set up listener
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratedValue = String.valueOf(ratingBar.getRating());                     // use method to get value for rating
                tmpString = "You have rated the Song : " + ratedValue + "/5.";          // construct output string
                Toast.makeText(MainActivity.this,tmpString,Toast.LENGTH_SHORT).show();  // Display song rating in toast msg.

                if (num != -1) {                                                        // use song index to insert rating into array
                    stars[num] = ratingBar.getRating();
                }

                showRankingsBtn = true;                                                 // initialize show rankings button
                for (int i=0; i<4; i++) {                                               // then check if any songs remain
                    if (stars[i]==0) {                                                  // unranked (0) - if so, set to false
                        showRankingsBtn = false;
                    }
                }

                if (showRankingsBtn) {                                                  // test boolean and display accordingly or not
                    displayRankings.setVisibility(View.VISIBLE);
                }
            } // end of onRatingChanged()
        }); // end of setOnRatingBarChangeListener method
}  // end of onCreate()

// **********************************************************************************
// Function for song buttons (all 4) listener.
// **********************************************************************************
    private View.OnClickListener leclicke = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.equals(songBtn1)) {                                // songBtn1 invoked
                num = 0;                                                // set song index to 0
                //Toast.makeText(MainActivity.this,"Button 1",Toast.LENGTH_SHORT).show();
                switch (playing) {                                      // swith on if song is playing
                    case 0:
                        mpLoveShack.start();                            // if not playing, start song
                        playing = 1;                                    //   and indicate playing
                        songBtn2.setVisibility(View.INVISIBLE);         //   then hide other 3 songs
                        songBtn3.setVisibility(View.INVISIBLE);
                        songBtn4.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        mpLoveShack.pause();                            // if playing, pause song
                        playing = 0;                                    //   and indicate not playing
                        songBtn2.setVisibility(View.VISIBLE);           //   then display othr 3 songs.
                        songBtn3.setVisibility(View.VISIBLE);
                        songBtn4.setVisibility(View.VISIBLE);
                        break;
                }
            } else if (view.equals(songBtn2)) {                         // songBtn2 invoked so
                num = 1;                                                // set song index to 1
                //Toast.makeText(MainActivity.this,"Button 2",Toast.LENGTH_SHORT).show();
                switch (playing) {
                    case 0:                                             // See above section for songBtn1
                        mpThreeSteps.start();
                        playing = 1;
                        songBtn1.setVisibility(View.INVISIBLE);
                        songBtn3.setVisibility(View.INVISIBLE);
                        songBtn4.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        mpThreeSteps.pause();
                        playing = 0;
                        songBtn1.setVisibility(View.VISIBLE);
                        songBtn3.setVisibility(View.VISIBLE);
                        songBtn4.setVisibility(View.VISIBLE);
                        break;
                }
            } else if (view.equals(songBtn3)) {                         // songBtn3 invoked so
                num = 2;                                                // set song index to 2
                //Toast.makeText(MainActivity.this,"Button 3",Toast.LENGTH_SHORT).show();
                switch (playing) {
                    case 0:                                             // See above section for songBtn1
                        mpBaby.start();
                        playing = 1;
                        songBtn1.setVisibility(View.INVISIBLE);
                        songBtn2.setVisibility(View.INVISIBLE);
                        songBtn4.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        mpBaby.pause();
                        playing = 0;
                        songBtn1.setVisibility(View.VISIBLE);
                        songBtn2.setVisibility(View.VISIBLE);
                        songBtn4.setVisibility(View.VISIBLE);
                        break;
                }
            } else {                                                    // songBtn4 invoked so
                num = 3;                                                // set song index to 3
                //Toast.makeText(MainActivity.this,"Button 4",Toast.LENGTH_SHORT).show();
                switch (playing) {
                    case 0:                                             // See above section for songBtn1
                        mpAndSheWas.start();
                        playing = 1;
                        songBtn1.setVisibility(View.INVISIBLE);
                        songBtn2.setVisibility(View.INVISIBLE);
                        songBtn3.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        mpAndSheWas.pause();
                        playing = 0;
                        songBtn1.setVisibility(View.VISIBLE);
                        songBtn2.setVisibility(View.VISIBLE);
                        songBtn3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        } // end of onClick()
    }; // end of leclicke View.OnClickListener()

    // **********************************************************************************
    // Function for show ratings button listener
    // **********************************************************************************
    Button.OnClickListener btnShowRankings = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (showingRankings) { // rankings are currently displayed - we want to switch so NOT showing rankings
                displayRankings.setText("Show Rankings");     // set new button text
                showingRankings = false;                      // set boolean to indicate no longer showing rankings
                rankingsDisplay.setVisibility(View.INVISIBLE);// hide the rankings display

                songBtn1.setVisibility(retainVis1);           // set the song buttons visibilities to their
                songBtn2.setVisibility(retainVis2);           //   retained values (when Show Rankings btn
                songBtn3.setVisibility(retainVis3);           //   was clicked.
                songBtn4.setVisibility(retainVis4);
            } else {              // rankings are NOT currently displayed - we want to SHOW them
                retainVis1 = songBtn1.getVisibility();        // save the current button visibilities - could
                retainVis2 = songBtn2.getVisibility();        //   be all are visibile or only 1
                retainVis3 = songBtn3.getVisibility();
                retainVis4 = songBtn4.getVisibility();

                displayRankings.setText("Hide Rankings");     // set the new button text
                showingRankings = true;                       // set the boolean to indicate showing rankings

                songBtn1.setVisibility(View.INVISIBLE);       //  hide all the song buttons
                songBtn2.setVisibility(View.INVISIBLE);
                songBtn3.setVisibility(View.INVISIBLE);
                songBtn4.setVisibility(View.INVISIBLE);

                String toastString = "";                      // initialize output string
                for (int i=0; i<4; i++) {                     // create output string
                    toastString = toastString + stars[i] + "\t\t" + songs[i] + "\n";
                }
                //Toast.makeText(MainActivity.this,toastString,Toast.LENGTH_LONG).show();
                rankingsDisplay.setText(toastString);         // put output string into widget
                rankingsDisplay.setVisibility(View.VISIBLE);  // set widget visibility to display
            }
        } // end of onClick()
    };  // end of btnShowRankings Button.OnClickListener

}// end of class
