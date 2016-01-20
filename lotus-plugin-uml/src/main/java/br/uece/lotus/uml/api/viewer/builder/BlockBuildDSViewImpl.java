/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uece.lotus.uml.api.viewer.builder;

import br.uece.lotus.uml.api.ds.BlockBuildDS;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Bruno Barbosa
 */
public class BlockBuildDSViewImpl extends Region implements BlockBuildDSView, BlockBuildDS.Listener{

    static final int LARGURA = 150,ALTURA = 70;
    static final int RAIO = 5;
    
    private Rectangle mRetangulo;
    private Circle mCircle;
    private Label mTitulo;
    
    private BlockBuildDS mBlock;
    private static final String DEFAULT_COLOR = "yellow";
    
    public BlockBuildDSViewImpl() {
        mRetangulo = new Rectangle(LARGURA, ALTURA);
        getChildren().add(mRetangulo);
        mRetangulo.setLayoutX(LARGURA);
        mRetangulo.setLayoutY(ALTURA);
        
        mCircle = new Circle(RAIO);
        mCircle.layoutXProperty().bind(mRetangulo.layoutXProperty().add(mRetangulo.widthProperty().subtract(8)));
        mCircle.layoutYProperty().bind(mRetangulo.layoutYProperty().add(mRetangulo.heightProperty().subtract(8)));
        getChildren().add(mCircle);
        
        mTitulo = new Label();
        mTitulo.layoutXProperty().bind(mRetangulo.layoutXProperty().add(mRetangulo.widthProperty().divide(2)).subtract(mTitulo.widthProperty().divide(2)));
        mTitulo.layoutYProperty().bind(mRetangulo.layoutYProperty().add(mRetangulo.heightProperty().divide(2)).subtract(mTitulo.heightProperty().divide(2)));
        getChildren().add(mTitulo);
    }
    
    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public boolean isInsideBounds(Point2D point) {
        Point2D aux = mRetangulo.localToScene(Point2D.ZERO);
        System.out.printf("(%f %f) (%f %f)\n", aux.getX(), aux.getY(), point.getX(), point.getY());
        return mRetangulo.contains(point);
       
    }

    @Override
    public BlockBuildDS getBlockBuildDS() {
        return mBlock;
    }

    @Override
    public void setBlockBuildDS(BlockBuildDS bbds) {
        if(mBlock != null){
            mBlock.removeListener(this);
        }
        mBlock = bbds;
        if(bbds != null){
            mBlock.addListener(this);
            updateView();
        }
    }

    @Override
    public void onChange(BlockBuildDS blockBuildDS) {
        updateView();
    }

    private void updateView() {
        String style = "-fx-effect: dropshadow( gaussian , gray , 3 , 0.2 , 1 , 1);";
        style += "-fx-fill: linear-gradient(to bottom right, white, " + computedColor() + ");";
        style += "-fx-border-color: black ;";
        style += "-fx-border-width: 10px ;";
        style += "-fx-border-style: segments(10, 15, 15, 15)  line-cap round ;";
        mRetangulo.setStyle(style);

        style = "";
        style += "-fx-effect: dropshadow( gaussian , gray , 3 , 0.2 , 1 , 1);";
        style += "-fx-border-color: black ;";
        style += "-fx-border-width: 6px;";
        style += "-fx-fill:"+mBlock.getColorStatus()+";";
        mCircle.setStyle(style);
         
        style = "-fx-text-fill: " + (mBlock.getTextColor() == null ? "black" : mBlock.getTextColor()) + ";";
        style += "-fx-font-weight: " + (mBlock.getTextStyle() == null ? "normal" : mBlock.getTextStyle()) + ";";
        style += "-fx-font-size: " + (mBlock.getTextSize() == null ? "12" : mBlock.getTextSize()) + ";";
        style += "-fx-text-alignment: center;";
        mTitulo.setStyle(style);
        mTitulo.setText(mBlock.getLabel());
        
        setLayoutX(mBlock.getLayoutX());
        setLayoutY(mBlock.getLayoutY());
    }

    private String computedColor() {
        String cor = mBlock.getColor();
        if(cor == null){
            return DEFAULT_COLOR;
        }
        return cor;
    }

    
    
}
