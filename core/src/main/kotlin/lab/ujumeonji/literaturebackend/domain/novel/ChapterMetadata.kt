package lab.ujumeonji.literaturebackend.domain.novel

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.util.*

@Entity
@Table(name = "chapter_metadata")
class ChapterMetadata(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<ChapterMetadata> = EntityId(UUID.randomUUID()),
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    var chapter: Chapter,
    @Column(nullable = false)
    var viewCount: Int = 0,
    @Column(nullable = false)
    var commentCount: Int = 0,
    @Column(nullable = false)
    var likeCount: Int = 0,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<ChapterMetadata>>()
