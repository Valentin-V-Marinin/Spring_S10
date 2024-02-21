package seminar10.entity;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private UUID uuid;
    @Column(name = "user_id")
    private Long userId;


    public Session() { }

    public Session(Long id, UUID uuid, Long userId) {
        this.id = id;
        this.uuid = uuid;
        this.userId = userId;
    }

    // region G&S

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // endregion


    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", userId=" + userId +
                '}';
    }
}