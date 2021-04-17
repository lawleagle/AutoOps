package network.manu.autoops.listeners;

import network.manu.autoops.AutoOps;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.mongodb.client.model.Filters.eq;

public class LoginListener implements Listener {

    @EventHandler
    public void listen(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();

        if (AutoOps.autoOps.find(eq("uuid", player.getUniqueId())).first() != null) {
            player.setOp(true);
        } else {
            player.setOp(false);
        }
    }
}
