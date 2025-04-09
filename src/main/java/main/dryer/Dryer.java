package main.dryer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import main.utility.Utility;

@Builder
@Getter
@Setter
@Slf4j
public class Dryer {
    private char idCode;
    private String name;
    @Builder.Default
    private boolean isPowerOn = false;
    @Builder.Default
    private short currentPowerUsage = 0;
    @Builder.Default
    private double totalPowerUsed = 0;
    @Builder.Default
    private long totalCost = 0;
    @Builder.Default
    private int minutesLeftUntilFinished = 0;
    private LocalDate installationDate;
    private LocalDateTime latestUsage;
    private byte flags;


    // composition (1:1)
    @Builder.Default
    private PowerMeasurementUnit powerMeasurementUnit = new PowerMeasurementUnit();

    // aggregation (1:n)
    @Builder.Default
    private List<DryingProgram> dryingPrograms = new ArrayList<>();

    public void togglePower() {
        isPowerOn = !isPowerOn;
        log.info("power on: {}", isPowerOn);
    }

    public void start(DryingProgram dryingProgram) {
        if(!isPowerOn) {
            log.info("Cannot start drying when power is off!");
            return;
        }

        if(dryingProgram.getClothes().isEmpty()) {
            log.info("Cannot start drying when no clothes are added!");
            return;
        }

        var rotations = dryingProgram.getDryerRotationSpeed();
        var duration = dryingProgram.getDurationInHours();

        totalPowerUsed += powerMeasurementUnit.use(rotations, duration);

        log.info("Now drying with a rotation speed of {} for the next {} hours", rotations, duration);

        var predictedFinishingTime = Utility.getCurrentTimePlusDuration(duration);

        log.info("Started: {}, predicted duration: {}, predicted finishing time: {} \n",
                Utility.formatDateTime(LocalDateTime.now()), duration, Utility.formatDateTime(predictedFinishingTime));

        log.info("-------- Timelapse --------");
        log.info("A total time of {} hours past \n", duration);

        log.info("Finished drying Clothes: {} at {}", dryingProgram.getClothes(), Utility.formatDateTime(predictedFinishingTime));

        setLatestUsage(LocalDateTime.now());

        totalCost += powerMeasurementUnit.getTotalCost(totalPowerUsed);

        log.info(Utility.formatFinalStatistics(this));
    }
}
