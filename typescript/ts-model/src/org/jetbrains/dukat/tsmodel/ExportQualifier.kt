package org.jetbrains.dukat.tsmodel

import org.jetbrains.dukat.astCommon.NameEntity

sealed class ExportQualifier

data class JsModule(val name: NameEntity?, val qualifier: Boolean = false) : ExportQualifier()
object JsDefault : ExportQualifier()