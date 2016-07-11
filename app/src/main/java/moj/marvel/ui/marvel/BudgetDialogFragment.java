package moj.marvel.ui.marvel;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;

import moj.marvel.R;
import moj.marvel.controllers.marvel.MarvelActivity;

public class BudgetDialogFragment extends DialogFragment {

    EditText mEditBudget;
    //AlertDialog.Builder builder; // Dependency inject?

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //initComponent();

        mEditBudget = new EditText(getActivity());
        mEditBudget.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.menu_budget)
                .setPositiveButton("SET", (dialogInterface, i) -> {
                    if (TextUtils.isEmpty(mEditBudget.getText().toString())) {
                        return;
                    }
                    Double value = Double.parseDouble(mEditBudget.getText().toString());
                    MarvelActivity callingActivity = (MarvelActivity) getActivity();
                    callingActivity.onBudgetSet(value);
                    dialogInterface.dismiss();
                })
                .setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss())
                .setView(mEditBudget).create();
    }
}