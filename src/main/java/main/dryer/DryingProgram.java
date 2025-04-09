package main.dryer;

import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DryingProgram {
    private String name;
    private DryerRotationSpeed dryerRotationSpeed;
    private float durationInHours;

    // n:m bidirectional relationship
    @Builder.Default
    private Set<Clothing> clothes = new HashSet<>();

    public void addClothing(Clothing clothing) {
        clothes.add(clothing);
        clothing.getDryingPrograms().add(this);
    }
}
