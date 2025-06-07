package com.o7solutions.androidkotlin5_6pmonline.maps_implementation

fun String?.appendIfNotBlank(s: String) = if (this != null && isNotBlank()) "$this$s" else ""

