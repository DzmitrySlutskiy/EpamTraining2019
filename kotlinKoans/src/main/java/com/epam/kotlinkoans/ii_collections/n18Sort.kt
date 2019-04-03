package ii_collections

fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> {
    return customers.sortedBy { it.orders.size }
}
