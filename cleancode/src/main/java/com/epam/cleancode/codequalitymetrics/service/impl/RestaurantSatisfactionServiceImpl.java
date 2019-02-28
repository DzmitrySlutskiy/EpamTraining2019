package com.epam.cleancode.codequalitymetrics.service.impl;

import com.epam.cleancode.codequalitymetrics.constants.Constants;
import com.epam.cleancode.codequalitymetrics.exception.InvalidDataException;
import com.epam.cleancode.codequalitymetrics.model.InputData;
import com.epam.cleancode.codequalitymetrics.service.RestaurantSatisfactionService;
import com.epam.cleancode.codequalitymetrics.util.FileUtils;
import com.epam.cleancode.codequalitymetrics.util.KnapsackResolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RestaurantSatisfactionServiceImpl implements RestaurantSatisfactionService {



    public int getMaxSatisfaction(String filePath) throws IOException, URISyntaxException, InvalidDataException {
        if (filePath == null || filePath.equalsIgnoreCase("")) {
            throw new InvalidDataException("Invalid File Path");
        }


        InputData inputData = processAndLoadInputData(filePath);
        int maxSatisfaction = computeMaxSatisfaction(inputData);
        return maxSatisfaction;
    }

    private InputData processAndLoadInputData(String filePath) throws IOException, URISyntaxException {
        List<String> fileData = (List<String>) FileUtils.readFile(filePath);

        InputDataProcessor inputDataProcessor = new InputDataProcessor(fileData);
        InputData inputData = inputDataProcessor.process();
        return inputData;
    }

    private int computeMaxSatisfaction(InputData inputData) {
        return new KnapsackResolver().getMaxValue(inputData);
    }

    private class InputDataProcessor {
        private InputData inputData = new InputData();
        private List<String> fileData;
        private int[] itemEatTimes;
        private int[] itemSatisfactions;

        public InputDataProcessor(List<String> fileData) {
            this.fileData = fileData;
            itemEatTimes = new int[fileData.size() - 1];
            itemSatisfactions = new int[fileData.size() - 1];
        }

        public InputData process() {
            updateMaxAvailableTimeAndNoOfItems(fileData.get(0));
            validateNoOfItems(inputData.getNoOfItemsOnMenu(), fileData.size() - 1);
            updateItemEatTimesAndItemSatisfactions();
            return inputData;
        }

        private void updateItemEatTimesAndItemSatisfactions() {
            for (int lineNum = 1; lineNum < fileData.size(); lineNum++) {
                processEachLineEntry(lineNum);
            }
            inputData.setSatisfactionPerItemArray(itemSatisfactions);
            inputData.setTimeAvailablePerItem(itemEatTimes);
        }

        private void processEachLineEntry(int lineNum) {
            String data = fileData.get(lineNum);
            validateLineEntry(data);
            updateItemSatisfactions(lineNum, data);
            updateItemEatTimes(lineNum, data);
        }

        private void validateNoOfItems(int noOfItemsMentioned, int noOfItemsFound) throws InvalidDataException {
            if (noOfItemsMentioned > noOfItemsFound) {
                throw new InvalidDataException("Found less number of items than mentioned, please check number of items given");
            }

            if (noOfItemsMentioned < noOfItemsFound) {
                throw new InvalidDataException("Found more number of items than mentioned, please check number of items given");
            }
        }

        private void updateMaxAvailableTimeAndNoOfItems(String data) {
            String[] lineData = data.split(Constants.FILE_DATA_SEPERATOR);
            validateLineEntry(data);

            inputData.setTotalTimeAvailableInMins(parseMaxEatTime(lineData[0]));
            inputData.setNoOfItemsOnMenu(parseNoOfItems(lineData[1]));
        }

        private void updateItemSatisfactions(int lineNum, String data) {
            String[] lineData = data.split(Constants.FILE_DATA_SEPERATOR);
            itemSatisfactions[lineNum - 1] = parseItemSatisfactionValue(lineData[0]);
        }

        private void updateItemEatTimes(int lineNum, String data) {
            String[] lineData = data.split(Constants.FILE_DATA_SEPERATOR);
            itemEatTimes[lineNum - 1] = parseTimeToEatItem(lineData[1]);
        }

        private int parseItemSatisfactionValue(String satisfaction) {
            int itemSatisfaction = parseStringToInteger(satisfaction);
            if (itemSatisfaction < 0) {
                throw new InvalidDataException("Item satisfaction should not be a negative number : " + itemSatisfaction);
            }
            return itemSatisfaction;
        }

        private int parseTimeToEatItem(String eatTime) {
            int itemEatTime = parseStringToInteger(eatTime);
            if (itemEatTime < 0) {
                throw new InvalidDataException("Time taken to eat an item should not be a negative number : " + itemEatTime);
            }
            return itemEatTime;
        }

        private void validateLineEntry(String entry) {
            if (entry.split(Constants.FILE_DATA_SEPERATOR).length != 2) {
                throw new InvalidDataException("'" + entry + "'" + " is invalid input entry, should be a number");
            }
        }

        private int parseMaxEatTime(String maxEatTime) {
            int maximumEatTime = parseStringToInteger(maxEatTime);
            if (maximumEatTime < 0) {
                throw new InvalidDataException("Maximum time given to eat items should not be a negative number : " + maximumEatTime);
            }
            return maximumEatTime;
        }

        private int parseNoOfItems(String noOfItems) {
            int numberOfItems = parseStringToInteger(noOfItems);
            if (numberOfItems < 0) {
                throw new InvalidDataException("Total number of items given should not be a negative number : " + numberOfItems);
            }
            return numberOfItems;
        }

        private int parseStringToInteger(String data) {
            int intData = 0;
            try {
                intData = new Integer(data);
            } catch (NumberFormatException exception) {
                throw new InvalidDataException("'" + data + "'" + " is invalid input format, should be a number");
            }
            return intData;
        }
    }
}
