package lab.ujumeonji.literaturebackend.domain.novel

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "chapters")
@SQLDelete(sql = "update chapters set deleted_at = current_timestamp where id = ?")
@SQLRestriction("deleted_at IS NULL")
class Chapter(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<Chapter> = EntityId(UUID.randomUUID()),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id", nullable = false)
    var novel: Novel,
    @Column(nullable = false)
    var episode: Int,
    @Column(nullable = false)
    var title: String,
    @Column(columnDefinition = "text")
    var description: String? = null,
    @OneToMany(mappedBy = "chapter", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var chapterTexts: MutableList<ChapterText> = mutableListOf(),
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: ChapterStatus,
    @Column(nullable = false)
    var editedAt: LocalDateTime,
    @Column
    var approvedAt: LocalDateTime? = null,
    // 기타 필요한 필드 및 관계
) : BaseEntity<EntityId<Chapter>>() {
    // 기존 메서드들은 필요에 따라 Kotlin 스타일로 변환하여 추가
}
