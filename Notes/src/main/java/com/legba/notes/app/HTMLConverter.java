package com.legba.notes.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.scene.paint.Color;

import com.legba.notes.elements.Br;
import com.legba.notes.elements.Format;
import com.legba.notes.elements.Text;

import javafx.scene.paint.Paint;

public class HTMLConverter {
	
	/**
	 * Converts a Text element into HTML code
	 * @param text that needs to be converted to HTML code
	 * @return html.toString() which is the converted text
	 */
	public static String toHTML(Text text) {
		
		if (text == null) {
			return null;
		}
		
		StringBuilder html = new StringBuilder();
		
		System.out.println("\n-----" + text + "\n-----");
		
		html.append("<html dir=\"ltr\"><head></head><body contenteditable='true' style=\"" + 
				"font-color:" + text.getColor() + ";font-family:" + text.getFont() + 
				";font-size:" + text.getTextsize() + "pt");
		if(text.getItalic() == true) {
			html.append(";font-style:italic");
		}
		if(text.getBold() == true) {
			html.append(";font-weight:bold");
		}
		if(text.getUnderline() == true) {
			html.append(";text-decoration:underline");
		}
		html.append("\">");
		
		for (int i=0; i<text.getContents().size(); i++) {
			
			if (text.getContents().get(i) instanceof String){
				html.append(text.getContents().get(i));
			}
			else if (text.getContents().get(i) instanceof Format){
				if (((Format)text.getContents().get(i)).getBold() != null) {
					if(((Format)text.getContents().get(i)).getBold() == true) {
						html.append("<b>");
					}
				}
				if (((Format)text.getContents().get(i)).getItalic() != null) {
					if(((Format)text.getContents().get(i)).getItalic() == true) {
						html.append("<i>");
					}
				}
				if (((Format)text.getContents().get(i)).getUnderline() != null) {
					if (((Format)text.getContents().get(i)).getUnderline() == true) {
						html.append("<u>");
					}
				}
				
				html.append("<p style=\"");
				
				if (((Format)text.getContents().get(i)).getColor() != null) {
					html.append("color" + ((Format)text.getContents().get(i)).getColor());
				}	
				if (((Format)text.getContents().get(i)).getTextsize() != null) { 
					html.append(";font-size:" + ((Format)text.getContents().get(i)).getTextsize() + "pt"); 
				}
				if (((Format)text.getContents().get(i)).getFont() != null) {
					html.append(";font-family:" + ((Format)text.getContents().get(i)).getFont());
				}
				html.append("\">");

				html.append(((Format)text.getContents().get(i)).getText());
				
				html.append("</p>");
				
				if (((Format)text.getContents().get(i)).getBold() != null) {
					if(((Format)text.getContents().get(i)).getBold() == true) {
						html.append("</b>");
					}
				}
				if (((Format)text.getContents().get(i)).getItalic() != null) {
					if(((Format)text.getContents().get(i)).getItalic() == true) {
						html.append("</i>");
					}
				}
				if (((Format)text.getContents().get(i)).getUnderline() != null) {
					if (((Format)text.getContents().get(i)).getUnderline() == true) {
						html.append("</u>");
					}
				}
			}
			else if (text.getContents().get(i) instanceof Br){
				html.append("\n");
			}
			else {
				System.err.println("Error");
			}
		}
		if((text.getFont() != null)) {
			html.append("</font>");
		}
		if((text.getUnderline() != null) && (text.getUnderline() == true)) {
			html.append("</u>");
		}
		if((text.getBold() != null) && (text.getBold() == true)) {
			html.append("</b>");
		}
		if((text.getItalic() != null) && (text.getItalic() == true)) {
			html.append("</i>");
		}
		html.append("</body></html>");
		
		return html.toString();
	}
	
	public static Text toPWS(String html) {
		//TODO: Currently only returns plain text, most formating removed
		Text pws = new Text();
		Document doc = Jsoup.parse(html);
		String StyleString = doc.body().getAllElements().attr("style");
		
		//Print out for testing purposes only
		System.out.println("-----\n" + doc.body()/*.getElementsByTag("p")*/ + "\n-----");
		
		//buildString(doc.body(), pws);
		pws.addContents(doc.body().text());
		applyStyle(StyleString, pws);
		
		System.out.println("Current PWS file contents: " + pws);

		
		return pws;
	}
	
	private static void applyStyle(String StyleString, Text text) {
		String[] Styles = StyleString.split(";");
		
		for (int i = 0; i < Styles.length; i++) {
			if(Styles[i].startsWith("font-size")) {
				String Size = Styles[i].split(":")[1].split("pt")[0];
				text.setTextsize(Integer.parseInt(Size));
			}
			else if(Styles[i].startsWith("font-color")) {
				String StringColor = Styles[i].split(":")[1];
				Color color = Color.web(StringColor);
				text.setColor(color);
			}
			else if(Styles[i].startsWith("font-family")) {
				String Font = Styles[i].split(":")[1];
				text.setFont(Font);
			}
			else if(Styles[i].startsWith("font-weight")) {
				text.setBold(true);
			}
			else if(Styles[i].startsWith("font-style")) {
				text.setItalic(true);	
			}
			else if(Styles[i].startsWith("text-decoration")) {
				text.setUnderline(true);
			}
		}	
	}
	
	private static void buildString(Element body, Text pws) {
		String[] content = body.toString().split(">|\\n");
		StringBuilder pwsString = new StringBuilder();
		
		for(int i = 1; i< content.length - 1; i++) {
			System.out.println(content[i]);
			if(content[i].contains("<u")) {
				pwsString.append("<Format underline=\"true\">");
				System.out.println("Added Undeline opener");
			}
			else if(content[i].contains("<b")) {
				pwsString.append("<Format bold =\"true\">");
				System.out.println("Added Bold opener");
			}
			else if(content[i].contains("<i")) {
				pwsString.append("<Format italic =\"true\">");
				System.out.println("Added Italic opener");
			}
			else if(content[i].contains("<p")) {
				if(content[i].contains("color")) {
					pwsString.append("<Format color=\"#"+ content[i].split("color0x")[1] +">");
					System.out.println("Added Color opener");
				}
				else if(content[i].contains("family")) {
					pwsString.append("<Format font=\""+ content[i].split(":")[1] +">");
					System.out.println("Added Font opener");
				}
				else if(content[i].contains("size")) {
					pwsString.append("<Format textsize=\""+ content[i].split(":")[1] +">");
					System.out.println("Added size opener");
				}
			}
			else if(content[i].contains("</u")) {
				pwsString.append(content[i].split("<")[0] + "</Format>");
				System.out.println("Added Undeline closer");
			}
			else if(content[i].contains("</b")) {
				pwsString.append(content[i].split("<")[0] + "</Format>");
				System.out.println("Added bold closer");
			}
			else if(content[i].contains("</i")) {
				pwsString.append(content[i].split("<")[0] + "</Format>");
				System.out.println("Added italic closer");
			}
			else if(content[i].contains("</p")) {
				pwsString.append(content[i].split("<")[0] + "</Format>");
				System.out.println("Added P closer");
			}
			else {
				pwsString.append(content[i]);
				System.out.println("Added Text");
			}
		}
		System.out.println(pwsString);
		
		Text tempText = new Text(pwsString.toString());
		pws.setContents(tempText.getContents());
	}
}


