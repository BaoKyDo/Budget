package com.example.comp259_finalproject;

public class Budget {

    private double _earnings = 0;
    private double _loans = 0;

    private double _rent = 0;
    private double _utilities = 0;
    private double _internet = 0;
    private double _phone = 0;
    private double _tuition = 0;
    private double _books = 0;
    private double _otherFees = 0;
    private boolean _isMonthly = true;

    public double get_earnings() {return _earnings; }
    public void set_earnings(double earnings) {
        _earnings = earnings;
    }
    public double get_loans() {return _loans; }
    public void set_loans(double loans) {
        _loans = loans;
    }

    public double get_rent() {return _rent; }
    public void set_rent(double rent) {
        _rent = rent;
    }
    public double get_utilities() {
        return _utilities;
    }
    public void set_utilities(double utilities) {
        _utilities = utilities;
    }
    public double get_internet() {return _internet; }
    public void set_internet(double internet) {_internet = internet;}
    public double get_phone() {
        return _phone;
    }
    public void set_phone(double phone) {_phone = phone; }
    public double get_tuition() {
        return _tuition;
    }
    public void set_tuition(double tuition) {
        _tuition = tuition;
    }
    public double get_books() {
        return _books;
    }
    public void set_books(double books) {_books = books; }
    public double get_otherFees() {
        return _otherFees;
    }
    public void set_otherFees(double otherFees) {
        _otherFees = otherFees;
    }
    public boolean is_isMonthly() { return _isMonthly;}
    public void set_isMonthly(boolean isMonthly) { _isMonthly = isMonthly;}


    public double totalHousingCost(){
        double totalHousingCost = _rent + _utilities + _internet + _phone;
        if(!is_isMonthly()){
            totalHousingCost/=12;
        }
        return totalHousingCost;

    }

    public double totalEducationCost(){
        double eduCost = _tuition + _books + _otherFees;
        if(!is_isMonthly()){
            eduCost/=12;
        }
        return eduCost;
    }

    public double totalExpenses(){
        return totalHousingCost() + totalEducationCost();
    }
    public double surplusOrShortfall(){ return totalIncome() - totalExpenses();}

    public double totalIncome(){
        double income = _earnings + _loans;
        return income;
    }

}
