package com.epam.cleancode.singleresponsibility;

import java.util.ArrayList;
import java.util.List;

interface IInputParser<T,U> {
    void validate(T data);
    List<U> parse(T data);
}

public class Calculator implements IInputParser<String, Integer> {
    private static final String INPUT_DATA_SEPARATOR = ",";
    enum Operation{
        ADD,
        MULTIPLY
    }

    @Override
    public void validate(String data) {
        if (data == null || data.matches("[\\d" + INPUT_DATA_SEPARATOR + "]*") == false) {
            throw new WrongFormatException();
        }
    }

    @Override
    public List<Integer> parse(String data) {
        ArrayList<Integer> arguments = new ArrayList<Integer>();
        for (String eachArg: data.split(INPUT_DATA_SEPARATOR + "+")) {
            if(eachArg.matches("\\d+")) {
                arguments.add(Integer.valueOf(eachArg));
            }
        }

        return arguments;
    }

    Integer calculate(Operation operator, List<Integer> arguments) {
        Integer result = operator == Operation.ADD ? 0 : 1;
        for(Integer num : arguments){
            if(operator == Operation.MULTIPLY) {
                result *= num;
            }
            else if(operator == Operation.ADD){
                result += num;
            }
            else{
                throw new WrongFormatException();
            }
        }

        return result;
    }

    public String add(String numbers) {
        validate(numbers);
        Integer sum = calculate(Operation.ADD, parse(numbers));

        return "sum: " + String.valueOf(sum);
    }

    public String multiply(String numbers) {
        validate(numbers);
        Integer multi = calculate(Operation.MULTIPLY, parse(numbers));

        return "product: " + String.valueOf(multi);
    }
}
