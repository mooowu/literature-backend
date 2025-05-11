package lab.ujumeonji.literaturebackend.domain.common

import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity<ID> : Persistable<ID> {
    var createdAt: LocalDateTime? = null

    @CreatedDate
    var persistedAt: LocalDateTime? = null

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

    var deletedAt: LocalDateTime? = null

    override fun isNew(): Boolean = persistedAt == null
}
