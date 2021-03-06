package orm;

import entities.StandPlan;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Ulokal on 29.06.2018.
 */
public class StandPlanDatabaseService extends GenericDatabaseService<StandPlan> {

    /**
     * @param term Der Suchbegriff mit dem alle Attribute der Stände durchsucht werden
     * @return Gibt eine ArrayList mit allen übereinstimmenden Ständen zurück
     */
    public ArrayList<StandPlan> search (String term) {
        ArrayList<StandPlan> allStandPlans = this.getAll(StandPlan.class);
        ArrayList<StandPlan> resultStandPlans = (ArrayList<StandPlan>) allStandPlans.stream().filter(standPlan -> standPlan.getStringWithAll().contains(term))
                .collect(Collectors.toCollection(ArrayList::new));

        return resultStandPlans;
    }
}
