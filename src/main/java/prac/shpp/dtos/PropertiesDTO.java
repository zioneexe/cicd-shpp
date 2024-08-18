package prac.shpp.dtos;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class PropertiesDTO {

    private String minimumNumber;

    private String maximumNumber;

    private String step;
}
