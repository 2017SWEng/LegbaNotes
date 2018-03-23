package com.legba.notes.models;

import java.util.Observable;

public class ViewMode extends Observable{
	
	private Mode mode;
	
	ViewMode(){
		super();
		setMode(Mode.HOMEPAGE);
	}
	
	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		
		if (mode == this.mode){
			System.out.println("Changed to the same mode");
			return;
		}
		
		this.mode = mode;

		setChanged();
		notifyObservers(this.mode);
	}

	/*
	 * Enum for different view modes
	 * <li>{@link #HOMEPAGE}</li>
	 * <li>{@link #VEIWING}</li>
	 * <li>{@link #LOGIN}</li>
	 * @author zraw500
	 * @author Contributed to by <jjds502>
	 */
	public enum Mode {
		/**
		 *  homepage has list of recent file, access to file browser and create new
		 */
		HOMEPAGE, 
		
		 /**
		  *  for viewing (and editing) a notes file
		  */
		VEIWING,
		
		/**
		 * Login page will be shown when user session is not in progress/has expired
		 */
		LOGIN
		// User Management
		// Account management (settings)
		// Dept./Course/Module management	
		// Forum QA

	}
}

