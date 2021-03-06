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
package org.helios.net.ssh;

/**
 * <p>Title: SSHConnectionException</p>
 * <p>Description: An exception thrown when a {@link SSHService} fails an initial connect request.</p> 
 * <p>Company: Helios Development Group LLC</p>
 * @author Whitehead (nwhitehead AT heliosdev DOT org)
 * <p><code>org.helios.net.ssh.SSHConnectionException</code></p>
 */
public class SSHConnectionException extends Exception {

	/**  */
	private static final long serialVersionUID = -8560418884953935953L;

	/**
	 * Creates a new SSHConnectionException
	 */
	public SSHConnectionException() {
	}

	/**
	 * Creates a new SSHConnectionException
	 * @param message The connection failure message
	 */
	public SSHConnectionException(String message) {
		super(message);
	}

	/**
	 * Creates a new SSHConnectionException
	 * @param cause The connection failure cause
	 */
	public SSHConnectionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new SSHConnectionException
	 * @param message The connection failure message
	 * @param cause The connection failure cause
	 */
	public SSHConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

}
