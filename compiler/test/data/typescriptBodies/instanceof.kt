// [test] instanceof.kt
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

fun f(isSet: Boolean) {
    var x: dynamic /* Set<Number> | Array<Number> */
    if (isSet) {
        x = mutableSetOf()
    } else {
        x = arrayOf()
    }
    if (x is Set) {
        console.log(x.contains(5))
    }
    if (x is Array) {
        x = x + 5
    }
}