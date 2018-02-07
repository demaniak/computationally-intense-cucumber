package coetzee.hendrik.cic.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import coetzee.hendrik.cic.entities.EntityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CicRegistration {
    @NotNull
    @Size(min = 1, max = 128)    
    private String cicType;

    @Size(min = 0, max = 4096)
    @NotNull    
    private String subject;

    @NotNull
    @Size(min = 0, max = 10485760)    
    private String body;

    @NotNull
    @Size(min = 1, max = 128)    
    private String sourceSystem;
    
    @NotNull
    private Long cicTimestamp;

    @NotNull
    private EntityEntity entity;
}
