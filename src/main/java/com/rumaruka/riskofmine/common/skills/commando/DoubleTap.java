package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.Survivors;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import net.minecraft.world.InteractionResult;

public class DoubleTap extends SkillBase {
    public DoubleTap( ) {
        super(Survivors.COMMANDO,SkillType.PRIMARY);
    }



    private void onEntityAttack(){

    }


}
