package com.example.samcheng.doodler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Sam Cheng on 11/5/2016.
 */

public class brushColor extends View {

    private Paint brushColor = new Paint();
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    int red = 0, green = 0, blue = 0;

    public brushColor(Context context) {
        super(context);
        init(null, 0);
    }

    public brushColor (Context context, AttributeSet attrs){
        super(context,attrs);
        init(attrs, 0);
    }

    public brushColor (Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        brushColor.setColor(Color.rgb(red, green, blue));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawBitmap(canvasBitmap, 0, 0, brushColor);
    }

    public void setBrushColor(int red, int green, int blue){
        drawCanvas.drawColor(Color.rgb(red,green,blue));
    }

    public void setRed(int red){
        this.red = red;
    }

    public void setGreen(int green){
        this.green = green;
    }

    public void setBlue(int blue){
        this.blue = blue;
    }
}
