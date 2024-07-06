package alg.ub.neighbourhood;

import java.util.Set;
import similarity.SimilarityMap;

public class ThresholdNeighbourhood extends Neighbourhood {
    private double threshold;

    public ThresholdNeighbourhood(double threshold) {
        super();
        this.threshold = threshold;
    }

    @Override
    public void computeNeighbourhoods(final SimilarityMap simMap) {
        Set<Integer> userIds = simMap.getIds();
        
        for (Integer userId : userIds) {
            for (Integer otherUserId : userIds) {
                if (!userId.equals(otherUserId)) {
                    double similarity = simMap.getSimilarity(userId, otherUserId);
                    
                    if (similarity > this.threshold) {
                        this.add(userId, otherUserId);
                    }
                }
            }
        }
    }
}
