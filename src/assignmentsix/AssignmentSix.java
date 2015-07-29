/*
Landon Speer
*/
package assignmentsix;

import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AssignmentSix extends Application {
    private double d;
    private Text t;
    
    /**
     * @param primaryStage: creates a stage for the program to reside on
     * @requires: a primaryStage as a parameter for the program to reside on
     * @ensures: This start method holds the entire program and will execute it
     * 
     * Creates 2 circles and a line connecting them. Computes their distance
     * and displays it at the middle of the line. The circles can be dragged with
     * the primary mouse button and the distance is updated when dragging takes place.
     */
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        int width = 300;
        int height = 300;
        
        //starting values for the circles
        double x1 = 40;
        double y1 = 40;
        double x2 = 120;
        double y2 = 150;
        
        Circle c1 = new Circle(x1, y1, 10);//create circle 1 with start values
        Circle c2 = new Circle(x2, y2, 10);//create circle 2 with start values
        Line l = new Line(x1, y1, x2, y2);//create a line connecting the two circles
        d = (new Point2D(x1, y1).distance(x2, y2));//compute the distance
        t = new Text(((x1+x2)/2), ((y1+y2)/2), d + "");//create a text field at middle of the line
        
        //handler for dragging circle 1
        c1.setOnMouseDragged(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
                c1.setCenterX(e.getX());
                c1.setCenterY(e.getY());
                l.setEndX(c2.getCenterX());
                l.setEndY(c2.getCenterY());
                l.setStartX(c1.getCenterX());
                l.setStartY(c1.getCenterY());
                double newX1 = c1.getCenterX();
                double newY1 = c1.getCenterY();
                pane.getChildren().remove(t);//remove old distance display
                d = (new Point2D(newX1, newY1).distance(c2.getCenterX(), c2.getCenterY()));
                t = new Text(((newX1+c2.getCenterX())/2), ((newY1+c2.getCenterY())/2), d + "");
                pane.getChildren().add(t);//show new distance display
            }
        });
        
        //handler for dragging circle 2
        c2.setOnMouseDragged(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
                c2.setCenterX(e.getX());
                c2.setCenterY(e.getY());
                l.setEndX(c1.getCenterX());
                l.setEndY(c1.getCenterY());
                l.setStartX(c2.getCenterX());
                l.setStartY(c2.getCenterY());
                double newX2 = c2.getCenterX();
                double newY2 = c2.getCenterY();
                pane.getChildren().remove(t);//remove old distance display
                d = (new Point2D(c1.getCenterX(), c1.getCenterY()).distance(newX2, newY2));
                t = new Text(((c1.getCenterX()+newX2)/2), ((c1.getCenterY()+newY2)/2), d + "");
                pane.getChildren().add(t);//show new distance display
            }
        });
        
        pane.getChildren().addAll(c1, c2, l, t);
        
        Scene scene = new Scene(pane, width, height);
        primaryStage.setTitle("Assignment Six");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }//main
    
}//class