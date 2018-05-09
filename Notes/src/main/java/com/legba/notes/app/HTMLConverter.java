package com.legba.notes.app;

import com.legba.notes.elements.Br;
import com.legba.notes.elements.Format;
import com.legba.notes.elements.Text;

public class HTMLConverter {
	
	public static String toHTML(Text text) {
		StringBuilder html = new StringBuilder();
		
		html.append("<html dir=\"ltr\"><head></head><body contenteditable='true' style=\"" + 
				"font-color:" + text.getColor() + ";font-family:" + text.getFont() + 
				";font-size:" + text.getTextsize() + "pt\">");
		if(text.getItalic() == true) {
			html.append("<i>");
		}
		if(text.getBold() == true) {
			html.append("<b>");
		}
		if(text.getUnderline() == true) {
			html.append("<u>");
		}
		
		for (int i=0; i<text.getContents().size(); i++) {
			
			if (text.getContents().get(i) instanceof String){
				html.append(text.getContents().get(i));
			}
			else if (text.getContents().get(i) instanceof Format){
				if (((Format)text.getContents().get(i)).getBold() == true) {
					html.append("<b>");
				}
				if (((Format)text.getContents().get(i)).getItalic() == true) {
					html.append("<i>");
				}
				if (((Format)text.getContents().get(i)).getUnderline() == true) {
					html.append("<u>");
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
				
				if (((Format)text.getContents().get(i)).getBold() == true) {
					html.append("</b>");
				}
				if (((Format)text.getContents().get(i)).getItalic() == true) {
					html.append("</i>");
				}
				if (((Format)text.getContents().get(i)).getUnderline() == true) {
					html.append("</u>");
				}
			}
			else if (text.getContents().get(i) instanceof Br){
				html.append("\n");
			}
			else {
				System.err.println("Error");
			}
		}
		if(text.getUnderline() == true) {
			html.append("</u>");
		}
		if(text.getBold() == true) {
			html.append("</b>");
		}
		if(text.getItalic() == true) {
			html.append("</i>");
		}
		html.append("</body></html>");
		
		//This line is for testing purposes only
		System.out.println(html.toString());
		
		return html.toString();
	}
	
	public static Text toPWS(String html) {
		Text pws = new Text();
		
		return pws;
	}
	
}


