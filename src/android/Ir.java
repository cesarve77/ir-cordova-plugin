package cesarve77.irplugin.Ir;

import android.hardware.ConsumerIrManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.Integer;

public class Ir extends CordovaPlugin {
    public static final String ACTION_TRANSMIT_IR_CODE = "transmit";


    @Override
    public boolean execute(String action, JSONArray jsonArgs, CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_SEND_IR_CODE.equals(action)) {

                JSONObject args = jsonArgs.getJSONObject(0);
                Integer frequency = args.getInt("frequency");
                Integer[] signal = args.getInt("signal");

                cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        irService = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
                        Log.d("transmit", "lets go");

                        if (Build.VERSION.SDK_INT == ANDROID_KITKAT_SDK) {
                            intlastIdx = Build.VERSION.RELEASE.lastIndexOf(".");
                            int VERSION_MR = Integer.valueOf(Build.VERSION.RELEASE.substring(lastIdx + 1));
                            if (VERSION_MR < 3) {
                                // Before version of Android 4.4.2
                                irService.transmit(frequency, signal);
                            } else {
                                // Later version of Android 4.4.3
                                irService.transmit(frequency, signal);
                            }
                        }
                        callbackContext.success();
                    }
                });




            }
            callbackContext.success();
            return true;
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
            return false;
        }

    }
}

