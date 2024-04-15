package ae.alkamul.utab_etisalat;

import static com.mswipe.uaesdk.view.MSAARHandlerActivity.MS_CPOC_ACTIVITY_INTENT_ACTION;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mswipe.uaesdk.view.MSAARHandlerActivity;
import ae.alkamul.utab_etisalat.methods_handler.make_payment.util.Constants;
import ae.alkamul.utab_etisalat.methods_handler.make_payment.models.*;
import android.app.Activity;
import java.util.HashMap;

public class MswipeCPoCARRPaymentView extends Activity {
    public static final int MS_CPOC_ACTIVITY_REQUEST_CODE = 1001;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.mswipe_arr_cpoc_view);
        initViews();
    }

    /**
     * @description All fields intilising here.
     */
    private void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            // Example of fetching a String extra
            HashMap<String, Object> extraString = (HashMap<String, Object>) intent.getSerializableExtra("paymentDetails");;

            if (extraString != null) {
                PosConfiguration configuration = new PosConfiguration().convertHashMapToModel(paymentDetails);
                processCPOCSale(configuration);
            }
            // Example of fetching an integer extra
            int extraInt = intent.getIntExtra("key_int", 0); // 0 is the default value if the key is not found
            // Do something with the integer

            // You can similarly fetch other types of extras like boolean, double, etc.
        }
    }

    private void processCPOCSale(PosConfiguration posConfiguration) {

        double amount = posConfiguration.getAmount();

        try
        {
            if (mTxtCreditAmount.length() > 0)
                amount = Double.parseDouble(removeChar(mTxtCreditAmount.getText().toString(),','));
        }
        catch (Exception ex) {
            amount = 0;
        }

        if (mTxtUserName.getText().toString().trim().startsWith("0")) {


            showDialog("The userid cannot start with 0.");
            mTxtUserName.requestFocus();
            return;
        }
        else if(mTxtUserName.getText().toString().length() < 9)
        {
            showDialog("enter valid userid");
            mTxtUserName.requestFocus();
            return;
        }
        else if(mTxtPassword.getText().toString().length() == 0)
        {
            showDialog("enter a valid password");
            mTxtPassword.requestFocus();
            return;
        }
        else if (amount < 1)
        {
            showDialog("invalid amount! minimum amount should be inr 1.00 to proceed.");
            return;
        }


        Intent intent = new Intent(MswipeCPoCARRPaymentView.this, MSAARHandlerActivity.class);
        intent.setType(MS_CPOC_ACTIVITY_INTENT_ACTION);

        intent.putExtra("username", mTxtUserName.getText().toString().trim());
        intent.putExtra("password", mTxtPassword.getText().toString().trim());

        intent.putExtra("production", production);

        double amt = Double.parseDouble(mTxtCreditAmount.getText().toString().trim());
        String totalAmt = String.format("%.2f", amt);

        Log.v("CPoC", "totalAmt" + totalAmt);


        intent.putExtra("amount", totalAmt);

        startActivityForResult(intent, MS_CPOC_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MS_CPOC_ACTIVITY_REQUEST_CODE) {

            boolean status = data.getBooleanExtra("status", false);
            String statusMessage = data.getStringExtra("statusMessage");

            Log.v("CPoC", "status" + status);

            Log.v("CPoC", "statusMessage" + statusMessage);


            if (resultCode == RESULT_OK) {
                showapproveDialog(Boolean.toString(status), data.getExtras().getString("AuthCode"),
                        data.getExtras().getString("RRNo"), statusMessage);
            } else {

                showDialog(statusMessage);
            }
        }
    }

    /**
     * @param s original string
     * @param c specific character.
     * @return
     * @description We are removing specific character form original string.
     */
    public String removeChar(String s, char c) {

        String r = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c)
                r += s.charAt(i);
        }

        return r;
    }

    public void showDialog(String message) {
        Constants.showDialog(this, "", message);
    }

    public void showapproveDialog(String status, String authcode, String rrno, String reason) {
        String msg = "";
        msg = msg + "AuthCode: " + authcode;
        msg = msg + "RRNo: " + rrno;

        Constants.showDialog(this, "", msg);
    }
}