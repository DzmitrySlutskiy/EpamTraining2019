package ii_collections

fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {
    val groupsByLength = collection.groupBy { s -> s.length }

    return groupsByLength.values.maxBy { group -> group.size }
}

