package lab.ujumeonji.literaturebackend.domain.novel

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "story_arcs")
@SQLDelete(sql = "update story_arcs set deleted_at = current_timestamp where id = ?")
@SQLRestriction("deleted_at IS NULL")
class StoryArc(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<StoryArc> = EntityId(UUID.randomUUID()),
    @Column(columnDefinition = "text")
    var description: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id", nullable = false)
    var novel: Novel,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var phase: StoryPhase,
    @Column
    var startChapterNumber: Int? = null,
    @Column
    var endChapterNumber: Int? = null,
) : BaseEntity<EntityId<StoryArc>>() {
    companion object {
        fun create(novel: Novel, phase: StoryPhase, now: LocalDateTime): StoryArc =
            StoryArc(
                description = null,
                novel = novel,
                phase = phase,
                startChapterNumber = null,
                endChapterNumber = null,
            )
    }

    fun updatePhase(startChapterNumber: Int?, endChapterNumber: Int?, description: String?, now: LocalDateTime) {
        this.startChapterNumber = startChapterNumber
        this.endChapterNumber = endChapterNumber
        this.description = description
        setUpdatedAt(now)
    }

    fun getLastModifiedAt(): LocalDateTime? =
        if (getCreatedAt() == getUpdatedAt()) null else getUpdatedAt()
}
