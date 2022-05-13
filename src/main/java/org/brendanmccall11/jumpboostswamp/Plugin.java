package org.brendanmccall11.jumpboostswamp;

import org.bukkit.Particle;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

import static org.bukkit.Bukkit.getServer;

public class Plugin implements Listener {

    private HashMap<Player, Boolean> slimy = new HashMap<>();

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

        if (!slimy.containsKey(player)) {
            slimy.put(player, false);
        }

        boolean oldSlimy = slimy.get(player); // Tests if the slimy variable changes

        if (isInSwamp(player) && isNight() && isFullMoon()) {
            slimy.put(player, true);
            player.spawnParticle(Particle.SLIME, player.getLocation(), 2); // Add slime trail to player
        } else {
            slimy.put(player, false);
        }

        if (!oldSlimy && slimy.get(player)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 2)); // Add jump boost the moment player enters the swamp biome
        } else if (oldSlimy && !slimy.get(player)) {
            player.removePotionEffect(PotionEffectType.JUMP); // Remove jump boost the moment a player exits the swamp biome
        }
    }
}