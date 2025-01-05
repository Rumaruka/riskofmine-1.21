package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.api.skills.FeaturesSkills;
import com.rumaruka.riskofmine.utils.ROMUtils;

public class ROMSkills {


    public static void init(){
        RiskOfMine.logger.info("Register Custom Registeres");

        ROMUtils.registerSkill(RiskOfMine.rl("double_tap"), FeaturesSkills.DOUBLE_TAP);
        RiskOfMine.logger.info("END");
    }
}
