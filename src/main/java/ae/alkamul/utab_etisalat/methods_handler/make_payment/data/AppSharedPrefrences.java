package ae.alkamul.utab_etisalat.methods_handler.make_payment.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * AppSharedPrefrences 
 * defines the data that can persisted using hte shared preferences
 * 
 */
@SuppressLint("ApplySharedPref")
public class AppSharedPrefrences 
{


	/* the shared preference store name where the persistence data get saved*/
	public static final String stSharePrefereceStore = "prefrences";

	/* singleton reference to access the share persistence data  */
	private static AppSharedPrefrences mAppSharedPreferences = null;
	private static SharedPreferences mSharedPreferences;

	private AppSharedPrefrences()
	{

	}

	/**
	 * Initialize the AppSharedPrefrences through single ton pattern
	 *
	 * @return
	 * the application shared preference
	 */
	public static AppSharedPrefrences getAppSharedPrefrencesInstace()
	{
		if(mAppSharedPreferences == null)
		{
			mAppSharedPreferences = new AppSharedPrefrences();

		}
		return mAppSharedPreferences;

	}

	/**
	 * Initialize the SharedPrefrences the allows to save the persistence data
	 *
	 * @return
	 * the application SharedPreferencese
	 */
	public SharedPreferences getSharePreferencesInstance(){

		if(mSharedPreferences == null)
		{
			mSharedPreferences = ApplicationData.getApplicationContex().getSharedPreferences(stSharePrefereceStore,
					Context.MODE_PRIVATE);
		}
		return mSharedPreferences;
	}

	/**
	 * setGatewayEnvironment
	 * sets the the details about the mswipe gateway live or labs environment to the shared preferences
	 * @param
	 * gatewayEnvironment that defined the parameter to be set to
	 *
	 */

	public  void setGatewayProduction(boolean gatewayEnvironment)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putBoolean("gatewayenvironment", gatewayEnvironment);
		edit.commit();
	}

	/**
	 * getGatewayEnvironment
	 * return the the details about the mswipe gateway live or labs environment saved to the shared preferences
	 * @return
	 * the saved mswipe gateway live or labls environment
	 */
	public  boolean isGatewayProduction()
	{
		 return getSharePreferencesInstance().getBoolean("gatewayenvironment", false);
	}

	/**
	 * setting user Id
	 */
	public  void setUserId(String value)
	{

		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("userId", value);
		edit.commit();
		//setEncryptedData("userId", value);
	}

	/**
	 * getting user Id
	 */
	public  String getUserId()
	{

		String userId = getSharePreferencesInstance().getString("userId","");
		return userId;
		//	return getEncryptedData("userId");
	}

	/**
	 * setting password
	 */
	public  void setUserPassword(String value)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("password", value);
		edit.commit();
		//	setEncryptedData("password", value);
	}

	/**
	 * getting session token
	 */
	public  String getUserPassword()
	{

		String password = getSharePreferencesInstance().getString("password","");
		return password;
		//return getEncryptedData("password");
	}

	/**
	 * setting session token
	 */
	public  void setSessionToken(String sessionToken)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("sessiontoken", sessionToken);
		edit.commit();
	}
	
	/**
	 * getting session token
	 * @return
	 */
	public  String getSessionToken()
	{	
		String sessionToken = getSharePreferencesInstance().getString("sessiontoken","");
		return sessionToken;
	}

	/**
	 * setting reference id token
	 */
	public  void setReferenceId(String refernceId)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("referenceid", refernceId);
		edit.commit();
	}

	/**
	 * getting session token
	 * @return
	 */
	public  String getMID()
	{
		String sessionToken = getSharePreferencesInstance().getString("mid","");
		return sessionToken;
	}

	/**
	 * setting reference id token
	 */
	public  void setMID(String mid)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("mid", mid);
		edit.commit();
	}
	
	/**
	 * getting reference id
	 * @return
	 */
	public  String getReferenceId()
	{	
		String referenceId = getSharePreferencesInstance().getString("referenceid","");
		return referenceId;
	}

	/**
	 * setting tip enabled
	 */
	public void setTipEnabled(boolean tipEnabled)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putBoolean("tipenabled", tipEnabled);
		edit.commit();
	}
	
	/**
	 * getting tip enabled 
	 * @return
	 */
	public boolean getTipEnabled()
	{	
		boolean tipEnabled = getSharePreferencesInstance().getBoolean("tipenabled", false);
		return tipEnabled;
	}
	
	
	/**
	 * setting currency denominator
	 */
	public  void setCurrencyCode(String currencyCode)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("currenyCode", currencyCode);
		edit.commit();
	}
	
	/**
	 * getting currency denominator
	 * @return
	 */
	public  String getCurrencyCode()
	{	
		String currencyCode = getSharePreferencesInstance().getString("currenyCode","");
		return currencyCode;
	}
	
	
	/**
	 * setting convenience percentage
	 */
	public  void setConveniencePercentage(float conveniencePercentage)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putFloat("conveniencePercentage", conveniencePercentage);
		edit.commit();
	}
	
	/**
	 * getting convenience percentage
	 * @return
	 */
	public float getConveniencePercentage()
	{	
		float conveniencePercentage = getSharePreferencesInstance().getFloat("conveniencePercentage",0.0f);
		return conveniencePercentage;
	}
	
	/**
	 * setting servicePercentageOnConvenience
	 */
	public  void setServicePercentageOnConvenience(float servicePercentageOnConvenience)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putFloat("servicePercentageOnConvenience", servicePercentageOnConvenience);
		edit.commit();
	}
	
	/**
	 * getting servicePercentageOnConvenience percentage
	 * @return
	 */
	public  float getServicePercentageOnConvenience()
	{	
		float servicePercentageOnConvenience = getSharePreferencesInstance().getFloat("servicePercentageOnConvenience",0.0f);
		return servicePercentageOnConvenience;
	}


	/**
	 * setting amount
	 */
	public  void setLastTrxAmount(String amount)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("amount", amount);
		edit.commit();
	}

	/**
	 * getting amount
	 * @return
	 */
	public  String getLastTrxAmount()
	{
		String amount = getSharePreferencesInstance().getString("amount","");
		return amount;
	}

	/**
	 * setting card number
	 */
	public  void setLastTrxCardLastFourDigits(String lastfourdigits){
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("lastfourdigits", lastfourdigits);
		edit.commit();
	}

	/**
	 * getting card number
	 * @return
	 */
	public  String getLastTrxCardLastFourDigits(){
		String lastfourdigits = getSharePreferencesInstance().getString("lastfourdigits","");
		return lastfourdigits;
	}


	/**
	 * setting ksn
	 */
	public  void setLastTrxKSN(String ksn){
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("ksn", ksn);
		edit.commit();
	}

	/**
	 * getting ksn
	 * @return
	 */
	public  String getLastTrxKSN(){
		String ksn = getSharePreferencesInstance().getString("ksn","");
		return ksn;
	}

	/**
	 * getting reference id
	 * @return
	 */
	public  boolean isPinBypassEnabled()
	{
		return getSharePreferencesInstance().getBoolean("pinbypass",false);
	}

	/**
	 * setting reference id token
	 */
	public  void setPinBypass(boolean pinBypass)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putBoolean("pinbypass", pinBypass);
		edit.commit();
	}


	public  void setCustCode(String custCode)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("CustCode", custCode);
		edit.commit();
	}

	/**
	 * getting currency denominator
	 * @return
	 */
	public  String getCustCode()
	{
		String custCode = getSharePreferencesInstance().getString("CustCode", "");
		return custCode;
	}


	/**
	 * setting tip enabled
	 */
	public void setReceiptEnabled(boolean receiptEnabled)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putBoolean("receiptEnabled", receiptEnabled);
		edit.commit();
	}

	/**
	 * getting tip enabled
	 * @return
	 */
	public boolean isReceiptEnabled()
	{
		return getSharePreferencesInstance().getBoolean("receiptEnabled", false);
	}

	public void setPrinterSupportRequired(boolean printerSupport) {

		Editor edit = getSharePreferencesInstance().edit();
		edit.putBoolean("printerSupport", printerSupport);
		edit.commit();
	}

	public boolean isPrinterSupportRequired(){
		return getSharePreferencesInstance().getBoolean("printerSupport", false);
	}

	public  void setPayByLinkBank(String settlementBank)
	{
		Editor edit = getSharePreferencesInstance().edit();
		edit.putString("SettlementBank", settlementBank);
		edit.commit();
	}

	/**
	 * getting currency denominator
	 * @return
	 */
	public  String getPayByLinkBank()
	{
		String settlementBank = getSharePreferencesInstance().getString("SettlementBank", "");
		return settlementBank;
	}

}
