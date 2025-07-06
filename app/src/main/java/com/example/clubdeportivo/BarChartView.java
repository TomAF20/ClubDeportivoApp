package com.example.clubdeportivo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BarChartView extends View {

    private float[] values = {};
    private String[] labels = {};
    private int[] colors = {};
    private Paint barPaint;
    private Paint textPaint;

    public BarChartView(Context context) {
        super(context);
        init();
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        barPaint = new Paint();
        textPaint = new Paint();
        textPaint.setColor(0xFF000000);
        textPaint.setTextSize(30f);
        textPaint.setAntiAlias(true);
    }

    public void setData(float[] values, String[] labels, int[] colors) {
        this.values = values;
        this.labels = labels;
        this.colors = colors;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (values == null || labels == null || colors == null || values.length != labels.length || values.length != colors.length) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int numBars = values.length;

        float maxValue = 0;
        for (float value : values) {
            if (value > maxValue) maxValue = value;
        }

        float padding = 40f;
        float availableWidth = width - (padding * 2);
        float barWidth = availableWidth / numBars;

        for (int i = 0; i < numBars; i++) {
            float value = values[i];
            float left = padding + i * barWidth;
            float right = left + barWidth * 0.8f;
            float top = height - ((value / maxValue) * (height - padding * 2)) - padding;
            float bottom = height - padding;


            barPaint.setColor(colors[i]);
            canvas.drawRect(left, top, right, bottom, barPaint);

            // Dibuja la etiqueta debajo de cada barrita
            float textWidth = textPaint.measureText(labels[i]);
            canvas.drawText(labels[i], left + (barWidth * 0.4f) - textWidth / 2, height - 10, textPaint);
        }
    }
}