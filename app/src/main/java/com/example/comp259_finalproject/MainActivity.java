package com.example.comp259_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    // declare a menu object at the top of the activity
    Menu AppMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BudgetSummary budgetSummary = new BudgetSummary();
        fragmentTransaction.replace(R.id.fragment_container_view, budgetSummary, "frag1");

        fragmentTransaction.commit();

        //set a listener to receive results from the first fragment which is the Budget Summary fragment
        fragmentManager.setFragmentResultListener("FromBudgetSummary", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                sendToExpensesAmount(bundle);
            }
        });

        //set a listener to receive results from the second fragment which is the Expense Amount fragment

        fragmentManager.setFragmentResultListener("FromExpenseAmounts", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                sendToBudgetSummary(bundle);
            }
        });
    }

    public void sendToBudgetSummary(Bundle bundle){

        double earnings = bundle.getDouble("earnings");
        double loans = bundle.getDouble("loans");
        double totalIncome = bundle.getDouble("totalIncome");

        double rent = bundle.getDouble("rent");
        double utilities = bundle.getDouble("utilities");
        double internet = bundle.getDouble("internet");
        double phone = bundle.getDouble("phone");
        double totalHousingCost = bundle.getDouble("totalHousingCost");
        double tuition = bundle.getDouble("tuition");
        double books = bundle.getDouble("books");
        double otherFees = bundle.getDouble("otherFees");
        double totalEduCost = bundle.getDouble("totalEduCost");
        double totalExpenses = bundle.getDouble("totalExpenses");
        boolean isMonthly = bundle.getBoolean("isMonthly");

        FragmentManager fragmentManger = getSupportFragmentManager();

        BudgetSummary budgetSummary = new BudgetSummary();

        //create a Bundle
        Bundle extras = new Bundle();

        extras.putDouble("earnings", earnings);
        extras.putDouble("loans", loans);
        extras.putDouble("totalIncome", totalIncome);

        extras.putDouble("rent", rent);
        extras.putDouble("utilities", utilities);
        extras.putDouble("internet", internet);
        extras.putDouble("phone", phone);
        extras.putDouble("totalHousingCost", totalHousingCost);
        extras.putDouble("tuition", tuition);
        extras.putDouble("books", books);
        extras.putDouble("otherFees", otherFees);
        extras.putDouble("totalEduCost", totalEduCost);
        extras.putDouble("totalExpenses", totalExpenses);
        extras.putBoolean("isMonthly", isMonthly);
        budgetSummary.setArguments(extras);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, budgetSummary, "frag1");
        fragmentTransaction.commit();
        // in the button click handler or menu item code, switch the title as needed
        AppMenu.findItem(R.id.menuitem_expense_accept).setTitle("Expenses");
    }

    public void sendToExpensesAmount(Bundle bundle){
        double earnings = bundle.getDouble("earnings");
        double loans = bundle.getDouble("loans");
        double totalIncome = bundle.getDouble("totalIncome");

        //switch to Loan Summary and pass it the result data as args in a bundle
        FragmentManager fragmentManager = getSupportFragmentManager();

        ExpensesAmount expensesAmount = new ExpensesAmount();
        //create a bundle
        Bundle extras = new Bundle();
        extras.putDouble("earnings", earnings);
        extras.putDouble("loans", loans);
        extras.putDouble("totalIncome", totalIncome);
        expensesAmount.setArguments(extras);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, expensesAmount, "frag2");
        fragmentTransaction.commit();

        // in the button click handler or menu item code, switch the title as needed
        AppMenu.findItem(R.id.menuitem_expense_accept).setTitle("Accept");
    }

    // instantiate the AppMenu instance in the activity’s onCreateOptionsMenu(Menu menu)
    // with code similar to:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mymenulayout, menu);
        AppMenu = menu;
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        BudgetSummary budgetSummary = (BudgetSummary) getSupportFragmentManager().findFragmentByTag("frag1");
        ExpensesAmount expensesAmount = (ExpensesAmount) getSupportFragmentManager().findFragmentByTag("frag2");
        if (id == R.id.menuitem_expense_accept) {
            if (budgetSummary != null && budgetSummary.isVisible()) {
                AppMenu.findItem(R.id.menuitem_expense_accept).setTitle("Expenses");
                budgetSummary.getView().findViewById(R.id.calculateExpensesBtn).callOnClick();
            }
            else if (expensesAmount != null & expensesAmount.isVisible()) {
                AppMenu.findItem(R.id.menuitem_expense_accept).setTitle("Accept");
                expensesAmount.getView().findViewById(R.id.acceptBtn).callOnClick();
            }
            return true;
        }
        else if (id == R.id.menuitem_quit) {
            finish();
        }

        return super.onOptionsItemSelected(item);

    }
            @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            toggleActionBar(); // show / hide ActionBar when screen is tapped
        }
        return true;
    }

    private void toggleActionBar() {
        // get a reference to the Activity's actionbar
        ActionBar actionBar = getSupportActionBar();

        // if the actionbar is not null, the toggle it on / off
        if(actionBar != null) {
            if(actionBar.isShowing()) {
                actionBar.hide();
            }
            else {
                actionBar.show();
            }
        }
    }
}