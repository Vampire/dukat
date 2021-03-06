// [test] genericWithDefaultNonGenericValue.kt
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
import yargs.Argv__0
import yargs.Arrrrgv__0

external interface TriggeredEvent<TDelegateTarget, TData, TCurrentTarget, TTarget>

external interface TriggeredEvent__2<TDelegateTarget, TData> : TriggeredEvent<TDelegateTarget, TData, Any, Any>

external interface EventHandlerBase<TContext, T>

typealias EventHandler<TCurrentTarget, TData> = EventHandlerBase<TCurrentTarget, TriggeredEvent__2<TCurrentTarget, TData>>

external var yargs: Argv__0

external var yarrrrgs: Arrrrgv__0

// ------------------------------------------------------------------------------------------
// [test] genericWithDefaultNonGenericValue.yargs.kt
@file:JsQualifier("yargs")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package yargs

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

external interface Argv<T> {
    fun ping(): T
}

external interface Argv__0 : Argv<Any>

external interface Arrrrgv<T> {
    fun ping(): T
}

external interface `T$0` {
    var x: String
}

external interface Arrrrgv__0 : Arrrrgv<`T$0`>

external interface `T$1` {
    var x: Number
}

external interface Rrrrgh : Arrrrgv<`T$1`>