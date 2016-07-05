package moj.marvel.injection.modules;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import dagger.Module;
import dagger.Provides;
import moj.marvel.injection.qualifers.ForActivity;
import moj.marvel.injection.scopes.PerActivity;
import moj.marvel.injection.scopes.PerFragment;
import moj.marvel.ui.marvel.BudgetDialogFragment;


@Module
public class BudgetModule {

    @Provides
    @PerFragment
    EditText providesBudgetEditText(@ForActivity Context context) {
        return new EditText(context);
    }

    @Provides
    @PerFragment
    AlertDialog.Builder providesDialogFragment(@ForActivity Context context){
        return new AlertDialog.Builder(context);
    }
}
