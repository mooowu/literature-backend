package lab.ujumeonji.literaturebackend.domain.common

import jakarta.persistence.Embeddable
import java.util.*

@JvmInline
@Embeddable
value class EntityId<T : Any>(val value: UUID) : java.io.Serializable {
    companion object {
        fun <T : Any> from(id: UUID): EntityId<T> = EntityId(id)

        fun <T : Any> from(id: String): EntityId<T> = EntityId(UUID.fromString(id))
    }

    override fun toString(): String = value.toString()
}
