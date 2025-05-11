package lab.ujumeonji.literaturebackend.domain.post

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.util.*

@Entity
@Table(name = "contributor_group_recruitments")
class ContributorGroupRecruitment(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<ContributorGroupRecruitment> = EntityId(UUID.randomUUID()),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor_group_id", nullable = false)
    var contributorGroup: lab.ujumeonji.literaturebackend.domain.contributor.ContributorGroup,
    @Column(nullable = false)
    var title: String,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<ContributorGroupRecruitment>>()
