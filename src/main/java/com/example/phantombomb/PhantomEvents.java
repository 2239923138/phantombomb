package com.example.phantombomb;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(
        modid = PhantomBombMod.MODID
)
public class PhantomEvents {

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event)
    {
        if (!(event.getEntity() instanceof Phantom phantom))
            return;

        if (phantom.level().isClientSide())
            return;

        // --- Find the nearest survival-mode player within follow range ---
        ServerPlayer player =
                (ServerPlayer) phantom.level()
                        .getNearestPlayer(phantom, ModConfig.FOLLOW_RANGE);

        if (player == null
                || player.isCreative()
                || player.isSpectator())
            return;

        double distanceSq = phantom.distanceToSqr(player);
        double explodeSq  = ModConfig.EXPLOSION_RADIUS * ModConfig.EXPLOSION_RADIUS;

        if (distanceSq <= explodeSq)
        {
            // Close enough – explode with fire, then remove the phantom
            explode(phantom);
        }
        else
        {
            // Fly aggressively toward the player like a missile
            chargeAtPlayer(phantom, player);
        }
    }

    /**
     * Charges the phantom toward the given player, overriding its normal
     * circling AI with a direct missile-like trajectory.
     */
    private static void chargeAtPlayer(Phantom phantom, ServerPlayer player)
    {
        Vec3 toPlayer = player.position()
                .subtract(phantom.position())
                .normalize();

        phantom.setTarget(player);
        phantom.setDeltaMovement(toPlayer.scale(ModConfig.CHARGE_SPEED));

        // Make the phantom face the player for visual effect
        phantom.lookAt(player, 30.0F, 30.0F);
    }

    /**
     * Creates an explosion (with fire when enabled) at the phantom's
     * location, then removes the phantom entity.
     *
     * Uses {@link Level.ExplosionInteraction#TNT} so that fire blocks
     * are placed on surrounding surfaces, just like a TNT blast.
     */
    private static void explode(Phantom phantom)
    {
        Level level = phantom.level();
        Level.ExplosionInteraction interaction =
                ModConfig.EXPLOSION_FIRE
                        ? Level.ExplosionInteraction.TNT
                        : Level.ExplosionInteraction.BLOCK;

        level.explode(
                phantom,
                phantom.getX(),
                phantom.getY(),
                phantom.getZ(),
                ModConfig.EXPLOSION_POWER,
                ModConfig.EXPLOSION_FIRE,
                interaction
        );

        phantom.discard();
    }
}
