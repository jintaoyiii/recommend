package similarity.metric;

import profile.Profile;
import java.util.Set;

public class MeanSquaredDifferenceMetric implements SimilarityMetric {

    private final double rMax;
    private final double rMin;

    public MeanSquaredDifferenceMetric(double rMin, double rMax) {
        this.rMin = rMin;
        this.rMax = rMax;
    }

    @Override
    public double getSimilarity(final Profile p1, final Profile p2) {
        Set<Integer> commonIds = p1.getCommonIds(p2);
        if (commonIds.isEmpty()) {
            return 0.000;
        }

        double sumSquaredDiffs = 0.0;
        for (Integer id : commonIds) {
            Double rating1 = p1.getValue(id);
            Double rating2 = p2.getValue(id);
            if (rating1 != null && rating2 != null) {
                double diff = rating1 - rating2;
                sumSquaredDiffs += diff * diff;
            }
        }
        double msd = sumSquaredDiffs / commonIds.size();
        double rangeSquared = Math.pow(rMax - rMin, 2);
        return 1 - (msd / rangeSquared);
    }
}
