package alg.ub.predictor;

import java.util.Collection;
import java.util.Map;
import alg.ub.neighbourhood.Neighbourhood;
import similarity.SimilarityMap;
import profile.Profile;

public class DeviationFromUserMeanPredictor implements Predictor {

    @Override
    public Double getPrediction(final Integer userId, final Integer itemId, final Map<Integer, Profile> userProfileMap, final Map<Integer, Profile> itemProfileMap, final Neighbourhood neighbourhood, final SimilarityMap simMap) {
        if (!userProfileMap.containsKey(userId) || !itemProfileMap.containsKey(itemId)) {
            return null;
        }

        Profile user = userProfileMap.get(userId);
        double userMeanRating = user.getMeanValue();

        double weightedSum = 0;
        double sumOfSimilarities = 0;

        // Ensure that we do not attempt to iterate over a null collection
        Collection<Integer> neighbours = neighbourhood.getNeighbours(userId);
        if (neighbours == null) return null;

        for (Integer neighbourId : neighbours) {
            if (userProfileMap.containsKey(neighbourId) && userProfileMap.get(neighbourId).getValue(itemId) != null) {
                double neighbourRating = userProfileMap.get(neighbourId).getValue(itemId);
                double neighbourMeanRating = userProfileMap.get(neighbourId).getMeanValue();
                Double similarity = simMap.getSimilarity(userId, neighbourId);

                if (similarity != null) { // Check for null similarity
                    weightedSum += similarity * (neighbourRating - neighbourMeanRating);
                    sumOfSimilarities += Math.abs(similarity);
                }
            }
        }

        if (sumOfSimilarities == 0) return null;

        return userMeanRating + (weightedSum / sumOfSimilarities);
    }

}
