package com.epam.cleancode.codequalitymetrics.util;

import com.epam.cleancode.codequalitymetrics.model.InputData;

public class KnapsackResolver {

    public int getMaxValue(InputData inputData) {
        int knapsackSize = inputData.getTotalTimeAvailableInMins();
        int[] items = inputData.getTimeAvailablePerItem();
        int[] values = inputData.getSatisfactionPerItemArray();
        if (knapsackSize <= 0) {
            return 0;
        }
        if (items == null || items.length == 0) {
            return 0;
        }
        if (values == null || values.length == 0) {
            return 0;
        }
        int noOfItems = items.length;

        int grid[][] = new int[noOfItems + 1][knapsackSize + 1];
        for (int i = 0; i <= noOfItems; i++) {
            for (int j = 0; j <= knapsackSize; j++) {
                if (i == 0 || j == 0)
                    grid[i][j] = 0;
                else if (j < items[i - 1])
                    grid[i][j] = grid[i - 1][j];
                else {
                    if (grid[i - 1][j] > values[i - 1] + grid[i - 1][j - items[i - 1]])
                        grid[i][j] = grid[i - 1][j];
                    else
                        grid[i][j] = values[i - 1] + grid[i - 1][j - items[i - 1]];
                }

            }
        }

        return grid[noOfItems][knapsackSize];
    }

}
