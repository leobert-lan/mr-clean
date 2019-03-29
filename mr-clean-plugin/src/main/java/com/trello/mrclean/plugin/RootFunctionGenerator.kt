package com.trello.mrclean.plugin

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier

class RootFunctionGenerator {
  fun createDebugRootFunction(packageName: String): FileSpec {
    val rootFunction = FunSpec.builder("sanitizedToString")
        .addModifiers(KModifier.INTERNAL)
        .receiver(Any::class)
        .returns(String::class)
        .apply {
          addStatement("return toString()")
        }
        .build()
    return FileSpec.builder(packageName, "RootSanitizeFunction")
        .addFunction(rootFunction)
        .addComment("This is the root function that generated functions will overload")
        .build()
  }

  fun createProdRootFunction(packageName: String): FileSpec {
    val rootFunction = FunSpec.builder("sanitizedToString")
        .addModifiers(KModifier.INTERNAL)
        .receiver(Any::class)
        .returns(String::class)
        .apply {
          addStatement("return error(%S)", "No function generated! Make sure to annotate with @Sanitize")
        }
        .build()
    return FileSpec.builder(packageName, "RootSanitizeFunction")
        .addFunction(rootFunction)
        .addComment("This is the root function that generated functions will overload")
        .build()
  }
}