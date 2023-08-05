package top.mpt.huihui.highversionqwq;

import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.huihui.highversionqwq.commands.CheckShenQuan;
import top.mpt.huihui.highversionqwq.commands.ClearBossbar;
import top.mpt.huihui.highversionqwq.commands.JoinTeam;
import top.mpt.huihui.highversionqwq.commands.LeaveTeam;
import top.mpt.huihui.highversionqwq.events.onCommandExecute;
import top.mpt.huihui.highversionqwq.events.onPlayerDeath;
import top.mpt.huihui.highversionqwq.events.onPlayerQuit;
import top.mpt.huihui.highversionqwq.team.TeamExecuter;

import java.util.Objects;

public final class HighVersionQWQ extends JavaPlugin {

    public static HighVersionQWQ instance;
    public static String normal = "[PVP] ";
    public static TeamExecuter teamExecuter = new TeamExecuter();
    public static boolean GameStart = false;
    @Override
    public void onEnable() {
        System.out.println("MoonCC服务器特供插件装载完成");
        instance = this;
        Objects.requireNonNull(getCommand("jointeam")).setExecutor(new JoinTeam());
        Objects.requireNonNull(getCommand("clearbossbar")).setExecutor(new ClearBossbar());
        Objects.requireNonNull(getCommand("checkshenquan")).setExecutor(new CheckShenQuan());
        Objects.requireNonNull(getCommand("leaveteam")).setExecutor(new LeaveTeam());
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new onCommandExecute(), this);
        getServer().getPluginManager().registerEvents(new onPlayerQuit(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
