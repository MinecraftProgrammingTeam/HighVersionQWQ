package top.mpt.huihui.highversionqwq;

import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.huihui.highversionqwq.commands.ClearBossbar;
import top.mpt.huihui.highversionqwq.commands.JoinTeam;
import top.mpt.huihui.highversionqwq.events.onPlayerDeath;
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
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
