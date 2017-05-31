package com.ledevs.bromate.app.dependencies.mapper

interface Mapper<in I, out O> {
  fun convert(input: I): O
}
