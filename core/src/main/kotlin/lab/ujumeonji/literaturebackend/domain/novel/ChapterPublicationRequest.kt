package lab.ujumeonji.literaturebackend.domain.novel

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.util.*

@Entity
@Table(name = "chapter_publication_requests")
class ChapterPublicationRequest(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<ChapterPublicationRequest> = EntityId(UUID.randomUUID()),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    var chapter: Chapter,
    @Column(nullable = false)
    var status: ChapterPublicationRequestStatus,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<ChapterPublicationRequest>>()
