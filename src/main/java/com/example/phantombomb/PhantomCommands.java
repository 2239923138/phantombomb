package com.example.phantombomb;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(
        modid = PhantomBombMod.MODID
)
public class PhantomCommands {

    @SubscribeEvent
    public static void registerCommands(
            RegisterCommandsEvent event
    )
    {
        event.getDispatcher().register(
                Commands.literal("phantom")

                        .requires(src -> src.hasPermission(2))

                        // /phantom power <0.1-100>
                        .then(
                                Commands.literal("power")
                                        .then(
                                                Commands.argument(
                                                        "value",
                                                        FloatArgumentType.floatArg(
                                                                0.1f,
                                                                100f
                                                        )
                                                )
                                                        .executes(ctx -> {
                                                            float power = FloatArgumentType
                                                                    .getFloat(ctx, "value");
                                                            ModConfig.EXPLOSION_POWER = power;

                                                            ctx.getSource()
                                                                    .sendSuccess(
                                                                            () -> Component.literal(
                                                                                    "Phantom explosion power set to "
                                                                                            + power),
                                                                            true
                                                                    );
                                                            return 1;
                                                        })
                                        )
                        )

                        // /phantom fire <true|false>
                        .then(
                                Commands.literal("fire")
                                        .then(
                                                Commands.argument(
                                                        "value",
                                                        BoolArgumentType.bool()
                                                )
                                                        .executes(ctx -> {
                                                            boolean fire = BoolArgumentType
                                                                    .getBool(ctx, "value");
                                                            ModConfig.EXPLOSION_FIRE = fire;

                                                            ctx.getSource()
                                                                    .sendSuccess(
                                                                            () -> Component.literal(
                                                                                    "Phantom explosion fire set to "
                                                                                            + fire),
                                                                            true
                                                                    );
                                                            return 1;
                                                        })
                                        )
                        )

                        // /phantom speed <0.1-5.0>
                        .then(
                                Commands.literal("speed")
                                        .then(
                                                Commands.argument(
                                                        "value",
                                                        FloatArgumentType.floatArg(
                                                                0.1f,
                                                                5.0f
                                                        )
                                                )
                                                        .executes(ctx -> {
                                                            float speed = FloatArgumentType
                                                                    .getFloat(ctx, "value");
                                                            ModConfig.CHARGE_SPEED = speed;

                                                            ctx.getSource()
                                                                    .sendSuccess(
                                                                            () -> Component.literal(
                                                                                    "Phantom charge speed set to "
                                                                                            + speed),
                                                                            true
                                                                    );
                                                            return 1;
                                                        })
                                        )
                        )

                        // /phantom range <1.0-64.0>
                        .then(
                                Commands.literal("range")
                                        .then(
                                                Commands.argument(
                                                        "value",
                                                        FloatArgumentType.floatArg(
                                                                1.0f,
                                                                64.0f
                                                        )
                                                )
                                                        .executes(ctx -> {
                                                            float range = FloatArgumentType
                                                                    .getFloat(ctx, "value");
                                                            ModConfig.FOLLOW_RANGE = range;

                                                            ctx.getSource()
                                                                    .sendSuccess(
                                                                            () -> Component.literal(
                                                                                    "Phantom follow range set to "
                                                                                            + range),
                                                                            true
                                                                    );
                                                            return 1;
                                                        })
                                        )
                        )

                        // /phantom explode_range <1.0-16.0>
                        .then(
                                Commands.literal("explode_range")
                                        .then(
                                                Commands.argument(
                                                        "value",
                                                        FloatArgumentType.floatArg(
                                                                1.0f,
                                                                16.0f
                                                        )
                                                )
                                                        .executes(ctx -> {
                                                            float radius = FloatArgumentType
                                                                    .getFloat(ctx, "value");
                                                            ModConfig.EXPLOSION_RADIUS = radius;

                                                            ctx.getSource()
                                                                    .sendSuccess(
                                                                            () -> Component.literal(
                                                                                    "Phantom explosion radius set to "
                                                                                            + radius),
                                                                            true
                                                                    );
                                                            return 1;
                                                        })
                                        )
                        )

                        // /phantom status – show current config
                        .then(
                                Commands.literal("status")
                                        .executes(ctx -> {
                                            ctx.getSource()
                                                    .sendSuccess(
                                                            () -> Component.literal(
                                                                    "§6=== Phantom Bomb Config ==="),
                                                            false
                                                    );
                                            ctx.getSource()
                                                    .sendSuccess(
                                                            () -> Component.literal(
                                                                    "§7Power: §f" + ModConfig.EXPLOSION_POWER),
                                                            false
                                                    );
                                            ctx.getSource()
                                                    .sendSuccess(
                                                            () -> Component.literal(
                                                                    "§7Fire:  §f" + ModConfig.EXPLOSION_FIRE),
                                                            false
                                                    );
                                            ctx.getSource()
                                                    .sendSuccess(
                                                            () -> Component.literal(
                                                                    "§7Speed: §f" + String.format("%.2f", ModConfig.CHARGE_SPEED)),
                                                            false
                                                    );
                                            ctx.getSource()
                                                    .sendSuccess(
                                                            () -> Component.literal(
                                                                    "§7Range: §f" + String.format("%.1f", ModConfig.FOLLOW_RANGE)),
                                                            false
                                                    );
                                            ctx.getSource()
                                                    .sendSuccess(
                                                            () -> Component.literal(
                                                                    "§7Explode distance: §f" + String.format("%.1f", ModConfig.EXPLOSION_RADIUS)),
                                                            false
                                                    );
                                            return 1;
                                        })
                        )
        );
    }
}
