package com.rumaruka.riskofmine.init;

import org.zeith.hammeranims.api.geometry.IGeometryContainer;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

//
//import net.minecraftforge.fml.common.Mod;
//import ru.timeconqueror.timecore.api.registry.TimeModelRegister;
//import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
//import ru.timeconqueror.timecore.client.render.model.InFileLocation;
//
//import static com.rumaruka.riskofmine.RiskOfMine.MODID;
//
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@SimplyRegister
public interface ROMModels {
    @RegistryName("small_chest")
    IGeometryContainer SMALL_CHEST = IGeometryContainer.createNoSuffix();
    @RegistryName("multi_shop_open")
    IGeometryContainer MULTI_SHOP_OPEN = IGeometryContainer.createNoSuffix();
    @RegistryName("sticky_bomb")
    IGeometryContainer STICKY_BOMB = IGeometryContainer.createNoSuffix();
//    @AutoRegistrable
//    private static final TimeModelRegister REGISTER = new TimeModelRegister(MODID);
//
//    public static InFileLocation SMALL_CHEST = REGISTER.register("models/tile/small_chest.json");
//    public static InFileLocation STICKY_BOMB = REGISTER.register("models/entity/sticky_bomb.json");
//    public static InFileLocation MULTI_SHOP_OPEN = REGISTER.register("models/tile/multi_shop_open.json");
}

//TODO:Switch to HC rendering