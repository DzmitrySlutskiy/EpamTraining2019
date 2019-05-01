package ii_collections

import java.util.*

fun example0(list: List<Int>) {
    list.toSet()

    list.toCollection(HashSet<Int>())
}

fun Shop.getSetOfCustomers(): Set<Customer> {
    return this.customers.toSet()
}

