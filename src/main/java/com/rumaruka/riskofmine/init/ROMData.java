package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.api.registry.skill.SkillData;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ROMData {

    @RegistryName("skill_data_rom")
    AttachmentType<SkillData> SKILL_DATA = AttachmentType.serializable(holder ->
    {
        if(holder instanceof Player pl)
            return new SkillData(pl);
        return null;
    }).copyOnDeath().copyHandler((attachment, holder, provider) ->
    {
        if(holder instanceof Player pl)
        {
            var psd = new SkillData(pl);
            psd.deserializeNBT(provider, attachment.serializeNBT(provider));
            return psd;
        }
        return null;
    }).build();
}
