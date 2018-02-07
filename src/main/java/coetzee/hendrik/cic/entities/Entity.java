package coetzee.hendrik.cic.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Represents the destination to which a {@link Cic} was/is to be sent.
 *
 */
@javax.persistence.Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long entityId;
    
    @NotNull
    @Size(min = 1, max = 128)
    private String entityName;
    
    @Email
    @NotNull
    private String emailAddress;
}
