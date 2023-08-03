package top.mpt.huihui.highversionqwq.team;


import org.bukkit.entity.Player;

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
        RedPlayer.setHealth(20.0);
        RedPlayer.setFoodLevel(20);
        RedPlayer = null;
    }
    public void removeBluePlayer() {
        BluePlayer.getInventory().clear();
        BluePlayer.setHealth(20.0);
        RedPlayer.setFoodLevel(20);
        BluePlayer = null;
    }

}
