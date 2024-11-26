package com.rumaruka.riskofmine.init;

import org.zeith.hammeranims.api.geometry.IGeometryContainer;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;


@SimplyRegister
public interface ROMModels {
    @RegistryName("small_chest")
    IGeometryContainer SMALL_CHEST = IGeometryContainer.createNoSuffix();
    @RegistryName("multi_shop_open")
    IGeometryContainer MULTI_SHOP_OPEN = IGeometryContainer.createNoSuffix();
    @RegistryName("sticky_bomb")
    IGeometryContainer STICKY_BOMB = IGeometryContainer.createNoSuffix();

    @RegistryName("zeith")
    IGeometryContainer ZEITH = IGeometryContainer.createNoSuffix();
}

