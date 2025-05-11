package lab.ujumeonji.literaturebackend.domain.account

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.util.*

@Entity
@Table(name = "accounts")
class Account(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<Account> = EntityId(UUID.randomUUID()),
    @Column(nullable = false)
    var username: String,
    @Column(nullable = false)
    var email: String,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<Account>>()
