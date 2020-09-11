package java.by.htp.ahremenko.task61.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Author extends Entity{
    private String name;
}
