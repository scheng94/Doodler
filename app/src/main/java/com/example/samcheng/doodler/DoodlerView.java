package com.example.samcheng.doodler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * Created by Sam Cheng on 11/3/2016.
 */

public class DoodlerView extends View {

    private Paint PaintDoodler = new Paint(), canvasPaint;
    private Path _path = new Path();
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public DoodlerView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodlerView (Context context, AttributeSet attrs){
        super(context,attrs);
        init(attrs, 0);
    }

    public DoodlerView (Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        PaintDoodler.setColor(Color.argb(255,0,0,0));
        PaintDoodler.setAntiAlias(true);
        PaintDoodler.setStyle(Paint.Style.STROKE);
        PaintDoodler.setStrokeWidth(0);
        PaintDoodler.setStrokeJoin(Paint.Join.ROUND);
        PaintDoodler.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
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

        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(_path, PaintDoodler);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                _path.moveTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(_path,PaintDoodler);
                _path.reset();
                break;
        }

        invalidate();
        return true;
    }

    public void clear(){

        drawCanvas.drawColor(Color.WHITE);
    }

    public void setLineWidth(int lineWidth){

        PaintDoodler.setStrokeWidth(lineWidth);
    }

    public void setColor(int red, int green, int blue){
        PaintDoodler.setColor(Color.rgb(red, green, blue));
    }

    public void setOpacity(int opacity){
        PaintDoodler.setAlpha(opacity);
    }
}
