package prac.shpp.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class Table {

    private List<BigDecimal> header;

    private List<List<BigDecimal>> numberTable;
}
