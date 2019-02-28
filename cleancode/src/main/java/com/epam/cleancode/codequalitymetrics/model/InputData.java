package com.epam.cleancode.codequalitymetrics.model;

public class InputData {

    private int mTotalTimeAvailableInMins;
    private int mNoOfItemsOnMenu;
    private int[] satisfactionPerItemArray;
    private int[] timeAvailablePerItem;

    public int getTotalTimeAvailableInMins() {
        return mTotalTimeAvailableInMins;
    }

    public void setTotalTimeAvailableInMins(int totalTimeAvailableInMins) {
        this.mTotalTimeAvailableInMins = totalTimeAvailableInMins;
    }

    public int getNoOfItemsOnMenu() {
        return mNoOfItemsOnMenu;
    }

    public void setNoOfItemsOnMenu(int noOfItemsOnMenu) {
        this.mNoOfItemsOnMenu = noOfItemsOnMenu;
    }

    public int[] getSatisfactionPerItemArray() {
        return satisfactionPerItemArray;
    }

    public void setSatisfactionPerItemArray(int[] satisfactionPerItemArray) {
        this.satisfactionPerItemArray = satisfactionPerItemArray;
    }

    public int[] getTimeAvailablePerItem() {
        return timeAvailablePerItem;
    }

    public void setTimeAvailablePerItem(int[] timeAvailablePerItem) {
        this.timeAvailablePerItem = timeAvailablePerItem;
    }

    private void addItemSatisfaction() {
        if(this.satisfactionPerItemArray == null){
            //todo implement this
        }
    }
}
