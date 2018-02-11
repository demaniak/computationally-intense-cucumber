package coetzee.hendrik.cic.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Represents the destination to which a {@link CicEntity} was/is to be sent.
 * "Entity" is an awkward name (collides with for example, {@link javax.persistence.Entity}), so 
 * now we have the even more awkward name of EntityEntity.
 *
 */
@javax.persistence.Entity
@Table(name = "Entity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityEntity {
    /**
     * A note here: personally I would rather go with a UUID as ID.
     * Just opens up so many good options.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long entityId;

    @NotNull
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String entityName;

    @Email
    @NotNull
    private String emailAddress;
}
