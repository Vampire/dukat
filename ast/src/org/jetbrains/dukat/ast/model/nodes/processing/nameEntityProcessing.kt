package org.jetbrains.dukat.ast.model.nodes.processing

import org.jetbrains.dukat.astCommon.IdentifierEntity
import org.jetbrains.dukat.astCommon.NameEntity
import org.jetbrains.dukat.astCommon.QualifierEntity

fun NameEntity.toNode(): NameEntity {
    return when (this) {
        is IdentifierEntity -> IdentifierEntity(value)
        is QualifierEntity -> QualifierEntity(left = left.toNode(), right = IdentifierEntity(right.value))
    }
}

private fun NameEntity.countDepth(current: Int): Int {
    return when (this) {
        is IdentifierEntity -> current + 1
        is QualifierEntity -> left.countDepth(current) + right.countDepth(current)
    }
}

fun NameEntity.process(handler: (String) -> String): NameEntity {
    return when (this) {
        is IdentifierEntity -> IdentifierEntity(handler(value))
        is QualifierEntity -> copy(left = left.process(handler), right = right.process(handler) as IdentifierEntity)
    }
}

val NameEntity.size: Int
    get() = countDepth(0)

fun String.toNameEntity(): NameEntity {
    return split(".").map { IdentifierEntity(it) }.reduce<NameEntity, IdentifierEntity> { acc, identifier -> identifier.appendRight(acc) }
}

private fun IdentifierEntity.appendLeft(qualifiedLeftNode: NameEntity): QualifierEntity {
    return when (qualifiedLeftNode) {
        is IdentifierEntity -> this.appendLeft(qualifiedLeftNode)
        is QualifierEntity -> this.appendLeft(qualifiedLeftNode)
    }
}

private fun QualifierEntity.appendLeft(qualifiedNode: NameEntity): QualifierEntity {
    return when (left) {
        is IdentifierEntity -> copy(left = (left as IdentifierEntity).appendLeft(qualifiedNode))
        is QualifierEntity -> copy(left = (left as QualifierEntity).appendLeft(qualifiedNode))
    }
}

fun NameEntity.appendLeft(qualifiedNode: NameEntity): NameEntity {
    return when (this) {
        is IdentifierEntity -> this.appendLeft(qualifiedNode)
        is QualifierEntity -> this.appendLeft(qualifiedNode)
    }
}

fun IdentifierEntity.appendLeft(identifierNode: IdentifierEntity): QualifierEntity {
    return QualifierEntity(this, identifierNode)
}

fun IdentifierEntity.appendLeft(qualifiedNode: QualifierEntity): QualifierEntity {
    val nodeLeft = qualifiedNode.left
    val left = when (nodeLeft) {
        is IdentifierEntity -> QualifierEntity(this, nodeLeft)
        is QualifierEntity -> {
            val left = nodeLeft.left
            QualifierEntity(when (left) {
                is IdentifierEntity -> this.appendLeft(left)
                is QualifierEntity -> this.appendLeft(left)
            }, nodeLeft.right)
        }
    }
    return QualifierEntity(left, qualifiedNode.right)
}

fun IdentifierEntity.appendRight(qualifiedLeftNode: NameEntity): QualifierEntity {
    return when (qualifiedLeftNode) {
        is IdentifierEntity -> this.appendRight(qualifiedLeftNode)
        is QualifierEntity -> this.appendRight(qualifiedLeftNode)
    }
}

fun IdentifierEntity.appendRight(qualifiedNode: IdentifierEntity): QualifierEntity {
    return QualifierEntity(qualifiedNode, this)
}

fun IdentifierEntity.appendRight(qualifiedNode: QualifierEntity): QualifierEntity {
    return QualifierEntity(qualifiedNode, this)
}

fun QualifierEntity.appendRight(identifierNode: IdentifierEntity): QualifierEntity {
    return QualifierEntity(this, identifierNode)
}

fun QualifierEntity.appendRight(qualifiedNode: QualifierEntity): QualifierEntity {
    val nodeLeft = qualifiedNode.left
    return when (nodeLeft) {
        is IdentifierEntity -> appendRight(nodeLeft).appendRight(qualifiedNode.right)
        is QualifierEntity -> appendRight(nodeLeft).appendRight(qualifiedNode.right)
    }
}

fun NameEntity.appendRight(qualifiedNode: NameEntity): NameEntity {
    return when (this) {
        is IdentifierEntity -> when (qualifiedNode) {
            is IdentifierEntity -> appendRight(qualifiedNode)
            is QualifierEntity -> appendRight(qualifiedNode)
        }
        is QualifierEntity -> when (qualifiedNode) {
            is IdentifierEntity -> appendRight(qualifiedNode)
            is QualifierEntity -> appendRight(qualifiedNode)
        }
    }
}

fun NameEntity.shiftRight(): NameEntity? {
    return when (this) {
        is IdentifierEntity -> null
        is QualifierEntity -> left
    }
}

fun NameEntity.leftMost(): NameEntity {
    return when (this) {
        is IdentifierEntity -> this
        is QualifierEntity -> left.leftMost()
    }
}

fun NameEntity.rightMost(): NameEntity {
    return when (this) {
        is IdentifierEntity -> this
        is QualifierEntity -> right
    }
}

fun NameEntity.shiftLeft(): NameEntity? {
    return when (this) {
        is IdentifierEntity -> null
        is QualifierEntity -> {
            val leftShifted = left.shiftLeft()
            if (leftShifted == null) {
                right
            } else {
                QualifierEntity(leftShifted, right)
            }
        }
    }
}

//TODO: this should be done somewhere near escapeIdentificators (at least code should be reused)
private fun escapeName(name: String): String {
    return name
            .replace("/".toRegex(), ".")
            .replace("-".toRegex(), "_")
            .replace("^_$".toRegex(), "`_`")
            .replace("^class$".toRegex(), "`class`")
            .replace("^var$".toRegex(), "`var`")
            .replace("^val$".toRegex(), "`val`")
            .replace("^interface$".toRegex(), "`interface`")
}

private fun unquote(name: String): String {
    return name.replace("(?:^[\"\'])|(?:[\"\']$)".toRegex(), "")
}

fun NameEntity.unquote(): NameEntity {
    return when (this) {
        is IdentifierEntity -> copy(value = escapeName(unquote(value)))
        else -> this
    }
}
