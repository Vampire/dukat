package org.jetbrains.dukat.astModel

import org.jetbrains.dukat.ast.model.nodes.AnnotationNode
import org.jetbrains.dukat.ast.model.nodes.GeneratedInterfaceReferenceNode
import org.jetbrains.dukat.ast.model.nodes.MergableNode
import org.jetbrains.dukat.ast.model.nodes.NameNode
import org.jetbrains.dukat.ast.model.nodes.StatementNode
import org.jetbrains.dukat.ast.model.nodes.TopLevelNode
import org.jetbrains.dukat.ast.model.nodes.TypeNode
import org.jetbrains.dukat.astCommon.MemberDeclaration
import org.jetbrains.dukat.astCommon.TopLevelDeclaration

data class FunctionModel(
        val name: NameNode,
        val parameters: List<ParameterModel>,
        val type: TypeNode,
        val typeParameters: List<TypeParameterModel>,

        val generatedReferenceNodes: MutableList<GeneratedInterfaceReferenceNode>,
        val annotations: MutableList<AnnotationNode>,

        val export: Boolean,
        val inline: Boolean,
        val operator: Boolean,

        val body: List<StatementNode>
) : MemberDeclaration, TopLevelDeclaration, MergableNode, TopLevelNode