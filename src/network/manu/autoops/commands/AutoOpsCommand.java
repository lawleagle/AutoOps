package network.manu.autoops.commands;

import network.manu.autoops.AutoOps;
import network.manu.autoops.pojos.Op;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import static com.mongodb.client.model.Filters.eq;
import org.bukkit.entity.Player;

public class AutoOpsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 2) {
            commandSender.sendMessage("invalid number of arguments");
            return false;
        }

        if (!strings[0].equals("add") && !strings[0].equals("remove")) {
            commandSender.sendMessage("invalid parameter");
            return false;
        }

        Player player = Bukkit.getPlayer(strings[1]);
        if (player == null) {
            commandSender.sendMessage("player not found");
            return true;
        }


        switch (strings[0]) {
            case "add": {
                if (AutoOps.autoOps.find(eq("uuid", player.getUniqueId())).first() != null) {
                    commandSender.sendMessage("player is already autoop");
                    break;
                }
                AutoOps.autoOps.insertOne(new Op(player.getUniqueId()));
                player.setOp(true);
            } return true;

            case "remove": {
                if (AutoOps.autoOps.find(eq("uuid", player.getUniqueId())).first() == null) {
                    commandSender.sendMessage("player is not autoop");
                    return true;
                }
                AutoOps.autoOps.findOneAndDelete(eq("uuid", player.getUniqueId()));
                player.setOp(false);
            } return true;

            default: {
                commandSender.sendMessage("ERROR: This should never be reached.");
            } return false;
        }
        return false;
    }
}
