package com.rumaruka.riskofmine.api;

import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum Category implements StringRepresentable {
    DAMAGE("Damage", "damage", ChatFormatting.DARK_RED),
    SCRAP("Scrap", "scrap", ChatFormatting.GRAY),
    UTILITY("Utility", "utility", ChatFormatting.GOLD),
    HEALING("Healing", "healing", ChatFormatting.GREEN);

    @Getter
    private final String name;
    private final String unlocalizedName;
    @Getter
    private final ChatFormatting chatColor;

    Category(String name, String unlocalizedName, ChatFormatting chatColor) {
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.chatColor = chatColor;
    }

    @Override
    @NotNull
    public String getSerializedName() {
        return unlocalizedName;
    }

}
