package main.main;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import main.dryer.Clothing;
import main.dryer.Dryer;
import main.utility.Utility;


@Slf4j
public class Application {
    public static void main(String[] args) {
        Dryer dryer = Dryer.builder()
                .idCode('A')
                .name("Sumsang Dryer H100")
                .installationDate(LocalDate.of(2022, 10, 22))
                .flags((byte) 0b00101101)
                .build();

        dryer.getDryingPrograms().addAll(Utility.generateWashingProgramMockData());
        ArrayList<Clothing> clothes = Utility.generateClothingMockData();

        //dynamic to save lines and for better readability
        dryer.getDryingPrograms().forEach(item ->
                    item.addClothing(clothes.removeFirst())
        );

        // Tries to start drying but fails due to power off
        dryer.start(dryer.getDryingPrograms().getFirst());

        dryer.togglePower();

        dryer.getDryingPrograms().forEach(dryer::start);
    }
}
