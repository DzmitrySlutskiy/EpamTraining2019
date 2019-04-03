package ii_collections

fun Shop.getSetOfProductsOrderedByEachCustomer(): Set<Product> {
    return customers.fold(allOrderedProducts, { orderedByAll, customer ->
        orderedByAll.intersect(customer.orders.flatMap { it.products }.toSet())
    })
}
