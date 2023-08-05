package top.mpt.huihui.highversionqwq.team;


import org.bukkit.entity.Player;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;

/**
 * 处理玩家队伍
 * @author X_huihui
 */
public class TeamExecuter {
    private Player RedPlayer;
    private Player BluePlayer;
    public void JoinRed(Player onJoinPlayer){
        RedPlayer = onJoinPlayer;
    }

    public void JoinBlue(Player onJoinPlayer){
        BluePlayer = onJoinPlayer;
    }

    public Player getRedPlayer(){
        return RedPlayer;
    }

    public Player getBluePlayer() {
        return BluePlayer;
    }

    public void removeRedPlayer() {
        RedPlayer.getInventory().clear();
        RedPlayer = null;
    }
    public void removeBluePlayer() {
        BluePlayer.getInventory().clear();
        BluePlayer = null;
    }

    public void showAllTeamMember(){
        if (getRedPlayer() != null && getBluePlayer() != null){
            ChatUtils.broadcast("#RED#红队队员：%s, #BLUE#蓝队队员：%s", getRedPlayer().getName(), getBluePlayer().getName());
        }
        else if (getRedPlayer() == null && getBluePlayer() != null){
            ChatUtils.broadcast("#RED#红队队员：未设定, #BLUE#蓝队队员：%s", getBluePlayer().getName());
        }
        else if (getRedPlayer() != null && getBluePlayer() == null){
            ChatUtils.broadcast("#RED#红队队员：%s, #BLUE#蓝队队员：未设定", getRedPlayer().getName());
        }
        else if (getRedPlayer() == null && getBluePlayer() == null){
            ChatUtils.broadcast("#RED#红队队员：未设定, #BLUE#蓝队队员：未设定");
        }

    }

}
