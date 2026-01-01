package io.github.iml1s.miniscript.node

import io.github.iml1s.miniscript.AbsLockTime
import io.github.iml1s.miniscript.Miniscript
import io.github.iml1s.miniscript.MiniscriptKey
import io.github.iml1s.miniscript.RelLockTime
import io.github.iml1s.miniscript.Threshold
import io.github.iml1s.miniscript.context.ScriptContext

sealed class Terminal<Pk : MiniscriptKey, Ctx : ScriptContext> {
    class True<Pk : MiniscriptKey, Ctx : ScriptContext> : Terminal<Pk, Ctx>() {
        override fun equals(other: Any?): Boolean = other is True<*, *>
        override fun hashCode(): Int = "True".hashCode()
        override fun toString(): String = "True"
    }

    class False<Pk : MiniscriptKey, Ctx : ScriptContext> : Terminal<Pk, Ctx>() {
        override fun equals(other: Any?): Boolean = other is False<*, *>
        override fun hashCode(): Int = "False".hashCode()
        override fun toString(): String = "False"
    }

    data class PkK<Pk : MiniscriptKey, Ctx : ScriptContext>(val pk: Pk) : Terminal<Pk, Ctx>()
    data class PkH<Pk : MiniscriptKey, Ctx : ScriptContext>(val pk: Pk) : Terminal<Pk, Ctx>()
    
    // RawPkH is internal/parsing only in Rust, but we might include it. 
    // Rust: RawPkH(hash160::Hash)
    // We'll use ByteArray for now.
    data class RawPkH<Pk : MiniscriptKey, Ctx : ScriptContext>(val hash: ByteArray) : Terminal<Pk, Ctx>()

    data class After<Pk : MiniscriptKey, Ctx : ScriptContext>(val value: AbsLockTime) : Terminal<Pk, Ctx>()
    data class Older<Pk : MiniscriptKey, Ctx : ScriptContext>(val value: RelLockTime) : Terminal<Pk, Ctx>()

    data class Sha256<Pk : MiniscriptKey, Ctx : ScriptContext>(val hash: ByteArray) : Terminal<Pk, Ctx>()
    data class Hash256<Pk : MiniscriptKey, Ctx : ScriptContext>(val hash: ByteArray) : Terminal<Pk, Ctx>()
    data class Ripemd160<Pk : MiniscriptKey, Ctx : ScriptContext>(val hash: ByteArray) : Terminal<Pk, Ctx>()
    data class Hash160<Pk : MiniscriptKey, Ctx : ScriptContext>(val hash: ByteArray) : Terminal<Pk, Ctx>()

    data class Alt<Pk : MiniscriptKey, Ctx : ScriptContext>(val sub: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class Swap<Pk : MiniscriptKey, Ctx : ScriptContext>(val sub: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class Check<Pk : MiniscriptKey, Ctx : ScriptContext>(val sub: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class DupIf<Pk : MiniscriptKey, Ctx : ScriptContext>(val sub: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class Verify<Pk : MiniscriptKey, Ctx : ScriptContext>(val sub: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class NonZero<Pk : MiniscriptKey, Ctx : ScriptContext>(val sub: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class ZeroNotEqual<Pk : MiniscriptKey, Ctx : ScriptContext>(val sub: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()

    data class AndV<Pk : MiniscriptKey, Ctx : ScriptContext>(val l: Miniscript<Pk, Ctx>, val r: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class AndB<Pk : MiniscriptKey, Ctx : ScriptContext>(val l: Miniscript<Pk, Ctx>, val r: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class AndOr<Pk : MiniscriptKey, Ctx : ScriptContext>(val a: Miniscript<Pk, Ctx>, val b: Miniscript<Pk, Ctx>, val c: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    
    data class OrB<Pk : MiniscriptKey, Ctx : ScriptContext>(val l: Miniscript<Pk, Ctx>, val r: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class OrD<Pk : MiniscriptKey, Ctx : ScriptContext>(val l: Miniscript<Pk, Ctx>, val r: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class OrC<Pk : MiniscriptKey, Ctx : ScriptContext>(val l: Miniscript<Pk, Ctx>, val r: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()
    data class OrI<Pk : MiniscriptKey, Ctx : ScriptContext>(val l: Miniscript<Pk, Ctx>, val r: Miniscript<Pk, Ctx>) : Terminal<Pk, Ctx>()

    data class Thresh<Pk : MiniscriptKey, Ctx : ScriptContext>(val thresh: Threshold<Miniscript<Pk, Ctx>>) : Terminal<Pk, Ctx>()
    data class Multi<Pk : MiniscriptKey, Ctx : ScriptContext>(val thresh: Threshold<Pk>) : Terminal<Pk, Ctx>()
    data class MultiA<Pk : MiniscriptKey, Ctx : ScriptContext>(val thresh: Threshold<Pk>) : Terminal<Pk, Ctx>()
}
