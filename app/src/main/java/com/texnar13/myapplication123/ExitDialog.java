package com.texnar13.myapplication123;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ExitDialog extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Activity activity = getActivity();
        if (activity == null) dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);


//        builder.setTitle(R.string.my_dialog_title);
//
//        builder.setPositiveButton(R.string.my_dialog_button_yes,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Log.i("testAPP", "my_dialog_button_yes");
//
//
//                        ((ExitClickAble) activity).dialogExitYes();
//
//                    }
//                }
//        );
//
//
//        builder.setNeutralButton(R.string.my_dialog_button_no,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Log.i("testAPP", "my_dialog_button_no");
//
//                    }
//                }
//        );


        View root = activity.getLayoutInflater().inflate(R.layout.main_activity_exit_dialog, null);
        builder.setView(root);

        root.findViewById(R.id.main_activity_exit_button_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        root.findViewById(R.id.main_activity_exit_button_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("testAPP", "my_dialog_button_yes");


                String width = ((EditText) root.findViewById(R.id.main_activity_exit_width)).getText().toString().trim();
                String height = ((EditText) root.findViewById(R.id.main_activity_exit_height)).getText().toString().trim();

                boolean isCorrect = false;
                int w = -1;
                int h = -1;

                try {
                    w = Integer.parseInt(width);
                    h = Integer.parseInt(height);
                    isCorrect = true;
                } catch (NumberFormatException e) {
                    Log.e("tag", "parseError \"" + width + '\"');
                }


                if (isCorrect) {
                    ((AbleCreateRedactorActivity) activity).startRedactorActivity(w, h);
                    dismiss();
                } else {
                    Toast.makeText(activity, "Неправильные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        main_activity_exit_width
//        main_activity_exit_height
//        main_activity_exit_button_no
//        main_activity_exit_button_yes


        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        return dialog;
    }
}

interface ExitClickAble {

    void dialogExitYes();

}

interface AbleCreateRedactorActivity {

    void startRedactorActivity(int width, int height);

}


