package com.legba.notes.controllers;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.legba.notes.elements.Presentation;

public class FileSystemController {

	Marshaller marshaller;
	Unmarshaller unmarshaller;
	
	JAXBContext context;
	
	public FileSystemController(){
		

		try {
			this.context = JAXBContext.newInstance(Presentation.class);

			
			
			this.unmarshaller = context.createUnmarshaller();
			
			
			this.marshaller = context.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "pws schema.xsd");

		} catch (JAXBException e) {
			System.err.println("Unable to create xml (un)marshaller");// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Presentation unmarshall(InputStream is){
		try {
			Presentation presentation = (Presentation) unmarshaller.unmarshal(is);
			return presentation;
		} catch (JAXBException e) {
			System.err.println("Unable to marshall presentaion");
			e.printStackTrace();
			return null;
		}
	}
	
	private void marshall(Presentation p, OutputStream os){
		try {
			this.marshaller.marshal(p, os);
		} catch (JAXBException e) {
			System.err.println("Unable to unmarshall presentaion");
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
