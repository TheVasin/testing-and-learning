/**
 * This class describes possible errors and provide method for printing error messages and exiting.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes;

public class Errors {
	public static final short ERROR_NO_ARGUMENT = 11;
	public static final short ERROR_FILE_NOT_FOUND = 12;
	public static final short ERROR_SAX_INIT_EXCEPTION = 21;
	public static final short ERROR_SAX_PARSE_EXCEPTION = 22;
	public static final short ERROR_INTERRUPTED = 30;
	public static final short ERROR_IO_PARSE_EXCEPTIO = 40;
	
	public static final String MESSAGE_NO_ARGUMENT = "Please specify a file to parse.";
	public static final String MESSAGE_FILE_NOT_FOUND = "Specified file was not found.";
	public static final String MESSAGE_SAX_INIT_EXCEPTION = "Unable to initalize SAX parser.";
	public static final String MESSAGE_SAX_PARSE_EXCEPTION = "SAX exception while parsing file.";
	public static final String MESSAGE_INTERRUPTED = "Thread was interrupted.";
	public static final String MESSAGE_IO_PARSE_EXCEPTION = "IO exception while parsing file.";
	
	public static void printErrorAndExit(String message, short code) {
		System.out.println(message);
		System.exit(code);
	}
}
