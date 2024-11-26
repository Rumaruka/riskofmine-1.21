package com.rumaruka.riskofmine.init;

import com.google.common.collect.Lists;
import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.common.items.common.*;
import com.rumaruka.riskofmine.common.items.eqiupment.BlastShowerItem;
import com.rumaruka.riskofmine.common.items.eqiupment.TheCrowdFunderItem;
import com.rumaruka.riskofmine.common.items.gameplay.LunarCoinItem;
import com.rumaruka.riskofmine.common.items.legendary.AlienHeadItem;
import com.rumaruka.riskofmine.common.items.legendary.DioBestFriendItem;
import com.rumaruka.riskofmine.common.items.lunar.BeadsOfFealtyItem;
import com.rumaruka.riskofmine.common.items.lunar.ShapedGlassItem;
import com.rumaruka.riskofmine.common.items.scrap.CommonItemScrapItem;
import com.rumaruka.riskofmine.common.items.scrap.UnCommonItemScrapItem;
import com.rumaruka.riskofmine.common.items.uncommon.ChronobaubleItem;
import com.rumaruka.riskofmine.common.items.uncommon.HopooFeatherItem;
import com.rumaruka.riskofmine.common.items.uncommon.InfusionItem;
import com.rumaruka.riskofmine.common.items.uncommon.OldWarStealthkitItem;
import com.rumaruka.riskofmine.common.items.voiditems.SaferSpacesItem;
import com.rumaruka.riskofmine.common.items.voiditems.TentabaubleItem;
import com.rumaruka.riskofmine.common.items.voiditems.WeepingFungusItem;
import net.minecraft.world.item.Item;
import org.zeith.hammerlib.annotations.Ref;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

import java.util.ArrayList;


@SimplyRegister(creativeTabs = @Ref(value = RiskOfMine.class, field = "TAB"))
public interface ROMItems {

    @RegistryName("armor_piercing_rounds")
    ArmorPiercingRoundsItem ARMOR_PIERCING_ROUNDS = new ArmorPiercingRoundsItem();
    @RegistryName("bustling_fungus")
    BustlingFungusItem BUSTLING_FUNGUS = new BustlingFungusItem();
    @RegistryName("gasoline")
    GasolineItem GASOLINE = new GasolineItem();
    @RegistryName("infusion")
    InfusionItem INFUSION = new InfusionItem();
    @RegistryName("hopoo_feather")
    HopooFeatherItem HOPOO_FEATHER = new HopooFeatherItem();
    @RegistryName("shaped_glass")
    ShapedGlassItem SHAPED_GLASS = new ShapedGlassItem();
    @RegistryName("soldier_syringe")
    SoldierSyringeItem SOLDIER_SYRINGE = new SoldierSyringeItem();
    @RegistryName("monster_tooth")
    MonsterToothItem MONSTER_TOOTH = new MonsterToothItem();
    @RegistryName("crowbar")
    CrowbarItem CROWBAR = new CrowbarItem();
    @RegistryName("energy_drink")
    EnergyDrinkItem ENERGY_DRINK = new EnergyDrinkItem();
    @RegistryName("beads_of_fealty")
    BeadsOfFealtyItem BEADS_OF_FEALTY = new BeadsOfFealtyItem();
    @RegistryName("chronobauble")
    ChronobaubleItem CHRONOBAUBLE = new ChronobaubleItem();
    @RegistryName("blast_shower")
    BlastShowerItem BLAST_SHOWER = new BlastShowerItem();
    @RegistryName("focus_crystal")
    FocusCrystalItem FOCUS_CRYSTAL = new FocusCrystalItem();
    @RegistryName("dio_best_friend")
    DioBestFriendItem DIO_BEST_FRIEND = new DioBestFriendItem();
    @RegistryName("alien_head")
    AlienHeadItem ALIEN_HEAD = new AlienHeadItem();
    @RegistryName("old_war_stealthkit")
    OldWarStealthkitItem OLD_WAR_STEALTHKIT = new OldWarStealthkitItem();
    @RegistryName("tri_tip_dagger")
    TriTipDaggerItem TRI_TIP_DAGGER = new TriTipDaggerItem();
    @RegistryName("stun_grenade")
    StunGrenadeItem STUN_GRENADE = new StunGrenadeItem();
    @RegistryName("warbanner")
    WarbannerItem WARBANNER = new WarbannerItem();
    @RegistryName("the_crowdfunder")
    TheCrowdFunderItem THE_CROWDFUNDER = new TheCrowdFunderItem();
    @RegistryName("sticky_bomb")
    StickyBombItem STICKY_BOMB = new StickyBombItem();
    @RegistryName("topaz_broosh")
    TopazBroochItem TOPAZ_BROOCH = new TopazBroochItem();
    @RegistryName("tentabauble")
    TentabaubleItem TENTABAUBLE = new TentabaubleItem();
    @RegistryName("bison_steak")
    BisonSteakItem BISON_STEAK = new BisonSteakItem();
    @RegistryName("common_item_scrap")
    CommonItemScrapItem COMMON_ITEM_SCRAP = new CommonItemScrapItem();
    @RegistryName("uncommon_item_scrap")
    UnCommonItemScrapItem UNCOMMON_ITEM_SCRAP = new UnCommonItemScrapItem();
    @RegistryName("weeping_fungus")
    WeepingFungusItem WEEPING_FUNGUS = new WeepingFungusItem();
    @RegistryName("power_elixir")
    PowerElixirItem POWER_ELIXIR = new PowerElixirItem();
    @RegistryName("empty_bottle")
    EmptyElixirItem EMPTY_ELIXIR = new EmptyElixirItem();
    @RegistryName("tougher_times")
    TougherTimesItem TOUGHER_TIMES = new TougherTimesItem();
    @RegistryName("safer_spaces")
    SaferSpacesItem SAFER_SPACES = new SaferSpacesItem();


    @RegistryName("lunar_coin")
    LunarCoinItem LUNAR_COIN = new LunarCoinItem();

    class Items {
        public static ArrayList<Item> getAllItem() {
            return (Lists.newArrayList(

                    ARMOR_PIERCING_ROUNDS,
                    BUSTLING_FUNGUS,
                    GASOLINE,
                    INFUSION,
                    SHAPED_GLASS,
                    SOLDIER_SYRINGE,
                    MONSTER_TOOTH,
                    CROWBAR,
                    ENERGY_DRINK,
                    BEADS_OF_FEALTY,
                    CHRONOBAUBLE,
                    BLAST_SHOWER,
                    FOCUS_CRYSTAL,
                    DIO_BEST_FRIEND,
                    ALIEN_HEAD,
                    OLD_WAR_STEALTHKIT,
                    TRI_TIP_DAGGER,
                    STUN_GRENADE,
                    WARBANNER,
                    THE_CROWDFUNDER,
                    STICKY_BOMB,
                    TOPAZ_BROOCH,
                    TENTABAUBLE,
                    BISON_STEAK,
                    COMMON_ITEM_SCRAP,
                    UNCOMMON_ITEM_SCRAP,
                    HOPOO_FEATHER,
                    WEEPING_FUNGUS,
                    POWER_ELIXIR,
                    EMPTY_ELIXIR,
                    TOUGHER_TIMES,
                    SAFER_SPACES,

                    LUNAR_COIN
            ));
        }
    }
}




