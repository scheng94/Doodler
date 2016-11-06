package com.example.samcheng.doodler;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;


public class MainActivity extends AppCompatActivity{

    private DoodlerView drawView;
    private brushColor brush_color;
    private Button saveButton;
    private Button clearButton;
    private SeekBar lineWidthSeekBar;
    private SeekBar redSeekbar;
    private SeekBar greenSeekbar;
    private SeekBar blueSeekbar;
    private SeekBar opacitySeekbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DoodlerView) findViewById(R.id.doodlerView);
        brush_color = (brushColor) findViewById(R.id.brushColor);
        lineWidthSeekBar = (SeekBar) findViewById(R.id.lineWidthSeekBar);
        redSeekbar = (SeekBar) findViewById(R.id.redSeekBar);
        greenSeekbar = (SeekBar) findViewById(R.id.greenSeekBar);
        blueSeekbar = (SeekBar) findViewById(R.id.blueSeekBar);
        opacitySeekbar = (SeekBar) findViewById(R.id.opacitySeekbar);
        clearButton = (Button) findViewById(R.id.clearButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        lineWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                drawView.setLineWidth(progress);
            }
        });

        redSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                drawView.setColor(progress,brush_color.green,brush_color.blue);
                brush_color.setBrushColor(progress, brush_color.green, brush_color.blue);
                brush_color.setRed(progress);
            }
        });

        greenSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                drawView.setColor(brush_color.red, progress, brush_color.blue);
                brush_color.setBrushColor(brush_color.red, progress, brush_color.blue);
                brush_color.setGreen(progress);
            }
        });

        blueSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                drawView.setColor(brush_color.red, brush_color.green, progress);
                brush_color.setBrushColor(brush_color.red, brush_color.green, progress);
                brush_color.setBlue(progress);
            }
        });

        opacitySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 256;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                drawView.setOpacity(progress);
            }
        });

        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(MainActivity.this);
                saveDialog.setTitle("Save Drawing");
                saveDialog.setMessage("Save drawing to Gallery?");
                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        drawView.setDrawingCacheEnabled(true);
                        String imgSaved = MediaStore.Images.Media.insertImage( getContentResolver(), drawView.getDrawingCache(),
                                UUID.randomUUID().toString()+".png", "drawing");
                        if(imgSaved!=null){
                            Toast.makeText(MainActivity.this, "Drawing saved!", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(MainActivity.this, "Drawing could not be saved.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                saveDialog.show();
                drawView.destroyDrawingCache();
            }
        });

        clearButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){

                drawView.clear();
            }
        });
    }
}
