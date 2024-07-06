package similarity.metric;

import java.util.Set;
import profile.Profile;

public class PearsonSigWeightingMetric implements SimilarityMetric {
    private final int threshold;

    public PearsonSigWeightingMetric(int threshold) {
        this.threshold = threshold;
    }

    public double getSimilarity(final Profile p1, final Profile p2) {
        double sum_r1 = 0;
        double sum_r1_sq = 0;
        double sum_r2 = 0;
        double sum_r2_sq = 0;
        double sum_r1_r2 = 0;

        Set<Integer> common = p1.getCommonIds(p2);
        for (Integer id : common) {
            double r1 = p1.getValue(id).doubleValue();
            double r2 = p2.getValue(id).doubleValue();

            sum_r1 += r1;
            sum_r1_sq += r1 * r1;
            sum_r2 += r2;
            sum_r2_sq += r2 * r2;
            sum_r1_r2 += r1 * r2;
        }

        double numerator = sum_r1_r2 - (sum_r1 * sum_r2) / common.size();
        double denominator = Math.sqrt((sum_r1_sq - (sum_r1 * sum_r1) / common.size()) * (sum_r2_sq - (sum_r2 * sum_r2) / common.size()));

        double similarity = (denominator > 0) ? numerator / denominator : 0;

        if (common.size() < threshold) {
            similarity *= ((double) common.size() / threshold);
        }

        return similarity;
    }
}
