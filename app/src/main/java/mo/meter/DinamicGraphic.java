
package mo.meter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DinamicGraphic extends View {
    private Paint circlePaint;
    private Paint needlePaint;
    private float needlePosition = 0;

    public DinamicGraphic(Context context) {
        super(context);
        init();
    }

    public DinamicGraphic(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // Constructor adicional para otros casos (opcional)
    public DinamicGraphic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.LTGRAY);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10f);
        circlePaint.setAntiAlias(true);

        needlePaint = new Paint();
        needlePaint.setColor(Color.RED);
        needlePaint.setStrokeWidth(8f);
        needlePaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Obtener el tamaño del gráfico
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float radius = Math.min(centerX, centerY) - 20;

        // Dibujar el círculo del velocímetro
        canvas.drawCircle(centerX, centerY, radius, circlePaint);

        // Dibujar la aguja del velocímetro
        float needleX = (float) (centerX + radius * Math.cos(Math.toRadians(needlePosition - 90)));
        float needleY = (float) (centerY + radius * Math.sin(Math.toRadians(needlePosition - 90)));
        canvas.drawLine(centerX, centerY, needleX, needleY, needlePaint);
    }

    // Método para actualizar la posición de la aguja
    public void setNeedlePosition(float value) {
        needlePosition = 180 * (value / 10000); // 0-100 mapea a 0-180 grados
        invalidate();  // Llama a onDraw para redibujar la vista
    }
}

