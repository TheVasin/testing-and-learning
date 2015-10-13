/**
 * This class checks for a input file, provided as an argument. Then it starts two threads. One for parsing the document
 * and second for calculating area of shapes and printing results.
 * Parser thread puts objects into queue. Printer thread reads from the queue and prints objects.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.LinkedTransferQueue;

import org.xml.sax.SAXException;

import vasin.xmlshapes.parser.XMLParser;
import vasin.xmlshapes.queue.QueuePrinter;

public class Main {
    
    public static void main(String[] args) {
        XMLParser parser;                     // parser that do the job
        LinkedTransferQueue<Object> queue;    // queue to put parsed objects to
        QueuePrinter printer;                 // printer that will take objects from queue and print them
        FileReader reader;                    // input file reader

        // get initial states
        reader = readFile(getFirstArgument(args));
        queue = new LinkedTransferQueue<Object>();
        parser = getParser(reader, queue);
        printer = new QueuePrinter(queue);

        // run parser and printer
        new Thread(parser).start();
        new Thread(printer).start();
    }
    
    private static FileReader readFile(String filename) {
        // checks for a file provided
        FileReader reader = null;
        try {
            reader =  new FileReader(filename);
        } catch (FileNotFoundException e) {
            Errors.printErrorAndExit(Errors.MESSAGE_FILE_NOT_FOUND, Errors.ERROR_FILE_NOT_FOUND);
        }
        return reader;
    }
    
    private static String getFirstArgument(String[] args) {
        // check for an argument provided
        if (args.length == 0) {
            Errors.printErrorAndExit(Errors.MESSAGE_NO_ARGUMENT, Errors.ERROR_NO_ARGUMENT);
        }
        return args[0];
    }
    
    private static XMLParser getParser(FileReader reader, LinkedTransferQueue<Object> queue) {
        // initialize parser
        XMLParser parser = null;
        try {
            parser = new XMLParser(reader, queue);
        } catch (SAXException e) {
            Errors.printErrorAndExit(Errors.MESSAGE_SAX_INIT_EXCEPTION, Errors.ERROR_SAX_INIT_EXCEPTION);
        }
        return parser;
    } 
}
