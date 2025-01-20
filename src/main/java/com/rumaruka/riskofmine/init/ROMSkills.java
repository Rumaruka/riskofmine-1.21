package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.skills.artificer.FlameBolt;
import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
import com.rumaruka.riskofmine.common.skills.commando.PhaseRound;
import com.rumaruka.riskofmine.common.skills.commando.SuppressiveFire;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ROMSkills {

    @RegistryName("double_tap")
    DoubleTap DOUBLE_TAP = new DoubleTap(0);
    @RegistryName("phase_round")
    PhaseRound PHASE_ROUND = new PhaseRound(5);
    @RegistryName("suppressive_fire")
    SuppressiveFire SUPPRESSIVE_FIRE = new SuppressiveFire(9);

    @RegistryName("flame_bolt")
    FlameBolt FLAME_BOLT = new FlameBolt(1);
}
