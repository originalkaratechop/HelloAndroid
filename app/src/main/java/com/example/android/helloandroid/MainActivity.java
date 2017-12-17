package com.example.android.helloandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //3 strings to get rid off action bar + buttons + fullscreen
        setContentView(R.layout.activity_main);

        ImageView log = (ImageView) findViewById(R.id.logoIcon);
//        int col = ResourcesCompat.getColor(getResources(), R.color.colorBlue, null);
//        log.getDrawable().mutate().setColorFilter(col, PorterDuff.Mode.SRC_IN);
        //log.setBackgroundColor(Color.TRANSPARENT);  //alternative

        Paint mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint.setAntiAlias(true);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.udacity_green).copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bmp);
        bmp.setHasAlpha(true);
        canvas.drawBitmap(bmp, 0, 0, mPaint);

        if (bmp.getPixel(0, 0) == Color.rgb(0x00, 0xff, 0x00)) {
            for (int x = 0; x < bmp.getWidth(); x++) {
                for (int y = 0; y < bmp.getHeight(); y++) {
                    if (bmp.getPixel(x, y) == Color.rgb(0x00, 0xff, 0x00)) {
                        bmp.setPixel(x, y, Color.TRANSPARENT);
                    }
                }

            }
        }

        log.setImageBitmap(bmp);
    }
}