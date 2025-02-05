/**
 * A class to compute a non-personalised prediction for the target item (mean item rating).
 */

package alg.ub.predictor;

import java.util.Map;

import alg.ub.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class SimpleNonPersonalisedPredictor implements Predictor
{
	/**
	 * constructor - creates a new NonPersonalisedPredictor object
	 */
	public SimpleNonPersonalisedPredictor()
	{
	}

	/**
	 * @returns the target user's predicted rating for the target item or null if a prediction cannot be computed
	 * @param userId - the numeric ID of the target user
	 * @param itemId - the numerid ID of the target item
	 * @param userProfileMap - a map containing user profiles
	 * @param itemProfileMap - a map containing item profiles
	 * @param neighbourhood - a Neighbourhood object
	 * @param simMap - a SimilarityMap object containing item-item similarities
	 */
	public Double getPrediction(final Integer userId, final Integer itemId, final Map<Integer,Profile> userProfileMap, final Map<Integer,Profile> itemProfileMap, final Neighbourhood neighbourhood, final SimilarityMap simMap)	
	{
		Profile p = itemProfileMap.get(itemId);
		return (p == null) ? null : p.getMeanValue();
	}
}

