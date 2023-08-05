package top.mpt.huihui.highversionqwq.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;

import static top.mpt.huihui.highversionqwq.HighVersionQWQ.*;

public class onPlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(!GameStart){
            if (teamExecuter.getRedPlayer() != null && teamExecuter.getRedPlayer().equals(player)){
                ChatUtils.broadcast("#RED#红队#AQUA#玩家: %s 在比赛开始前陶艺了，已取消其比赛资格。"
                , player.getName());
                teamExecuter.removeRedPlayer();
            }
            if (teamExecuter.getBluePlayer() != null && teamExecuter.getBluePlayer().equals(player)){
                ChatUtils.broadcast("#BLUE#蓝队#AQUA#玩家: %s 在比赛开始前陶艺了，已取消其比赛资格。"
                , player.getName());
                teamExecuter.removeBluePlayer();
            }
        }
        else {
            if (teamExecuter.getRedPlayer().equals(player)){
                ChatUtils.broadcast("#RED#红队#AQUA#玩家: %s 在比赛开始后陶艺了，比赛被迫结束。"
                , player.getName());
                teamExecuter.removeRedPlayer();
                teamExecuter.removeBluePlayer();
            }
            if (teamExecuter.getBluePlayer().equals(player)){
                ChatUtils.broadcast("#BLUE#蓝队#AQUA#玩家: %s 在比赛开始后陶艺了，比赛被迫结束。"
                , player.getName());
                teamExecuter.removeRedPlayer();
                teamExecuter.removeBluePlayer();
            }
        }
    }
}
