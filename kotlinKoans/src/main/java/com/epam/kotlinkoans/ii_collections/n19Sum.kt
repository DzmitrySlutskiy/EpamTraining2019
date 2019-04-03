package ii_collections

fun Customer.getTotalOrderPrice(): Double {
    return orders.flatMap { it.products }.sumByDouble { it.price }
}
