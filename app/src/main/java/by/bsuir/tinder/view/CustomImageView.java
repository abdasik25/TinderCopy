package by.bsuir.tinder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomImageView extends View {

    private Bitmap bitmapToDraw;
    private Matrix mShaderMatrix;
    private CustomTextView title;

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmapToDraw = null;
        mShaderMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updateBitmapSize();
    }

    public void setBitmapToDraw(Bitmap bitmapToDraw) {
        this.bitmapToDraw = bitmapToDraw;

        updateBitmapSize();
    }

    private void updateBitmapSize() {
        if (bitmapToDraw == null) return;

        float viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        float viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        float bitmapWidth = bitmapToDraw.getWidth();
        float bitmapHeight = bitmapToDraw.getHeight();

        float scale;
        float dx = 0, dy = 0;

        if (bitmapWidth * viewHeight > viewWidth * bitmapHeight) {
            scale =  viewHeight /  bitmapHeight;
            dx = (viewWidth - bitmapWidth * scale) * 0.5f;
        } else {
            scale =  viewWidth / bitmapWidth;
            dy = (viewHeight - bitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate(Math.round(dx), Math.round(dy));

        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(bitmapToDraw != null)
            canvas.drawBitmap(bitmapToDraw,mShaderMatrix,null);

    }
}
