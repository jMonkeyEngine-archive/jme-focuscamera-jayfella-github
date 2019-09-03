package com.jayfella.camera;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication implements ActionListener {

    public static void main(String[] args) {

        Main app = new Main();

        AppSettings settings = new AppSettings(true);
        settings.setTitle("My Awesome Game");

        settings.setResolution(1280, 720);

        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();

    }

    private FocusCameraState focusCameraState;
    private Geometry[] geometries = new Geometry[3];

    @Override
    public void simpleInitApp() {

        // !!important - we don't want two cameras at once.
        flyCam.setEnabled(false);

        // create a scene to play with...
        rootNode.addLight(new DirectionalLight(new Vector3f(-1, -1, -1).normalizeLocal()));
        rootNode.addLight(new AmbientLight(ColorRGBA.White.mult(0.4f)));

        float x = 0;

        for (int i = 0; i < geometries.length; i++) {
            Box b = new Box(1, 1, 1);
            geometries[i] = new Geometry("Box", b);

            Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
            geometries[i].setMaterial(mat);

            geometries[i].setLocalTranslation(x, 0, 0);
            x += 5;

            rootNode.attachChild(geometries[i]);
        }


        // create and attach a focus camera
        focusCameraState = new FocusCameraState();
        stateManager.attach(focusCameraState);

        // and focus on the box.
        focusCameraState.setFocusPoint(geometries[0]);

        registerModelFocuser();
    }

    private static final String OBJ_1 = "OBJ_1";
    private static final String OBJ_2 = "OBJ_2";
    private static final String OBJ_3 = "OBJ_3";

    private void registerModelFocuser() {
        inputManager.addMapping(OBJ_1, new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping(OBJ_2, new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping(OBJ_3, new KeyTrigger(KeyInput.KEY_3));

        inputManager.addListener(this, OBJ_1, OBJ_2, OBJ_3);
    }


    @Override
    public void onAction(String name, boolean isPressed, float tpf) {

        if (isPressed) {
            return;
        }

        if (name.equals(OBJ_1)) {
            focusCameraState.setFocusPoint(geometries[0]);
        }
        else if (name.equals(OBJ_2)) {
            focusCameraState.setFocusPoint(geometries[1]);
        }
        else if (name.equals(OBJ_3)) {
            focusCameraState.setFocusPoint(geometries[2]);
        }

    }
}