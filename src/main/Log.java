package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO maybe create new LogFile each time Program is started?

/**
 * @author Sebastian Reith
 *
 */
public class Log {
	static FileWriter writer;
	static File file;
	
	/**
	 * Log Error-message
	 * @param tag	Position where Log is caused
	 * @param text	Message
	 */
	public static void e(String tag, String text){
		String log = getDate() + "	Error:		" + tag + "	" + text;
		System.out.println(log);
		writeFile(log);
	}
	
	/**
	 * Log Warning-message
	 * @param tag	Position where Log is caused
	 * @param text	Message
	 */
	public static void w(String tag, String text){
		String log = getDate() + "	Warning:	" + tag + "	" + text;
		System.out.println(log);
		writeFile(log);
	}
	
	/**
	 * Log Info-message
	 * @param tag	Position where Log is caused
	 * @param text	Message
	 */
	public static void i(String tag, String text){
		String log = getDate() + "	Info:		" + tag + "	" + text;
		System.out.println(log);
		writeFile(log);
	}	
	
	/**
	 * Log Debug-message
	 * @param tag	Position where Log is caused
	 * @param text	Message
	 */
	public static void d(String tag, String text){
		String log = getDate() + "	Debug:		" + tag + "	" + text;
		System.out.println(log);
		writeFile(log);
	}
	
	/**
	 * Log Verbose-message
	 * @param tag	Position where Log is caused
	 * @param text	Message
	 */
	public static void v(String tag, String text){
		String log = getDate() + "	Verbose:	" + tag + "	" + text;
		System.out.println(log);
		writeFile(log);
	}
	
	/**
	 * Log new line
	 */
	public static void newLine(){
		System.out.println(" ");
		writeFile(" ");
	}
	
	private static String getDate(){
		return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
	}
	
	private static boolean writeFile(String inhalt){
		file = new File("ErrorLog.log");
		try{
			writer = new FileWriter(file, true);
			writer.write(inhalt + "\r\n");
			writer.flush();
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
//	public static void main(String[] args){
//		e("tag", "errortext");
//		w("tag", "test");
//		i("tag", "bla");
//		d("tag", "asdf");
//		v("tag", "verbosetext");
//	}
	
}
