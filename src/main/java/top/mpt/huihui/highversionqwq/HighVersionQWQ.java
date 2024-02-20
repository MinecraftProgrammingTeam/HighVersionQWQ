package top.mpt.huihui.highversionqwq;

import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.huihui.highversionqwq.commands.*;
import top.mpt.huihui.highversionqwq.events.onCommandExecute;
import top.mpt.huihui.highversionqwq.events.onPlayerDeath;
import top.mpt.huihui.highversionqwq.events.onPlayerQuit;
import top.mpt.huihui.highversionqwq.team.TeamExecutor;

import java.util.Objects;

public final class HighVersionQWQ extends JavaPlugin {

    public static HighVersionQWQ instance;
    public static String normal = "[PVP] ";
    public static TeamExecutor teamExecutor = new TeamExecutor();
    public static boolean GameStart = false;

    @Override
    public void onEnable() {
        System.out.println("MoonCC服务器特供插件装载完成");
        instance = this;
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        Objects.requireNonNull(this.getCommand("jointeam")).setExecutor(new JoinTeam());
        Objects.requireNonNull(this.getCommand("clearbossbar")).setExecutor(new ClearBossbar());
        Objects.requireNonNull(this.getCommand("checkshenquan")).setExecutor(new CheckShenQuan());
        Objects.requireNonNull(this.getCommand("leaveteam")).setExecutor(new LeaveTeam());
        Objects.requireNonNull(this.getCommand("checktaoyi")).setExecutor(new CheckTaoYi());

        this.getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        this.getServer().getPluginManager().registerEvents(new onCommandExecute(), this);
        this.getServer().getPluginManager().registerEvents(new onPlayerQuit(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
