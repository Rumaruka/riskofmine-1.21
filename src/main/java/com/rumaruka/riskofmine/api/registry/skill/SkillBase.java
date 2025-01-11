package com.rumaruka.riskofmine.api.registry.skill;


import com.google.common.base.Suppliers;
import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.api.Survivors;
import com.rumaruka.riskofmine.api.client.IClientSkillExtensions;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import org.zeith.hammerlib.api.fml.IRegisterListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public  class SkillBase implements IHasRegisterName,IRegisterListener {


    @Setter
    @Getter
    protected SkillType skillType;
    @Setter
    @Getter
    protected Survivors survivors;
    private List<Consumer<? extends Event>> events = new ArrayList<>();
    protected Supplier<Integer> color = Suppliers.memoize(() -> getRegisterName().toString().hashCode());
    private ResourceLocation id;
    public SkillBase(Survivors survivors,SkillType skillType) {

        this.skillType = skillType;
        this.survivors=survivors;
        initClient();
    }


    //    public void tick(SkillData data, boolean isActive)
//    {
//    }
    @Override
    public void onPostRegistered() {
        for (var listener : events) {
            NeoForge.EVENT_BUS.addListener(listener);
        }
    }
    public <T extends Event> void addListener(Consumer<T> consumer)
    {
        events.add(consumer);
    }
    public boolean is(SkillBase skill)
    {
        return skill == this;
    }
    private void initClient()
    {
        if(FMLEnvironment.dist == Dist.CLIENT && !DatagenModLoader.isRunningDataGen())
        {
            initializeClient(properties ->
            {
                if(properties == this)
                    throw new IllegalStateException("Don't extend IItemRenderProperties in your item, use an anonymous class instead.");
                this.renderProperties = properties;
            });
        }
    }
    public void initializeClient(java.util.function.Consumer<IClientSkillExtensions> consumer)
    {
    }
    private Object renderProperties;

    public Object getRenderPropertiesInternal()
    {
        return renderProperties;
    }

    @Override
    public ResourceLocation getRegisterName() {
        if(id == null)
            id = RiskOfMine.SKILLS.getKey(this);
        return id;
    }
}
