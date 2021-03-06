package org.jetrbains.dukat.nodeLowering

import org.jetbrains.dukat.ast.model.duplicate
import org.jetbrains.dukat.ast.model.nodes.ClassLikeNode
import org.jetbrains.dukat.ast.model.nodes.ClassNode
import org.jetbrains.dukat.ast.model.nodes.EnumNode
import org.jetbrains.dukat.ast.model.nodes.FunctionNode
import org.jetbrains.dukat.ast.model.nodes.InterfaceNode
import org.jetbrains.dukat.ast.model.nodes.ModuleNode
import org.jetbrains.dukat.ast.model.nodes.ObjectNode
import org.jetbrains.dukat.ast.model.nodes.TopLevelNode
import org.jetbrains.dukat.ast.model.nodes.TypeAliasNode
import org.jetbrains.dukat.ast.model.nodes.VariableNode

interface TopLevelNodeLowering {
    fun lowerVariableNode(declaration: VariableNode): VariableNode = declaration
    fun lowerFunctionNode(declaration: FunctionNode): FunctionNode = declaration
    fun lowerClassNode(declaration: ClassNode): ClassNode = declaration
    fun lowerObjectNode(declaration: ObjectNode): ObjectNode = declaration
    fun lowerInterfaceNode(declaration: InterfaceNode): InterfaceNode = declaration
    fun lowerEnumNode(declaration: EnumNode, owner: ModuleNode): EnumNode = declaration
    fun lowerTypeAliasNode(declaration: TypeAliasNode, owner: ModuleNode): TypeAliasNode = declaration

    fun lowerClassLikeNode(declaration: ClassLikeNode, owner: ModuleNode): ClassLikeNode {
        return when (declaration) {
            is InterfaceNode -> lowerInterfaceNode(declaration)
            is ClassNode -> lowerClassNode(declaration)
            is ObjectNode -> lowerObjectNode(declaration)
            else -> declaration
        }
    }

    fun lowerTopLevelEntity(declaration: TopLevelNode, owner: ModuleNode): TopLevelNode {
        return when (declaration) {
            is VariableNode -> lowerVariableNode(declaration)
            is FunctionNode -> lowerFunctionNode(declaration)
            is ClassLikeNode -> lowerClassLikeNode(declaration, owner)
            is ModuleNode -> lowerModuleNode(declaration)
            is TypeAliasNode -> lowerTypeAliasNode(declaration, owner)
            is EnumNode -> lowerEnumNode(declaration, owner)
            else -> declaration.duplicate()
        }
    }

    fun lowerTopLevelDeclarations(declarations: List<TopLevelNode>, owner: ModuleNode): List<TopLevelNode> {
        return declarations.map { declaration ->
            lowerTopLevelEntity(declaration, owner)
        }
    }

    fun lowerModuleNode(moduleNode: ModuleNode): ModuleNode {
        return moduleNode.copy(
                declarations = lowerTopLevelDeclarations(moduleNode.declarations, moduleNode)
        )
    }
}
