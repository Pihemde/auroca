/**
 * 
 */
package com.googlecode.common.tools;

import java.io.StringWriter;

/**
 * @author Pierre-Marie Dhaussy
 * 
 */
public class AdvancedStringWriter extends StringWriter {

	public boolean empty() {
		return getBuffer().length() != 0;
	}
}