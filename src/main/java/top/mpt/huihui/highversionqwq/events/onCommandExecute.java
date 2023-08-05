package top.mpt.huihui.highversionqwq.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;
import top.mpt.huihui.highversionqwq.utils.PlayerUtils;

import static top.mpt.huihui.highversionqwq.HighVersionQWQ.*;

public class onCommandExecute implements Listener {
    @EventHandler
    public void onPlayerExecuteCommand(PlayerCommandPreprocessEvent event){
        if (GameStart){
            Player player = event.getPlayer();
            if (player.equals(teamExecuter.getRedPlayer()) && !event.getMessage().equals("/checkshenquan")){
                ChatUtils.broadcast("#RED#红队#AQUA#玩家: %s 试图在PVP的时候应用命令: %s, 已给予其相应的惩罚。"
                    , player.getName(), event.getMessage());
                event.setCancelled(true);
                player.setHealth(1);
                player.setFoodLevel(0);
            }
            if (player.equals(teamExecuter.getBluePlayer()) && !event.getMessage().equals("/checkshenquan")){
                ChatUtils.broadcast("#BLUE#蓝队#AQUA#玩家: %s 试图在PVP的时候应用命令: %s, 已给予其相应的惩罚。"
                    , player.getName(), event.getMessage());
                event.setCancelled(true);
                player.setHealth(1);
                player.setFoodLevel(0);
            }
        }
    }
}
