package com.example.jonas.mymoviesdatabase_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Jonas on 09-07-2015.
 */
public class DownloadRoundImageManager extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;

    public DownloadRoundImageManager(ImageView bmImage){
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap output = null;
        try {
            InputStream in = new URL(urlDisplay).openStream();
            Bitmap mIcon11 = BitmapFactory.decodeStream(in);

            output = Bitmap.createBitmap(mIcon11.getWidth(), mIcon11
                    .getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, mIcon11.getWidth(), mIcon11.getWidth());
            final RectF rectF = new RectF(rect);
            final float roundPx = 500;
            Log.d("ROUDNPIXELS", Integer.toString(output.getHeight()));

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(mIcon11, rect, rect, paint);

            Log.d("CROPPING", Integer.toString(output.getHeight()));
            //output = Bitmap.createBitmap(output, 0, 0, output.getWidth(),output.getHeight()-130);
            output = Bitmap.createBitmap(output, 0, 0, 300,300);


        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return output;
    }

    protected void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }
}