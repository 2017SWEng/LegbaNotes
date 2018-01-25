package com.legba.notes;

import com.legba.notes.backend.Backend;
import com.legba.notes.frontend.Frontend;
/**
 * Hello world!
 *
 */
public class App 
{
	static Backend backend;
	static Frontend frontend;

    public static void main( String[] args )
    {
    	App.backend = new Backend();
    	App.frontend = new Frontend();
    }
}
