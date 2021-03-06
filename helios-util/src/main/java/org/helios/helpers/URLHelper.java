/**
 * Helios, OpenSource Monitoring
 * Brought to you by the Helios Development Group
 *
 * Copyright 2007, Helios Development Group and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org. 
 *
 */
package org.helios.helpers;

import static org.helios.helpers.ClassHelper.nvl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>Title: URLHelper</p>
 * <p>Description: Generic helper classes for working with URLs and related IO</p> 
 * <p>Company: Helios Development Group LLC</p>
 * @author Whitehead (nwhitehead AT heliosdev DOT org)
 * @version $LastChangedRevision$
 * <p><code>org.helios.helpers.URLHelper</code></p>
 */

public class URLHelper {
	/** Text line separator */
	public static final String EOL = System.getProperty("line.separator", "\n");
	
	/**
	 * Reads the content of a URL as text
	 * @param url The url to get the text from
	 * @return a string representing the text read from the passed URL
	 */
	public static String getTextFromURL(URL url) {
		StringBuilder b = new StringBuilder();
		InputStreamReader isr = null;
		BufferedReader br = null;
		InputStream is = null;
		try {
			is = url.openStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line = null;
			while((line=br.readLine())!=null) {
				b.append(line).append(EOL);
			}
			return b.toString();			
		} catch (Exception e) {
			throw new RuntimeException("Failed to read source of [" + url + "]", e);
		} finally {
			try { br.close(); } catch (Exception e) {}
			try { isr.close(); } catch (Exception e) {}
			try { is.close(); } catch (Exception e) {}
		}
	}
	
	/**
	 * Returns the URL for the passed file
	 * @param file the file to get the URL for
	 * @return a URL for the passed file
	 */
	public static URL toURL(File file) {
		try {
			return nvl(file, "Passed file was null").toURI().toURL();
		} catch (Exception e) {
			throw new RuntimeException("Failed to get URL for file [" + file + "]", e);
		}
	}
	
	/**
	 * Creates a URL from the passed string 
	 * @param urlStr A char sequence containing a URL representation
	 * @return a URL
	 */
	public static URL toURL(CharSequence urlStr) {
		try {
			return new URL(nvl(urlStr, "Passed string was null").toString());
		} catch (Exception e) {
			throw new RuntimeException("Failed to create URL from string [" + urlStr + "]", e);
		}
	}
	
	
	/**
	 * Reads the content of a URL as a byte array
	 * @param url The url to get the bytes from
	 * @return a byte array representing the text read from the passed URL
	 */
	public static byte[] getBytesFromURL(URL url) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		InputStream is = null;
		try {
			is = url.openStream();
			int bytesRead = 0;
			byte[] buffer = new byte[8092]; 
			while((bytesRead=is.read(buffer))!=-1) {
				baos.write(buffer, 0, bytesRead);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("Failed to read source of [" + url + "]", e);
		} finally {
			try { is.close(); } catch (Exception e) {}
		}
	}
	
	/**
	 * Returns the last modified time stamp for the passed URL
	 * @param url The URL to get the timestamp for
	 * @return the last modified time stamp for the passed URL
	 */
	public static long getLastModified(URL url) {
		URLConnection conn = null;
		try {
			conn = nvl(url, "Passed URL was null").openConnection();
			return conn.getLastModified();
		} catch (Exception e) {
			throw new RuntimeException("Failed to get LastModified for [" + url + "]", e);
		}
	}
	
	
	/**
	 * Determines if the passed string is a valid URL
	 * @param urlStr The URL string to test
	 * @return true if is valid, false if invalid or null
	 */
	public static boolean isValidURL(CharSequence urlStr) {
		if(urlStr==null) return false;
		try {
			new URL(urlStr.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
