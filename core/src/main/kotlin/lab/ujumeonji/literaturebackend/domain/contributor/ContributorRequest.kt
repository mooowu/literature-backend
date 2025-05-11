package lab.ujumeonji.literaturebackend.domain.contributor

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.util.*

@Entity
@Table(name = "contributor_requests")
class ContributorRequest(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<ContributorRequest> = EntityId(UUID.randomUUID()),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor_group_id", nullable = false)
    var contributorGroup: ContributorGroup,
    @Column(nullable = false)
    var status: String,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<ContributorRequest>>()
