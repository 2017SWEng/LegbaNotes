package com.legba.notes.app;

import com.legba.notes.elements.Br;
import com.legba.notes.elements.Format;
import com.legba.notes.elements.Text;

public class HTMLConverter {
	
	public static String toHTML(Text text) {
		
		if (text == null) {
			return null;
		}
		
		StringBuilder html = new StringBuilder();
		html.append("<html dir='ltr'><head></head><body contenteditable='true' style=\""+"font-color:"+text.getColor()+
				";font-family:"+text.getFont()+"\">");
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
				html.append(((Format)text.getContents().get(i)).getText());
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
		
		return html.toString();
	}
	
	public static Text toPWS(String html) {
		Text pws = new Text();
		System.out.println("");
		return pws;
	}
	
}


