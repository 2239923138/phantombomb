package com.example.phantombomb;

/**
 * Runtime configuration for the Phantom Bomb mod.
 *
 * Values can be changed in-game via the {@code /phantom} command.
 */
public class ModConfig {

    /** Explosion power (0.1 – 100.0). Default is roughly TNT-level (4.0). */
    public static float EXPLOSION_POWER = 4.0f;

    /** Whether the explosion creates fire (requires TNT interaction). */
    public static boolean EXPLOSION_FIRE = true;

    /** Charging speed multiplier for phantoms flying toward players. */
    public static double CHARGE_SPEED = 1.2d;

    /** Distance (blocks) at which a phantom will explode when close to a player. */
    public static double EXPLOSION_RADIUS = 3.5d;

    /** Max range (blocks) at which a phantom can detect and chase a player. */
    public static double FOLLOW_RANGE = 32.0d;
}
