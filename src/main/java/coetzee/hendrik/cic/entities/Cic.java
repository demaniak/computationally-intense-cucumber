package coetzee.hendrik.cic.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * Carries the actual message data.
 * 
 *
 */
@Entity
@Data
public class Cic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cicId;

    @NotNull
    @Size(min = 1, max = 128)
    private String cicType;

    @Size(min = 0, max = 4096)
    @NotNull
    private String subject;
    
    @NotNull
    @Size(min=0)
    private String body;
    
    @NotNull
    @Size(min =1 , max = 128)
    private String sourceSystem;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar cicTimestamp;
    
    @ManyToOne
    private coetzee.hendrik.cic.entities.Entity entity;
}
