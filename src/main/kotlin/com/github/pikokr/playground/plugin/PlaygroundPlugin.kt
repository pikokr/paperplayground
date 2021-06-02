package com.github.pikokr.playground.plugin

import net.kyori.adventure.text.Component
import net.minecraft.server.v1_16_R3.EntityLiving
import net.minecraft.server.v1_16_R3.EntitySkeleton
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSkeleton
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

/**
 * @author pikokr
 */
class PlaygroundPlugin : JavaPlugin(), Listener {
    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
        server.scheduler.runTaskTimer(this, kotlinx.coroutines.Runnable {
            for (world in Bukkit.getWorlds()) {
                world.livingEntities.filter { it.type == EntityType.SKELETON }.map { (it as CraftSkeleton).handle }.filter { it.goalTarget != null }.forEach {
                    it.a(it.goalTarget, 1.0F)
                }
            }
        }, 0, 1)
    }
}