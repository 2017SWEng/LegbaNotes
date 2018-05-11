package com.legba.notes.renderers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

import com.legba.notes.controllers.AppController;
import com.legba.notes.elements.Audio;
import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;
import com.legba.notes.elements.Text;
import com.legba.notes.elements.Video;
import com.legba.notes.models.AppModel;

/**
 * Takes an instance of a Slide and produces an javafx Node tree.
 * @author zraw500, lm1370
 *
 */
public class SlideRenderer extends Renderer<Slide> {
	
	public static final Color DEFAULT_BG = Color.WHITESMOKE;
	public static final Color DEFAULT_FG = Color.DIMGREY;

	VectorRenderer vectorRenderer;
	AudioRenderer audioRenderer;
	TextRenderer textRenderer;
	VideoRenderer videoRenderer;
	//TODO: add other renders to this

	/**
	 * Default constructor, use default SlideElement renderers
	 */
	public SlideRenderer(){
		this.vectorRenderer = new VectorRenderer();
		this.audioRenderer = new AudioRenderer();
		this.textRenderer = new TextRenderer();
		this.videoRenderer = new VideoRenderer();
		//TODO: add other renders to this
	}
	
	/**
	 * Custom constructor, use custom SlideElement renderers
	 * @param vectorRenderer
	 * @param audioRenderer
	 */
	public SlideRenderer(
			VectorRenderer vectorRenderer,
			AudioRenderer audioRenderer,
			TextRenderer textRenderer,
			VideoRenderer videoRenderer
			){
		this.vectorRenderer = vectorRenderer;
		this.audioRenderer = audioRenderer;
		this.textRenderer = textRenderer;
		this.videoRenderer = videoRenderer;
		//TODO: add other renders to this
	}
	
	/**
	 * Renders the given slide
	 * @param s Slide to render
	 * @return javafx Node tree
	 */
	@Override
	public Node render(Slide s) {

		// using pane because it allows absolute positioning
		Pane pane =  new Pane();
		pane.getStyleClass().add("element-slide");
		
		Paint color;
		if (s.getColor() == null) {
			color = Paint.valueOf(DEFAULT_FG.toString());
		}
		else if (s.getColor() instanceof LinearGradient) {
			Stop[] stops = new Stop[] {new Stop(0, ((LinearGradient) s.getColor()).getStops().get(0).getColor()), new Stop(1, ((LinearGradient) s.getColor()).getStops().get(1).getColor())};
			s.setColor(new LinearGradient(pane.getLayoutBounds().getMinX(), pane.getLayoutBounds().getMinY(), pane.getLayoutBounds().getMaxX(), pane.getLayoutBounds().getMaxY(), false, CycleMethod.NO_CYCLE, stops));
			color = s.getColor();
		}
		else {
			color = s.getColor();
		}
		pane.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		if (s.getFill() == null) {
			pane.setStyle("-fx-background-color: " + convertToHex(DEFAULT_BG));
		}
		else if (s.getFill() instanceof LinearGradient) {
			Stop[] stops = new Stop[] {new Stop(0, ((LinearGradient) s.getFill()).getStops().get(0).getColor()), new Stop(1, ((LinearGradient) s.getFill()).getStops().get(1).getColor())};
			s.setFill(new LinearGradient(pane.getLayoutBounds().getMinX(), pane.getLayoutBounds().getMinY(), pane.getLayoutBounds().getMaxX(), pane.getLayoutBounds().getMaxY(), false, CycleMethod.NO_CYCLE, stops));
			pane.setStyle("-fx-background-color: " + convertToGradient(s.getFill()));
		}
		else {
			pane.setStyle("-fx-background-color: " + convertToHex((Color) s.getFill()));
		}

		//When mouse enters pane it puts border around it
		pane.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			}
		});
		
		//When mouse exits pane it removes the border
		pane.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				pane.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			}
		});

		for(Shape shape : s.getShapes()){
			Node n = this.vectorRenderer.render(shape);
			
			//When mouse clicks on shape, selective editing enabled, current state displayed on toolbar
			n.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {								
					if(n!=null) {
						//Sets variables
						AppController.getInstance().toolbar.CurrentShape = shape;
						AppController.getInstance().viewing.CurrentNode = n;
						
						//Enable shape mode
						AppController.getInstance().toolbar.shapeMode();
						
						if (shape.getType().equals("line")) {
							AppController.getInstance().toolbar.fillGradient.setDisable(true);
							AppController.getInstance().toolbar.shapeFill.setDisable(true);
						}
						
						//Displays selected shape variables on toolbar
						AppController.getInstance().toolbar.typeCombo.setValue(shape.getType());	
						AppController.getInstance().toolbar.strokeCombo.setValue(shape.getStroke());
						
						if (AppController.getInstance().toolbar.CurrentShape.getColor() instanceof LinearGradient) {
							AppController.getInstance().toolbar.strokeColor2.setValue(((LinearGradient) shape.getColor()).getStops().get(1).getColor());
							AppController.getInstance().toolbar.strokeColor.setValue(((LinearGradient) shape.getColor()).getStops().get(0).getColor());
						}
						else {
							AppController.getInstance().toolbar.strokeColor.setValue((Color) shape.getColor());
						}
						//System.out.println("box : " + AppController.getInstance().toolbar.strokeColor2.getValue());
						//System.out.println("actual : " + shape.getColor());
						//AppController.getInstance().toolbar.strokeColor.setValue((Color) shape.getColor());
						
						if (AppController.getInstance().toolbar.CurrentShape.getFill() instanceof LinearGradient) {
							AppController.getInstance().toolbar.shapeFill2.setValue(((LinearGradient) shape.getFill()).getStops().get(1).getColor());
							AppController.getInstance().toolbar.shapeFill.setValue(((LinearGradient) shape.getFill()).getStops().get(0).getColor());
						}
						else {
							AppController.getInstance().toolbar.shapeFill.setValue((Color) shape.getFill());
						}
						
						//Highlights selected shape
						DropShadow dropShadow = new DropShadow();
						dropShadow.setBlurType(BlurType.GAUSSIAN);
						dropShadow.setColor(Color.BLACK);
						dropShadow.setOffsetX(0.0);
						dropShadow.setOffsetY(0.0);
						dropShadow.setRadius(20.0);
						n.setEffect(dropShadow);
					}
				}
			});
			
			n.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					//Un-highlights selected shape
					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setColor(Color.TRANSPARENT);
					dropShadow.setOffsetX(0.0);
					dropShadow.setOffsetY(0.0);
					dropShadow.setRadius(0.0);
					n.setEffect(dropShadow);
				}
			});
			
			pane.getChildren().add(n);
		}
		
		for(Audio audio : s.getAudios()){
			pane.getChildren().add(this.audioRenderer.render(audio));
		}
		
		for(Video video : s.getVideos()){
			pane.getChildren().add(this.videoRenderer.render(video));
		}
		
		for(Text text : s.getTexts()){
			Node n = this.textRenderer.render(text);
			
			//When mouse clicks on shape, selective editing enabled, current state displayed on toolbar
			n.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {								
					if(n!=null) {
						//Enable shape mode
						AppController.getInstance().toolbar.textMode();	
						
						//Sets variables
						AppController.getInstance().toolbar.CurrentText = text;
						AppController.getInstance().viewing.CurrentNode = n;
						
						/*----------------------------------------------------------------------------------------------------------------------
						TODO: I'm not sure if binding has been completed for text yet but this code should work as it is
							  the identical method for shapes and they work. If text has been binded then i'll have a another 
							  look at this, text can be set from the toolbar, but can't retrieve data from text to display on toolbar - lm1370
						
						//Displays selected shape variables on toolbar
						AppController.getInstance().toolbar.boldFont.setSelected(text.getBold());
						AppController.getInstance().toolbar.italicFont.setSelected(text.getItalic());
						AppController.getInstance().toolbar.undFont.setSelected(text.getUnderline());
						AppController.getInstance().toolbar.textColor.setValue(text.getColor());
						AppController.getInstance().toolbar.textFill.setValue(text.getFill());
						AppController.getInstance().toolbar.fontCombo.setValue(text.getFont());
						AppController.getInstance().toolbar.sizeCombo.setValue(text.getTextsize());
						
						-------------------------------------------------------------------------------------------------------------------------*/
						
						//Highlights selected text
						DropShadow dropShadow = new DropShadow();
						dropShadow.setBlurType(BlurType.GAUSSIAN);
						dropShadow.setColor(Color.BLACK);
						dropShadow.setOffsetX(0.0);
						dropShadow.setOffsetY(0.0);
						dropShadow.setRadius(20.0);
						n.setEffect(dropShadow);
					}
				}
			});
			
			n.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					//Un-highlights selected text
					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setColor(Color.TRANSPARENT);
					dropShadow.setOffsetX(0.0);
					dropShadow.setOffsetY(0.0);
					dropShadow.setRadius(0.0);
					n.setEffect(dropShadow);
				}
			});
			
			pane.getChildren().add(n);
			//AppController.getInstance().toolbar.CurrentPane = pane;
			
		}
		
		
		
		//TODO: repeat for other renderers
		
		
		return pane;
	}
	
	public String convertToHex(Color color) {
		
		String string =  String.format( "#%02X%02X%02X",
				(int)( color.getRed()	* 255 ),
				(int)( color.getGreen() * 255 ),
				(int)( color.getBlue()	* 255 ) 
			);
		
		return string;
		
	}
	
	public String convertToGradient(Paint color) {
		Color color1 = ((LinearGradient) color).getStops().get(0).getColor();
		Color color2 = ((LinearGradient) color).getStops().get(1).getColor();
		String string = ("linear-gradient(" + convertToHex(color1) + ", " + convertToHex(color2) + ")");
		
		return string;
		
	}

}
