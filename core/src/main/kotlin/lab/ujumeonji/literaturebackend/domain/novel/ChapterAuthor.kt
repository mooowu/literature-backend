package lab.ujumeonji.literaturebackend.domain.novel

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.util.*

@Entity
@Table(name = "chapter_authors")
class ChapterAuthor(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<ChapterAuthor> = EntityId(UUID.randomUUID()),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    var chapter: Chapter,
    @Column(nullable = false)
    var name: String,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<ChapterAuthor>>()
