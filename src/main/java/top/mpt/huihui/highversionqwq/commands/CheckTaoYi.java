package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.mpt.huihui.highversionqwq.team.TeamExecutor;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;


import static top.mpt.huihui.highversionqwq.HighVersionQWQ.*;

/**
 * @author YouM
 * Created on 2023/8/6
 */
public class CheckTaoYi implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!GameStart || !(commandSender instanceof Player)) {
            commandSender.sendMessage("游戏未开始，或者你不是玩家，你无法使用该指令 ");
            return false;
        }
        int taoyiMaxDistance = instance.getConfig().getInt("taoyiMaxDistance", 30);
        TeamExecutor.Team playersTeam = teamExecutor.getPlayersTeam((Player) commandSender);
        boolean result = checkDistance(playersTeam,taoyiMaxDistance);
        if(result){
            teamExecutor.getPlayer(playersTeam).setHealth(0);
            commandSender.sendMessage("检测到玩家逃逸,自动为您击杀");
            ChatUtils.broadcast("玩家: %s 距离自己开始的区域超过了30格，检测为陶艺，已将其击毙。");
        } else {
            commandSender.sendMessage("未检测玩家逃逸,请重试");
        }
        return result;
    }
    public boolean checkDistance(TeamExecutor.Team team, int max){
        if (team == TeamExecutor.Team.NONE){
            return false;
        } else {
            TeamExecutor.Team enemyTeam = teamExecutor.getEnemyTeam(team);
            int distance = (int) teamExecutor.getPlayer(enemyTeam)
                    .getLocation()
                    .distance(teamExecutor.getStartGameLocation().get(enemyTeam));
            return Math.abs(distance) > max;
        }
    }
}
