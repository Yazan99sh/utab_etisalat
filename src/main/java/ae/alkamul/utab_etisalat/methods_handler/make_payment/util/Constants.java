package ae.alkamul.utab_etisalat.methods_handler.make_payment.util;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ae.alkamul.utab_etisalat.R;
import ae.alkamul.utab_etisalat.methods_handler.make_payment.data.ApplicationData;


public class Constants 
{

	public static enum CUSTOM_DLG_TYPE 
	{
		CUSTOM_DLG_TYPE_SHOW_DLG_INFO,
		CUSTOM_DLG_TYPE_RETURN_DLG_INFO,
		CUSTOM_DLG_TYPE_RETURN_DLG_CONFIRMATION,
	}



    public static String  getCurrentDateWithFormate(String aDateFormat)
    {
        String formatedDate = "";

        final Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(aDateFormat);
        formatedDate = df.format(c.getTime());

        return formatedDate.toLowerCase();

    }

//    public static Dialog showDeviceConnectionDialog(Context context, String title) {
//        Dialog dialog = new Dialog(context, R.style.styleCustDlg);
//        dialog.setContentView(R.layout.customdeviceconnectiondlg);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        dialog.setCancelable(false);
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.customdeviceconnectiondlgTXT_title);
//        txttitle.setText(title);
//        return dialog;
//
//    }

	public static void showDialog(Context context, String title, String msg) {
    	
		Dialog dlg = showDialog(context, title, msg,
				CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_SHOW_DLG_INFO, "Ok","Cancel");
		dlg.show();
    }

    public static Dialog showDialog(Context context, String title, String msg, CUSTOM_DLG_TYPE customDlgType) {

		Dialog dlg = showDialog(context, title, msg,
				customDlgType, "Ok","Cancel");


		return dlg;
    }


    public static Dialog showDialog(Context context, String title, String msg,
    		CUSTOM_DLG_TYPE customDlgType, String firstBtnText, String secondBtnText)
    {
    	
    	final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.customdlg);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setCancelable(true);

       
        // set the title
        TextView txttitle = (TextView) dialog.findViewById(R.id.customdlg_TXT_title);
        txttitle.setText(title);
        

        // to set the message
        TextView message = (TextView) dialog.findViewById(R.id.customdlg_TXT_Info);
        message.setText(msg);
      
        Button yes = (Button) dialog.findViewById(R.id.customdlg_BTN_yes);
        yes.setText(firstBtnText);
      
        Button no = (Button) dialog.findViewById(R.id.customdlg_BTN_no);
        no.setText(secondBtnText);
        
        if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_SHOW_DLG_INFO) {
            no.setVisibility(View.GONE);
            yes.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
        }
        else if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_RETURN_DLG_INFO) {
        	no.setVisibility(View.GONE);
        }
        else if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_RETURN_DLG_CONFIRMATION) {
        	
        }
        
        return dialog;

    }


//    public static void showActivityDialog(Context context, String title, String msg) {
//
//        final Dialog dialog = showActivityDialog(context, title, msg, CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_SHOW_DLG_INFO,
//                context.getString(R.string.ok),
//                context.getString(R.string.cancel));
//
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey (DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK &&
//                        event.getAction() == KeyEvent.ACTION_UP &&
//                        !event.isCanceled()) {
//
//                    dialog.dismiss();
//                    return true;
//                }
//                return false;
//            }
//        });
//        dialog.show();
//    }


//    public static Dialog showActivityDialog(final Context context, String title, String msg,
//                                    CUSTOM_DLG_TYPE customDlgType, String firstBtnText, String secondBtnText)
//    {
//
//        final Dialog dialog = new Dialog(context, R.style.StyleCustomDlg_white);
//
//
//        dialog.setContentView(R.layout.activity_customdlg);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//
//        ApplicationData applicationData = ApplicationData.getApplicationDataSharedInstance();
//
//        // set the title
//        TextView txtTitle = (TextView) dialog.findViewById(R.id.customdlg_sdk_TXT_title);
//        txtTitle.setText(title);
//        txtTitle.setTypeface(applicationData.font);
//
//        // to set the message
//        TextView message = (TextView) dialog.findViewById(R.id.customdlg_sdk_TXT_Info);
//        message.setText(msg);
//        message.setTypeface(applicationData.font);
//
//        RelativeLayout yes = (RelativeLayout) dialog.findViewById(R.id.customdlg_sdk_RLT_yes);
//        final  RelativeLayout no = (RelativeLayout) dialog.findViewById(R.id.customdlg_sdk_RLT_no);
//        ((Button) dialog.findViewById(R.id.customdlg_sdk_BTN_yes)).setText(firstBtnText);
//        ((Button) dialog.findViewById(R.id.customdlg_sdk_BTN_no)).setText(secondBtnText);
//
//        if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_SHOW_DLG_INFO) {
//
//            ((RelativeLayout)dialog.findViewById(R.id.customdlg_sdk_RLT_no)).setVisibility(View.GONE);
//            ((View) dialog.findViewById(R.id.customdlg_sdk_view)).setVisibility(View.GONE);
//            ((Button) dialog.findViewById(R.id.customdlg_sdk_BTN_yes)).setText(firstBtnText);
//            yes.setOnClickListener(new OnClickListener() {
//
//                public void onClick(View v) {
//                    dialog.dismiss();
//
//                }
//            });
//        }
//        else if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_RETURN_DLG_INFO) {
//
//            ((RelativeLayout)dialog.findViewById(R.id.customdlg_sdk_RLT_no)).setVisibility(View.GONE);
//            ((View) dialog.findViewById(R.id.customdlg_sdk_view)).setVisibility(View.GONE);
//
//        }
//        else if (customDlgType == CUSTOM_DLG_TYPE.CUSTOM_DLG_TYPE_RETURN_DLG_CONFIRMATION) {
//
//            ((RelativeLayout)dialog.findViewById(R.id.customdlg_sdk_RLT_no)).setVisibility(View.VISIBLE);
//
//            no.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    dialog.dismiss();
//                }
//            });
//
//        }
//
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        dialog.show();
//        return dialog;
//
//    }


//    public static Dialog shwoAppCustionDialog(Context context, String title, String firstBtnText, String secondBtnText) {
//
//        Dialog dialog = new Dialog(context,R.style.styleCustDlg);
//       /* dialog.setContentView(R.layout.application_cust_dialog);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        dialog.setCancelable(true);
//        ApplicationData applicationData = ApplicationData.getApplicationDataSharedInstance();
//
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.application_customdlg_TXT_Info);
//        txttitle.setText(title);
//        txttitle.setTypeface(applicationData.fontBold);
//
//        Button firstBtn = (Button) dialog.findViewById(R.id.application_customdlg_BTN_yes);
//        firstBtn.setText(firstBtnText);
//        firstBtn.setTypeface(applicationData.font);
//
//        Button secondBtn = (Button) dialog.findViewById(R.id.application_customdlg_BTN_no);
//        secondBtn.setText(secondBtnText);
//        secondBtn.setTypeface(applicationData.font);
//
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);*/
//
//        return dialog;
//
//    }

//    public static Dialog showDialogPin(Context context, String title, String msg,
//                                        String firstBtnText, String secondBtnText, String thirdText) {
//         Dialog dialog = new Dialog(context, R.style.styleCustDlg);
//        dialog.setContentView(R.layout.custompindlg);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.custompindlg_TXT_title);
//        txttitle.setText(title);
//
//
//        // to set the message
//        TextView message = (TextView) dialog.findViewById(R.id.custompindlg_TXT_info);
//        message.setText(msg);
//
//        Button accept = (Button) dialog.findViewById(R.id.custompindlg_BTN_accept);
//        accept.setText(firstBtnText);
//
//
//        Button bypass = (Button) dialog.findViewById(R.id.custompindlg_BTN_bypass);
//        bypass.setText(secondBtnText);
//
//        Button no = (Button) dialog.findViewById(R.id.custompindlg_BTN_no);
//        no.setText(thirdText);
//        if (thirdText.length() == 0) {
//            no.setVisibility(View.GONE);
//        }
//        return dialog;
//
//    }

//    public static Dialog showAppCustomDialog(Context context, String title) {
//        Dialog dialog = new Dialog(context, R.style.styleCustDlg);
//        dialog.setContentView(R.layout.customappdlg);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        dialog.setCancelable(true);
//        // set the title
//        TextView txttitle = (TextView) dialog.findViewById(R.id.customapplicationdlg_TXT_title);
//        txttitle.setText(title);
//
//        return dialog;
//
//    }

    public final static boolean isValidEmail(CharSequence target) 
    {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static void parseXml(String xmlString,String[][] strTags ) throws Exception {

        XmlPullParser parser = Xml.newPullParser();

        try {
            parser.setInput(new StringReader(xmlString));
            int eventType = XmlPullParser.START_TAG;
            boolean leave = false;

            while (!leave && eventType != XmlPullParser.END_DOCUMENT) {
                eventType = parser.next();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String xmlTag = parser.getName().toString();
                        for (int iTagIndex = 0; iTagIndex < strTags.length; iTagIndex++) {
                            if (strTags[iTagIndex][0].equals(xmlTag)) {
                                eventType = parser.next();
                                if (eventType == XmlPullParser.TEXT) {
                                    String xmlText = parser.getText();
                                    //Log.v(ApplicationData.packName, "Constatnts " + xmlTag + " message " + xmlText);

                                    strTags[iTagIndex][1] = ( xmlText == null ? "" : xmlText);// store the key
                                } else if (eventType == XmlPullParser.END_TAG) {
                                    //Log.v(ApplicationData.packName, "Constatnts " + xmlTag + " message is blank");
                                    strTags[iTagIndex][1]  =  "";
                                }
                                break;
                            }
                        }
                        break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (parser != null) {
                parser = null;
            }
        }

    }


    /**
     *
     * getDateWithFormate
     * returns the formated date from the required formate
     *
     *
     * @return
     * return the formated dare
     *
     */

    public static String getAmountWithComma(String amount){

        String stAmt = amount;
        String stOrg = removeChar(stAmt, '.');
        stOrg = removeChar(stOrg, ',');
        stOrg = removeChar(stOrg, '-');

        if(stOrg.indexOf("0") == 0)
        {
            if(stOrg.equals("0"))
                stOrg = stOrg.substring(0);
            else if(stOrg.equals("00000"))
                stOrg = stOrg.substring(2);
            else
                stOrg = stOrg.substring(1);
        }

        int ilen = stOrg.length();

        if (ilen == 2 || ilen ==1)
        {
            stOrg = "0." + stOrg;
        }
        else if (ilen > 2)
        {
            stOrg = stOrg.substring(0, ilen - 2) + "." + stOrg.substring(ilen - 2, ilen);
        }

        ilen = stOrg.length();
        if (ilen > 6)
        {
            stOrg = stOrg.substring(0, ilen - 6)+","+ stOrg.substring(ilen - 6, ilen);
        }

        ilen = stOrg.length();
        if (ilen > 9)
        {
            stOrg = stOrg.substring(0, ilen - 9)+","+ stOrg.substring(ilen - 9, ilen);
        }

        if (!stOrg.equals(stAmt))
        {
            return stOrg;
        }

        return stOrg;
    }

    public static String removeChar(String s, char c) {

        String r = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) r += s.charAt(i);
        }

        return r;
    }



    public static String  getDateWithFormate(String aDate, String aParseFormat, String aDisplayFormat)
    {
        String formatedDate = "";

        try {

            SimpleDateFormat displayFormat = new SimpleDateFormat(aDisplayFormat);
            SimpleDateFormat parseFormat = new SimpleDateFormat(aParseFormat);
            Date date = parseFormat.parse(aDate);
            formatedDate = displayFormat.format(date);

        } catch (final Exception e) {
            formatedDate = aDate;
            e.printStackTrace();

        }

        return formatedDate.toLowerCase();

    }


    public static ArrayList<String> splitStringByTwoChars(String strText, char delimiter) {
        ArrayList<String> extractStrings = new ArrayList<String>();
        try {
            StringBuffer extractString = new StringBuffer();
            for (int iCtr = 0; iCtr < strText.length(); iCtr++) {
                char ch = strText.charAt(iCtr);
                if (ch == delimiter && iCtr + 1 < strText.length() && strText.charAt(iCtr + 1) == delimiter) {
                    iCtr++;
                    extractStrings.add(extractString.toString());
                    extractString.delete(0, extractString.length());
                } else {
                    extractString.append(ch);
                }

            }
            extractStrings.add(extractString.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        return extractStrings;
    }

    // change password and Login
    public static final String PWD_DIALOG_MSG = "Change Password";
    public static final String PWD_ERROR_INVALIDPWD = "Invalid Password! Cannot be blank.";
    public static final String PWD_ERROR_INVALIDPWDLENGTH = "Minimum length of the password should be 6 characters.";
    public static final String PWD_ERROR_INVALIDPWDMAXLENGTH = "Length of the password should be between 6 and 10 characters.";

    public static final String PWD_ERROR_INVALIDPWDNEWLENGTH = "Minimum length of the new password should be 6 characters.";
    public static final String PWD_ERROR_INVALIDPWDMAXNEWLENGTH = "Length of the new password should be between 6 and 10 characters.";
    public static final String PWD_ERROR_INVALIDPWDRETYPELENGTH = "Minimum length of the re-entered password should be 6 characters.";
    public static final String PWD_ERROR_INVALIDNEWANDRETYPE = "New passwords do not match.";
    public static final String PWD_CHANGEPWD_CONFIRMATION = "Would you like to change the password?";
    public static final String PWD_ERROR_PROCESSIING_DATA = "Error in processing your change password request.";
 // cash sale
    public static final String CASHSALE_DIALOG_MSG = "Cash Sale";
    // bank sale
    public static final String BANKSALE_DIALOG_MSG = "Bank Sale";
    public static final String CASHSALE_ERROR_invalidChequeNO = "Required length of the Cheque no. is 6 digits";
    public static final String BANKSALE_ALERT_AMOUNTMSG = "The total  bank sale amount is %s ";

    // card sale
    public static final String CARDSALE_DIALOG_MSG = "Card Sale";
    public static final String CARDSALE_ALERT_AMOUNTMSG = "The total card sale amount is %s ";
    public static final String CARDSALE_ERROR_INVALIDAMT = "Invalid amount! Minimum amount should be " + "AED" + " 1.00 to proceed.";
    public static final String CARDSALE_ERROR_INVALIDCARDDIGITS = "Invalid last 4 digits.";
    public static final String CARDSALE_ERROR_salecash_max= "Invalid Sale cash amount!  Amount should be between " + "AED" + " 1.00 to 1000.00 to proceed.";
    public static final String CARDSALE_ERROR_salecash= "amount or sale cash is mandatory for this transaction";
    

    public static final String CASHSALE_ALERT_AMOUNTMSG = "The total cash sale amount is %s ";
    
    public static final String CARDSALE_ALERT_swiperAMOUNTMSG = "Total amount of this transaction \nis %s ";
    public static final String CARDSALE_ERROR_mobilenolen = "Please enter valied mobile number.";
    public static final String CARDSALE_ERROR_mobileformat = "The mobile number cannot start with 0.";
    public static final String CARDSALE_ERROR_receiptvalidation = "Invoice No. can not be blank.";
    public static final String CARDSALE_ERROR_receiptmandatory = "Receipt mandatory for this transaction, please un check the field to proceed";


    public static final String CARDSALE_ERROR_email = "Invalid e-mail address.";
    public static final String CARDSALE_ERROR_LstFourDigitsNotMatched = "Last 4 digits don't match, bad card.";
    public static final String CARDSALE_ERROR_PROCESSIING_DATA = "Error in processing Card Sale.";

    public static final String CARDSALE_AMEX_Validation = "Invalid Amex card security code.";

    public static final String CARDSALE_Sign_Validation = "Receipt needs to be signed to proceed.";
    public static final String CARDSALE_Sign_ERROR_PROCESSIING_DATA = "Error in uploading the receipt to " + ApplicationData.SERVER_NAME + " server.";
    public static final String CARDSALE_Sign_SUCCESS_Msg = "Receipt successfully uploaded to " + ApplicationData.SERVER_NAME + " server.";

    public static final String CARDSALE_AUTO_REVERSAL = "Auto Reversal successfull.";
    public static final String CARDSALE_ERROR_FO35 = "Error in processing Card Sale.";


    public static final String CARDSALE_Device_Connect_Msg = "Device not connected, please make sure the Device is switched on.";
    public static final String CARDSALE_Device_Connecting_Msg = "Connecting to  device, if its taking longer then usual, please restart the Device and try re-connecting.";
    // for Card history
    public static final String CARDHISTORYLIST_DIALOG_MSG = "History";
    public static final String CARDHISTORYLIST_GET_HISTROYDATA = "Fetch the latest sale history?";
    public static final String CARDHISTORYLIST_ERROR_PROCESSIING_DATA = "Error in processing sale history, please try again.";
    public static final String CARDHISTORYLIST_ERROR_NODATA_FOUND = "No sale history found on the " + ApplicationData.SERVER_NAME + " server.";

    // last transaction
    public static final String LSTTRXST_DIALOG_MSG = "Last Tx Status";
    public static final String LSTTRXST_ERROR_FETCHING_DATA = "Error in fetching last tx details.";
    public static final String LSTTRXST_ERROR_Processing_DATA = "Error in processing the last transaction request.";
    public static final String LSTTRXST_Success_msg = "The receipt was successfully resent.";
    public static final String LSTTRXST_NODATAFOUND = "No  transaction found on " + ApplicationData.SERVER_NAME + " server.";
    // Login
    public static final String LOGIN_DIALOG_MSG = "Login";
    public static final String LOGIN_ERROR_ValidUserMsg = "Please enter a valid User Id and Password.";
    public static final String LOGIN_ERROR_Processing_DATA = "Unable to login, please try again.";

    // void
    public static final String VOID_DIALOG_MSG = "Void Sale";
    public static final String VOID_ERROR_Processing_DATA = "Error in processing the void sale request.";
    public static final String VOID_ERROR_Processing_FLAG = "Error in updating the void sale flag.";
    public static final String VOID_ALERT_AMOUNTMSG = "Proceed to void card sale of %s %s for the card ending with last 4 digits %s?";
    public static final String VOID_ALERT_FORVOID = "Would you like to VOID the selected transaction dated %s of %s %s for the card with the last 4 digits %s?";

    public static final String VOID_NODATAFOUND = "No matching transaction found on " + ApplicationData.SERVER_NAME + " server.";
  //Pre auth completion
    public static final String PREAUTHVOID_DIALOG_MSG = "PreAuth Completion";
    public static final String PREAUTHVOID_ERROR_Processing_DATA = "Error in processing the PreAuth sale request.";
    public static final String PREAUTHVOID_ERROR_Processing_FLAG = "Error in updating the PreAuth sale flag.";
    public static final String PREAUTHVOID_ALERT_AMOUNTMSG = "Proceed to Pre auth compeltion  of %s %s for the card ending with last 4 digits %s?";
   // 
    public static final String PREAUTHVOID_ALERT_FORVOID = "Would you like to use Preauth Completion for the selected transaction dated %s of %s %s for the card with the last 4 digits %s?";
    public static final String PREAUTH_ERROR_NODATA_FOUND = "No PreAuth sale data found on the " + ApplicationData.SERVER_NAME + " server.";
    public static final String PREAUTH_ERROR_INVALIDAMT = "Invalid amount ! PreAuth completion amount cannot exceed the PreAuth sale amount.";


//Refund trx
      public static final String REFUNDVOID_DIALOG_MSG = "Refund";
      public static final String REFUNDVOID_ERROR_Processing_DATA = "Error in processing the refund sale request.";
      public static final String REFUNDVOID_ERROR_Processing_FLAG = "Error in updating the refund sale flag.";
      public static final String REFUNDVOID_ALERT_AMOUNTMSG = "Proceed to Refund Trx  of %s %s for the card ending with last 4 digits %s?";
      public static final String REFUNDVOID_ALERT_FORVOID = "Would you like to Refund the selected transaction dated %s of %s %s for the card with the last 4 digits %s?";

      public static final String DEVICEINFO_DIALOG_MSG = "Device Info";

      //Error
      public static final String  MSWIPE_ERROR_INENRYPTINGKEY ="Data encryption error.";
}
