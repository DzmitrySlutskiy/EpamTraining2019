package com.epam.themes.uicomponents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CircularImageView extends AppCompatImageView {

    public CircularImageView(Context context) {
        super(context);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();

        if (!isValidToProceed(drawable)) return;

        canvas.drawBitmap(getRoundedBitmap(drawable), 0, 0, null);
    }

    private boolean isValidToProceed(Drawable drawable) {
        return drawable != null && getWidth() != 0 && getHeight() != 0;
    }

    private Bitmap getRoundedBitmap(Drawable drawable) {
        Bitmap originalBitmap = ((BitmapDrawable) drawable).getBitmap();

        Bitmap copyBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);

        return getRoundedCroppedBitmap(copyBitmap, getWidth());
    }

    private Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap = setBitmap(bitmap, radius);

        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(finalBitmap.getWidth() / 2 + 0.7f,
                finalBitmap.getHeight() / 2 + 0.7f,
                finalBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }

    private Bitmap setBitmap(Bitmap bitmap, int radius) {
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius) {
            return Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        }
        return bitmap;
    }
}
