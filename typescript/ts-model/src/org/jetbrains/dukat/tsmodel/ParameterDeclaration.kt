package org.jetbrains.dukat.tsmodel

import org.jetbrains.dukat.astCommon.Entity
import org.jetbrains.dukat.tsmodel.expression.ExpressionDeclaration
import org.jetbrains.dukat.tsmodel.types.ParameterValueDeclaration

data class ParameterDeclaration(
    override val name: String,
    override val type: ParameterValueDeclaration,
    override val initializer: ExpressionDeclaration?,

    val vararg: Boolean,
    val optional: Boolean
) : ConstructorParameterDeclaration