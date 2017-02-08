package com.example.kenneth.a2048game;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by Kenneth on 27/01/2017.
 */

public class WonGameFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String scoretxt = getArguments().getString("score");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("proficiat u heeft gewonnen" + scoretxt)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                    }
                });
        return builder.create();
    }
}
