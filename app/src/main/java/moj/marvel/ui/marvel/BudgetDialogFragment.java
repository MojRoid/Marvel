package moj.marvel.ui.marvel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import javax.inject.Inject;

import moj.marvel.R;
import moj.marvel.controllers.marvel.MarvelActivity;
import moj.marvel.injection.modules.BudgetModule;

public class BudgetDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {



    //@Inject
    EditText mEditBudget;

    //@Inject
    AlertDialog.Builder builder;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void initComponent(){
        MarvelActivity activity = (MarvelActivity) getContext();
        activity.getComponent().plus(new BudgetModule());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //initComponent();

        mEditBudget = new EditText(getActivity());
        mEditBudget.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.menu_budget)
                .setMessage("What is your budget?")
                .setPositiveButton("SET", this)
                .setNegativeButton("CANCEL", null)
                .setView(mEditBudget).create();

    }

    @Override
    public void onClick(DialogInterface dialog, int position) {
        String value = mEditBudget.getText().toString();
        Log.d("Budget: ", value);
        //TODO:callingActivity.onUserSelectValue(value);
        dialog.dismiss();
    }
}