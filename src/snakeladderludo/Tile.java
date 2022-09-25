/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeladderludo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author easin
 */
public class Tile extends Rectangle{

    public Tile(int x, int y) {
        setWidth(SnakeLadderLudo.Tile_size);
        setHeight(SnakeLadderLudo.Tile_size);
        
        setFill(Color.PINK);
        setStroke(Color.BLACK);
        
    }
    
    
}
