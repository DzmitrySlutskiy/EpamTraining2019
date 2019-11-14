package iii_conventions

import util.TODO
import util.doc28

fun iterateOverCollection(collection: Collection<Int>) {
    for (element in collection) {
    }
}

fun iterateOverString() {
    // You can iterate over anything that has an 'iterator' method, member or extension.
    for (c in "abcd") {
    }
    "abcd".iterator() //library extension method
}

fun iterateOverRange() {
    for (i in 1..10) {
    }
    for (c in 'a'..'z') {
    }
}

fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}
