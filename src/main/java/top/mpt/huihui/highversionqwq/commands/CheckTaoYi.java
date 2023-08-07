package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import top.mpt.huihui.highversionqwq.team.TeamExecuter;

import java.util.function.BiConsumer;

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
        return checkDistance(teamExecuter.hasFightPlayer((Player) commandSender),30,(team,result)-> {
            if(result){
                teamExecuter.getPlayer(team).setHealth(0);
                commandSender.sendMessage("检测到玩家逃逸,自动为您击杀");
            }else {
                commandSender.sendMessage("未检测玩家逃逸,请重试");
            }
        });
    }
    public boolean checkDistance(TeamExecuter.Team team, int max, BiConsumer<TeamExecuter.Team,Boolean> success){
        if (team == TeamExecuter.Team.NONE){
            return false;
        } else {
            TeamExecuter.Team enemyTeam = teamExecuter.getEnemyTeam(team);
            int distance = (int) teamExecuter.getPlayer(enemyTeam)
                    .getLocation()
                    .distance(teamExecuter.getStartGameLocation().get(enemyTeam));
            success.accept(enemyTeam,Math.abs(distance) > max);
        }
        return true;
    }
}
