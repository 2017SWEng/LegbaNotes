package com.legba.notes.controllers;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FilenameFilter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;



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
	private final String recentDocsFileName = ".recentdocs";


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
	
	
	public Path getLegbaPath() {
 		//append to the recents file
		Path path = Paths.get(System.getProperty("user.home") + File.separator + "Legba");
		
		
		if(!Files.exists(path))
		{
			//create the directory
			try{
				File LegbaDir = path.toFile();
				LegbaDir.mkdir();
				System.out.println("Legba directory created...");
			}
			catch(SecurityException se){
				se.printStackTrace();
				System.err.println("Unable to create legba folder in home directory");
				return null;
			}
		}
		
 		return path;
 	}

	
	public List<String> loadScannedFiles() {
		
		System.out.println("Adding list of items from ~/Legba to homepage");
		
		File[] files = scanDirectoryForNotes(getLegbaPath().toFile());
		
		List<String> fileItems = new ArrayList<String>();

		for(int i = 0; i < files.length; i++ ){
			fileItems.add(files[i].toString());
		}	
		
		System.out.println("Added " + fileItems.size() + " items from ~/Legba to homepage");
		return fileItems;
	}

		protected List<Module> readModuleFile(){
		/**
		 * @author jjds502
		 */
		return null;
	}

	public File[] scanDirectoryForNotes(File dir) {
 		
		System.out.println(dir.getAbsolutePath());
		System.out.println(dir.isDirectory());
		
		if (dir.isDirectory()) {
 			
 			FilenameFilter valid = new FilenameFilter() {
				public boolean accept(File dir, String filename) {
					return filename.endsWith(".pws");
				}
			};
 			
 			return dir.listFiles(valid);
 		}
 		return null;
 	}

public List<String> loadRecentDocsFile() throws FileNotFoundException {
 		
 		System.out.println("Loading recent docs file");
		Path legbaPath = getLegbaPath();
		if (legbaPath == null) {
			return null;
		}
		
		File recentsDoc = new File(legbaPath.toString() + File.separator + recentDocsFileName);
		if(Files.exists(Paths.get(recentsDoc.getPath()))) {
			Scanner sc = new Scanner(recentsDoc);
			Set<String> lines = new HashSet<String>();
			while (sc.hasNextLine()) {
			  lines.add(sc.nextLine().trim());
			}
			sc.close();
			System.out.println("Loaded " + lines.size() + " recent docs");
			
			List<String> recents = new ArrayList<String>();
			recents.addAll(lines);
			
			return recents;
		}
		else {
			System.out.println("No recent docuemnts file");
			return null;
		}
		
 	}
 	
 	public void saveRecentDocsFile(List<String> lines) throws IOException {
 		
 		System.out.println("Saving recent docs file");

		Path legbaPath = getLegbaPath();
		if (legbaPath == null) {
			return;
		}
		
		File recentsDoc = new File(legbaPath.toString() + File.separator + recentDocsFileName);

		FileWriter fw = new FileWriter(recentsDoc);
	    BufferedWriter bw = new BufferedWriter(fw);
	    for(String l : lines) {
		    bw.write(l);
		    bw.newLine();
		    System.out.println(l);
	    }
	    bw.flush();
	    bw.close();
	    
		System.out.println("Saved " + lines.size() + " recent docs");

 	}
 	
 	public void addFileToRecents(File openedFile) throws IOException{
		 		
 		List<String> recents = loadRecentDocsFile();
 		
 		if (recents == null) {
 			recents = new ArrayList<String>();
 		}
 		
 		if (!recents.contains(openedFile.getAbsolutePath())) {
 	 		recents.add(openedFile.getAbsolutePath());
 		}
 		
 		saveRecentDocsFile(recents);
 		
	    //testing purposes
	    System.out.println("recents updated");
	}

}
