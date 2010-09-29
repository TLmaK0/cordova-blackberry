package com.phonegap.notification;

import com.phonegap.api.PluginResult;

import org.json.me.JSONArray;
import org.json.me.JSONException;

import net.rim.device.api.system.Alert;

/**
 * Vibrate Action (Singleton)
 *
 */
public class VibrateAction {
	
	private static final int DEFAULT_DURATION = 1000;
	
	/**
	 * Vibrates the device for a given amount of time.
	 *
	 * @param args JSONArray formatted as [ duration ]
	 *             duration: specifies the vibration length in milliseconds (default: 1000).
	 * @return A CommandResult object with the success or failure
	 *         state for vibrating the device.
	 */
	public static PluginResult execute(JSONArray args) {
		PluginResult result = null;
		
		if (Alert.isVibrateSupported()) {
			try {
				int duration = (args.length() >= 1) ? ((Integer)args.get(0)).intValue() : DEFAULT_DURATION;
				
				Alert.startVibrate(duration); 
			}
			catch (JSONException e) {
				result = new PluginResult(PluginResult.Status.JSONEXCEPTION, "JSONException: " + e.getMessage());
			}
			
			result = new PluginResult(PluginResult.Status.OK, "OK");
		}
		else {
			result = new PluginResult(PluginResult.Status.ILLEGALACCESSEXCEPTION, "Vibrate not supported");
		}
		
		return result;
	}
}