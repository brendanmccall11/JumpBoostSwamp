package org.brendanmccall11.jumpboostswamp;

import org.bukkit.Particle;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class Plugin implements Listener {

    public boolean isFullMoon () {

        long days = getServer().getWorld("world").getFullTime()/24000;
        long phase = days % 8;
        return phase == 0; // Tests if it is a full moon
    }

    public boolean isNight () {

        long time = getServer().getWorld("world").getTime();
        return time >= 13000 && time <= 23000; // Tests if it is nighttime
    }

    public boolean isInSwamp (Player player) {
        return player.getLocation().getWorld().getBiome(player.getLocation()).equals(Biome.SWAMP); // Tests if the player is in a swamp biome
    }

    @EventHandler
    public void main (PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (isInSwamp(player) && isNight() && isFullMoon()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 2)); // Add jump boost when player enters the swamp biome
            player.spawnParticle(Particle.SLIME, player.getLocation(), 1);
        } else if (!isInSwamp(player)) {
            player.removePotionEffect(PotionEffectType.JUMP); // Removes jump boost when player enters the swamp biome
        }
    }
}