package com.epam.cleancode.codequalitymetrics.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface RestaurantSatisfactionService {

    /**
     * Returns maximum satisfaction that someone can get given the following input file format.
     *
     *  [totalTime]:[Number of items on menu(n)]
     *  [amount of satisfaction from eating dish 1]:[time taken to eat dish 1]
     *  [amount of satisfaction from eating dish 2]:[time taken to eat dish 2]
     *  ....
     *  [amount of satisfaction from eating dish n]:[time taken to eat dish n]
     *
     * @param filePath The path of input file
     * @return maximum satisfaction
     */
    int getMaxSatisfaction(String filePath) throws IOException, URISyntaxException;
}
