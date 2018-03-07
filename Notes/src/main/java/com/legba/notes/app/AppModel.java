package com.legba.notes.app;

import com.legba.notes.elements.Presentation;

public class AppModel{

	private static AppModel instance;
	
	private String file;
	private Presentation pres;
	
	/**
	 * Current view mode
	 */
	private ViewMode viewMode;
	
	private AppModel(){
		// any setup
		viewMode = ViewMode.HOMEPAGE;
	}
	
	public static AppModel getInstance() {
		if (instance == null){
			instance = new AppModel();
		}
		return instance;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Presentation getPres() {
		return pres;
	}

	public void setPres(Presentation pres) {
		
		// Cannot view a null presentaion
		if (pres == null && this.viewMode == ViewMode.VEIWING){
			System.err.println("Presentaion was nulled whilst being veiwed, bailing to homepage");
			this.viewMode = ViewMode.HOMEPAGE;
		}
		
		this.pres = pres;
	}

	public ViewMode getVeiwMode() {
		return viewMode;
	}

	public void setVeiwMode(ViewMode viewMode) {
		
		// if null is passed default to the homepage
		if (viewMode == null){
			System.err.println("AppModel.setVeiwMode was passed null, defaulting to homepage");
			viewMode = ViewMode.HOMEPAGE;
		}
		
		// if were not in viewing mode we shouldn't have a presentation open
		if (viewMode != ViewMode.VEIWING){
			this.pres = null;
		}
		
		// Carn't view a null presentaion
		if (viewMode != ViewMode.VEIWING && this.pres == null){
			System.err.println("Trying to enter veiwmode when no presentation is set");
			return;
		}
		
		this.viewMode = viewMode;
	}

	
}
