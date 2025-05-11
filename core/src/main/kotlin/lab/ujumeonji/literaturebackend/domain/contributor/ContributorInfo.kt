package lab.ujumeonji.literaturebackend.domain.contributor

import lab.ujumeonji.literaturebackend.domain.common.EntityId
import java.time.LocalDateTime

data class ContributorInfo(
    val contributorId: EntityId<Contributor>,
    val accountId: EntityId<lab.ujumeonji.literaturebackend.domain.account.Account>,
    val writingOrder: Int,
    val createdAt: LocalDateTime,
    val role: ContributorRole
) {
    companion object {
        fun from(contributor: Contributor): ContributorInfo =
            ContributorInfo(
                contributor.id,
                contributor.accountId,
                contributor.getWritingOrder(),
                contributor.getCreatedAt(),
                contributor.getRole(),
            )
    }
} 
