package com.brb.brbcom.foundation.property;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

/**
 * 
 * @author back
 *
 */
public interface PropertyService {
    public static final Logger LOGGER = LoggerFactory.getLogger(PropertyService.class);
    
	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract boolean getBoolean(String s);

	/**
	 * 
	 * @param s
	 * @param flag
	 * @return
	 */
	public abstract boolean getBoolean(String s, boolean flag);

	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract double getDouble(String s);

	/**
	 * 
	 * @param s
	 * @param d
	 * @return
	 */
	public abstract double getDouble(String s, double d);

	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract float getFloat(String s);

	/**
	 * 
	 * @param s
	 * @param f
	 * @return
	 */
	public abstract float getFloat(String s, float f);

	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract int getInt(String s);

	/**
	 * 
	 * @param s
	 * @param i
	 * @return
	 */
	public abstract int getInt(String s, int i);

	/**
	 * 
	 * @return
	 */
	public abstract Iterator<?> getKeys();

	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract Iterator<?> getKeys(String s);

	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract long getLong(String s);

	/**
	 * 
	 * @param s
	 * @param l
	 * @return
	 */
	public abstract long getLong(String s, long l);

	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract String getString(String s);

	/**
	 * 
	 * @param s
	 * @param s1
	 * @return
	 */
	public abstract String getString(String s, String s1);
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract String[] getStringArray(String s);

	/**
	 * 
	 * @param s
	 * @return
	 */
	public abstract Vector<?> getVector(String s);

	/**
	 * 
	 * @param s
	 * @param vector
	 * @return
	 */
	public abstract Vector<?> getVector(String s, Vector<?> vector);

	/**
	 * 
	 * @throws Exception
	 */
	public abstract void refreshPropertyFiles()  throws Exception;

	/**
	 * 
	 * @return
	 */
	public abstract Collection<?> getAllKeyValue();
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public abstract String getPath(String name);

}
