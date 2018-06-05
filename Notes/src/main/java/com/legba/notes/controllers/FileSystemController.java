package com.legba.notes.controllers;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.legba.notes.elements.Course;
import com.legba.notes.elements.Module;
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
	
	protected void saveToModulesFile(Module module){
		/** Used to serialise the Module object into xml using JAXB
		 * Will save to an xml file on local storage, will need to be updated to save to server
		 * @author jjds502
		 * @param Module to be saved to file
		 */		
		
		File moduleFile = new File("H:" + File.separator + "Legba" + File.separator + "Modules");
		//save the file
        if (moduleFile != null) {   
        	try {
        		StringWriter writer = new StringWriter();
        		JAXBContext context = JAXBContext.newInstance(Module.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				m.marshal(module, writer);
        	
        		//check that the file extension is correct. Referenced from:
        		//https://stackoverflow.com/questions/17010647/set-default-saving-extension-with-jfilechooser
        		
        		//if the file doesn't exist, create a new one
        		if (!moduleFile.exists()) {
        			moduleFile.createNewFile();
        		}
        		
        		//now we need to save the module
        		FileWriter fw = new FileWriter(moduleFile.getAbsoluteFile(), true);
        		fw.append(writer.toString());
        		fw.close();
        		System.out.println("Created new Module");
        		
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
        	} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	}
        }	
	}
	
	protected void saveToCourseFile(Course course){
		/** Used to serialise the Course object into xml using JAXB
		 * Will save to an xml file on local storage, will need to be updated to save to server
		 * @author jjds502
		 * @param Module to be saved to file
		 */
		
		File courseFile = new File("H:" + File.separator + "Legba" + File.separator + "Courses");
		//save the file
        if (courseFile != null) {   
        	try {
        		StringWriter writer = new StringWriter();
				JAXBContext context = JAXBContext.newInstance(Course.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				m.marshal(course, writer);
        		
        		//check that the file extension is correct. Referenced from:
        		//https://stackoverflow.com/questions/17010647/set-default-saving-extension-with-jfilechooser
        		
        		//if the file doesn't exist, create a new one
        		if (!courseFile.exists()) {
        			courseFile.createNewFile();
        		}
        		
        		//now we need to save the module
        		FileWriter fw = new FileWriter(courseFile.getAbsoluteFile(), true);
        		fw.append(writer.toString());
        		fw.close();
        		System.out.println("Created new Course");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (JAXBException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
	}

	
	protected List<Module> readModuleFile(){
		/**
		 * @author jjds502
		 */
		return null;
	}
	
	protected List<Course> readCourseFile(){
		/**
		 * @author jjds502
		 */
		try {
			File courseFile = new File("H:" + File.separator + "Legba" + File.separator + "Courses");
			//set up the marshaller
			JAXBContext context = JAXBContext.newInstance(Course.class);
			Unmarshaller um = context.createUnmarshaller();
			Course course = (Course) um.unmarshal(courseFile);
			System.out.println(um.unmarshal(courseFile).toString());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
