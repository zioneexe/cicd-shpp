package prac.shpp.pojo;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class CalculationProperties {

    private String minimumNumber;

    private String maximumNumber;

    private String step;
}
