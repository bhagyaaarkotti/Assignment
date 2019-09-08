package LogData.Assignment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
/**
 * 
 * This class writes and reads data from files in the form of bytes.
 * @author bhagya_aarkotti
 *
 * @param <T>
 */
public class CreateLogger<T extends BinaryLoggable> extends  BinaryLogger<T> {
	FileOutputStream  fileOutput  = null;
	BufferedOutputStream bosOutput = null;
	FileInputStream fileInput = null;
	BufferedReader bInInput =null;

	public CreateLogger(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	/*
	 * Close files
	 * (non-Javadoc)
	 * @see java.lang.AutoCloseable#close()
	 */
	public void close() throws Exception {
		// TODO Auto-generated method stub
		bosOutput.close();
		fileOutput.close();
		fileInput.close();
		bInInput.close();
	}
	/**
	 * Writes data to file in the form of bytes.
	 * Data is written to file as classname 
	 * and data associated to that class using toBytes method.
	 * A new line character is added at the end to read data as line.
	 */
	@Override
	void write(T loggable) throws IOException {
		if(loggable==null) {
			 throw new IllegalArgumentException("Arguments are null");
		}
		// TODO Auto-generated method stub
		fileOutput = new FileOutputStream (this.outputFile,true);
		bosOutput = new BufferedOutputStream(fileOutput);
		
		byte []className = loggable.getClass().getCanonicalName().getBytes();
		byte []data = loggable.toBytes();
		bosOutput.write(className);	
		bosOutput.write(data);
		bosOutput.write('\n');
		bosOutput.flush();
		
	}
	/**
	 * Reads data from file and find required class by comparing 
	 * bytes of required class and bytes from file.
	 * 
	 * 
	 * If class found get datachars and convert it to bytes and 
	 * use fromBytes method to associate to found class.
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	Iterator<T> read(Class clazz) throws IOException {
		if(clazz==null) {
			 throw new IllegalArgumentException("Arguments are null");
		}
		
		if(this.outputFile==null || !this.outputFile.exists())
			throw new IllegalArgumentException("Provide file name");
		
		if(!this.outputFile.canRead())
			throw new IllegalArgumentException("File permissions needs to be changed to read");
		
		ArrayList<T> resultData = new ArrayList<T>();
		try {
			if(fileInput==null) 
				fileInput = new FileInputStream(this.outputFile);
			
			if(bInInput==null)
				bInInput = new BufferedReader(new InputStreamReader(fileInput));
			
			
			byte [] requiredClass = clazz.getCanonicalName().getBytes();
			byte [] resultedClass = new byte[requiredClass.length];
			T results  = null;
			for (String fileData = bInInput.readLine(); fileData != null; fileData = bInInput.readLine()) {
				resultedClass = fileData.substring(0, requiredClass.length).getBytes();
				if(Arrays.equals(requiredClass,resultedClass)
						&& fileData.length() > requiredClass.length ) {
					results = (T) Class.forName(new String(resultedClass)).newInstance();
					results.fromBytes(fileData.substring(requiredClass.length).getBytes());
					resultData.add(results);
				}
				
			}		
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultData.iterator();

	}
	
	
}
