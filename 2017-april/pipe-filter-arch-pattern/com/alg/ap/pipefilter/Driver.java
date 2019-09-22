package com.alg.ap.pipefilter;

public class Driver {
   public static void main( String[] args)   {
		InputReaderFilter irfilter = new InputReaderFilter(args[0]);
		CircularShifterFilter csfilter = new CircularShifterFilter();
		SorterFilter sortfilter = new SorterFilter();
		OutputWriterFilter owfilter = new OutputWriterFilter(args[1]);

		owfilter.connect(sortfilter);
		sortfilter.connect(csfilter);
		csfilter.connect(irfilter);
		
		irfilter.start();
		csfilter.start();
		sortfilter.start();
		owfilter.start();
   }
}