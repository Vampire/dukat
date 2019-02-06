package org.jetbrains.dukat.compiler.lowerings

import org.jetbrains.dukat.ast.model.nodes.ClassLikeNode
import org.jetbrains.dukat.ast.model.nodes.ClassNode
import org.jetbrains.dukat.ast.model.nodes.DocumentRootNode
import org.jetbrains.dukat.ast.model.nodes.InterfaceNode
import org.jetbrains.dukat.ast.model.nodes.MethodNode
import org.jetbrains.dukat.ast.model.nodes.PropertyNode
import org.jetbrains.dukat.ast.model.nodes.metadata.ThisTypeInGeneratedInterfaceMetaData
import org.jetbrains.dukat.astCommon.MemberDeclaration
import org.jetbrains.dukat.astCommon.TopLevelDeclaration
import org.jetbrains.dukat.tsmodel.ThisTypeDeclaration
import org.jetbrains.dukat.tsmodel.types.ParameterValueDeclaration
import org.jetbrains.dukat.tsmodel.types.TypeDeclaration

private class LowerThisType {

    private fun ClassLikeNode.convertToTypeSignature(): TypeDeclaration {
        return when(this) {
           is InterfaceNode ->  TypeDeclaration(name, emptyList(), false, ThisTypeInGeneratedInterfaceMetaData())
           is ClassNode ->  TypeDeclaration(name, emptyList(), false, ThisTypeInGeneratedInterfaceMetaData())
           else -> TypeDeclaration("Any", emptyList(), false, ThisTypeInGeneratedInterfaceMetaData())
        }
    }

    private fun ParameterValueDeclaration.lower(owner: ClassLikeNode) : ParameterValueDeclaration {
        return when (this) {
            is ThisTypeDeclaration -> owner.convertToTypeSignature()
            else -> this
        }
    }

    fun lowerMemberNode(member: MemberDeclaration, owner: ClassLikeNode): MemberDeclaration {
        return when (member) {
            is PropertyNode -> member.copy(type = member.type.lower(owner))
            is MethodNode -> member.copy(type = member.type.lower(owner))
            else -> member
        }
    }

    fun lowerInterfaceNode(declaration: InterfaceNode): InterfaceNode {
        return declaration.copy(members = declaration.members.map { lowerMemberNode(it, declaration) })
    }

    fun lowerClassNode(declaration: ClassNode): ClassNode {
        return declaration.copy(members = declaration.members.map { lowerMemberNode(it, declaration) })
    }

    fun lowerTopLevelDeclaration(declaration: TopLevelDeclaration): TopLevelDeclaration  {
        return when (declaration) {
            is InterfaceNode -> lowerInterfaceNode(declaration)
            is ClassNode -> lowerClassNode(declaration)
            else -> declaration
        }
    }

    fun lowerDocumentRoot(documentRootNode: DocumentRootNode) : DocumentRootNode {
        return documentRootNode.copy(declarations = documentRootNode.declarations.map { lowerTopLevelDeclaration(it) })
    }
}

fun DocumentRootNode.lowerThisType(): DocumentRootNode {
    return LowerThisType().lowerDocumentRoot(this)
}