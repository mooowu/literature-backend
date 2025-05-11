package lab.ujumeonji.literaturebackend.domain.account

import com.github.f4b6a3.uuid.UuidCreator
import jakarta.persistence.*
import lab.ujumeonji.literaturebackend.domain.common.BaseEntity
import lab.ujumeonji.literaturebackend.support.encrypt.PasswordEncoder
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "accounts",
    indexes = [Index(name = "idx_account_email", columnList = "email")],
)
@SQLDelete(sql = "UPDATE accounts SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
class Account : BaseEntity<UUID>() {
    companion object {
        const val UNKNOWN = "알 수 없음"

        fun create(email: String, name: String, hashedPassword: String, now: LocalDateTime): Account {
            return Account(email, hashedPassword, name, now, now, null)
        }
    }

    @Id
    private var id: UUID? = null

    @Column
    private var email: String? = null

    @Column
    private var password: String? = null

    @Column
    private var name: String? = null

    protected constructor()

    private constructor(
        email: String?,
        password: String?,
        name: String?,
        createdAt: LocalDateTime,
        updatedAt: LocalDateTime,
        deletedAt: LocalDateTime?
    ) : super() {
        this.id = UuidCreator.getTimeOrderedEpoch()
        this.email = email
        this.password = password
        this.name = name
        setCreatedAt(createdAt)
        setUpdatedAt(updatedAt)
        setDeletedAt(deletedAt)
        validate()
    }

    private fun validate() {}

    fun checkPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(password, this.password)
    }

    override fun getId(): UUID? = id

    fun getEmail(): String? = email

    fun getName(): String? = name

    fun getIdValue(): AccountId? = id?.let { AccountId.from(it) }

    fun updatePassword(password: String, passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(password)
    }
} 
