package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ROMSkills {

    @RegistryName("double_tap")
    DoubleTap DOUBLE_TAP = new DoubleTap();
}
