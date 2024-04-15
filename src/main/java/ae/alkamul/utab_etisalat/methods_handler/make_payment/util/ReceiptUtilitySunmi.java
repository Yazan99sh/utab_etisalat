//package ae.alkamul.utab_etisalat.methods_handler.make_payment.util;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
//import com.mswipe.uaesdk.data.ReceiptData;
//import com.mswipe.uaesdk.device.MSPrinterController;
//import com.mswipe.uaesdk.device.MSPrinterController.Align;
//import com.mswipe.uaesdk.util.ScalingUtilities;
//
//import java.io.ByteArrayOutputStream;
//
//public class ReceiptUtilitySunmi {
//
//    private Context context = null;
//
//    private ReceiptData mReceiptDataModel;
//    Bitmap bitmap;
//    boolean isPrintSignatureRequired;
//    boolean isCustomerCopy;
//    boolean isDuplicatePrint;
//
//    String line = "--------------------------------------";
//
//    MSPrinterController sunmiPrintHelper;
//
//    public void setPrintHelper(MSPrinterController sunmiPrintHelper) {
//
//        if (ApplicationData.IS_DEBUGGING_ON)
//            Logs.v(ApplicationData.packName, "", true, true);
//
//        this.sunmiPrintHelper = sunmiPrintHelper;
//
//    }
//
//    public ReceiptUtilitySunmi(Context context, ReceiptData aReceiptDataModel, Bitmap bitmap, boolean isPrintSignatureRequired, boolean isCustomerCopy, boolean isDuplicatePrint) {
//
//        this.context = context;
//        this.mReceiptDataModel = aReceiptDataModel;
//        this.bitmap = bitmap;
//        this.isPrintSignatureRequired = isPrintSignatureRequired;
//        this.isCustomerCopy = isCustomerCopy;
//        this.isDuplicatePrint = isDuplicatePrint;
//
//    }
//
//    public void printCardSaleReceipt() {
//
//
//        if (ApplicationData.IS_DEBUGGING_ON)
//            Logs.v(ApplicationData.packName, "genReceipt4", true, true);
//
//
//        String strTipLbl = "Tip Amount:    ";
//        if (mReceiptDataModel.isConvenceFeeEnable.equalsIgnoreCase("true")) {
//            strTipLbl = "Convenience Fee:";
//        }
//
//        if (ApplicationData.IS_DEBUGGING_ON)
//            Logs.v(ApplicationData.packName, " isConvenceFeeEnable " + mReceiptDataModel.isConvenceFeeEnable + strTipLbl, true, true);
//
//        double tipamount = 0.00;
//        if (mReceiptDataModel.tipAmount.length() > 0) {
//            tipamount = Double.parseDouble(mReceiptDataModel.tipAmount);
//        }
//
//        if (ApplicationData.IS_DEBUGGING_ON)
//            Logs.v(ApplicationData.packName, "tipamount" + tipamount, true, true);
//
//        try {
//
//            sunmiPrintHelper.initPrinter();
//            sunmiPrintHelper.setFontSize(20);
//            sunmiPrintHelper.setLineSpacing(1);
//            sunmiPrintHelper.setBold(true);
//            sunmiPrintHelper.setAlige(Align.CENTER);
//
//            getHeaderLogo();
//
//            //getBankLogo(mReceiptDataModel.bankLogoName);
//
//            sunmiPrintHelper.printNewLine();
//            sunmiPrintHelper.setAlige(Align.CENTER);
//
//            if (mReceiptDataModel.bankLogoName != null) {
//                sunmiPrintHelper.printText(mReceiptDataModel.bankLogoName);
//            }
//            sunmiPrintHelper.printText(mReceiptDataModel.merchantName);
//
//            if (ApplicationData.IS_DEBUGGING_ON)
//                Logs.v(ApplicationData.packName, "address  " + mReceiptDataModel.merchantAdd, true, true);
//
//
//            String splitStr = "<br />";
//
//            if (mReceiptDataModel.merchantAdd.contains("\n")) {
//                splitStr = "\n";
//            }
//
//
//            for (String add : mReceiptDataModel.merchantAdd.split(splitStr)) {
//
//                if (ApplicationData.IS_DEBUGGING_ON)
//                    Logs.v(ApplicationData.packName, "address  " + add, true, true);
//
//                sunmiPrintHelper.printText(add);
//            }
//
//            sunmiPrintHelper.setAlige(Align.CENTER);
//
//            sunmiPrintHelper.printText(line);
//
//            String dateTime = mReceiptDataModel.dateTime;
//
//            if(mReceiptDataModel.dateTime != null) {
//                String tempDate = removeampm(mReceiptDataModel.dateTime);
//                dateTime = Constants.getDateWithFormate(tempDate, "dd-MMMM-yyyy HH:mm", "dd MMM yyyy hh:mm a");
//            }
//
//            sunmiPrintHelper.setAlige(Align.LEFT);
//            sunmiPrintHelper.printText("Date/Time: " + dateTime.toUpperCase());
//
//
//            String mMidTid = "";
//            if(mReceiptDataModel.mId != null && mReceiptDataModel.mId.length() > 0) {
//                mMidTid = "MID:" + mReceiptDataModel.mId;
//            }
//            if(mReceiptDataModel.tId != null && mReceiptDataModel.tId.length() > 0) {
//                mMidTid = mMidTid + " " + "TID:" + mReceiptDataModel.tId;
//            }
//            if(mMidTid.length() > 0) {
//                sunmiPrintHelper.printText(mMidTid);
//            }
//
//
//            String mBatchInvoide = "";
//            if(mReceiptDataModel.trxBatchNo != null && mReceiptDataModel.trxBatchNo.length() > 0) {
//                mBatchInvoide = "Batch no:" + mReceiptDataModel.trxBatchNo + " ";
//            }
//            if(mReceiptDataModel.invoiceNo != null && mReceiptDataModel.invoiceNo.length() > 0) {
//                mBatchInvoide = mBatchInvoide + "Inv. no:" + mReceiptDataModel.invoiceNo.replace("\n", "");
//            }
//            if(mBatchInvoide.length() > 0) {
//                sunmiPrintHelper.printText(mBatchInvoide);
//            }
//
//
//            if(mReceiptDataModel.refNo != null && mReceiptDataModel.refNo.length() > 0) {
//                sunmiPrintHelper.printText("Ref no."+ mReceiptDataModel.refNo);
//            }
//
//            if (mReceiptDataModel.cashAmount.length() > 0 && mReceiptDataModel.tipAmount.length() <= 0 && mReceiptDataModel.baseAmount.length() <= 0) {
//
//            }
//            else{
//
//                sunmiPrintHelper.setAlige(Align.CENTER);
//                sunmiPrintHelper.printText(mReceiptDataModel.saleType.toUpperCase());
//
//            }
//
//            sunmiPrintHelper.setAlige(Align.LEFT);
//            sunmiPrintHelper.printText("Card no.:" + mReceiptDataModel.cardNo + " " + mReceiptDataModel.trxType);
//            sunmiPrintHelper.printText("Card type:" + mReceiptDataModel.cardType + "  Exp dt:" + "xx/xx");
//
//            String totalAmount = "";
//            if(mReceiptDataModel.totalAmount != null && mReceiptDataModel.totalAmount.length() > 0){
//                totalAmount = Constants.getAmountWithComma(String.format("%.2f", Double.parseDouble(mReceiptDataModel.totalAmount)));
//            }
//
//            String baseAmount = "";
//            if(mReceiptDataModel.baseAmount != null && mReceiptDataModel.baseAmount.length() > 0){
//                baseAmount = Constants.getAmountWithComma(String.format("%.2f", Double.parseDouble(mReceiptDataModel.baseAmount)));
//            }
//
//            String cashAmount = "";
//            if(mReceiptDataModel.cashAmount != null && mReceiptDataModel.cashAmount.length() > 0){
//                cashAmount = Constants.getAmountWithComma(String.format("%.2f", Double.parseDouble(mReceiptDataModel.cashAmount)));
//            }
//
//            String tipAmount = "";
//            if(mReceiptDataModel.tipAmount != null && mReceiptDataModel.tipAmount.length() > 0){
//                tipAmount = Constants.getAmountWithComma(String.format("%.2f", Double.parseDouble(mReceiptDataModel.tipAmount)));
//            }
//
//
//
//            if (mReceiptDataModel.cashAmount.length() > 0 && mReceiptDataModel.baseAmount.length() > 0) {
//
//                sunmiPrintHelper.printText("Sale Amt:   " + ApplicationData.mCurrency + " " + baseAmount);
//                sunmiPrintHelper.printText("Cash withdrawal:  " + ApplicationData.mCurrency + " " + cashAmount);
//
//                if (mReceiptDataModel.tipAmount.length() > 0 && tipamount > 0) {
//
//                    sunmiPrintHelper.printText(ApplicationData.mCurrency + " " + mReceiptDataModel.tipAmount);
//                }
//
//                sunmiPrintHelper.printText(line);
//                sunmiPrintHelper.printText("Total Amount:   " + ApplicationData.mCurrency + " " + totalAmount);
//
//            } else if (mReceiptDataModel.cashAmount.length() > 0 && mReceiptDataModel.baseAmount.length() <= 0) {
//
//                if (mReceiptDataModel.tipAmount.length() > 0 && tipamount > 0) {
//
//                    sunmiPrintHelper.printText("Sale Amt:   " + ApplicationData.mCurrency + " 0.00");
//                    sunmiPrintHelper.printText("Cash withdrawal:  " + ApplicationData.mCurrency + " " + cashAmount);
//                    sunmiPrintHelper.printText(strTipLbl + ApplicationData.mCurrency + " " + tipAmount);
//                    sunmiPrintHelper.printText(line);
//                    sunmiPrintHelper.printText("Total Amount:   " + ApplicationData.mCurrency + " " + totalAmount);
//
//                } else {
//
//                    sunmiPrintHelper.printText("Cash withdrawal:  " + ApplicationData.mCurrency + " " + cashAmount);
//                }
//
//            } else {
//
//                if (mReceiptDataModel.tipAmount.length() > 0 && tipamount > 0) {
//
//                    sunmiPrintHelper.printText("Base Amount:    " + ApplicationData.mCurrency + " " + baseAmount);
//                    sunmiPrintHelper.printText(strTipLbl + ApplicationData.mCurrency + " " + tipAmount);
//                    sunmiPrintHelper.printText(line);
//                    sunmiPrintHelper.printText("Total Amount:   " + ApplicationData.mCurrency + " " + totalAmount);
//
//                } else {
//
//                    sunmiPrintHelper.printText("Total Amount:   " + ApplicationData.mCurrency + " " + totalAmount);
//                }
//            }
//
//            String mApprcd = "";
//            if(mReceiptDataModel.mId != null && mReceiptDataModel.mId.length() > 0) {
//                mApprcd = "Appr cd:" + mReceiptDataModel.authCode;
//            }
//            if(mReceiptDataModel.tId != null && mReceiptDataModel.tId.length() > 0) {
//                mApprcd = mApprcd + " " + "RRN:" + mReceiptDataModel.rrNo;
//            }
//
//            if(mApprcd.length() > 0) {
//                sunmiPrintHelper.printText(mApprcd);
//            }
//
//            if (mReceiptDataModel.trxType.equalsIgnoreCase("Chip")) {
//
//                sunmiPrintHelper.setAlige(Align.CENTER);
//                sunmiPrintHelper.printText(line);
//
//                sunmiPrintHelper.setAlige(Align.LEFT);
//
//                sunmiPrintHelper.printText("TC:" + mReceiptDataModel.certif);
//                sunmiPrintHelper.printText("App id:" + mReceiptDataModel.appId);
//                sunmiPrintHelper.printText("App name:" + mReceiptDataModel.appName);
//                sunmiPrintHelper.printText("TVR:" + mReceiptDataModel.tvr.trim() + " TSI:" + mReceiptDataModel.tsi.trim());
//            }
//
//            sunmiPrintHelper.setAlige(Align.CENTER);
//
//            if (mReceiptDataModel.isPinVarifed.equalsIgnoreCase("true")) {
//
//                sunmiPrintHelper.printText(line);
//                sunmiPrintHelper.printText("PIN Verified");
//                sunmiPrintHelper.printText(line);
//
//            } else {
//
//                if (isPrintSignatureRequired) {
//
//                    if (bitmap != null) {
//                        sunmiPrintHelper.printBitmap(getSignatureBitmap(bitmap), 0);
//                        sunmiPrintHelper.printNewLine();
//                    }
//
//                } else {
//                    sunmiPrintHelper.setAlige(Align.LEFT);
//
//                    sunmiPrintHelper.printNewLine();
//                    sunmiPrintHelper.printText("signature");
//                    sunmiPrintHelper.printNewLine();
//                    sunmiPrintHelper.setAlige(Align.CENTER);
//
//                    sunmiPrintHelper.printText(line);
//
//                }
//            }
//
//            sunmiPrintHelper.setAlige(Align.CENTER);
//            sunmiPrintHelper.printText(mReceiptDataModel.cardHolderName);
//
//            sunmiPrintHelper.setFontSize(16);
//            sunmiPrintHelper.printText("I AGREE TO PAY THE ABOVE TOTAL AMOUNT");
//            sunmiPrintHelper.printText("ACCORDING TO THE CARD ISSUER AGREEMENT");
//
//            sunmiPrintHelper.setFontSize(20);
//            sunmiPrintHelper.printText(line);
//
//            sunmiPrintHelper.setFontSize(16);
//
//            if (isCustomerCopy) {
//
//                if (isDuplicatePrint) {
//                    sunmiPrintHelper.printText("*** DUPLICATE CUSTOMER COPY ***");
//
//                } else {
//                    sunmiPrintHelper.printText("*** CUSTOMER COPY ***");
//                }
//
//            } else {
//
//                if (isDuplicatePrint) {
//                    sunmiPrintHelper.printText("*** DUPLICATE MERCHANT COPY ***");
//
//                } else {
//                    sunmiPrintHelper.printText("*** MERCHANT COPY ***");
//                }
//            }
//
//            sunmiPrintHelper.printText("Version No :" + mReceiptDataModel.appVersion);
//
//            getSettlementBank(mReceiptDataModel.bankName);
//
//            sunmiPrintHelper.feedPaper();
//            sunmiPrintHelper.startPrint();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private String removeampm(String aDate) {
//        //as the date issue from the service level we are removing the am/pm from the date
//        //we are getting with 24 form include am/pm which createing the error while converting.
//
//        aDate.replaceAll("am", "");
//        aDate.replaceAll("AM", "");
//        aDate.replaceAll("pm", "");
//        aDate.replaceAll("PM", "");
//
//        return aDate.trim();
//
//    }
//
//    private void getHeaderLogo(){
//
//        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),	R.drawable.utap_print_header);
//
//        if(icon != null) {
//            sunmiPrintHelper.printBitmap(icon, 0);
//        }
//    }
//
//
//    private void getSettlementBank(String bankName){
//
//        if (bankName != null && bankName.length() > 0) {
//
//            sunmiPrintHelper.printText("Settlement Bank : " + bankName);
//        }
//    }
//
//    private Bitmap getSignatureBitmap(Bitmap bitmap){
//
//        int newWidth = bitmap.getWidth();
//
//        if(newWidth > 300){
//            newWidth = 300;
//        }
//
//        int newHeigth = 120;
//
//        // Part 2: Scale image
//        Bitmap scaledBitmap = ScalingUtilities.createScaledBitmap(bitmap, newWidth/2, newHeigth, ScalingUtilities.ScalingLogic.FIT);
//        //mBitmapExternallyDrawn.recycle();
//
//        int quality = 80;
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, quality, os);
//
//        return scaledBitmap;
//    }
//}