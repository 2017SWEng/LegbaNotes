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

import com.legba.notes.controllers.AppController;
import com.legba.notes.elements.Audio;
import com.legba.notes.elements.Image;
import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;
import com.legba.notes.elements.Text;
import com.legba.notes.elements.Video;

/**
 * Takes an instance of a Slide and produces an javafx Node tree.
 * @author zraw500, lm1370
 *
 */
public class SlideRenderer extends Renderer<Slide> {

	VectorRenderer vectorRenderer;
	AudioRenderer audioRenderer;
	TextRenderer textRenderer;
	VideoRenderer videoRenderer;
	ImageRenderer imageRenderer;

	/**
	 * Default constructor, use default SlideElement renderers
	 */
	public SlideRenderer(){
		this.vectorRenderer = new VectorRenderer();
		this.audioRenderer = new AudioRenderer();
		this.textRenderer = new TextRenderer();
		this.videoRenderer = new VideoRenderer();
		this.imageRenderer = new ImageRenderer();
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
			VideoRenderer videoRenderer,
			ImageRenderer imageRenderer
			){
		this.vectorRenderer = vectorRenderer;
		this.audioRenderer = audioRenderer;
		this.textRenderer = textRenderer;
		this.videoRenderer = videoRenderer;
		this.imageRenderer = imageRenderer;
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
		
		//When mouse enters pane it puts border around it
		pane.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			}
		});
		
		//When mouse exits pane it removes the border
		pane.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.EMPTY)));
			}
		});

		for(Shape shape : s.getShapes()){
			Node n = this.vectorRenderer.render(shape);
			
			//When mouse clicks on shape, selective editing enabled, current state displayed on toolbar
			n.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {								
					if(n!=null) {
						//Enable shape mode
						AppController.getInstance().toolbar.shapeMode();	
						
						//Sets variables
						AppController.getInstance().toolbar.CurrentShape = shape;
						AppController.getInstance().viewing.CurrentNode = n;
						
						//Displays selected shape variables on toolbar
						AppController.getInstance().toolbar.typeCombo.setValue(shape.getType());	
						AppController.getInstance().toolbar.strokeCombo.setValue(shape.getStroke());
						AppController.getInstance().toolbar.strokeColor.setValue(shape.getColor());
						AppController.getInstance().toolbar.shapeFill.setValue(shape.getFill());
						
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
		
		for(Image image : s.getImages()){
			pane.getChildren().add(this.imageRenderer.render(image));
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
		
		return pane;
	}

}
