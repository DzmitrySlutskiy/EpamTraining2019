package com.epam.cleancode.singleresponsibility;

public enum Operator {
    ADDITION() {
        @Override
        public int apply(int x1, int x2) {
            return x1 + x2;
        }
    },
    MULTIPLICATION() {
        @Override
        public int apply(int x1, int x2) {
            return x1 * x2;
        }
    };

    public abstract int apply(int x1, int x2);
}
