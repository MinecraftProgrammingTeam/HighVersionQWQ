package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import top.mpt.huihui.highversionqwq.HighVersionQWQ;
import top.mpt.huihui.highversionqwq.bossbar.ShowBossBar;
import top.mpt.huihui.highversionqwq.team.TeamExecutor;
import top.mpt.huihui.highversionqwq.utils.ChatUtils;
import top.mpt.huihui.highversionqwq.utils.ItemUtils;
import top.mpt.huihui.highversionqwq.utils.PlayerUtils;

import java.util.List;
import java.util.Objects;


public class JoinTeam implements CommandExecutor {

    public static ShowBossBar showBossBar = new ShowBossBar(); // 设置BossBar

    // 获取Team
    private static final TeamExecutor TEAM_EXECUTOR = HighVersionQWQ.teamExecutor;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // strings[0] == "Blue/Red"
        // strings[1] == "X_huihui/NameFlying"
        if (strings.length != 2){
            PlayerUtils.send(commandSender, "#RED#格式错误! 用法：/jointeam red/blue <PlayerName>");
            return true;
        }
        if (Bukkit.getPlayer(strings[1]) == null){
            PlayerUtils.send(commandSender, "#RED#玩家不存在");
        }
        // 如果是目标选择器
        // 定义玩家
        Player onJoinTeamPlayer;
        // 如果是目标选择器
        if (strings[1].contains("@")){
            List<Entity> selectEntities = Bukkit.selectEntities(commandSender, strings[1]);
            // 判断格式
            if (selectEntities.toArray().length != 1){
                PlayerUtils.send(commandSender, "#RED#所给选择器目标不明确，请给一个目标明确的选择器。");
                return true;
            }
            else if (selectEntities.get(0) instanceof Player){
                onJoinTeamPlayer = (Player) selectEntities.get(0);
            }
            else {
                PlayerUtils.send(commandSender, "#RED#请选择一个玩家，而不是一个生物或者其他乱七八走的东西。");
                return true;
            }
        }
        // 如果是玩家名
        else {
            // 设置玩家
            onJoinTeamPlayer = Objects.requireNonNull(Bukkit.getPlayer(strings[1]));
        }
        // 断言onJoinTeamPlayer不为null
        assert onJoinTeamPlayer != null;
        // 如果玩家要加入Red队伍
        if (strings[0].equalsIgnoreCase("red")){
            // 防止同一个玩家重复加入队伍
            if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED) != null && TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED).equals(onJoinTeamPlayer)){
                ChatUtils.broadcast("#RED#红队队员试图重复加入队伍制造Bug，公开ta的ID：#AQUA#%s", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.showAllTeamMember();
                return true;
            }
            // 不同玩家加入，但游戏未开始
            else if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED) != null && !HighVersionQWQ.GameStart){
                ChatUtils.broadcast("#RED#检测到有两个玩家试图加入红队，已清除原先的红队玩家，现在的红队玩家是：%s", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.removeRedPlayer();
            }
            // 不同玩家加入同一支队伍，但游戏已开始
            else if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED) != null && HighVersionQWQ.GameStart){
                ChatUtils.broadcast("#RED#有一个玩家试图在游戏开始后加入队伍，已拒绝ta的请求，该玩家ID为：#AQUA#%s", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.showAllTeamMember();
                return true;
            }
            // 如果该玩家原先为蓝队队员，叛变为红队队员(游戏未开始)
            else if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE) != null && TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE).equals(onJoinTeamPlayer) && !HighVersionQWQ.GameStart){
                ChatUtils.broadcast("#GREEN#玩家：%s 试图从蓝队叛变至红队，由于游戏未开始，蓝队空出来了一个位置，等待加入。", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.removeBluePlayer();
            }
            // 如果该玩家原先为蓝队队员，叛变为红队队员(游戏已开始)
            else if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE) != null && TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE).equals(onJoinTeamPlayer)){
                ChatUtils.broadcast("#RED#玩家：%s 试图从蓝队叛变至红队，游戏已经开始，无法执行该操作。", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.showAllTeamMember();
                return true;
            }
            // 让玩家加入红队
            TEAM_EXECUTOR.joinRed(onJoinTeamPlayer);
            // 设置玩家背包
            setItems(onJoinTeamPlayer);
            // 给玩家发送消息
            PlayerUtils.send(onJoinTeamPlayer, onJoinTeamPlayer.getName() + "#AQUA#加入队伍：#RED#Red #GREEN#成功");
        // 如果玩家要加入Blue队伍
        } else if (strings[0].equalsIgnoreCase("blue")){
            // 防止同一个玩家重复加入队伍
            if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE) != null && TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE).equals(onJoinTeamPlayer)){
                ChatUtils.broadcast("#BLUE#蓝队队员试图重复加入队伍制造Bug，公开ta的ID：#AQUA#%s", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.showAllTeamMember();
                return true;
            }
            // 不同玩家加入，但游戏未开始
            else if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE) != null && !HighVersionQWQ.GameStart){
                ChatUtils.broadcast("#BLUE#检测到有两个玩家试图加入蓝队，已清除原先的蓝队玩家，现在的蓝队玩家是：%s", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.removeRedPlayer();
            }
            // 不同玩家加入同一支队伍，但游戏已开始
            else if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE) != null && HighVersionQWQ.GameStart){
                ChatUtils.broadcast("#BLUE#有一个玩家试图在游戏开始后加入队伍，已拒绝ta的请求，该玩家ID为：#AQUA#%s", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.showAllTeamMember();
                return true;
            }
            // 如果该玩家原先为红队队员，叛变为蓝队队员(游戏未开始)
            else if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED) != null && TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED).equals(onJoinTeamPlayer) && !HighVersionQWQ.GameStart){
                ChatUtils.broadcast("#GREEN#玩家：%s 试图从红队叛变至蓝队，由于游戏未开始，红队空出来了一个位置，等待加入。", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.removeRedPlayer();
            }
            // 如果该玩家原先为红队队员，叛变为蓝队队员(游戏已开始)
            else if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED) != null && TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED).equals(onJoinTeamPlayer)){
                ChatUtils.broadcast("#GREEN#玩家：%s 试图从红队叛变至蓝队，游戏已经开始，无法执行该操作。", onJoinTeamPlayer.getName());
                TEAM_EXECUTOR.showAllTeamMember();
                return true;
            }
            // 让玩家加入蓝队
            TEAM_EXECUTOR.joinBlue(onJoinTeamPlayer);
            // 设置玩家背包
            setItems(onJoinTeamPlayer);
            // 给玩家发送消息
            PlayerUtils.send(onJoinTeamPlayer, onJoinTeamPlayer.getName() + "#AQUA#加入队伍：#BLUE#Blue #GREEN#成功");
        }
        TEAM_EXECUTOR.showAllTeamMember();
        if (TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.RED) != null && TEAM_EXECUTOR.getPlayer(TeamExecutor.Team.BLUE) != null){
            // 游戏开启
            HighVersionQWQ.GameStart = true;
            // 发布提示
            ChatUtils.broadcast("#AQUA#游戏已开始！");
            // 设置BossBar
            showBossBar.createBossBar();
        }
        return true;
    }

    private void setItems(Player player){
        // 清空玩家背包
        player.getInventory().clear();
        // 设置成生存模式
        player.setGameMode(GameMode.SURVIVAL);
        // 治疗玩家
        player.setHealth(20.0);
        player.setFoodLevel(20);
        // 初始化药水
        PotionEffect TArrowEff = new PotionEffect(
                PotionEffectType.HARM,
                1,
                0,
                true
        );
        PotionEffect FPPotionEff = new PotionEffect(
                PotionEffectType.INCREASE_DAMAGE,
                3600,
                0,
                true
        );
        // 初始化装备
        ItemStack Helmet = ItemUtils.newItem(Material.DIAMOND_HELMET, "#WHITE#钻石头盔", 1, null);
        ItemStack ChestPlate = ItemUtils.newItem(Material.DIAMOND_CHESTPLATE, "#WHITE#钻石胸甲", 1, null);
        ItemStack Leggings = ItemUtils.newItem(Material.DIAMOND_LEGGINGS, "#WHITE#钻石护腿", 1, null);
        ItemStack Boots = ItemUtils.newItem(Material.DIAMOND_BOOTS, "#WHITE#钻石靴子", 1, null);
        ItemStack GApple = ItemUtils.newItem(Material.GOLDEN_APPLE, "#GOLD#金苹果", 64, null);
        ItemStack Shield = ItemUtils.newItem(Material.SHIELD, "#WHITE#盾", 1, null);
        ItemStack Bow = ItemUtils.newItem(Material.BOW, "#RED#攻", 1, null);
        ItemStack Sword = ItemUtils.newItem(Material.DIAMOND_SWORD, "#GOLD#钻石剑", 1, null);
        ItemStack Axe = ItemUtils.newItem(Material.DIAMOND_AXE, "#AQUA#钻石斧", 1, null);
        ItemStack TArrow = ItemUtils.newItem(Material.TIPPED_ARROW, "#RED#伤害箭", 64, TArrowEff);
        ItemStack Arrow = ItemUtils.newItem(Material.ARROW, "#WHITE#箭", 64, null);
        ItemStack Force_Potion = ItemUtils.newItem(Material.POTION, "#AQUA#腻酿药水", 1, FPPotionEff);
        /*
         * https://bukkit.windit.net/javadoc/org/bukkit/inventory/PlayerInventory.html#setItem(org.bukkit.inventory.EquipmentSlot,org.bukkit.inventory.ItemStack)
         * 把物品放在背包的指定位置.
         * 索引值0~8指向平视显示器(HUD)上的工具栏. 9~35指向主物品栏(中间的27个物品槽), 从主物品栏的左上角往上数(索引值9指向主物品栏左上角的物品槽),向右移动, 到行末时再从下一行的最左的物品槽继续往上数.
         * 索引值36~39指向玩家的盔甲槽. 即使你可以使用本方法设置盔甲槽内的物品, 我们还是建议你使用我们提供的相关的设置盔甲槽内物品的方法来设置.
         * 索引值40指向副手(盾牌)物品槽. 即使你可以使用40索引值设置副手上的物品, 但仍建议您使用已有方法来设置此物品槽 (该方法指setItemInOffHand(ItemStack)).
         * 如果你试图传递错误的index值(取值范围为0≤index≤40)给本方法, 将抛出ArrayIndexOutOfBounds异常.
         */
        // 设置盔甲栏里的东西
        player.getInventory().setHelmet(Helmet);
        player.getInventory().setChestplate(ChestPlate);
        player.getInventory().setLeggings(Leggings);
        player.getInventory().setBoots(Boots);
        // 设置玩家副手中的物品
        player.getInventory().setItemInOffHand(Shield);
        // 设置玩家物品栏中的物品
        player.getInventory().setItem(0, Sword);
        player.getInventory().setItem(1, Bow);
        player.getInventory().setItem(2, Axe);
        player.getInventory().setItem(3, GApple);
        player.getInventory().setItem(4, Force_Potion);
        player.getInventory().setItem(9, TArrow);
        player.getInventory().setItem(10, Arrow);
        player.getInventory().setItem(11, Arrow);

    }
}
