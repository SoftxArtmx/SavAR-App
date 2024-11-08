package mo.ar;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.objdetect.BarcodeDetector;

import java.util.List;

public class BarcodeFacade {
    private static final String TAG = "BarcodeFacade";
    private BarcodeDetector barcodeDetector;

    public BarcodeFacade() {
        // Inicializar el detector de códigos de barras
        barcodeDetector = new BarcodeDetector();
    }

    // Método para detectar el código de barras
    public boolean detectBarcode(Mat imgGray, List<String> decodedInfo, List<String> decodedType) {
        Mat points = new Mat();
        boolean found = barcodeDetector.detect(imgGray, points);
        if (found && !points.empty()) {
            barcodeDetector.decodeWithType(imgGray, points, decodedInfo, decodedType);
            return true;
        }
        return false;
    }

    // Método para devolver los contornos del código de barras detectado
    public MatOfPoint2f getBarcodeContours(Mat imgGray) {
        Mat points = new Mat();
        boolean found = barcodeDetector.detect(imgGray, points);
        if (found && !points.empty()) {
            return new MatOfPoint2f(points);
        }
        return null;
    }
}
