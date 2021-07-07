package ii_collections

import java.util.*

/*
 * This part of workshop was inspired by:
 * https://github.com/goldmansachs/gs-collections-kata
 */

/*
 *  There are many operations that help to transform one collection into another, starting with 'to'
 */
fun example0(list: List<Int>) {
    list.toSet()

    list.toCollection(HashSet<Int>())
}

fun Shop.getSetOfCustomers(): Set<Customer> {
    return this.customers.toSet()
}

