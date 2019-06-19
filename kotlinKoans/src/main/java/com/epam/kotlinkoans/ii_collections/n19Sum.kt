package ii_collections

fun example6() {
    listOf(1, 3).sum() == 4
    listOf("a", "b", "cc").sumBy { it.length } == 4
}

// Return the sum of prices of all products that a customer has ordered.
// Note: a customer may order the same product several times.
fun Customer.getTotalOrderPrice() = orders.sumByDouble { it.products.sumByDouble { it.price } }