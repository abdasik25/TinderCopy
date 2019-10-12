/**
 * Created by Alexander Lomat
 */


package by.bsuir.tinder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * The type Home button.
 */
public class HomeButton extends View {

    private RectF buttonBackground;
    private RectF buttonShadow;
    private RectF homeBaseDrawBounds;
    private RectF doorDrawBounds;
    private Path roofPath;

    private Paint buttonPaint;
    private Paint buttonShadowPaint;
    private Paint homePaint;

    private static final float HOME_WIDTH_PERCENT = 0.5f;
    private static final float DOOR_WIDTH_PERCENT = 0.35f;
    private static final float DOOR_HEIGHT_PERCENT = 0.6f;

    private static final int BUTTON_COLOR = Color.BLACK;
    private static final int BACKGROUND_COLOR = Color.WHITE;

    /**
     * Instantiates a new Home button.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public HomeButton(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        buttonBackground = new RectF();
        buttonShadow = new RectF();
        homeBaseDrawBounds = new RectF();
        doorDrawBounds = new RectF();

        roofPath = new Path();

        buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint.setColor(BUTTON_COLOR);
        buttonPaint.setStyle(Paint.Style.FILL);

        buttonShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonShadowPaint.setColor(BACKGROUND_COLOR);
        buttonShadowPaint.setStyle(Paint.Style.FILL);

        homePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        homePaint.setColor(Color.WHITE);
        homePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        sizeChanged(HOME_WIDTH_PERCENT, DOOR_WIDTH_PERCENT, DOOR_HEIGHT_PERCENT);

        invalidate();
        requestLayout();
    }

    private void sizeChanged(float homeWidthPercent, float doorWidthPercent, float doorHeightPercent) {

        float contentWidth = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2;
        float contentHeight = (getHeight() - getPaddingTop() - getPaddingBottom());

        float center = getWidth() / 2;
        float topBorder = getPaddingTop();
        float leftBorder = center - contentWidth / 2;
        float shadowSize = contentHeight / 20;

        buttonBackground.set(leftBorder + shadowSize, topBorder + shadowSize, (leftBorder + contentWidth - shadowSize), topBorder + contentHeight - shadowSize);
        buttonShadow.set(leftBorder, topBorder, leftBorder + contentWidth, topBorder + contentHeight);

        float width = contentHeight * homeWidthPercent;
        float doorWidth = width * doorWidthPercent;
        float doorHeight = width * doorHeightPercent;

        leftBorder = leftBorder + contentWidth / 2 - width / 2;
        topBorder = topBorder + contentHeight / 2 - width / 2 + width / 4;

        homeBaseDrawBounds.set(leftBorder, topBorder, leftBorder + width, topBorder + width);

        float topOffset = (topBorder + width) - (getPaddingTop() + contentHeight);
        topOffset = -topOffset;
        PointF a = new PointF(leftBorder - width / 4, topBorder + width / 4);
        PointF b = new PointF(leftBorder + width / 2, getPaddingTop() + topOffset);
        PointF c = new PointF(leftBorder + width + width / 4, topBorder + width / 4);

        roofPath.moveTo(b.x, b.y);
        roofPath.lineTo(c.x, c.y);
        roofPath.lineTo(a.x, a.y);
        roofPath.close();

        leftBorder = leftBorder + (width / 2 - doorWidth / 2);
        topBorder = topBorder + width - doorHeight;

        doorDrawBounds.set(leftBorder, topBorder, leftBorder + doorWidth, topBorder + doorHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(buttonShadow, 25, 25, buttonShadowPaint);
        canvas.drawRoundRect(buttonBackground, 20, 20, buttonPaint);
        canvas.drawRect(homeBaseDrawBounds, homePaint);
        canvas.drawPath(roofPath, homePaint);
        canvas.drawRect(doorDrawBounds, buttonPaint);

        roofPath.reset();
    }
}
