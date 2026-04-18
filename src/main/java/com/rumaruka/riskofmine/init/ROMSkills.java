package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.skills.artificer.FlameBolt;
import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
import com.rumaruka.riskofmine.common.skills.commando.PhaseRound;
import com.rumaruka.riskofmine.common.skills.commando.SuppressiveFire;
import com.rumaruka.riskofmine.common.skills.commando.TacticalDive;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ROMSkills {


    //Skills for Commando
    @RegistryName("double_tap")
    DoubleTap DOUBLE_TAP = new DoubleTap(0);
    @RegistryName("phase_round")
    PhaseRound PHASE_ROUND = new PhaseRound(5);
    @RegistryName("tactical_dive")
    TacticalDive TACTICAL_DIVE = new TacticalDive(4);
    @RegistryName("suppressive_fire")
    SuppressiveFire SUPPRESSIVE_FIRE = new SuppressiveFire(9);


    //Skills for Artificer
    @RegistryName("flame_bolt")
    FlameBolt FLAME_BOLT = new FlameBolt(1);
}
