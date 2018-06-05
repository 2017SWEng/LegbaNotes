package com.legba.notes.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.legba.notes.elements.Br;
import com.legba.notes.elements.Format;
import com.legba.notes.elements.Text;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class HTMLConverter {
	
	public static String toHTML(Text text) {
		
		if (text == null) {
			return null;
		}
		
		StringBuilder html = new StringBuilder();
		
		html.append("<html dir='ltr'><head></head><body contenteditable='true' style=\""+"font-color:"+text.getFill()+"\">");
		if((text.getFont() != null)) {
			html.append("<font>");
		}		
		if((text.getItalic() != null) && (text.getItalic() == true)) {
			html.append("<i>");
		}
		if((text.getBold() != null) && (text.getBold() == true)) {
			html.append("<b>");
		}
		if((text.getUnderline() != null) && (text.getUnderline() == true)) {
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
		
		if(html == null || html.trim().length() == 0){
			return null;
		}
		
		Document doc = Jsoup.parse(html);
		Element bodyTag = doc.getElementsByTag("body").get(0);
		System.out.println(bodyTag);
		
		Text pws = new Text(bodyTag.text());
		String stylesText = bodyTag.attr("style").toString();
		String[] styles = stylesText.split(";");
		
		pws.setTextsize(32);
		
		for(int i = 0; i < styles.length; i++){
			String HTMLkey = styles[i].split(":")[0];
			String value = styles[i].split(":")[1];
			
			if(HTMLkey.equals("font-family")){
				if(!value.trim().equals("null")) {
					pws.setFont(value);
				}
				else {
					pws.setFont("Ariel");
				}

			} else if(HTMLkey.equals("font-color")){
				if(!value.trim().equals("null")) {
					pws.setFill(Color.web(value));
				}
				else {
					pws.setFill(Color.BLACK);
				}
			}
		}
		
		Element fontTag = doc.getElementsByTag("font").get(0);
		
		//Font
		if(fontTag.attr("face").equals("")) {
			//No font selected
		} else {
			pws.setFont(fontTag.attr("face"));
		}
		
		//Size
		if(fontTag.attr("size").equals("")) {
			//No size selected
		} else {
			switch (Integer.parseInt(fontTag.attr("size"))){
				case 1: pws.setTextsize(8);
					break;
				case 2: pws.setTextsize(10);
					break;
				case 3: pws.setTextsize(12);
					break;
				case 4: pws.setTextsize(14);
					break;
				case 5: pws.setTextsize(18);
					break;
				case 6: pws.setTextsize(24);
					break;
				case 7: pws.setTextsize(36);
					break;
			}
		}
		
		//Fill
		if(fontTag.attr("color").equals("")) {
			//No color selected
		} else {
			pws.setFill(Color.web(fontTag.attr("color")));
		}

		return pws;
	}
	
}


