package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;

import static top.mpt.huihui.highversionqwq.HighVersionQWQ.GameStart;
import static top.mpt.huihui.highversionqwq.HighVersionQWQ.teamExecuter;

public class CheckShenQuan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (GameStart){
            int countRed = checkPlayer(teamExecuter.getRedPlayer());
            int countBlue = checkPlayer(teamExecuter.getBluePlayer());
            if (countRed != 0){
                ChatUtils.broadcast("#AQUA#经由玩家: %s 检测，#RED#红队#AQUA#玩家: %s 涉嫌作弊，犯下了 %d 项罪行，现给予处罚。"
                , commandSender, teamExecuter.getRedPlayer(), countRed);
                teamExecuter.getRedPlayer().setHealth(0.5);
                teamExecuter.getRedPlayer().teleport(teamExecuter.getBluePlayer());
            }
            if (countBlue != 0){
                ChatUtils.broadcast("#AQUA#经由玩家: %s 检测，#BLUE#蓝队#AQUA#玩家: %s 涉嫌作弊，犯下了 %d 项罪行，现给予处罚。"
                , commandSender, teamExecuter.getBluePlayer(), countBlue);
                teamExecuter.getBluePlayer().setHealth(0.5);
                teamExecuter.getBluePlayer().teleport(teamExecuter.getRedPlayer());
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
        if (player.getWalkSpeed() != 1.0){
            count++;
            player.setWalkSpeed(1.0F);
        }
        if (player.getFlySpeed() != 1.0){
            count++;
            player.setFlySpeed(1.0F);
        }
        if (player.getGameMode() != GameMode.SURVIVAL){
            count++;
            player.setGameMode(GameMode.SURVIVAL);
        }
        return count;
    }
}
