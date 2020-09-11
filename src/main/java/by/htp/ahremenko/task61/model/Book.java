package java.by.htp.ahremenko.task61.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Book extends Entity {
    private String name;
    private Integer creationYear;
    private Genre genre;
    private Author author;
}
