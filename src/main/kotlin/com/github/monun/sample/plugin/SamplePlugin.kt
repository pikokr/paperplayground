package com.github.monun.sample.plugin

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
class SamplePlugin : JavaPlugin(), Listener {
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

//    @EventHandler
//    fun test(e: EntityShootBowEvent) {
//        e.entityType.also { if (it != EntityType.SKELETON) return }
//        e.isCancelled = true
//        val sk = (e.entity as CraftSkeleton)
//        Bukkit.broadcast(Component.text("sans"))
//    }
}