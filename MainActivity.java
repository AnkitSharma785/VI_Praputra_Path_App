package com.example.vipraputra;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declare the TextView and Buttons
    private TextView textView;
    private Button button7, button8, button9, button10, button11;

    // Declare the ImageButton and TextView
    private ImageButton imageButton;
    private TextView welcomeTextView;

    // Declare MediaPlayer instances for each audio file
    private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4, mediaPlayer5;

    // Buttons for Play/Pause
    private Button buttonPlayPause1, buttonPlayPause2, buttonPlayPause3, buttonPlayPause4, buttonPlayPause5;

    // Track currently playing MediaPlayer
    private MediaPlayer currentMediaPlayer = null;
    private Button currentButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ImageButton and TextView
        imageButton = findViewById(R.id.imageButton);
        welcomeTextView = findViewById(R.id.welcomeTextView);

        // Initialize buttons
        buttonPlayPause1 = findViewById(R.id.button);
        buttonPlayPause2 = findViewById(R.id.button3);
        buttonPlayPause3 = findViewById(R.id.button4);
        buttonPlayPause4 = findViewById(R.id.button5);
        buttonPlayPause5 = findViewById(R.id.button6);

        // Initialize MediaPlayers
        mediaPlayer1 = MediaPlayer.create(this, R.raw.audio1);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.audio2);
        mediaPlayer3 = MediaPlayer.create(this, R.raw.audio3);
        mediaPlayer4 = MediaPlayer.create(this, R.raw.audio4);
        mediaPlayer5 = MediaPlayer.create(this, R.raw.audio5);

        // Set listeners for each audio play/pause button
        buttonPlayPause1.setOnClickListener(view -> toggleAudio(mediaPlayer1, buttonPlayPause1));
        buttonPlayPause2.setOnClickListener(view -> toggleAudio(mediaPlayer2, buttonPlayPause2));
        buttonPlayPause3.setOnClickListener(view -> toggleAudio(mediaPlayer3, buttonPlayPause3));
        buttonPlayPause4.setOnClickListener(view -> toggleAudio(mediaPlayer4, buttonPlayPause4));
        buttonPlayPause5.setOnClickListener(view -> toggleAudio(mediaPlayer5, buttonPlayPause5));

        // Set an OnClickListener on the ImageButton for displaying the welcome message
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the welcome message in the TextView when the ImageButton is clicked
                welcomeTextView.setText("Welcome to VIPraputra Path App!");
                welcomeTextView.setVisibility(View.VISIBLE); // Show the TextView

                // Use a Handler to hide the TextView after 1 second (1000 milliseconds)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        welcomeTextView.setVisibility(View.GONE); // Hide the TextView
                    }
                }, 1000); // 1000 milliseconds = 1 second
            }
        });
    }

    private void toggleAudio(MediaPlayer selectedMediaPlayer, Button selectedButton) {
        // If another audio is playing, stop it
        if (currentMediaPlayer != null && currentMediaPlayer != selectedMediaPlayer) {
            currentMediaPlayer.pause();
            if (currentButton != null) {
                currentButton.setText(getButtonTextForAudio(currentButton));
            }
        }

        // Play or Pause the selected audio
        if (selectedMediaPlayer.isPlaying()) {
            selectedMediaPlayer.pause();
            selectedButton.setText(getButtonTextForAudio(selectedButton));
            currentMediaPlayer = null;
            currentButton = null;
        } else {
            selectedMediaPlayer.start();
            selectedButton.setText("Pause");
            currentMediaPlayer = selectedMediaPlayer;
            currentButton = selectedButton;
        }
    }

    private String getButtonTextForAudio(Button button) {
        if (button == buttonPlayPause1) return "Play-1";
        if (button == buttonPlayPause2) return "Play-2";
        if (button == buttonPlayPause3) return "Play-4"; // Corrected text
        if (button == buttonPlayPause4) return "Play-3"; // Corrected text
        if (button == buttonPlayPause5) return "Play-5";
        return "Play";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer1 != null) mediaPlayer1.release();
        if (mediaPlayer2 != null) mediaPlayer2.release();
        if (mediaPlayer3 != null) mediaPlayer3.release();
        if (mediaPlayer4 != null) mediaPlayer4.release();
        if (mediaPlayer5 != null) mediaPlayer5.release();
    }
}
