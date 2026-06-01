package com.example.phantombomb;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(PhantomBombMod.MODID)
public class PhantomBombMod {

    public static final String MODID = "phantombomb";

    public PhantomBombMod(IEventBus modBus)
    {
        modBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }
}