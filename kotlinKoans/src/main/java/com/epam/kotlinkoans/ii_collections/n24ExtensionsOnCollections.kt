package ii_collections

fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {
    val sortedCollection = collection.groupBy { it.length }
    return sortedCollection.values.maxBy { it.size }
}

