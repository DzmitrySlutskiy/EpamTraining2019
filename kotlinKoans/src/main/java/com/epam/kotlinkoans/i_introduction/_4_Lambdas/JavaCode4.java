package com.epam.kotlinkoans.i_introduction._4_Lambdas;

import com.epam.kotlinkoans.util.JavaCode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.Collection;

public class JavaCode4 extends JavaCode {
    public boolean task4(Collection<Integer> collection) {
        return Iterables.any(collection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer element) {
                return element % 2 == 0;
            }
        });
    }
}