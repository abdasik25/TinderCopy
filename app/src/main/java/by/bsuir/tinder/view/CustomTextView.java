package by.bsuir.tinder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomTextView extends View {

    private static final int TEXT_HEIGHT = 1500;

    private Paint paint;
    private String text = "Test text";
    private int textSize = 63;
    private float heightPercentage = 1;

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setHeightPercentage(float heightPercentage) {
        this.heightPercentage = heightPercentage;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setTextSize(textSize * ((float) getHeight()) / TEXT_HEIGHT);
        System.out.println();
        canvas.drawText(text, getPaddingLeft(), (getHeight() - getPaddingBottom()) * heightPercentage, paint);
    }
}
