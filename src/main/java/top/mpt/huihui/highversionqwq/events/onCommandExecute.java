package top.mpt.huihui.highversionqwq.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import top.mpt.huihui.highversionqwq.team.TeamExecutor;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;

import static top.mpt.huihui.highversionqwq.HighVersionQWQ.*;

public class onCommandExecute implements Listener {
    @EventHandler
    public void onPlayerExecuteCommand(PlayerCommandPreprocessEvent event){
        if (GameStart){
            Player player = event.getPlayer();
            if (player.equals(teamExecutor.getPlayer(TeamExecutor.Team.RED)) && !event.getMessage().equals("/checkshenquan") && !event.getMessage().equals("/checktaoyi")){
                ChatUtils.broadcast("#RED#红队#AQUA#玩家: %s 试图在PVP的时候应用命令: %s, 已给予其相应的惩罚。"
                    , player.getName(), event.getMessage());
                event.setCancelled(true);
                player.setHealth(1);
                player.setFoodLevel(0);
            }
            if (player.equals(teamExecutor.getPlayer(TeamExecutor.Team.BLUE)) && !event.getMessage().equals("/checkshenquan") && !event.getMessage().equals("/checktaoyi")){
                ChatUtils.broadcast("#BLUE#蓝队#AQUA#玩家: %s 试图在PVP的时候应用命令: %s, 已给予其相应的惩罚。"
                    , player.getName(), event.getMessage());
                event.setCancelled(true);
                player.setHealth(1);
                player.setFoodLevel(0);
            }
        }
    }
    @EventHandler
    public void onServerExecuteCommand(ServerCommandEvent event){
        if (GameStart){
            if (event.getCommand().contains("@") || event.getCommand().contains("*") ||
                    event.getCommand().contains("**") ||
                    event.getCommand().contains(teamExecutor.getPlayer(TeamExecutor.Team.BLUE).getName()) ||
                    event.getCommand().contains(teamExecutor.getPlayer(TeamExecutor.Team.RED).getName())
            ){
                event.getSender().sendMessage("请求被驳回，不允许场外助攻场内玩家！");
                event.setCancelled(true);
            }
        }
    }
}
