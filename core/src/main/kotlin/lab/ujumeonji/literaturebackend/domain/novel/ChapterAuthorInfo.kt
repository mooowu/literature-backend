package lab.ujumeonji.literaturebackend.domain.novel

import lab.ujumeonji.literaturebackend.domain.common.EntityId

// EntityId value class 사용

data class ChapterAuthorInfo(
    val chapterAuthorId: EntityId<ChapterAuthor>,
    val contributorId: EntityId<lab.ujumeonji.literaturebackend.domain.contributor.Contributor>,
    val accountId: EntityId<lab.ujumeonji.literaturebackend.domain.account.Account>
) {
    companion object {
        fun from(chapterAuthor: ChapterAuthor): ChapterAuthorInfo =
            ChapterAuthorInfo(
                chapterAuthor.id,
                chapterAuthor.contributorId,
                chapterAuthor.accountId,
            )
    }
} 
