package android.h.horizon.budget_assistant.dashboard;


import android.h.horizon.budget_assistant.abstract_classes.SingleFragmentActivity;
import android.util.Log;


import androidx.fragment.app.Fragment;

/**
 * This class is the activity that deals with all expenses
 */
public class ExpensesActivity extends SingleFragmentActivity {
    private static final String TAG = "ExpensesActivity";

    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment() called");
        return new ExpensesFragment();
    }
}
