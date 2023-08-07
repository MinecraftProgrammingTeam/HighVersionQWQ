package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.highversionqwq.team.TeamExecuter;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;

import static top.mpt.huihui.highversionqwq.HighVersionQWQ.GameStart;
import static top.mpt.huihui.highversionqwq.HighVersionQWQ.teamExecuter;

public class CheckShenQuan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (GameStart){
            int countRed = checkPlayer(teamExecuter.getPlayer(TeamExecuter.Team.RED));
            int countBlue = checkPlayer(teamExecuter.getPlayer(TeamExecuter.Team.BLUE));
            if (countRed != 0){
                ChatUtils.broadcast("#AQUA#经由玩家: %s 检测，#RED#神权红队#AQUA#玩家: %s 涉嫌Bypass本插件，犯下了 %d 项罪行，现给予处罚。"
                , commandSender.getName(), teamExecuter.getPlayer(TeamExecuter.Team.RED).getName(), countRed);
                teamExecuter.getPlayer(TeamExecuter.Team.RED).setHealth(0.5);
                teamExecuter.getPlayer(TeamExecuter.Team.RED).setFoodLevel(0);
                teamExecuter.getPlayer(TeamExecuter.Team.RED).teleport(teamExecuter.getPlayer(TeamExecuter.Team.BLUE));
            }
            if (countBlue != 0){
                ChatUtils.broadcast("#AQUA#经由玩家: %s 检测，#BLUE#神权蓝队#AQUA#玩家: %s 涉嫌Bypass本插件，犯下了 %d 项罪行，现给予处罚。"
                , commandSender.getName(), teamExecuter.getPlayer(TeamExecuter.Team.BLUE).getName(), countBlue);
                teamExecuter.getPlayer(TeamExecuter.Team.BLUE).setHealth(0.5);
                teamExecuter.getPlayer(TeamExecuter.Team.BLUE).setFoodLevel(0);
                teamExecuter.getPlayer(TeamExecuter.Team.BLUE).teleport(teamExecuter.getPlayer(TeamExecuter.Team.RED));
            }
            if (countRed == 0 && countBlue == 0){
                ChatUtils.broadcast("#AQUA#没有人是神权狗");
            }
        }
        return true;
    }

    public int checkPlayer(Player player){
        int count = 0;
        if (player.getAllowFlight()){
            count++;
            player.setAllowFlight(false);
        }
        if (player.getVehicle() != null){
            count++;
            player.getVehicle().removePassenger(player);
        }
        if (player.getWalkSpeed() != 0.2F){
            System.out.println(player.getWalkSpeed() + " != " + 0.2F);
            count++;
            player.setWalkSpeed(0.2F);
        }
        if (player.getFlySpeed() != 0.1F){
            System.out.println(player.getFlySpeed() + " != " + 0.1F);
            count++;
            player.setFlySpeed(0.1F);
        }
        if (player.getGameMode() != GameMode.SURVIVAL){
            count++;
            player.setGameMode(GameMode.SURVIVAL);
        }
        return count;
    }
}
