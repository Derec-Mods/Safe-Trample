package io.github.derexxd.safeTrample

import org.bukkit.plugin.java.JavaPlugin

class SafeTrample : JavaPlugin() {

    override fun onEnable() {
        server.pluginManager.registerEvents(TrampleListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
