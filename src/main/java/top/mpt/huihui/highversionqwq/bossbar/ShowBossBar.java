package top.mpt.huihui.highversionqwq.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import top.mpt.huihui.highversionqwq.HighVersionQWQ;
import top.mpt.huihui.highversionqwq.team.TeamExecuter;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;
import top.mpt.huihui.highversionqwq.utils.LogUtils;
import top.mpt.huihui.highversionqwq.utils.PlayerUtils;

// 给玩家显示对面的血量BossBar
public class ShowBossBar {
    private final TeamExecuter teamExecuter = HighVersionQWQ.teamExecuter;
    private BossBar RedBossBar = null;
    private BossBar BlueBossBar = null;

    public void createBossBar(){
        // PlayerUtils.send(teamExecuter.getRedPlayer(), "#AQUA#check if it is null:" + HighVersionQWQ.instance);
        Player RedPlayer = teamExecuter.getRedPlayer();
        Player BluePlayer = teamExecuter.getBluePlayer();
        RedBossBar = Bukkit.createBossBar(RedPlayer.getName(), BarColor.RED, BarStyle.SEGMENTED_6);
        BlueBossBar = Bukkit.createBossBar(BluePlayer.getName(), BarColor.BLUE, BarStyle.SEGMENTED_6);
        RedBossBar.removeAll();
        BlueBossBar.removeAll();
        RedBossBar.addPlayer(BluePlayer);
        BlueBossBar.addPlayer(RedPlayer);
        new BukkitRunnable(){
            @Override
            public void run() {
                RedBossBar.setProgress(RedPlayer.getHealth() / 100 / 0.2);
                BlueBossBar.setProgress(BluePlayer.getHealth() / 100 / 0.2);
                if (!RedPlayer.isValid() || !BluePlayer.isValid()){
                    LogUtils.info("BossBar移除成功");
                    RedBossBar.removeAll();
                    BlueBossBar.removeAll();
                    cancel();
                }
            }
        }.runTaskTimer(HighVersionQWQ.instance, 1L, 5L);
    }


}
