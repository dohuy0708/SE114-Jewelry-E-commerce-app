package com.example.jewelryecommerceapp.Models;

public class Filter {
    private String FilterName;
    private boolean isSelected;
    public Filter(){}
    public Filter(String FilterName){
        this.FilterName=FilterName;
        isSelected=false;
    }
    public String getFilterName(){return FilterName;}

    public void setFilterName(String filterName) {
        FilterName = filterName;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
