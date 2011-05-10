/**
 * 
 */
package com.googlecode.android.communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import android.bluetooth.BluetoothSocket;

import com.googlecode.common.tools.AdvancedStringWriter;

/**
 * @author Pierre-Marie Dhaussy
 * 
 */
public class BluetoothListener implements Runnable {

	/**  */
	public static final String ENCODING = "UTF-8";

	/**  */
	public long SLEEP = 1000;

	/**  */
	private boolean activated = true;

	/**  */
	private BluetoothSocket socket = null;

	/**
	 * Constructor
	 * 
	 * @param socket
	 *            bluetooth socket
	 * @throws IOException
	 */
	public BluetoothListener(BluetoothSocket socket) throws IOException {
		this.socket = socket;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (activated) {
			readMessage();
			try {
				Thread.sleep(SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void readMessage() {
		AdvancedStringWriter writer = new AdvancedStringWriter();
		char[] buffer = new char[1024];
		int n = 0;
		try {
			Reader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), ENCODING));

			while (n != -1) {
				n = reader.read(buffer);
				writer.write(buffer, 0, n);
			}
			if (!writer.empty()) {
				// send message to ?
				writer.flush();
			}
		} catch (IOException e) {
			//
		}
	}
}