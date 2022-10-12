package com.example.comp259_finalproject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class BudgetSummary extends Fragment {

    EditText earningsET;
    EditText loansET;
    TextView totalIncomeTV;
    TextView totalExpensesTV;
    TextView surplusOrShortfallTV;
    Button expensesCalBtn;

    double earnings;
    double loans;
    double totalIncome;

    double rent;
    double utilities;
    double internet ;
    double phone;
    double totalHousingCost;
    double tuition;
    double books;
    double otherFees;
    double totalEduCost;
    double totalExpenses;
    boolean isMonthly;

    Budget budget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.budget_summary_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        budget = new Budget();

        earningsET = (EditText) getView().findViewById(R.id.earningsEditText);
        loansET = (EditText) getView().findViewById(R.id.loansEditText);
        totalIncomeTV = (TextView) getView().findViewById(R.id.totalIncomeTextView);
        totalExpensesTV = (TextView) getView().findViewById(R.id.totalExpensesTextView);
        surplusOrShortfallTV = (TextView) getView().findViewById(R.id.surplusOrShortfallTextView);
        expensesCalBtn = (Button) getView().findViewById(R.id.calculateExpensesBtn);

        earningsET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText earnings = (EditText) v;
                    budget.set_earnings((double) Double.parseDouble(earnings.getText().toString()));
                    totalIncomeTV.setText(String.format("%.02f",budget.totalIncome()));
                }
            }
        });

        loansET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EditText loans = (EditText) v;
                    budget.set_loans((double) Double.parseDouble(loans.getText().toString()));
                    totalIncomeTV.setText(String.format("%.02f",budget.totalIncome()));
                }
            }
        });


        //attach handler to the calculate expenses button
        expensesCalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putDouble("earnings", (double) Double.valueOf(budget.get_earnings()));
                bundle.putDouble("loans", (double) Double.valueOf(budget.get_loans()));
                bundle.putDouble("totalIncome", (double) Double.valueOf(budget.totalIncome()));

                getParentFragmentManager().setFragmentResult("FromBudgetSummary", bundle);

            }
        });


        //receive properties from the main activity
        //get the any arguments (Bundle) passed into the fragment by host activity
        Bundle args = getArguments();

        //extract the property values from the bundle
        if (args != null) {

            earnings = args.getDouble("earnings", 0);
            loans = args.getDouble("loans", 0);
            totalIncome = args.getDouble("totalIncome", 0);

            rent = args.getDouble("rent", 0);
            utilities = args.getDouble("utilities", 0);
            internet = args.getDouble("internet", 0);
            phone = args.getDouble("phone", 0);
            totalHousingCost = args.getDouble("totalHousingCost", 0);
            tuition = args.getDouble("tuition", 0);
            books = args.getDouble("books", 0);
            otherFees = args.getDouble("otherFees", 0);
            totalEduCost = args.getDouble("totalEduCost", 0);
            totalExpenses = args.getDouble("totalExpenses", 0);
            isMonthly = args.getBoolean("isMonthly", true);

            //load values into the properties of the new Budget object

            budget.set_earnings(earnings);
            budget.set_loans(loans);

            budget.set_rent(rent);
            budget.set_utilities(utilities);
            budget.set_internet(internet);
            budget.set_phone(phone);
            budget.set_tuition(tuition);
            budget.set_books(books);
            budget.set_otherFees(otherFees);
            budget.set_isMonthly(isMonthly);

            earningsET.setText(String.format("%.02f",budget.get_earnings()));
            loansET.setText(String.format("%.02f",budget.get_loans()));
            totalIncomeTV.setText(String.format("%.02f",budget.totalIncome()));
            totalExpensesTV.setText(String.format("%.02f",budget.totalExpenses()));
            surplusOrShortfallTV.setText(String.format("%.02f",budget.surplusOrShortfall()));
        }



    }







































}
