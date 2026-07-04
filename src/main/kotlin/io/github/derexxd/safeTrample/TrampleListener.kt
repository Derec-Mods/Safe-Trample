package io.github.derexxd.safeTrample

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class TrampleListener : Listener {

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        if (event.action != Action.PHYSICAL) {
            return
        }

        val block = event.clickedBlock
        if (block == null || block.type != Material.FARMLAND) {
            return
        }

        event.isCancelled = true
    }
}
