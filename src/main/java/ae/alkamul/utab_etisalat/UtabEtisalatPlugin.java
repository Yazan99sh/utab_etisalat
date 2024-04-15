package ae.alkamul.utab_etisalat;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import java.util.HashMap;

import ae.alkamul.utab_etisalat.methods_handler.make_payment.models.PosConfiguration;
import ae.alkamul.utab_etisalat.methods_handler.make_payment.controller.MswipeCPoCARRPaymentView;
/**
 * UtabEtisalatPlugin
 */
public class UtabEtisalatPlugin implements FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    Context context;
    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        context = flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "utab_etisalat");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("makePayment")) {
            // Get the payment details from the call arguments
            HashMap<String, Object> arguments = call.argument("paymentDetails");
            // Convert the HashMap to a model class
            Intent intent = new Intent(context, MswipeCPoCARRPaymentView.class);
            // Pass payment details to the PaymentActivity if needed
            intent.putExtra("paymentDetails", arguments);
            // Start the activity
            context.startActivity(intent);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}
