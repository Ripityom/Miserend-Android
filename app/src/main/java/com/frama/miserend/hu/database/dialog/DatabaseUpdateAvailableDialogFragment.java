package com.frama.miserend.hu.database.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.frama.miserend.hu.R;

/**
 * Created by Balazs on 2018. 03. 11..
 */

public class DatabaseUpdateAvailableDialogFragment extends DialogFragment {

    private DatabaseDialogCallback callback;

    public static DatabaseUpdateAvailableDialogFragment newInstance() {
        DatabaseUpdateAvailableDialogFragment frag = new DatabaseUpdateAvailableDialogFragment();
        return frag;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (DatabaseDialogCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DatabaseDialogCallback");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_db_update_title)
                .setMessage(R.string.dialog_db_update_message)
                .setPositiveButton(R.string.dialog_db_update_btn_ok,
                        (dialog, whichButton) -> callback.onDownloadClicked()
                )
                .setNegativeButton(R.string.dialog_db_update_btn_cancel,
                        (dialog, whichButton) -> callback.onDontDownloadClicked(false)
                )
                .create();
    }

}
