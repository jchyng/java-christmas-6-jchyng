package christmas.constant;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Badge {
    별(5_000),
    트리(10_000),
    산타(20_000);

    private final int minBenefitAmount;

    Badge(int minDiscount) {
        this.minBenefitAmount = minDiscount;
    }

    public static Optional<Badge> getBadge(int benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minBenefitAmount <= benefitAmount)
                .max(Comparator.comparingInt(badge -> badge.minBenefitAmount));
    }

}
