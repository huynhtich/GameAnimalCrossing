package com.example.animalcrossing;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;
    ImageButton btnPlay;
    TextView txtPoint;
    int pointReward = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        txtPoint.setText(pointReward + "");
        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);

        CountDownTimer countDownTimer = new CountDownTimer(60000, 50) {
            @Override
            public void onTick(long l) {

                int number = 3;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                if(skOne.getProgress() >= skOne.getMax()) {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    enableCheckbox();
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();

                    if(cbOne.isChecked()) {
                        pointReward += 10;
                    } else {
                        pointReward -= 5;
                    }
                    txtPoint.setText(pointReward + "");
                }
                if(skTwo.getProgress() >= skTwo.getMax()) {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    enableCheckbox();
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();

                    if(cbTwo.isChecked()) {
                        pointReward += 10;
                    } else {
                        pointReward -= 5;
                    }
                    txtPoint.setText(pointReward + "");
                }
                if(skThree.getProgress() >= skThree.getMax()) {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    enableCheckbox();
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();

                    PlaySound();

                    if(cbThree.isChecked()) {
                        pointReward += 10;
                    } else {
                        pointReward -= 5;
                    }
                    txtPoint.setText(pointReward + "");
                }

                skOne.setProgress(skOne.getProgress() + one);
                skTwo.setProgress(skTwo.getProgress() + two);
                skThree.setProgress(skThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) {
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);
                    btnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    disableCheckbox();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược trước khi chơi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                  cbOne.setChecked(false);
                  cbTwo.setChecked(false);
                }
            }
        });
    }

    void PlaySound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.dinosaur);
        mediaPlayer.start();
    }

    void enableCheckbox() {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    void disableCheckbox() {
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    public void AnhXa() {
        cbOne   = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo   = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree = (CheckBox) findViewById(R.id.checkboxThree);
        skOne   = (SeekBar) findViewById(R.id.seekbarOne);
        skTwo   = (SeekBar)findViewById(R.id.seekbarTwo);
        skThree = (SeekBar) findViewById(R.id.seekbarThree);
        txtPoint = (TextView) findViewById(R.id.textviewPoint);
        btnPlay = (ImageButton) findViewById(R.id.buttonPlay);
    }
}