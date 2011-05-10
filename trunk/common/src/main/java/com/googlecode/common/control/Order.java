/**
 * 
 */
package com.googlecode.common.control;

import java.util.Collection;
import java.util.Map;

/**
 * @author Pierre-Marie Dhaussy
 * 
 */
public interface Order {

	/**
	 * Return the order name
	 * @return a {@link String}
	 */
	public String getName();
	
	/**
	 * Return the list of parameter names 
	 * @return a {@link Collection} of {@link String}
	 */
	public Collection<String> getParameterNames();
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Object> getParameters();
	
	/**
	 * @param name
	 * @return
	 */
	public Object getParameter(String name);
}
