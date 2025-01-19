package com.rumaruka.riskofmine.ntw;

import com.rumaruka.riskofmine.api.registry.skill.SkillData;
import com.rumaruka.riskofmine.ntw.packets.PacketSyncSkillData;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SyncSkills {
    private static SkillData CLIENT_DATA;

    public static boolean is(SkillData data)
    {
        return data == CLIENT_DATA;
    }

    public static void doCheck(Player localPlayer)
    {
        if(localPlayer == null && CLIENT_DATA != null)
        {

            CLIENT_DATA = null;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static SkillData getData()
    {
        var mcp = ROMUtils.getPlayer();
        if(CLIENT_DATA == null || CLIENT_DATA.player != mcp)
        {
            CLIENT_DATA = new SkillData(mcp);
            CLIENT_DATA.requestSync();
        }
        return CLIENT_DATA;
    }



    public static void handle(Player localPlayer, PacketSyncSkillData packet)
    {
        CLIENT_DATA = SkillData.deserialize(localPlayer, packet.getNbt());
    }
}
