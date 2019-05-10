package org.jetbrains.dukat.tsmodel.types

data class TypeDeclaration(
        val value: String,
        val params: List<ParameterValueDeclaration>,
        override var nullable: Boolean = false,
        override var meta: ParameterValueDeclaration? = null
) : ParameterValueDeclaration