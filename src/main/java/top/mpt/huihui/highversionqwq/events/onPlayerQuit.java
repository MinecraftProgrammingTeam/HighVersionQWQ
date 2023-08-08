package top.mpt.huihui.highversionqwq.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import top.mpt.huihui.highversionqwq.team.TeamExecutor;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;

import static top.mpt.huihui.highversionqwq.HighVersionQWQ.*;

public class onPlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(!GameStart){
            if (teamExecutor.getPlayer(TeamExecutor.Team.RED) != null && teamExecutor.getPlayer(TeamExecutor.Team.RED).equals(player)){
                ChatUtils.broadcast("#RED#红队#AQUA#玩家: %s 在比赛开始前陶艺了，已取消其比赛资格。"
                , player.getName());
                teamExecutor.removeRedPlayer();
            }
            if (teamExecutor.getPlayer(TeamExecutor.Team.BLUE) != null && teamExecutor.getPlayer(TeamExecutor.Team.BLUE).equals(player)){
                ChatUtils.broadcast("#BLUE#蓝队#AQUA#玩家: %s 在比赛开始前陶艺了，已取消其比赛资格。"
                , player.getName());
                teamExecutor.removeBluePlayer();
            }
        }
        else {
            if (teamExecutor.getPlayer(TeamExecutor.Team.RED).equals(player)){
                ChatUtils.broadcast("#RED#红队#AQUA#玩家: %s 在比赛开始后陶艺了，比赛被迫结束。"
                , player.getName());
                teamExecutor.removeRedPlayer();
                teamExecutor.removeBluePlayer();
                GameStart = false;
            }
            if (teamExecutor.getPlayer(TeamExecutor.Team.BLUE).equals(player)){
                ChatUtils.broadcast("#BLUE#蓝队#AQUA#玩家: %s 在比赛开始后陶艺了，比赛被迫结束。"
                , player.getName());
                teamExecutor.removeRedPlayer();
                teamExecutor.removeBluePlayer();
                GameStart = false;
            }
        }
    }
}
