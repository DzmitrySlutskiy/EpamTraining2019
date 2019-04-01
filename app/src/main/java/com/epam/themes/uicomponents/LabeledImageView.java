package com.epam.themes.uicomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.epam.cleancodetest.R;

public class LabeledImageView extends AppCompatImageView {
    private Bitmap bitmapDrawableLabelBitmap;


    public LabeledImageView(Context context) {
        super(context);
    }

    public LabeledImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDrawableLabel(context, attrs);

    }

    public LabeledImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isDrawableValidToProceed(getDrawable())) return;

        canvas.drawBitmap(getLabeledImageBitmap(), 0, 0, null);
    }

    private Bitmap getLabeledImageBitmap() {
        Drawable drawable = getDrawable();

        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap finalBitmap;
        if (bitmap.getWidth() != getWidth() || bitmap.getHeight() != getHeight())
            finalBitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);
        else
            finalBitmap = bitmap;

        Bitmap resultBitmap = Bitmap.createBitmap(finalBitmap.getWidth(), finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(resultBitmap);

        Rect rect = createRect(finalBitmap, false);
        canvas.drawBitmap(bitmap, null, rect, null);

        if (isLabelValidToProceed()) {
            canvas.drawBitmap(bitmapDrawableLabelBitmap, null, createRect(finalBitmap, true), null);
        }
        return resultBitmap;
    }

    private Rect createRect(Bitmap f, boolean isLabelRect) {
        if (isLabelRect) {
            return new Rect(0, getHeight() / 2, getWidth() / 2, getHeight());
        }
        return new Rect(0, 0, f.getWidth(), f.getHeight());
    }

    private void setDrawableLabel(Context context, AttributeSet attrs) {
        final TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.LabeledImageView, 0, 0);
        Drawable drawableLabel = styledAttrs.getDrawable(R.styleable.LabeledImageView_labelSrc);
        if (drawableLabel != null) {
            bitmapDrawableLabelBitmap = ((BitmapDrawable) drawableLabel).getBitmap();
        } else {
            bitmapDrawableLabelBitmap = null;
        }
        styledAttrs.recycle();
    }

    private boolean isDrawableValidToProceed(Drawable drawable) {
        return drawable != null && getWidth() != 0 && getHeight() != 0;
    }

    private boolean isLabelValidToProceed() {
        return bitmapDrawableLabelBitmap != null;
    }
}
