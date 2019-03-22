package iii_conventions

fun iterateOverCollection(collection: Collection<Int>) {
    collection.forEach { element ->
    }
}

fun iterateOverString() {
    // You can iterate over anything that has an 'iterator' method, member or extension.
    "abcd".forEach { c ->
    }
    "abcd".iterator() //library extension method
}

fun iterateOverRange() {
    (1..10).forEach { i ->
    }
    ('a'..'z').forEach { c ->
    }
}

fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}
