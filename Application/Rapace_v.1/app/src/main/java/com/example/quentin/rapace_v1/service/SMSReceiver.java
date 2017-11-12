package com.example.quentin.rapace_v1.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.quentin.rapace_v1.Activity.Alert;

/**
 * Classe s'occupant de la détection des alertes.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        if (bundle != null) {
            /* Les pdus correspondent aux numéros de téléphonne des expéditeurs */
        Object[] pdus = (Object[]) bundle.get("pdus");
        msgs = new SmsMessage [pdus.length];
            /* Pour tout messages entrant */
        for (int i=0; i < msgs.length; i++) {
            SmsMessage message_entrant = SmsMessage.createFromPdu((byte[]) pdus[i]);
            /* Si le numéro correpond à celui du serveur (000) */
            if (message_entrant.getOriginatingAddress().equals("000")) {
                msgs[i] = message_entrant;

                /* On appelle l'Activité Alerte */
                Intent mainActivityIntent = new Intent(context, Alert.class);
                mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mainActivityIntent);

                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("SMS_RECEIVED_ACTION");
                context.sendBroadcast(broadcastIntent);


            }

        }

        }
}
}
