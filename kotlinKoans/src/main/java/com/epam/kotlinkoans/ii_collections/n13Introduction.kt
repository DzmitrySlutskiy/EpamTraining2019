package ii_collections

fun Shop.getSetOfCustomers(): Set<Customer> {
    return this.customers.toSet()
}

