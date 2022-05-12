package org.brendanmccall11.jumpboostswamp;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Plugin implements Listener {

    public boolean isInSwamp (Player player) {
        return player.getLocation().getWorld().getBiome(player.getLocation()).equals(Biome.SWAMP); // Tests if the player is in a swamp biome
    }

    @EventHandler
    public void main (PlayerMoveEvent event) {

        if (isInSwamp(event.getPlayer())) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 2)); // Add jump boost when player enters the swamp biome
        } else if (!isInSwamp(event.getPlayer())) {
            event.getPlayer().removePotionEffect(PotionEffectType.JUMP); // Removes jump boost when player enters the swamp biome
        }
    }
}