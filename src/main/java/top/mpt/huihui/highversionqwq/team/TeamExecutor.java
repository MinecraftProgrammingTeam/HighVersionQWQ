package top.mpt.huihui.highversionqwq.team;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;

import java.util.HashMap;
import java.util.Map;
/**
 * 处理玩家队伍
 * @author X_huihui
 */
public class TeamExecutor {
    private final Map<Team,Player> teamPlayerMap = new HashMap<>();
    private final Map<Team, Location> startGameLocation = new HashMap<>();
    public void JoinRed(Player onJoinPlayer){
        this.teamPlayerMap.put(TeamExecutor.Team.RED,onJoinPlayer);
        startGameLocation.put(Team.RED,onJoinPlayer.getLocation());
    }

    public void JoinBlue(Player onJoinPlayer){
        this.teamPlayerMap.put(TeamExecutor.Team.BLUE,onJoinPlayer);
        startGameLocation.put(Team.BLUE,onJoinPlayer.getLocation());
    }
    public Player getPlayer(Team team){
        return teamPlayerMap.get(team);
    }

    public void removeRedPlayer() {
        Player player = teamPlayerMap.get(TeamExecutor.Team.RED);
        player.getInventory().clear();
        teamPlayerMap.remove(TeamExecutor.Team.RED);
        startGameLocation.remove(Team.RED);
    }
    public void removeBluePlayer() {
        Player player = teamPlayerMap.get(TeamExecutor.Team.BLUE);
        player.getInventory().clear();
        teamPlayerMap.remove(TeamExecutor.Team.BLUE);
        startGameLocation.remove(Team.BLUE);
    }

    public void showAllTeamMember(){
        if (getPlayer(TeamExecutor.Team.RED) != null && getPlayer(TeamExecutor.Team.BLUE) != null){
            ChatUtils.broadcast("#RED#红队队员：%s, #BLUE#蓝队队员：%s", getPlayer(TeamExecutor.Team.RED).getName(), getPlayer(TeamExecutor.Team.BLUE).getName());
        }
        else if (getPlayer(TeamExecutor.Team.RED) == null && getPlayer(TeamExecutor.Team.BLUE) != null){
            ChatUtils.broadcast("#RED#红队队员：未设定, #BLUE#蓝队队员：%s", getPlayer(TeamExecutor.Team.BLUE).getName());
        }
        else if (getPlayer(TeamExecutor.Team.RED) != null && getPlayer(TeamExecutor.Team.BLUE) == null){
            ChatUtils.broadcast("#RED#红队队员：%s, #BLUE#蓝队队员：未设定", getPlayer(TeamExecutor.Team.RED).getName());
        }
        else if (getPlayer(TeamExecutor.Team.RED) == null && getPlayer(TeamExecutor.Team.BLUE) == null){
            ChatUtils.broadcast("#RED#红队队员：未设定, #BLUE#蓝队队员：未设定");
        }
    }
    public Team getPlayersTeam(Player player){
        if(player.equals(this.getPlayer(TeamExecutor.Team.BLUE))){
            return TeamExecutor.Team.BLUE;
        }else if(player.equals(this.getPlayer(TeamExecutor.Team.RED))){
            return TeamExecutor.Team.RED;
        }else{
            return Team.NONE;
        }
    }
    public Team getEnemyTeam(Team team){
        if(team == Team.NONE){
            return Team.NONE;
        }else if (team == Team.RED) {
            return Team.BLUE;
        } else{
            return Team.RED;
        }

    }

    public Map<Team, Location> getStartGameLocation() {
        return startGameLocation;
    }

    public enum Team{
        RED,BLUE,NONE
    }


}
