Jme-FocusCamera
===

A jMonkey AppState that focuses on a given spatial. You can then rotate around it and zoom.
You can also optionally offset the focus point.

There are several options to choose speeds, max zoom, min zoom, invert Y, etc..

Use the Left Mouse Button to rotate. Use the mousewheel to zoom, middle button to reset offset.

See the main class for a working example.

```java

FocusCameraState focusCameraState = new FocusCameraState();
stateManager.attach(focusCameraState);

// set a spatial to focus on.
focusCameraState.setFocusPoint(mySpatial);

// optional settings

focusCameraState.setZoomSpeed(1); // how fast the camera will zoom when you scroll the mouse wheel in world units.
focusCameraState.setMinZoom(3); // the minimum zoom distance the camera can get to the spatial.
focusCameraState.setMaxZoom(30); // the maximum zoom distance the camera can get from the spatial.

focusCameraState.setRotationSpeed(FastMath.TWO_PI * 4); // how fast the camera will rotate.
focusCameraState.setInvertY(false); // invert the up/down movement.

```
