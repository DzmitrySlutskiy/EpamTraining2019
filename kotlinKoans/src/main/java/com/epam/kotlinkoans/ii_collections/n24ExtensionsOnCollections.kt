package ii_collections

fun todoTask24(collection: Collection<String>): Collection<String> {

//    Map<Integer, List<String>> groupsByLength = Maps.newHashMap();
//    for (String s : collection) {
//        List<String> strings = groupsByLength.get(s.length());
//        if (strings == null) {
//            strings = Lists.newArrayList();
//            groupsByLength.put(s.length(), strings);
//        }
//        strings.add(s);
//    }
//
//    int maximumSizeOfGroup = 0;
//    for (List<String> group : groupsByLength.values()) {
//        if (group.size() > maximumSizeOfGroup) {
//            maximumSizeOfGroup = group.size();
//        }
//    }
//
//    for (List<String> group : groupsByLength.values()) {
//        if (group.size() == maximumSizeOfGroup) {
//            return group;
//        }
//    }
//    return null;
}


//        TODO(
//    """
//        Task 24.
//        The function should do the same as '_24_JavaCode.doSomethingStrangeWithCollection'.
//        Replace all invocations of 'todoTask24()' with the appropriate code.
//    """,
//    references = { c: Collection<String> -> _24_JavaCode().doSomethingStrangeWithCollection(c) }
//)

fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {
    val groupsByLength = collection.groupBy { s -> todoTask24() }

    return groupsByLength.values.maxBy { group -> todoTask24() }
}

