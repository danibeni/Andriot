package org.danibeni.andriot.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

import org.danibeni.andriot.R;
import org.danibeni.andriot.tasks.SendPingCoAPDeviceTask;

import java.util.concurrent.ExecutionException;

/**
 * Created by dbenitez on 20/09/2017.
 */

public class AddDeviceDialogFragment extends DialogFragment {

    private static final String TAG = AddDeviceDialogFragment.class.getSimpleName();
    private SendPingCoAPDeviceTask sendPingTask;
    private boolean isReachable;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_dialog_add_device, null))
                // Add action buttons
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        sendPingTask = new SendPingCoAPDeviceTask();
                        sendPingTask.execute(new String[]{"192.168.1.49", "5683"});
                        Log.i(TAG, "ACCESIBLE ");
                        try {
                            if (sendPingTask.get().booleanValue()) {
                                Log.i(TAG, "IS ACCESIBLE");
                            } else {
                                Log.i(TAG, "NOT ACCESIBLE");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddDeviceDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
