package com.rumaruka.riskofmine.api.enumeration;

import net.minecraft.ChatFormatting;
import net.minecraft.util.StringRepresentable;


public enum ChestTypes implements StringRepresentable {
    COMMON(0, 5, "Common", "common", 16383998, ChatFormatting.WHITE),
    UNCOMMON(1, 4, "Uncommon", "uncommon", 6192150, ChatFormatting.DARK_GREEN),
    LEGENDARY(2, 3, "Legendary", "legendary", 11546150, ChatFormatting.RED),
    BOSS(3, 2, "Boss", "boss", 16351261, ChatFormatting.YELLOW),
    LUNAR(4, 1, "Lunar", "lunar", 3949738, ChatFormatting.BLUE),
    EQUIPMENT(5, 0, "Equipment", "equipment", 439738, ChatFormatting.GOLD),
    SCRAP(5, 0, "Equipment", "equipment", 439738, ChatFormatting.GRAY),
    VOID(6, 1, "Void", "void", 8991416, ChatFormatting.DARK_PURPLE);


    private static final ChestTypes[] META_LOOKUP = new ChestTypes[values().length];
    private static final ChestTypes[] TYPE_DMG_LOOKUP = new ChestTypes[values().length];
    private final int meta;
    private final int typeDamage;
    private final String name;
    private final String unlocalizedName;
    private final int colorValue;
    private final float[] colorComponentValues;
    private final ChatFormatting chatColor;

    ChestTypes(int metaIn, int dyeDamageIn, String nameIn, String unlocalizedNameIn, int colorValue, ChatFormatting chatColorIn) {
        this.meta = metaIn;
        this.typeDamage = dyeDamageIn;
        this.name = nameIn;
        this.unlocalizedName = unlocalizedNameIn;
        this.colorValue = colorValue;
        this.chatColor = chatColorIn;
        int i = (colorValue & 16711680) >> 16;
        int j = (colorValue & 65280) >> 8;
        int k = (colorValue & 255);
        this.colorComponentValues = new float[]{(float) i / 255.0F, (float) j / 255.0F, (float) k / 255.0F};
    }

    public int getMetadata() {
        return this.meta;
    }

    public int getDyeDamage() {
        return this.typeDamage;
    }


    public String getDyeColorName() {
        return this.name;
    }

    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }



    public int getColorValue() {
        return this.colorValue;
    }


    public float[] getColorComponentValues() {
        return this.colorComponentValues;
    }

    public static ChestTypes byDyeDamage(int damage) {
        if (damage < 0 || damage >= TYPE_DMG_LOOKUP.length) {
            damage = 0;
        }

        return TYPE_DMG_LOOKUP[damage];
    }

    public static ChestTypes byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String toString() {
        return this.unlocalizedName;
    }

    public String getName() {
        return this.name;
    }

    static {
        for (ChestTypes enumdyecolor : values()) {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
            TYPE_DMG_LOOKUP[enumdyecolor.getDyeDamage()] = enumdyecolor;
        }
    }

    public ChatFormatting getChatColor() {
        return chatColor;
    }

    @Override
    public String getSerializedName() {
        return this.unlocalizedName;
    }
}
