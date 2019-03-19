package ii_collections

fun example() {

    val result = listOf("abc", "12").flatMap { it.toList() }

    result == listOf('a', 'b', 'c', '1', '2')
}

// Return all products this customer has ordered
val Customer.orderedProducts: Set<Product>
    get() = orders.flatMap { it.products }.toSet()

// Return all products that were ordered by at least one customer
val Shop.allOrderedProducts: Set<Product>
    get() = customers.flatMap { it.orders.flatMap { it.products } }.toSet()
