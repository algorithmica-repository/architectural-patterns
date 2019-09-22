package com.alg.ap.pipefilter;

import java.io.*;

public class Filter extends Thread {
	private PipedInputStream inputReadPort = new PipedInputStream();
	private PipedOutputStream outputWritePort = new PipedOutputStream();
	//private Filter inputFilter;

	public void connect(Filter filter) {
		try {
			inputReadPort.connect( filter.getOutputWriterPort() );
			//inputFilter = Filter;

		} catch(Exception e ) {
			System.out.println(e);

		} 

	} 

	public void closePorts() {
		try {
			inputReadPort.close();
			outputWritePort.close(); 
		} catch( Exception e ) {
			System.out.println(e); 
		} 

	} 

	public PipedInputStream getInputReadPort() {
		return inputReadPort;
	}
	
	public PipedOutputStream getOutputWriterPort() {
		return outputWritePort;
	}

} 