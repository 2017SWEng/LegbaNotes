package com.legba.notes.renderers;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
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
import javafx.util.Duration;

import com.legba.notes.controllers.AppController;
import com.legba.notes.elements.Audio;
import com.legba.notes.elements.Image;
import com.legba.notes.elements.Shape;
import com.legba.notes.elements.Slide;
import com.legba.notes.elements.Text;
import com.legba.notes.elements.Video;
import com.legba.notes.models.AppModel;

/**
 * Takes an instance of a Slide and produces an javafx Node tree.
 * @author zraw500
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
			
			//When mouse enters shape it puts a shadow behind it
			n.onMouseEnteredProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setColor(Color.BLACK);
					dropShadow.setOffsetX(0.0);
					dropShadow.setOffsetY(0.0);
					dropShadow.setRadius(15.0);
					n.setEffect(dropShadow);
					
					if(n!=null) {
						AppController.getInstance().viewing.CurrentNode = n;
					}
				}
			});
			
			//When mouse exits shape it removes the shadow
			n.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setColor(Color.BLACK);
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
			
			//When mouse clicks on text highlights whole text
			n.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {			
					DropShadow dropShadow = new DropShadow();
					dropShadow.setBlurType(BlurType.GAUSSIAN);
					dropShadow.setColor(Color.BLACK);
					dropShadow.setOffsetX(0.0);
					dropShadow.setOffsetY(0.0);
					dropShadow.setRadius(15.0);
					n.setEffect(dropShadow);
					
					if(n!=null) {
						AppController.getInstance().viewing.CurrentNode = n;
					}
				}
			});
			
			//When mouse exits text it removes the shadow
			n.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
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
		
		return pane;
	}

}
