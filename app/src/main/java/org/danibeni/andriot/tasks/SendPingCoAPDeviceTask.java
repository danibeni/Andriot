package org.danibeni.andriot.tasks;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import de.uzl.itm.ncoap.application.client.ClientCallback;
import de.uzl.itm.ncoap.application.client.CoapClient;
import de.uzl.itm.ncoap.message.CoapResponse;

/**
 * Created by dbenitez on 19/09/2017.
 */

public class SendPingCoAPDeviceTask extends AsyncTask <String, Void, Boolean>{

    private static final String TAG = SendPingCoAPDeviceTask.class.getSimpleName();
    private WeakReference<Context> mContextRef;
    private CoapClient coapClient;
    private boolean isAccesible;

    @Override
    protected Boolean doInBackground(String... params) {
        coapClient = new CoapClient();
        final InetSocketAddress remoteEndpoint;
        final String address = params[0];
        final int port =Integer.parseInt(params[1]);
        try {
            remoteEndpoint = new InetSocketAddress(InetAddress.getByName(address), port);
            PingCallback pingCallback = new PingCallback(remoteEndpoint);
            coapClient.sendCoapPing(remoteEndpoint, pingCallback);
            while (!pingCallback.isEnded) {

            }
        } catch (final Exception e) {
            Log.e(TAG, "Cannot access to CoAP Server. Details: " + e);
            isAccesible = false;
        }
        return isAccesible;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Log.e(TAG, "LLEGA");
    }

    public class PingCallback extends ClientCallback {

        private InetSocketAddress remoteEndpoint;
        private long timeSent;
        private int counter;
        public boolean isEnded;

        public PingCallback(InetSocketAddress remoteEndpoint){
            this.remoteEndpoint = remoteEndpoint;
            this.timeSent = System.currentTimeMillis();
            this.counter = 1;
            this.isEnded = false;
        }

        @Override
        public void processCoapResponse(CoapResponse coapResponse) {
            //Nothing to do...
        }

        @Override
        public void processReset() {
            long timeReceived = System.currentTimeMillis();
            isAccesible = true;
            isEnded = true;
            Log.i(TAG, "PONG " + " received after " + (timeReceived - timeSent) + "ms\n" +
                    "(from " + remoteEndpoint.getHostName() + ")");
        }

        @Override
        public void processTransmissionTimeout(){
            isEnded = true;
            Log.i(TAG, "Server did not respond \n" + remoteEndpoint.getHostName());
        }

        @Override
        public void processRetransmission(){
            Log.i(TAG, "PING No. " + (++counter) + " sent to " + remoteEndpoint.getHostName() + "!");
        }

        @Override
        public void processEmptyAcknowledgement(){
            isEnded = true;
            long timeReceived = System.currentTimeMillis();
            Log.i(TAG,"Empty ACK (should have been RST) from " + remoteEndpoint.getHostName() +
                    " received after " + (timeReceived - timeSent) + "ms!");
        }

        @Override
        public void processMiscellaneousError(final String description) {
            isEnded = true;
            Log.i(TAG, "ERROR: " + description + "!");
        }
    }

}
