package io.github.iml1s.miniscript

/**
 * Trait representing a key which can be converted to a hash type.
 */
interface MiniscriptKey {
    val isUncompressed: Boolean get() = false
    val isXOnly: Boolean
    val numDerPaths: Int get() = 0

    // Associated types are handled via generics or specific implementations in Kotlin.
    // In Rust: type Sha256, Hash256, Ripemd160, Hash160
    // We will use ByteArray or specific Hash types for these.
    
    // For simplicity in Kotlin, we might just return the hash bytes directly or wrapped types.
    // Let's assume standard ByteArray for hashes for now, or define a Hash interface if needed.
}

interface ToPublicKey : MiniscriptKey {
    fun toPublicKey(): ByteArray
    
    fun toHash256(hash: ByteArray): ByteArray
    fun toRipemd160(hash: ByteArray): ByteArray
    fun toHash160(hash: ByteArray): ByteArray
}

data class StringKey(val content: String) : MiniscriptKey {
    override val isXOnly: Boolean = false
    override fun toString(): String = content
}
