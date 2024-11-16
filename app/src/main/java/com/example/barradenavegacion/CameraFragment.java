/*
 * |                             S O F T x A R T
 * | Proyecto: SaVR
 * | Archivo: cameraFragment
 * | Clase/modulo a codificar: MO_AR
 * | Descripción general: Manejo e implementacion del modulo de camara.
 * | Funciones especificas:
 * |   -Escaneo del codigo de barras
 * |   -Mandar a la base de datos el codigo escaneado.
 * |   -Mostrar la información relevante del producto.
 * |   -Facilitar al usuario agregar productos al carrito.
 * |
 * | Desarrollador encargado: Leonardo Zavala González
 * | Aprobado por: Marcos Emmanuel Juarez Navarro
 * |
 * | CAMBIOS REALIZADOS DESDE LA ULTIMA VERSION
 * | Nombre:        Fecha:               Cambios Realizados:
 * | LZG            23/10/24             Comencé a pasar mi modulo al patron de diseño de Facade.
 * | LZG            29/10/24             Terminé de implementar el patrón de diseño de Facade.
 * | GC             02/10/24             Implementación de la BD y las clases referentes a la API.
 * | MJN            08/10/24             Implementar el modulo de camara en la aplicación principal.
 * |
 * |
 * |
 * */
package com.example.barradenavegacion;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.FrameLayout;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mo.ar.PopupFacade;
import mo.ar.BarcodeFacade;

public class CameraFragment extends Fragment implements CameraBridgeViewBase.CvCameraViewListener2 {
    private static final int     ANALYSIS_INTERVAL_MS = 500;
    private CameraBridgeViewBase mOpenCvCameraView;
    private boolean              isProcessingFrame    = false;
    private long                 lastAnalysisTime     = 0;
    private boolean              scanSuccessful       = false;

    private Activity activity;
    // Facades
    private BarcodeFacade barcodeFacade;
    private PopupFacade popupFacade;
    private FrameLayout blurBackground;

    public CameraFragment() {
    }

    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(mOpenCvCameraView);
    }

    @Override
    public void onStart() {
        super.onStart();
        List<? extends CameraBridgeViewBase> cameraViews = getCameraViewList();
        for (CameraBridgeViewBase cameraBridgeViewBase : cameraViews) {
            if (cameraBridgeViewBase != null) {
                cameraBridgeViewBase.setCameraPermissionGranted();
            }
        }
    }

    // TODO: Rename and change types and number of parameters
    public static CameraFragment newInstance(String param1, String param2) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_camera, container, false);
        if ( !OpenCVLoader.initLocal()) {
            Log.i("CAMERA", "OpenCV failed successfully");
        }
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mOpenCvCameraView = root.findViewById(R.id.camera_view);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.setCameraIndex(0);

        // Inicializar las Facade
        barcodeFacade = new BarcodeFacade();
        blurBackground = root.findViewById(R.id.blur_background);
        popupFacade = new PopupFacade(this.getContext(), blurBackground);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null) mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOpenCvCameraView != null) mOpenCvCameraView.enableView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null) mOpenCvCameraView.disableView();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat mRgba = inputFrame.rgba();

        long currentTime = System.currentTimeMillis();

        if (scanSuccessful) {
            return mRgba;
        }

        if (currentTime - lastAnalysisTime >= ANALYSIS_INTERVAL_MS && !isProcessingFrame) {
            lastAnalysisTime = currentTime;
            isProcessingFrame = true;

            Mat gray = inputFrame.gray();

            new Thread(() -> {
                try {
                    List<String> decodedInfo = new ArrayList<>();
                    List<String> decodedType = new ArrayList<>();
                    boolean found = barcodeFacade.detectBarcode(gray, decodedInfo, decodedType);

                    if (found) {
                        for (int i = 0; i < decodedInfo.size(); i++) {
                            if ("EAN_13".equals(decodedType.get(i))) {
                                String barcode = decodedInfo.get(i);
                                requireActivity().runOnUiThread(() -> {
                                    scanSuccessful = true;
                                    popupFacade.showPopup("Producto escaneado correctamente", barcode, this::reanudarEscaneo, this::confirmarProducto);
                                    detenerEscaneo();
                                });
                                break;
                            }
                        }
                    }

                    isProcessingFrame = false;
                } catch (Exception e) {
                    Log.e("ERROR", "Error en la detección del código de barras: " + e.getMessage());
                    isProcessingFrame = false;
                }
            }).start();
        }

        return mRgba;
    }

    private void detenerEscaneo() {
        if (mOpenCvCameraView != null) {
            mOpenCvCameraView.disableView();
        }
    }

    private void reanudarEscaneo() {
        scanSuccessful = false;
        if (mOpenCvCameraView != null) {
            mOpenCvCameraView.enableView();
        }
    }

    private void confirmarProducto() {
        reanudarEscaneo();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {}

    @Override
    public void onCameraViewStopped() {}

}