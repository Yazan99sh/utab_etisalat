package ae.alkamul.utab_etisalat.methods_handler.make_payment.data;

import android.content.Context;
import android.graphics.Typeface;

import androidx.multidex.MultiDexApplication;


public class ApplicationData extends MultiDexApplication
{
	public final static boolean IS_DEBUGGING_ON = false;
	public final static String packName = "MSWIPE_SDK";
    public static final String SERVER_NAME = "Mswipe";
    
    /*The application global attributes */
    public static final int PhoneNoLength = 9;
    public static final String Currency_Code = "AED.";
    public static final String smsCode = "+91";
    public static final String mTipRequired = "false";

    public Typeface font = null;
    public Typeface fontBold = null;
    public Typeface fontMedium = null;
    public Typeface fontLight = null;
    public Typeface fontLightItalic = null;

    public static String mCurrency = "AED";

    public static final int mCashAtPosAmountLimit = 2000;



    /*The application context statically available to the entire application */
    private static Context mContext = null;

    private static ApplicationData mApplicationData = null;


    public static final int font_size_amount_small = 40 ;
    public static final int font_size_amount_normal = 60;

    public static final int REQUEST_THRESHOLD_TIME = 2000;
	
    
    @Override
	public void onCreate()
	{
    	// TODO Auto-generated method stub
		super.onCreate();
		mContext = getApplicationContext();

        font = Typeface.createFromAsset(mContext.getAssets(),"fonts/english/roboto/Roboto-Regular.ttf");
        fontBold = Typeface.createFromAsset(mContext.getAssets(),"fonts/english/roboto/Roboto-Bold.ttf");
        fontMedium = Typeface.createFromAsset(mContext.getAssets(),"fonts/english/roboto/Roboto-Medium.ttf");
        fontLight = Typeface.createFromAsset(mContext.getAssets(),"fonts/english/roboto/Roboto-Light.ttf");
        fontLightItalic = Typeface.createFromAsset(mContext.getAssets(),"fonts/english/roboto/Roboto-LightItalic.ttf");

	}
    
    /**
     * The  context of the application that can be accesses
     * @param  
     * 
     * @return 
     * the application context
     */
    public static Context getApplicationContex()
    {
    	return mContext;
    	
    }

    /**
     * single instance.
     * @return
     */
    public static ApplicationData getApplicationDataSharedInstance()
    {
        if(mApplicationData == null)
        {
            mApplicationData = new ApplicationData();
        }
        return mApplicationData;
    }


}





