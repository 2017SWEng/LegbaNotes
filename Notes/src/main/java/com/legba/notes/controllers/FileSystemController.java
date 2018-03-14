package com.legba.notes.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	public Presentation loadXmlFile(String path) {
		
		System.out.println("Loading XML file : " + path);
		
		File f = new File(path);
		if (f.exists() && !f.isDirectory()) {
			
			try {
				InputStream s = new FileInputStream(f);
				
				System.out.println("Loaded XML file : " + path);

				Presentation p = unmarshall(s);
				
				s.close();
				
				return p;
								
			} catch (FileNotFoundException | JAXBException e) {
				e.printStackTrace();
				System.err.println("Unable to open stream to read file");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Unable to cloase stream that was reading file");
			}
			
		}
		
		System.out.println(" Failed to Load XML file : " + path);

		return null;
	}
	
	
	public boolean saveXmlFile(String path, Presentation p) {
		File f = new File(path); 
	
		try {
			OutputStream s = new FileOutputStream(f);
			marshall(p, s);
			
			s.close();
			return true;
		}
		catch (Exception e) {
			System.err.println("Unable to save presentaion");
			e.printStackTrace();
			return false;
		}
	}
	
	protected Presentation unmarshall(InputStream is) throws JAXBException{
		try {
			Presentation presentation = (Presentation) unmarshaller.unmarshal(is);
			return presentation;
		} catch (JAXBException e) {
			System.err.println("Unable to marshall presentaion");
			e.printStackTrace();
			//TODO: make own exception class
			throw(e);
		}
		
	}
	
	protected void marshall(Presentation p, OutputStream os) throws JAXBException{
		try {
			this.marshaller.marshal(p, os);
		} catch (JAXBException e) {
			System.err.println("Unable to unmarshall presentaion");
			e.printStackTrace();
			//TODO: make own exception class
			throw(e);
		}
		
	}
	
	
	
	
	
}
