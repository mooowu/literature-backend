package lab.ujumeonji.literaturebackend.domain.contributor

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.util.*

@Entity
@Table(name = "contributor_groups")
class ContributorGroup(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<ContributorGroup> = EntityId(UUID.randomUUID()),
    @Column(nullable = false)
    var name: String,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<ContributorGroup>>()
