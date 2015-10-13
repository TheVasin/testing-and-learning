/**
 * This class initialize parser with specific parameters and starts parsing.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import vasin.xmlshapes.Errors;

public class XMLParser implements Runnable {
	private final InputSource input; 	// input file
	private final XMLReader reader;		// SAX reader
	private final XMLHandler handler;	// custom handler
	
	public XMLParser(FileReader input, BlockingQueue<Object> output) throws SAXException {
		this.input = new InputSource(input);
		this.reader = XMLReaderFactory.createXMLReader();
		this.handler = new XMLHandler(output);
		this.reader.setContentHandler(handler);
		this.reader.setErrorHandler(handler);
	}
	
	@Override
	public void run() {
		try {
			reader.parse(input);
		} catch (IOException e) {
			Errors.printErrorAndExit(Errors.MESSAGE_IO_PARSE_EXCEPTION, Errors.ERROR_IO_PARSE_EXCEPTIO);
		} catch (SAXException e) {
			Errors.printErrorAndExit(Errors.MESSAGE_SAX_PARSE_EXCEPTION, Errors.ERROR_SAX_PARSE_EXCEPTION);
		}
	}
}
