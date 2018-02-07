package coetzee.hendrik.cic.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Carries the actual message data.
 * 
 *
 */
@Entity
@Table(name = "Cic")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CicEntity {
    /**
     * A note here: personally I would rather go with a UUID as ID.
     * Just opens up so many good options. Do I hear "scaling"?
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cicId;

    @NotNull
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String cicType;

    @Size(min = 0, max = 4096)
    @NotNull
    @Column(length = 4096)
    private String subject;

    @NotNull
    @Size(min = 0, max = 10485760)
    @Column(length = 10485760)
    private String body;

    @NotNull
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String sourceSystem;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar cicTimestamp;

    @ManyToOne
    private coetzee.hendrik.cic.entities.EntityEntity entity;
}
