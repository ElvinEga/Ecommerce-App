package com.twisac.apps.amazin.datastore;


import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.twisac.apps.amazin.datastore.models.Bank;
import com.twisac.apps.amazin.datastore.models.Card;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AppDataStore {

    public static void saveBankData (){
        Bank appData  = new Bank("bank","number","name",getDateTime());
        appData.save();
    }
    public static void saveCardData (String number, String expiration, String cvv, String holder, String provider){
        Card appData  = new Card(number,expiration,cvv,holder,provider,getDateTime());
        appData.save();
    }

    public static List<Bank> getAllBanks() {
        return new Select()
                .all()
                .from(Bank.class)
                .execute();
    }
    public static List<Card> getAllCards() {
        return new Select()
                .all()
                .from(Card.class)
                .execute();
    }   public static void deleteCard(long itemId) {
        // Log.d("DeletePos",productId);

        new Delete()
                .from(Card.class)
                .where("id = ?",itemId).execute();
    }

    private static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
