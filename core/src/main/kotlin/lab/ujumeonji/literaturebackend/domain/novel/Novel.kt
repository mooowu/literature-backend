package lab.ujumeonji.literaturebackend.domain.novel

import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.EntityId
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.util.*

@Entity
@Table(name = "novels")
@SQLDelete(sql = "update novels set deleted_at = current_timestamp where id = ?")
@SQLRestriction("deleted_at IS NULL")
class Novel(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: EntityId<Novel> = EntityId(UUID.randomUUID()),
    @Column(nullable = false)
    var title: String,
    @Column
    var coverImage: String? = null,
    @Column(columnDefinition = "text")
    var synopsis: String? = null,
    @OneToMany(
        mappedBy = "novel",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true,
    )
    var tags: MutableList<NovelTag> = mutableListOf(),
    @OneToMany(
        mappedBy = "novel",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true,
    )
    var characters: MutableList<Character> = mutableListOf(),
    @OneToMany(mappedBy = "novel", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var storyArcs: MutableList<StoryArc> = mutableListOf(),
    @OneToMany(mappedBy = "novel", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var chapters: MutableList<Chapter> = mutableListOf(),
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var category: NovelCategory,
    // 생성/수정/삭제 시간 등은 BaseEntity에서 상속받거나 별도 필드로 추가
) : BaseEntity<EntityId<Novel>>() {
    fun addCharacter(name: String, description: String?, now: java.time.LocalDateTime): Character {
        val character = Character(novel = this, name = name, description = description)
        this.characters.add(character)
        return character
    }

    fun update(title: String, synopsis: String?, category: NovelCategory, tags: List<String>, now: java.time.LocalDateTime) {
        this.title = title
        this.synopsis = synopsis
        this.category = category
        addTags(tags, now)
    }

    fun updatePhase(phase: StoryPhase, startChapterNumber: Int?, endChapterNumber: Int?, description: String?, now: java.time.LocalDateTime) {
        this.storyArcs.forEach { storyArc ->
            if (storyArc.phase == phase) {
                storyArc.updatePhase(startChapterNumber, endChapterNumber, description, now)
            }
        }
    }

    fun findChapterTexts(chapterId: EntityId<Chapter>): List<ChapterText> {
        return this.chapters.firstOrNull { it.id == chapterId }?.chapterTexts ?: emptyList()
    }

    fun createChapter(orderedContributors: List<Any>, title: String?, description: String?, now: java.time.LocalDateTime): Chapter {
        val chapter =
            Chapter(
                novel = this,
                episode = this.chapters.size + 1,
                title = title ?: "",
                description = description,
                status = ChapterStatus.DRAFT,
                editedAt = now,
            )
        this.chapters.add(chapter)
        return chapter
    }

    fun replaceCharacters(commands: List<Pair<String, String?>>, now: java.time.LocalDateTime): List<Character> {
        this.characters.clear()
        val newCharacters = commands.map { (name, description) -> Character(novel = this, name = name, description = description) }
        this.characters.addAll(newCharacters)
        return newCharacters
    }

    fun updateChapter(chapterId: EntityId<Chapter>, title: String?, description: String?, now: java.time.LocalDateTime): Boolean {
        val chapter = this.chapters.firstOrNull { it.id == chapterId }
        return if (chapter != null) {
            chapter.title = title ?: chapter.title
            chapter.description = description ?: chapter.description
            chapter.editedAt = now
            true
        } else {
            false
        }
    }

    fun findDraftChapterText(chapterId: EntityId<Chapter>): ChapterText? {
        return this.chapters.firstOrNull { it.id == chapterId }?.chapterTexts?.firstOrNull { it.status == ChapterTextStatus.DRAFT }
    }
}
