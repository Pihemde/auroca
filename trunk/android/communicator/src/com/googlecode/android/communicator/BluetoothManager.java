/**
 * 
 */
package com.googlecode.android.communicator;

import java.io.IOException;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import com.googlecode.common.Constants;

/**
 * @author Pierre-Marie Dhaussy
 * 
 */
public class BluetoothManager extends Activity {

	/**  */
	public static final int REQUEST_ENABLE_BT = 1;

	/**  */
	public static final String ROBOT_BLUETOOTH_ADAPTER_NAME = "CATDROID";

	/**  */
	private BluetoothAdapter bluetoothAdapter = null;

	/**  */
	private BluetoothDevice bluetoothDevice = null;

	/**  */
	private BluetoothSocket bluetoothSocket = null;

	/**
	 * 
	 */
	public BluetoothManager() {
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		checkRequirement();
		try {
			searchRobot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 *      android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == REQUEST_ENABLE_BT) {

			// Le bluetooth est activé ?

		} else {

			// Le bluetooth est activé ?

		}
	}

	private void checkRequirement() {
		if (bluetoothAdapter == null) {
			// Device does not support Bluetooth
		}

		if (!bluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
	}

	private void searchRobot() throws IOException {
		Set<BluetoothDevice> bondedDevices = bluetoothAdapter
				.getBondedDevices();
		// If there are paired devices
		if (bondedDevices.size() > 0) {
			// Loop through paired devices
			for (BluetoothDevice device : bondedDevices) {
				// Check the name of device
				if (device.getName().equals(ROBOT_BLUETOOTH_ADAPTER_NAME)) {
					bluetoothDevice = device;
				}
			}
		}

		if (bluetoothDevice != null) {
			bluetoothSocket = bluetoothDevice
					.createRfcommSocketToServiceRecord(Constants.BLUETOOTH_UUID);
		}
	}
}
