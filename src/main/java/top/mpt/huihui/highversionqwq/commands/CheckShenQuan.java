package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.highversionqwq.team.TeamExecutor;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;
import top.mpt.huihui.highversionqwq.utils.PlayerUtils;

import static top.mpt.huihui.highversionqwq.HighVersionQWQ.GameStart;
import static top.mpt.huihui.highversionqwq.HighVersionQWQ.teamExecutor;

public class CheckShenQuan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (GameStart){
            int countRed = checkPlayer(teamExecutor.getPlayer(TeamExecutor.Team.RED));
            int countBlue = checkPlayer(teamExecutor.getPlayer(TeamExecutor.Team.BLUE));
            if (countRed != 0){
                ChatUtils.broadcast("#AQUA#经由玩家: %s 检测，#RED#神权红队#AQUA#玩家: %s 涉嫌Bypass本插件，犯下了 %d 项罪行，现给予处罚。"
                , commandSender.getName(), teamExecutor.getPlayer(TeamExecutor.Team.RED).getName(), countRed);
                teamExecutor.getPlayer(TeamExecutor.Team.RED).setHealth(0.5);
                teamExecutor.getPlayer(TeamExecutor.Team.RED).setFoodLevel(0);
                teamExecutor.getPlayer(TeamExecutor.Team.RED).teleport(teamExecutor.getPlayer(TeamExecutor.Team.BLUE));
            }
            if (countBlue != 0){
                ChatUtils.broadcast("#AQUA#经由玩家: %s 检测，#BLUE#神权蓝队#AQUA#玩家: %s 涉嫌Bypass本插件，犯下了 %d 项罪行，现给予处罚。"
                , commandSender.getName(), teamExecutor.getPlayer(TeamExecutor.Team.BLUE).getName(), countBlue);
                teamExecutor.getPlayer(TeamExecutor.Team.BLUE).setHealth(0.5);
                teamExecutor.getPlayer(TeamExecutor.Team.BLUE).setFoodLevel(0);
                teamExecutor.getPlayer(TeamExecutor.Team.BLUE).teleport(teamExecutor.getPlayer(TeamExecutor.Team.RED));
            }
            if (countRed == 0 && countBlue == 0){
                ChatUtils.broadcast("#AQUA#没有人是神权狗");
            }
        }
        else{
            PlayerUtils.send(commandSender, "#AQUA#游戏未开始，无法检测神权");
        }
        return true;
    }

    public int checkPlayer(Player player){
        int count = 0;
        if (player.getAllowFlight()){
            ChatUtils.broadcast("玩家: %s 启用了飞行，已将其禁用。", player.getName());
            count++;
            player.setAllowFlight(false);
        }
        if (player.getVehicle() != null){
            ChatUtils.broadcast("玩家: %s 乘坐矿车陶艺，已将其惩罚。", player.getName());
            count++;
            player.getVehicle().removePassenger(player);
        }
        if (player.getWalkSpeed() != 0.2F){
            ChatUtils.broadcast("玩家: %s 开了Speed，太L了。", player.getName());
            System.out.println(player.getWalkSpeed() + " != " + 0.2F);
            count++;
            player.setWalkSpeed(0.2F);
        }
        if (player.getFlySpeed() != 0.1F){
            ChatUtils.broadcast("玩家: %s 启用了飞行，还开启了飞行Speed，太太太L了。", player.getName());
            System.out.println(player.getFlySpeed() + " != " + 0.1F);
            count++;
            player.setFlySpeed(0.1F);
        }
        if (player.getGameMode() != GameMode.SURVIVAL){
            ChatUtils.broadcast("玩家: %s 竟然不是生存模式，太L了", player.getName());
            count++;
            player.setGameMode(GameMode.SURVIVAL);
        }
        return count;
    }
}
