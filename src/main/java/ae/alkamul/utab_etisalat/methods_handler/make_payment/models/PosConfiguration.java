package ae.alkamul.utab_etisalat.methods_handler.make_payment.models;

import java.util.HashMap;

public class PosConfiguration {
    private String username;
    private String password;
    private boolean production;
    private double amount;

    private int timeOut;

    public PosConfiguration() {

    }

    public PosConfiguration convertHashMapToModel(HashMap<String, Object> hashMap) {
        PosConfiguration model = new PosConfiguration();
        model.setUsername((String) hashMap.get("username"));
        model.setPassword((String) hashMap.get("password"));
        model.setAmount((double) hashMap.get("amount"));
        model.setProduction((boolean) hashMap.get("production"));
        model.setTimeOut((int) hashMap.get("timeout"));

        return model;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isProduction() {
        return production;
    }

    public double getAmount() {
        return amount;
    }

    public int getTimeOut() {
        return timeOut;
    }
}