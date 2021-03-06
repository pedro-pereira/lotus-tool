/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uece.lotus.uml.designer.blockDiagramModeling.strategy;

import br.uece.lotus.uml.designer.blockDiagramModeling.DesingWindowImplBlockDs;
import br.uece.lotus.uml.designer.standardModeling.StandardModelingWindowImpl;
import br.uece.lotus.uml.designer.standardModeling.strategy.OnDragOver;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Bruno Barbosa
 */
public class Context{

    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }


    public void executeStrategyOnMovedMouse(DesingWindowImplBlockDs s, MouseEvent e){
        strategy.onMovedMouse(s,e);
    }

    public void executeStrategyOnClikedMouse(DesingWindowImplBlockDs s, MouseEvent event) {
        strategy.onClickedMouse(s,event);
    }
    
    public void executeStrategyOnPressedMouse(DesingWindowImplBlockDs s, MouseEvent event) {
        strategy.onPressedMouse(s, event);
    }
    
    public void executeStrategyOnDraggedMouse(DesingWindowImplBlockDs s, MouseEvent event) {
        strategy.onDraggedMouse(s, event);
    }
    
    public void executeStrategyOnReleasedMouse(DesingWindowImplBlockDs s, MouseEvent event) {
        strategy.onReleasedMouse(s, event);
    }

    public void executeStrategyOnDragDetectedMouse(DesingWindowImplBlockDs desingWindowImplBlockDs, MouseEvent event) {
        strategy.onDragDetectedMouse(desingWindowImplBlockDs,event);
    }

    public void executeStrategyOnDragOverMouse(DesingWindowImplBlockDs desingWindowImplBlockDs, DragEvent event) {
        strategy.onDragOverMouse(desingWindowImplBlockDs,event);
    }

    public void executeStrategyOnDragDroppedMouse(DesingWindowImplBlockDs desingWindowImplBlockDs, DragEvent event) {
        strategy.onDragDroppedMouse(desingWindowImplBlockDs,event);

    }
}
