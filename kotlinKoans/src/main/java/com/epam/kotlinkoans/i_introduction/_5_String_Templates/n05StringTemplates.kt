package i_introduction._5_String_Templates

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun task5(): String = """\d{2} ${month} \d{4}"""
