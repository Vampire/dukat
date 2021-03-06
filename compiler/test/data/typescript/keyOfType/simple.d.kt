// [test] simple.kt
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

external interface MODULE_KINDMap {
    var DECLARATION_FILE: Number /* 0 */
    var SOURCE_FILE: Number /* 1 */
    var AMBIENT_MODULE: Number /* 2 */
    var NAMESPACE: Number /* 3 */
}

external var x: String /* "DECLARATION_FILE" | "SOURCE_FILE" | "AMBIENT_MODULE" | "NAMESPACE" */

external var y: Number /* 0 | 1 | 2 | 3 */