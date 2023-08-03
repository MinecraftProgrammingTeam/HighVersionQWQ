package top.mpt.huihui.highversionqwq.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import top.mpt.huihui.highversionqwq.HighVersionQWQ;
import top.mpt.huihui.highversionqwq.team.TeamExecuter;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;
import top.mpt.huihui.highversionqwq.utils.PlayerUtils;

public class onPlayerDeath implements Listener {
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event){
        if (!HighVersionQWQ.GameStart){
            return;
        }
        // 拿到死亡玩家
        Player onDeathPlayer = event.getEntity();
        // 设置变量
        TeamExecuter teamExecuter = HighVersionQWQ.teamExecuter;
        // 判断玩家是否是PVP战死
        if (onDeathPlayer.equals(teamExecuter.getRedPlayer()) || onDeathPlayer.equals(teamExecuter.getBluePlayer())){
            // 结束游戏
            HighVersionQWQ.GameStart = false;
            // 显示标题
            PlayerUtils.showTitle(onDeathPlayer, "#GRAY#别气馁，没准下次就赢了呢？", "");
            if (onDeathPlayer.equals(teamExecuter.getRedPlayer())) {
                ChatUtils.broadcast("#GOLD#恭喜玩家：#AQUA#%s #GOLD#赢得了本次#GREEN#PVP比赛#GOLD#的胜利", teamExecuter.getBluePlayer());
                PlayerUtils.showTitle(teamExecuter.getBluePlayer(), "#GREEN#恭喜您获得本场比赛的胜利！", "");
            } else {
                ChatUtils.broadcast("#GOLD#恭喜玩家：#AQUA#%s #GOLD#赢得了本次#GREEN#PVP比赛#GOLD#的胜利", teamExecuter.getRedPlayer());
                PlayerUtils.showTitle(teamExecuter.getRedPlayer(), "#GREEN#恭喜您获得本场比赛的胜利！", "");
            }
            // 清除队伍
            teamExecuter.removeRedPlayer();
            teamExecuter.removeBluePlayer();

        }
    }
}
