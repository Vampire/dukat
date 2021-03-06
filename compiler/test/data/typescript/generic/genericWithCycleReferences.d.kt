// [test] genericWithCycleReferences.kt
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

external interface S<D, T : () -> S__1<D>>

external interface S__1<D> : S<D, () -> S__1<D>>

external interface Chain<T, D>

external interface Chain__1<T> : Chain<T, T>

external interface ArrayChain<G> : Chain__1<Array<G>>

external interface Component<S, T>

external interface FrameworkElement<P, T> {
    var type: T
    var props: P
}

external interface FrameworkElement__0 : FrameworkElement<Any, dynamic /* String | (props: Any) -> FrameworkElement__0? | Any */>