package ii_collections

fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {
    val groupsByLength = collection.groupBy { s ->
        s?.filter { s != null }.length
    }

    return groupsByLength.values.maxBy { group -> group.size }
}

