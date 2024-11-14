/*
 * |                             S O F T x A R T
 * | Proyecto: SaVR
 * | Archivo: BarcodeFacade
 * | Clase/modulo a codificar: MO.AR.BarcodeFacade
 * | Descripción general: Manejo de la logica de los codigos de barras escaneados.
 * | Funciones especificas:
 * |   -Inicialización del Escaneo de codigos de barras.
 * |   -Procesamiento de Imágenes en Tiempo Real.
 * |   -Detección de Código de Barras.
 * |   -Gestión de Resultados.
 * |   -Detener y Reiniciar el Escaneo.
 * |
 * | Desarrollador encargado: Leonardo Zavala González
 * | Aprobado por: Marcos Emmanuel Juarez Navarro
 * |
 * | CAMBIOS REALIZADOS DESDE LA ULTIMA VERSION
 * | Nombre:        Fecha:               Cambios Realizados:
 * | LZG            23/10/24             Creacion de la clase e inicio de la implementacion
 * | LZG            29/10/24             Finalización de implementación la clase.
 * |
 * |
 * |
 * */

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
