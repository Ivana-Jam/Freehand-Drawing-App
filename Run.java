 package application;
import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class Run extends Application 
{
	Color myColour = Color.BLACK;
    int myWidth = 5;
    
    Color custom1, custom2, custom3;
    int width1, width2, width3;
    
    Label quickS = new Label ("Quick Select: ");
    Label customS = new Label ("Custom Select: ");
    Label mine = new Label ("My Custom Colour:");
 
    @SuppressWarnings("static-access")
	@Override
    public void start(Stage primaryStage) 
    {
    	Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
        Canvas canvas = new Canvas(screenSize.getWidth(), screenSize.getHeight()-50);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
       

        initDraw(graphicsContext);
        
        final Stage customize = new Stage();
        customize.initModality(Modality.APPLICATION_MODAL);
        customize.initOwner(primaryStage);
        
		final ColorPicker colourWheel = new ColorPicker();
		colourWheel.setValue(Color.RED);
		
		final Circle colourCircle = new Circle(30);
		colourCircle.setFill(colourWheel.getValue());
		
		Rectangle cust1 = new Rectangle(20, 10);
		cust1.setFill(Color.TRANSPARENT);
		Rectangle cust2 = new Rectangle(20, 10);
		cust2.setFill(Color.TRANSPARENT);
		Rectangle cust3 = new Rectangle(20, 10);
		cust3.setFill(Color.TRANSPARENT);
		
		//Slider Labels, used for editing purposes
		Label infoLabel = new Label("-");
		infoLabel.setTextFill(Color.BLUE);
		
		Circle thicknessCircle = new Circle(3);
		
		Label thicknessLabel = new Label("Select Thiccccness:");
		Slider thicknessSlider = new Slider();
		thicknessSlider.setMin(1);
		thicknessSlider.setMax(100);
		thicknessSlider.setValue(10);
		thicknessSlider.setShowTickLabels(true);
		thicknessSlider.setShowTickMarks(true);
		thicknessSlider.setBlockIncrement(10);
		
		//listener 
		thicknessSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
					//infoLabel.setText("New Value: " + newValue);
					thicknessCircle.setRadius((double)newValue*.3);
					colourCircle.setRadius((double)newValue*.3);
				}
			});
		
		final Circle opacityCircle = new Circle(30);
		opacityCircle.setFill(Color.BLACK);
		
		Label opacityLabel = new Label("Select Opacity:");
		Slider opacitySlider = new Slider();
		opacitySlider.setMin(1);
		opacitySlider.setMax(100);
		opacitySlider.setValue(100);
		opacitySlider.setShowTickLabels(true);
		opacitySlider.setShowTickMarks(true);
		opacitySlider.setBlockIncrement(10);
		
		opacitySlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue2, Number newValue2) {
					opacityCircle.setOpacity((double)newValue2*0.01);

					double r = colourWheel.getValue().getRed();
					double g = colourWheel.getValue().getGreen();
					double b = colourWheel.getValue().getBlue();
					double o = (double)newValue2*0.01;
					Color colour = new Color(r, g, b, o);
					
					colourWheel.setValue(colour);
					colourCircle.setFill(colourWheel.getValue());
					//infoLabel.setText("New Value: " + colourWheel.getValue().getOpacity());
				}
			});
			
		colourWheel.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				colourCircle.setFill(colourWheel.getValue());
				//infoLabel.setText("New Value: " + colourWheel.getValue().getOpacity());
			}
		});
	
		Button set1 = new Button("Set Custom 1");
		set1.setPrefSize(160, 10);
		set1.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				custom1 = (Color) colourCircle.getFill();
				width1 = (int) thicknessCircle.getRadius();
		        cust1.setFill(custom1);
			}
		});
		
		Button set2 = new Button("Set Custom 2");
		set2.setPrefSize(160, 10);
		set2.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				custom2 = (Color) colourCircle.getFill();
				width2 = (int) thicknessCircle.getRadius();
		        cust2.setFill(custom2);
			}
		});
		
		Button set3 = new Button("Set Custom 3");
		set3.setPrefSize(160, 10);
		set3.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				custom3 = (Color) colourCircle.getFill();
				width3 = (int) thicknessCircle.getRadius();
		        cust3.setFill(custom3);

			}
		});
		
		Button black= new Button("Black Pen");
		black.setPrefSize(160, 10);
		Image blackIcon = new Image(getClass().getResourceAsStream("31-512.png"));
		black.setGraphic(new ImageView(blackIcon));
		black.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				myColour = Color.BLACK;
				myWidth = 1;
				graphicsContext.setStroke(myColour);
		        graphicsContext.setLineWidth(myWidth);
		        customize.hide();
			}
		});
		
		Button blue= new Button("Blue Pen");
		blue.setPrefSize(160, 10);
		Image blueIcon = new Image(getClass().getResourceAsStream("31-512blue.png"));
		blue.setGraphic(new ImageView(blueIcon));
		blue.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				myColour = Color.BLUE;
				myWidth = 1;
				graphicsContext.setStroke(myColour);
		        graphicsContext.setLineWidth(myWidth);
				customize.hide();
			}
		});
		
		Button red= new Button("Red Pen");
		red.setPrefSize(160, 10);
		Image redIcon = new Image(getClass().getResourceAsStream("31-512red.png"));
		red.setGraphic(new ImageView(redIcon));
		red.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				myColour = Color.RED;
				myWidth = 1;
				graphicsContext.setStroke(myColour);
		        graphicsContext.setLineWidth(myWidth);
				customize.hide();
			}
		});
		
		Button Hyellow= new Button("Yellow Highlight");
		Hyellow.setPrefSize(160, 10);
		Image yellowIcon = new Image(getClass().getResourceAsStream("31-512yellow.png"));
		Hyellow.setGraphic(new ImageView(yellowIcon));
		Hyellow.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				myColour = Color.rgb(255, 255, 77, 0.01);
				myWidth = 30;
				graphicsContext.setStroke(myColour);
		        graphicsContext.setLineWidth(myWidth);
				customize.hide();
			}
		});
		
		Button Hpink= new Button("Pink Highlight");
		Hpink.setPrefSize(160, 10);
		Image pinkIcon = new Image(getClass().getResourceAsStream("31-512pink.png"));
		Hpink.setGraphic(new ImageView(pinkIcon));
		Hpink.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				myColour = Color.rgb(255, 195, 255, 0.01);
				myWidth = 30;
				graphicsContext.setStroke(myColour);
		        graphicsContext.setLineWidth(myWidth);
				customize.hide();
			}
		});
		
		Button Hblue= new Button("Blue Highlight");
		Hblue.setPrefSize(160, 10);
		Image HblueIcon = new Image(getClass().getResourceAsStream("31-512Hblue.png"));
		Hblue.setGraphic(new ImageView(HblueIcon));
		Hblue.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				myColour = Color.rgb(190, 220, 255, 0.01);
				myWidth = 30;
				graphicsContext.setStroke(myColour);
		        graphicsContext.setLineWidth(myWidth);
				customize.hide();
			}
		});
		
		Button use1= new Button("Use Custom 1");
		use1.setPrefSize(160, 10);
		use1.setGraphic(cust1);
		use1.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				graphicsContext.setStroke(custom1);
		        graphicsContext.setLineWidth(width1);
		        cust1.setFill(custom1);
				customize.hide();
			}
		});
		
		Button use2= new Button("Use Custom 2");
		use2.setPrefSize(160, 10);
		use2.setGraphic(cust2);
		use2.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				graphicsContext.setStroke(custom2);
		        graphicsContext.setLineWidth(width2);
		        cust2.setFill(custom2);
				customize.hide();
			}
		});
		
		Button use3= new Button("Use Custom 3");
		use3.setPrefSize(160, 10);
		use3.setGraphic(cust3);
		use3.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				graphicsContext.setStroke(custom3);
		        graphicsContext.setLineWidth(width3);
		        cust3.setFill(custom3);
				customize.hide();    
			}
		});
		
		Button OKbutton = new Button("OK? OK!");
		OKbutton.setPrefSize(80, 80);
		OKbutton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			synchronized public void handle(ActionEvent event)
			{
				myColour = (Color) colourCircle.getFill();
				myWidth = (int) thicknessCircle.getRadius();
				graphicsContext.setStroke(myColour);
		        graphicsContext.setLineWidth(myWidth);
		        
				customize.hide();
			}
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(15);

		GridPane.setConstraints(quickS, 0, 0);

		GridPane.setConstraints(black, 0, 1);
		GridPane.setConstraints(blue, 1, 1);
		GridPane.setConstraints(red, 2, 1);
		
		GridPane.setConstraints(Hyellow, 0, 2);
		GridPane.setConstraints(Hpink, 1, 2);
		GridPane.setConstraints(Hblue, 2, 2);
		
		GridPane.setConstraints(customS, 0, 4);
		
		GridPane.setConstraints(set1, 0, 5);
		GridPane.setConstraints(set2, 1, 5);
		GridPane.setConstraints(set3, 2, 5);
		GridPane.setConstraints(mine, 3, 5);
		
		GridPane.setConstraints(use1, 0, 6);
		GridPane.setConstraints(use2, 1, 6);
		GridPane.setConstraints(use3, 2, 6);
		GridPane.setConstraints(colourWheel, 3, 6);

		GridPane.setConstraints(thicknessLabel, 0, 7);
		
		GridPane.setConstraints(thicknessSlider, 0, 8);
		GridPane.setConstraints(thicknessCircle, 1, 8);
		GridPane.setConstraints(colourCircle, 3, 8);

		GridPane.setConstraints(opacityLabel, 0, 9);
		
		GridPane.setConstraints(opacitySlider, 0, 10);
		GridPane.setConstraints(opacityCircle, 1, 10);
		
		GridPane.setConstraints(OKbutton, 3, 11);
		
		grid.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		//GridPane.setConstraints(infoLabel, 0, 3);
		
		grid.getChildren().addAll(colourCircle, colourWheel, thicknessLabel, thicknessSlider, thicknessCircle);
		grid.getChildren().addAll(opacityLabel, opacitySlider, opacityCircle, OKbutton, quickS, customS, mine);
		grid.getChildren().addAll(set1, set2, set3, black, blue, red, Hyellow, Hpink, Hblue, use1, use2, use3);
		
		Scene scene = new Scene(grid, 700, 500);
		
		customize.setScene(scene);
		customize.initStyle(StageStyle.UNDECORATED);		
        
        Button editButton = new Button ("EDIT");
		editButton.setPrefSize(100, 50);
		editButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Something Works!");

		        customize.show();
				
			}
		});
		
		Button closeButton = new Button ("CLOSE");
		closeButton.setPrefSize(100, 50);
		closeButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Bye Bye!");
				primaryStage.close();
			}
		});

		
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
        {
 
            @Override
            public void handle(MouseEvent event) 
            {
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.stroke();
                
            }
        });
         
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>()
        {
 
            @Override
            public void handle(MouseEvent event) 
            {
                graphicsContext.lineTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
 
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>()
        {
 
            @Override
            public void handle(MouseEvent event) 
            {
 
            }
        });
 
        StackPane root = new StackPane();
        root.setAlignment(editButton, Pos.BOTTOM_RIGHT);
        root.setAlignment(closeButton, Pos.TOP_RIGHT);
        String style = "-fx-background-color: rgba(255, 255, 255, 0.1);";
        root.setStyle(style);
        root.getChildren().addAll(canvas, editButton, closeButton);
        Scene scene2 = new Scene(root, screenSize.getWidth(), screenSize.getHeight()-50);
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene2.setFill(Color.TRANSPARENT);
        //primaryStage.setOpacity(0.1);
        
        primaryStage.setScene(scene2);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
     
    private void initDraw(GraphicsContext gc)
    {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle
         
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
         
    }
     
}