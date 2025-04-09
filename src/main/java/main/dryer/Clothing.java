package main.dryer;

import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Clothing {
    private String brand;
    private String clothingType;
    private char size;

    @Builder.Default
    private Set<DryingProgram> dryingPrograms = new HashSet<>();
}
