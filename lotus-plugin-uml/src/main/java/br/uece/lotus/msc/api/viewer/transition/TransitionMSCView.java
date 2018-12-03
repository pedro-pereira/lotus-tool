/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uece.lotus.msc.api.viewer.transition;

import br.uece.lotus.msc.api.model.msc.TransitionMSC;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Bruno Barbosa
 */
public interface TransitionMSCView {
    
    Node getNode();

    interface Factory {
        TransitionMSCView create(TransitionMSC t);
    }

    abstract boolean isInsideBoundshMSC(Point2D point);
    abstract boolean isInsideBoundsbMSC(Circle circle);
    abstract Line getLineTransition();

    TransitionMSC getTransition();
    void setTransitionMSC(TransitionMSC t, Object component);

    public class Geometry {
        public static final int LINE = 0;
        public static final int CURVE = 1;
    }
}