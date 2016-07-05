package moj.marvel.injection.components;


import dagger.Subcomponent;
import moj.marvel.injection.modules.BudgetModule;
import moj.marvel.injection.scopes.PerFragment;
import moj.marvel.ui.marvel.BudgetDialogFragment;

@PerFragment
@Subcomponent (modules = {BudgetModule.class})
public interface BudgetComponent {

    void inject(BudgetDialogFragment fragment);
}
