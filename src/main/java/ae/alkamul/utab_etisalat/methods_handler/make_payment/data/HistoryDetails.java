/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.alkamul.utab_etisalat.methods_handler.make_payment.data;

import java.io.Serializable;

/**
 *
 * @author shilpa
 */
public class HistoryDetails implements Serializable{
    
    
    public String TrxDate;
    public String CardLastFourDigits;
    public String TrxAmount;
    public String TrxType;
    public String TrxNotes;
    public String MerchantInvoice;
    public String StanNo;
    public String VoucherNo;
    public String AuthNo;
    public String RRNo;
    public String TrxStatus;
    public String TerminalMessage;
    public String ChequeNo;
    
}
