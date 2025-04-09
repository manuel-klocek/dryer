package main.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import main.dryer.Clothing;
import main.dryer.Dryer;
import main.dryer.DryerRotationSpeed;
import main.dryer.DryingProgram;

public class Utility {

    public static List<DryingProgram> generateWashingProgramMockData() {
        return List.of(
                DryingProgram.builder()
                        .name("Eco Washing Program")
                        .dryerRotationSpeed(DryerRotationSpeed.SLOW)
                        .durationInHours(2.5f)
                        .build(),
                DryingProgram.builder()
                        .name("Basic Washing Program")
                        .dryerRotationSpeed(DryerRotationSpeed.MEDIUM)
                        .durationInHours(2f)
                        .build(),
                DryingProgram.builder()
                        .name("Premium Washing Program")
                        .dryerRotationSpeed(DryerRotationSpeed.FAST)
                        .durationInHours(1.5f)
                        .build(),
                DryingProgram.builder()
                        .name("Quick Washing Program")
                        .dryerRotationSpeed(DryerRotationSpeed.TURBO)
                        .durationInHours(0.75f)
                        .build()
        );
    }

    //using ArrayList to have a mutable list
    public static ArrayList<Clothing> generateClothingMockData() {
        return new ArrayList<>(List.of(
                Clothing.builder()
                        .brand("Neki")
                        .clothingType("Shirt")
                        .size('S')
                        .build(),
                Clothing.builder()
                        .brand("Pamu")
                        .clothingType("Shorts")
                        .size('M')
                        .build(),
                Clothing.builder()
                        .brand("Giccu")
                        .clothingType("Cap")
                        .size('L')
                        .build(),
                Clothing.builder()
                        .brand("Robeek")
                        .clothingType("Shoes")
                        .size('M')
                        .build()
        ));
    }

    public static String formatFinalStatistics(Dryer dryer) {
        return "Final Statistics:\n" +
                "\t\t\t\t\t\t\t   Machine Installation Date: " + Utility.formatDate(dryer.getInstallationDate()) + "\n" +
                "\t\t\t\t\t\t\t   Total Power Used: " + dryer.getTotalPowerUsed() + " kW/h\n" +
                "\t\t\t\t\t\t\t   Total Cost: " + dryer.getPowerMeasurementUnit().getTotalCost(dryer.getTotalPowerUsed()) + " EUR\n";
    }

    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "N/A";
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) : "N/A";
    }

    public static long parseFloatHourToMinutes(float minutes) {
        String decimals = "0." + Float.toString(minutes).split("\\.")[1];
        return (long) (Float.parseFloat(decimals) * 60);
    }

    public static LocalDateTime getCurrentTimePlusDuration(float duration) {
        return LocalDateTime.now().plusHours((long) duration).plusMinutes(parseFloatHourToMinutes(duration));
    }
}
