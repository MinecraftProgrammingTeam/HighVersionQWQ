package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import top.mpt.huihui.highversionqwq.team.TeamExecutor;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;
import top.mpt.huihui.highversionqwq.utils.PlayerUtils;

import java.util.List;

import static top.mpt.huihui.highversionqwq.HighVersionQWQ.*;

public class LeaveTeam implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 0 && strings[0].contains("@")){
            List<Entity> selectEntities = Bukkit.selectEntities(commandSender, strings[1]);
            // 判断格式
            if (selectEntities.toArray().length != 1){
                PlayerUtils.send(commandSender, "#RED#所给选择器目标不明确，请给一个目标明确的选择器。");
                return true;
            }
            else if (selectEntities.get(0) instanceof Player){
                commandSender = selectEntities.get(0);
            }
            else {
                PlayerUtils.send(commandSender, "#RED#请选择一个玩家，而不是一个生物或者其他乱七八走的东西。");
                return true;
            }
        }
        if (commandSender instanceof Player){
            PlayerUtils.send(commandSender, "#AQUA#检测到您非玩家，请选定一个玩家再执行此指令。");
            return true;
        }
        if (GameStart){
            if (commandSender.equals(teamExecutor.getPlayer(TeamExecutor.Team.BLUE))){
                ChatUtils.broadcast("#BLUE#蓝队#GREEN#玩家: %s 试图在比赛开始时离开队伍，已拒绝其请求。", command.getName());
                return true;
            }
            else {
                ChatUtils.broadcast("#RED#红队#GREEN#玩家: %s 试图在比赛开始时离开队伍，已拒绝其请求。", command.getName());
                return true;
            }

        }
        else {
            if (commandSender.equals(teamExecutor.getPlayer(TeamExecutor.Team.BLUE))){
                ChatUtils.broadcast("#BLUE#蓝队#GREEN#玩家: %s 请求离开队伍，已认可其请求。", command.getName());
                teamExecutor.removeBluePlayer();
                teamExecutor.showAllTeamMember();
                return true;
            }
            else {
                ChatUtils.broadcast("#RED#红队#GREEN#玩家: %s 请求离开队伍，已认可其请求。", command.getName());
                teamExecutor.removeBluePlayer();
                teamExecutor.showAllTeamMember();
                return true;
            }
        }
    }
}
