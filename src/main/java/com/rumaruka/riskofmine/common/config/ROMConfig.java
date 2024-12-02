package com.rumaruka.riskofmine.common.config;


import org.zeith.hammerlib.annotations.SetupConfigs;
import org.zeith.hammerlib.util.configured.ConfigFile;
import org.zeith.hammerlib.util.configured.ConfiguredLib;
import org.zeith.hammerlib.util.configured.data.IntValueRange;
import org.zeith.hammerlib.util.configured.types.ConfigCategory;


public class ROMConfig {
    public static ConfigFile config;

    private static ConfigCategory gameplay;


    public static int cooldownEq;
    public static int TIME_UPDATE_TIMER; //DEFAULT after 15 minute
    public static int priceSmallChest;
  //  public IQuickConfigValue<Integer> size_default;

    @SetupConfigs
    public static void reloadCustom(ConfigFile cfgs)
    {
       config=cfgs;
        gameplay = cfgs.setupCategory("Gameplay");
        {
            cooldownEq = gameplay.getElement(ConfiguredLib.INT,"Cooldown").withRange(IntValueRange.range(1, Integer.MAX_VALUE)).withDefault(10).getValue().intValue();
            priceSmallChest = gameplay.getElement(ConfiguredLib.INT,"Price Small").withRange(IntValueRange.range(1, Integer.MAX_VALUE)).withDefault(25).getValue().intValue();
            TIME_UPDATE_TIMER =gameplay.getElement(ConfiguredLib.INT,"Updated Timer").withRange(IntValueRange.range(5, Integer.MAX_VALUE)).withDefault(15).getValue().intValue();
        }
    }



}
