package com.googlecode.catdroid.android.mock;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;

public class CatDroidMock extends Activity {
	
	/**  */
	public static final int REQUEST_ENABLE_BT = 1;
	
	/**  */
	public static final String SERVER_BLUETOOTH_ADAPTER_NAME = "CATSERVER";

	/**  */
	private BluetoothAdapter bluetoothAdapter = null;

	/**  */
	private BluetoothDevice catDroidBluetoothDevice = null;

	/**
	 * 
	 */
	public CatDroidMock() {
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	private void checkRequirement() {
		if (bluetoothAdapter == null) {
			// Device does not support Bluetooth
		}

		if (!bluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
	}
	
	private void searchServer()
	{
		Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
		// If there are paired devices
		if (bondedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : bondedDevices) {
		    	// Check the name of device
		    	if(device.getName().equals(SERVER_BLUETOOTH_ADAPTER_NAME))
		    	{
		    		catDroidBluetoothDevice = device;
		    	}
		    }
		}
	}
}