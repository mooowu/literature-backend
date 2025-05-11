package lab.ujumeonji.literaturebackend.domain.novel

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.util.*

@Entity
@Table(name = "novel_tags")
class NovelTag(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<NovelTag> = EntityId(UUID.randomUUID()),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id", nullable = false)
    var novel: Novel,
    @Column(nullable = false)
    var name: String,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<NovelTag>>()
