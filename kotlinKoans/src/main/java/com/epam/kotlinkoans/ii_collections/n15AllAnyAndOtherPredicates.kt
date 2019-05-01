package ii_collections

fun Customer.isFrom(city: City): Boolean {
    return this.city.equals(city)
}

fun Shop.checkAllCustomersAreFrom(city: City): Boolean {
    return this.customers.all { it.city == city }
}

fun Shop.hasCustomerFrom(city: City): Boolean {
    return this.customers.any { it.city == city }
}

fun Shop.countCustomersFrom(city: City): Int {
    return this.customers.count { it.city == city }
}

fun Shop.findFirstCustomerFrom(city: City): Customer? {
    return this.customers.find { it.city == city }
}
