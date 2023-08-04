package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import top.mpt.huihui.highversionqwq.bossbar.ShowBossBar;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;

public class ClearBossbar implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.isOp()){
            ChatUtils.broadcast("#GREEN#OP:%s认为BossBar出现了问题，需要手动清空。", commandSender.getName());
            ShowBossBar showBossBar = JoinTeam.showBossBar;
            showBossBar.RedBossBar.removeAll();
            showBossBar.BlueBossBar.removeAll();
            ChatUtils.broadcast("#AQUA#清空BossBar命令执行成功");
        }
        else {
            ChatUtils.broadcast("#RED#非OP玩家:%s试图清空BossBar，太LLLLLLLLL了", commandSender.getName());
        }
        return true;
    }
}
