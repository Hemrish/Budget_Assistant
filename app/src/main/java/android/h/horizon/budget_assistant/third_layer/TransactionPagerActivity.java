package android.h.horizon.budget_assistant.third_layer;

import android.content.Context;
import android.content.Intent;
import android.h.horizon.budget_assistant.R;
import android.h.horizon.budget_assistant.transaction.Transaction;
import android.h.horizon.budget_assistant.transaction.TransactionContainer;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Contains the fragment that operates the ViewPager
 */
public class TransactionPagerActivity extends AppCompatActivity {
    private static final String EXTRA_TRANSACTION_TITLE = "transaction_title";
    private static final String TAG = "TransactionPager";
    private List<Transaction> mTransactionList;
    private String mTransactionTitle;
    private static final String EXTRA_TRANSACTION_ID =
            "transaction_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle savedInstanceState) called");
        setContentView(R.layout.activity_transaction_pager);
        UUID transactionId = getTransactionIdFromArguments();
        setArgumentTo_mTransactionTitle();
        setTitle(mTransactionTitle);//Set title of frame
        setDataToReturnToParent();
        setsViewPager(transactionId);
    }

    /**
     * Gets arguments from the second layer
     *
     * @param packageContext   is the current state of the application
     * @param transactionId    is the id of a Transaction object to display
     * @param transactionTitle is type of the transaction the object belongs to
     * @return the mechanism that allows this activity to operate
     */
    public static Intent newIntent(Context packageContext, UUID transactionId,
                                   String transactionTitle) {
        Log.d(TAG, "newIntent(Context packageContext, UUID transactionId,\n" +
                "                                   String transactionTitle) called");
        Intent intent = new Intent(packageContext, TransactionPagerActivity.class);
        intent.putExtra(EXTRA_TRANSACTION_ID, transactionId);
        intent.putExtra(EXTRA_TRANSACTION_TITLE, transactionTitle);
        return intent;
    }

    /**
     * @param result is the mechanism that allows this layer to send data to the second layer
     *               (previous layer)
     * @return the argument (title)
     */
    public static String returnArgumentTitle(Intent result) {
        Log.d(TAG, "decodeTitle(Intent result) called");
        return result.getStringExtra(EXTRA_TRANSACTION_TITLE);
    }

    private UUID getTransactionIdFromArguments() {
        Log.d(TAG, "getTransactionIdFromArguments() called");
        return (UUID) getIntent()
                .getSerializableExtra(EXTRA_TRANSACTION_ID);
    }

    private void setArgumentTo_mTransactionTitle() {
        Log.d(TAG, "setArgumentTo_mTransactionTitle() called");
        mTransactionTitle = (String) getIntent()
                .getSerializableExtra(EXTRA_TRANSACTION_TITLE);
    }

    private void setDataToReturnToParent() {
        Log.d(TAG, "setDataToReturnToParent() called");
        Intent data = new Intent();
        data.putExtra(EXTRA_TRANSACTION_TITLE, mTransactionTitle);
        setResult(RESULT_OK, data);
    }

    private void setsViewPager(UUID transactionId) {
        Log.d(TAG, "setsViewPager transactionId) called");
        ViewPager mViewPager = findViewById(R.id.activity_transaction_pager_view_pager);
        mTransactionList = TransactionContainer.get(this).getTransactions();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Log.d(TAG, "getItem(int position) called");
                Transaction transaction = mTransactionList.get(position);
                return TransactionPagerFragment.newInstance(transaction.getId());
                // calls the fragment
            }

            @Override
            public int getCount() {
                Log.d(TAG, "getCount() called");
                return mTransactionList.size();
            }
        });

        for (int i = 0; i < mTransactionList.size(); i++) {
            if (mTransactionList.get(i).getId().equals(transactionId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}
