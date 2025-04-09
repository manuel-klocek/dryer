package main.dryer;

import java.util.Map;
import lombok.Getter;

@Getter
public class PowerMeasurementUnit {
    private static final float PRICE_EUR_PER_KILOWATT = 0.405f;

    public float use(DryerRotationSpeed dryerRotationSpeed, float timeUsedInHours) {
        Map<DryerRotationSpeed, Integer> dryerRotationSpeedTokWsPerHourMap = Map.of(
                DryerRotationSpeed.SLOW, 10,
                DryerRotationSpeed.MEDIUM, 20,
                DryerRotationSpeed.FAST, 30,
                DryerRotationSpeed.TURBO, 50
        );

        return dryerRotationSpeedTokWsPerHourMap.get(dryerRotationSpeed) * timeUsedInHours;
    }

    public long getTotalCost(double totalPowerUsed) {
        return (long) (totalPowerUsed * PRICE_EUR_PER_KILOWATT);
    }
}
