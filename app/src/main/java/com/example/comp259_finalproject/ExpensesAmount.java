package com.example.comp259_finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ExpensesAmount extends Fragment {

    Budget budget;
    double earnings;
    double loans;
    double totalIncome;
    EditText rentET;
    EditText utilitiesET;
    EditText internetET;
    EditText phoneET;
    TextView totalHousingCostTV;
    EditText tuitionET;
    EditText booksET;
    EditText otherFeesET;
    TextView totalEduCostTV;
    TextView totalExpensesTV;
    Button acceptBtn;
    RadioGroup amountAreRadioGrp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.enter_expense_amount_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Instantiate the UI variables
        //CREATE AN AUTOMOBILE OBJECT TO STORE  DATA
        budget = new Budget();

        rentET = (EditText) getView().findViewById(R.id.rentEditText);
        utilitiesET = (EditText) getView().findViewById(R.id.utilitiesEditText);
        internetET = (EditText) getView().findViewById(R.id.internetEditText);
        phoneET = (EditText) getView().findViewById(R.id.phoneEditText);
        totalHousingCostTV = (TextView) getView().findViewById(R.id.totalHousingCostTextView);
        tuitionET = (EditText) getView().findViewById(R.id.tuitionEditText);
        booksET = (EditText) getView().findViewById(R.id.booksEditText);
        otherFeesET = (EditText) getView().findViewById(R.id.otherFeesEditText);
        totalEduCostTV = (TextView) getView().findViewById(R.id.totalEduCostTextView);
        totalExpensesTV = (TextView) getView().findViewById(R.id.totalExpenses2TextView);
        acceptBtn = (Button) getView().findViewById(R.id.acceptBtn);
        amountAreRadioGrp = (RadioGroup) getView().findViewById(R.id.radioGroup);

        //get the any arguments (Bundle) passed into the fragment by host activity
        Bundle args = getArguments();

        //extract the property values from the bundle
        if (args != null) {
            earnings = args.getDouble("earnings", 0);
            loans = args.getDouble("loans", 0);
            totalIncome = args.getDouble("totalIncome", 0);

            //load values into the properties of the new Budget object
            budget.set_earnings(earnings);
            budget.set_loans(loans);
        }

        amountAreRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.annualRadioBtn:
                        budget.set_isMonthly(false);
                        break;
                    case R.id.monthlyRadioBtn:
                        budget.set_isMonthly(true);
                        break;
                }
            }
        });

        rentET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText rent = (EditText) v;
                    budget.set_rent((double) Double.parseDouble(rent.getText().toString()));
                    totalHousingCostTV.setText(String.format("%.02f",budget.totalHousingCost()));
                    totalExpensesTV.setText(String.format("%.02f",budget.totalExpenses()));
                }
            }
        });

        utilitiesET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText utilities = (EditText) v;
                    budget.set_utilities((double) Double.parseDouble(utilities.getText().toString()));
                    totalHousingCostTV.setText(String.format("%.02f",budget.totalHousingCost()));
                    totalExpensesTV.setText(String.format("%.02f",budget.totalExpenses()));
                }
            }
        });

        internetET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText internet = (EditText) v;
                    budget.set_internet((double) Double.parseDouble(internet.getText().toString()));
                    totalHousingCostTV.setText(String.format("%.02f",budget.totalHousingCost()));
                    totalExpensesTV.setText(String.format("%.02f",budget.totalExpenses()));
                }
            }
        });

        phoneET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText phone = (EditText) v;
                    budget.set_phone((double) Double.parseDouble(phone.getText().toString()));
                    totalHousingCostTV.setText(String.format("%.02f",budget.totalHousingCost()));
                    totalExpensesTV.setText(String.format("%.02f",budget.totalExpenses()));
                }
            }
        });

        tuitionET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText tuition = (EditText) v;
                    budget.set_tuition((double) Double.parseDouble(tuition.getText().toString()));
                    totalEduCostTV.setText(String.format("%.02f",budget.totalEducationCost()));
                    totalExpensesTV.setText(String.format("%.02f",budget.totalExpenses()));
                }
            }
        });

        booksET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText books = (EditText) v;
                    budget.set_books((double) Double.parseDouble(books.getText().toString()));
                    totalEduCostTV.setText(String.format("%.02f",budget.totalEducationCost()));
                    totalExpensesTV.setText(String.format("%.02f",budget.totalExpenses()));
                }
            }
        });

        otherFeesET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText otherFees = (EditText) v;
                    budget.set_otherFees((double) Double.parseDouble(otherFees.getText().toString()));
                    totalEduCostTV.setText(String.format("%.02f",budget.totalEducationCost()));
                    totalExpensesTV.setText(String.format("%.02f",budget.totalExpenses()));
                }
            }
        });


        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putDouble("earnings", (double) Double.valueOf(earnings));
                bundle.putDouble("loans", (double) Double.valueOf(loans));
                bundle.putDouble("totalIncome", (double) Double.valueOf(totalIncome));

                bundle.putDouble("rent", (double) Double.valueOf(budget.get_rent()));
                bundle.putDouble("utilities", (double) Double.valueOf(budget.get_utilities()));
                bundle.putDouble("internet", (double) Double.valueOf(budget.get_internet()));
                bundle.putDouble("phone", (double) Double.valueOf(budget.get_phone()));
                bundle.putDouble("totalHousingCost", (double) Double.valueOf(budget.totalHousingCost()));
                bundle.putDouble("tuition", (double) Double.valueOf(budget.get_tuition()));
                bundle.putDouble("books", (double) Double.valueOf(budget.get_books()));
                bundle.putDouble("otherFees", (double) Double.valueOf(budget.get_otherFees()));
                bundle.putDouble("totalEduCost", (double) Double.valueOf(budget.totalEducationCost()));
                bundle.putDouble("totalExpenses", (double) Double.valueOf(budget.totalExpenses()));
                bundle.putBoolean("isMonthly", (boolean) Boolean.valueOf(budget.is_isMonthly()));

                getParentFragmentManager().setFragmentResult("FromExpenseAmounts", bundle);
            }
        });

    }
}
