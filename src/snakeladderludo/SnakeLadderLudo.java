package snakeladderludo;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author easin
 */
public class SnakeLadderLudo extends Application {

    public int rand;
    public Label randResult;
    public ImageView dice;
    public Image d1, d2, d3, d4, d5, d6;

    public int cirPos[][] = new int[10][10];
    public int ladderPos[][] = new int[6][3];

    public Circle p1;
    public Circle p2;
    
    public Circle p1Ind;
    public Circle p2Ind;

    public int p1PosRow = 1;
    public int p2PosRow = 1;

    public int p1PosVal = 1;
    public int p2PosVal = 1;

    public boolean p1Turn = true;
    public boolean p2Turn = true;

    public static int p1XPos = 40;
    public static int p1YPos = 760;

    public static int p2XPos = 40;
    public static int p2YPos = 760;

    public boolean gameStarted = false;

    public Button gameButton;

    public static final int Tile_size = 80;
    public static final int width = 10;
    public static final int height = 10;

    private Group tileGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(width * Tile_size, (height * Tile_size) + 80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile(Tile_size, Tile_size);
                tile.setTranslateX(j * Tile_size);
                tile.setTranslateY(i * Tile_size);
                tileGroup.getChildren().add(tile);
            }
        }

        p1 = new Circle(40);
        p1.setId("Player 1");
        p1.setFill(Color.AQUAMARINE);
        p1.getStyleClass().add("style.css");
        p1.setTranslateX(p1XPos);
        p1.setTranslateY(p1YPos);

        p2 = new Circle(40);
        p2.setId("Player 2");
        p2.setFill(Color.BLUEVIOLET);
        p2.getStyleClass().add("style.css");
        p2.setTranslateX(p2XPos);
        p2.setTranslateY(p2YPos);
        
        p1Ind = new Circle(10);
        p1Ind.setId("Player 1 indicator");
        p1Ind.setFill(Color.AQUAMARINE);
        p1Ind.setTranslateX(680);
        p1Ind.setTranslateY(830);
        
        p2Ind = new Circle(10);
        p2Ind.setId("Player 2 indicator");
        p2Ind.setFill(Color.BLUEVIOLET);
        p2Ind.setTranslateX(60);
        p2Ind.setTranslateY(830);

        Button buttonP1 = new Button("Player 1");
        buttonP1.setTranslateX(700);
        buttonP1.setTranslateY(820);
        buttonP1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (gameStarted) {
                    if (p1Turn) {
                        getDiceVal();
                        p1PosVal += rand;
//                        randResult.setText(String.valueOf(rand));
                        moveP1();
                        movePlayer(p1XPos, p1YPos, p1);
                        p1Turn = false;
                        p2Turn = true;
                        p2Ind.setVisible(true);
                        p1Ind.setVisible(false);
                        //System.out.println("p1Xpos: " + p1XPos +" p1Ypos: " + p1YPos + " p1posval: " + p1PosVal);
                    }
                }
            }
        });

        Button buttonP2 = new Button("Player 2");
        buttonP2.setTranslateX(80);
        buttonP2.setTranslateY(820);
        buttonP2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (gameStarted) {
                    if (p2Turn) {
                        getDiceVal();
                        p2PosVal += rand;
//                        randResult.setText(String.valueOf(rand));
                        moveP2();
                        movePlayer(p2XPos, p2YPos, p2);
                        p2Turn = false;
                        p1Turn = true;
                        p2Ind.setVisible(false);
                        p1Ind.setVisible(true);
//                        System.out.println("p2Xpos: " + p2XPos +" p2Ypos: " + p2YPos + " p2posval: " + p2PosVal);
                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(380);
        gameButton.setTranslateY(850);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameButton.setText("Game Started");
                randResult.setVisible(false);
//                randResult.setText("0");
                gameStarted = true;
                dice.setVisible(true);
                p1Turn = true;
                p2Turn = false;
                rand = 0;

                p1PosRow = 1;
                p2PosRow = 1;
                
                p1PosVal = 1;
                p2PosVal = 1;
                
                p1XPos = 40;
                p1YPos = 760;

                p2XPos = 40;
                p2YPos = 760;

                p1.setTranslateX(p1XPos);
                p1.setTranslateY(p1YPos);
                p2.setTranslateX(p2XPos);
                p2.setTranslateY(p2YPos);
                
                p2Ind.setVisible(false);
            }
        });

        randResult = new Label("0");
        randResult.setTranslateX(400);
        randResult.setTranslateY(820);
        randResult.setVisible(false);

        Image img = new Image("snakeBG.jpeg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(800);
        bgImage.setFitWidth(800);

        d1 = new Image("d1.png");
        d2 = new Image("d2.png");
        d3 = new Image("d3.png");
        d4 = new Image("d4.png");
        d5 = new Image("d5.png");
        d6 = new Image("d6.png");

        dice = new ImageView();
        dice.setFitHeight(50);
        dice.setFitWidth(50);

        dice.setTranslateX(400);
        dice.setTranslateY(800);
        dice.setVisible(false);

        tileGroup.getChildren().addAll(bgImage, p1, p2, buttonP1, buttonP2, p1Ind, p2Ind, randResult, dice, gameButton);

        return root;
    }

    private void getDiceVal() {
        rand = (int) (Math.random() * 6 + 1);

        if (rand == 1) {
            dice.setImage(d1);
        }
        if (rand == 2) {
            dice.setImage(d2);
        }
        if (rand == 3) {
            dice.setImage(d3);
        }
        if (rand == 4) {
            dice.setImage(d4);
        }
        if (rand == 5) {
            dice.setImage(d5);
        }
        if (rand == 6) {
            dice.setImage(d6);
        }
    }

    private void moveP1() {

        if (p1XPos == 40 && p1YPos == 40) {
            p1XPos = 40;
            p1YPos = 40;
        }

        for (int i = 0; i < rand; i++) {
            if (p1PosRow % 2 == 1) {
                p1XPos += 80;
            }
            if (p1PosRow % 2 == 0) {
                p1XPos -= 80;
            }

            if (p1XPos > 760) {
                p1YPos -= 80;
                p1XPos -= 80;
                p1PosRow++;
            }

            if (p1XPos < 40) {
                p1YPos -= 80;
                p1XPos += 80;
                p1PosRow++;
            }

            if (p1XPos < 30 || p1YPos < 30) {
                p1YPos = 40;
                p1XPos = 40;
            }
        }
        newMoveP1();
        if (p1XPos < 30 || p1YPos < 30 || p1PosVal==100) {
                p1YPos = 40;
                p1XPos = 40;
                dice.setVisible(false);
                randResult.setVisible(true);
                randResult.setText("Player 1 wins!!");
                gameButton.setText("Start Again!");
                gameStarted = false;
            }
    }

    public void newMoveP1() {

        if (p1PosVal == 3) {
            p1XPos = 120;
            p1YPos = 520;
            p1PosRow += 3;
            p1PosVal = 39;
        }
        if (p1PosVal == 10) {
            p1XPos = 680;
            p1YPos = 680;
            p1PosRow += 1;
            p1PosVal = 12;
        }
        if (p1PosVal == 27) {
            p1XPos = 600;
            p1YPos = 360;
            p1PosRow += 3;
            p1PosVal = 53;
        }
        if (p1PosVal == 56) {
            p1XPos = 280;
            p1YPos = 120;
            p1PosRow += 3;
            p1PosVal = 84;
        }
        if (p1PosVal == 61 || p1PosVal == 99) {
            p1XPos = 120;
            p1YPos = 40;
            p1PosRow += 3;
            p1PosVal = 99;
        }
        if (p1PosVal == 72) {
            p1XPos = 760;
            p1YPos = 120;
            p1PosRow += 1;
            p1PosVal = 90;
        }
        if (p1PosVal == 16) {
            p1XPos = 600;
            p1YPos = 680;
            p1PosVal = 13;
        }
        if (p1PosVal == 31) {
            p1XPos = 280;
            p1YPos = 760;
            p1PosRow -= 3;
            p1PosVal = 4;
        }
        if (p1PosVal == 47) {
            p1XPos = 360;
            p1YPos = 600;
            p1PosRow -= 2;
            p1PosVal = 25;
        }
        if (p1PosVal == 63) {
            p1XPos = 40;
            p1YPos = 360;
            p1PosRow -= 1;
            p1PosVal = 60;
        }
        if (p1PosVal == 66) {
            p1XPos = 680;
            p1YPos = 360;
            p1PosRow -= 1;
            p1PosVal = 52;
        }
        if (p1PosVal == 97) {
            p1XPos = 440;
            p1YPos = 200;
            p1PosRow -= 2;
            p1PosVal = 75;
        }
        if (p1PosVal >= 100) {
            p1XPos = 40;
            p1YPos = 40;
            p1PosRow = 10;
            p1PosVal = 100;
        }

        p1Turn = false;
        p2Turn = true;

    }

    private void moveP2() {
        for (int i = 0; i < rand; i++) {
            if (p2PosRow % 2 == 1) {
                p2XPos += 80;
            }
            if (p2PosRow % 2 == 0) {
                p2XPos -= 80;
            }

            if (p2XPos > 760) {
                p2YPos -= 80;
                p2XPos -= 80;
                p2PosRow++;
            }

            if (p2XPos < 40) {
                p2YPos -= 80;
                p2XPos += 80;
                p2PosRow++;
            }

            if (p2XPos < 30 || p2YPos < 30) {
                p2YPos = 40;
                p2XPos = 40;
            }
        }
        newMoveP2();
        if (p2XPos < 30 || p2YPos < 30 || p2PosVal == 100) {
                p2YPos = 40;
                p2XPos = 40;
                dice.setVisible(false);
                randResult.setVisible(true);
                randResult.setText("Player 2 wins!!");
                gameButton.setText("Start Again!");
                gameStarted = false;
            }
    }
    
    public void newMoveP2() {

        if (p2PosVal == 3) {
            p2XPos = 120;
            p2YPos = 520;
            p2PosRow += 3;
            p2PosVal = 39;
        }
        if (p2PosVal == 10) {
            p2XPos = 680;
            p2YPos = 680;
            p2PosRow += 1;
            p2PosVal = 12;
        }
        if (p2PosVal == 27) {
            p2XPos = 600;
            p2YPos = 360;
            p2PosRow += 3;
            p2PosVal = 53;
        }
        if (p2PosVal == 56) {
            p2XPos = 280;
            p2YPos = 120;
            p2PosRow += 3;
            p2PosVal = 84;
        }
        if (p2PosVal == 61 || p2PosVal == 99) {
            p2XPos = 120;
            p2YPos = 40;
            p2PosRow += 3;
            p2PosVal = 99;
        }
        if (p2PosVal == 72) {
            p2XPos = 760;
            p2YPos = 120;
            p2PosRow += 1;
            p2PosVal = 90;
        }
        if (p2PosVal == 16) {
            p2XPos = 600;
            p2YPos = 680;
            p2PosVal = 13;
        }
        if (p2PosVal == 31) {
            p2XPos = 280;
            p2YPos = 760;
            p2PosRow -= 3;
            p2PosVal = 4;
        }
        if (p2PosVal == 47) {
            p2XPos = 360;
            p2YPos = 600;
            p2PosRow -= 2;
            p2PosVal = 25;
        }
        if (p2PosVal == 63) {
            p2XPos = 40;
            p2YPos = 360;
            p2PosRow -= 1;
            p2PosVal = 60;
        }
        if (p2PosVal == 66) {
            p2XPos = 680;
            p2YPos = 360;
            p2PosRow -= 1;
            p2PosVal = 52;
        }
        if (p2PosVal == 97) {
            p2XPos = 440;
            p2YPos = 200;
            p2PosRow -= 2;
            p2PosVal = 75;
        }
        if (p2PosVal >= 100) {
            p2XPos = 40;
            p2YPos = 40;
            p2PosRow = 10;
            p2PosVal = 100;
        }

        p1Turn = true;
        p2Turn = false;

    }

    private void movePlayer(int x, int y, Circle p) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), p);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createContent());
        scene.getStylesheets().add("style.css");
        primaryStage.setTitle("Snake Ladder Ludo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
