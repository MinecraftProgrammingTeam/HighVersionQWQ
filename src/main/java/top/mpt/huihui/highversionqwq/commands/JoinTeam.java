package top.mpt.huihui.highversionqwq.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import top.mpt.huihui.highversionqwq.HighVersionQWQ;
import top.mpt.huihui.highversionqwq.bossbar.ShowBossBar;
import top.mpt.huihui.highversionqwq.team.TeamExecuter;
import top.mpt.huihui.highversionqwq.utils.ItemUtils;
import top.mpt.huihui.highversionqwq.utils.PlayerUtils;

import java.util.Objects;


public class JoinTeam implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // strings[0] == "Blue/Red"
        // strings[1] == "X_huihui/NameFlying"
        if (strings.length != 2){
            PlayerUtils.send(commandSender, "#RED#格式错误");
            return true;
        }
        TeamExecuter teamExecuter = HighVersionQWQ.teamExecuter;
        if (strings[0].equals("red") || strings[0].equals("Red")){
            if (teamExecuter.getBluePlayer()!= null && teamExecuter.getBluePlayer().equals(Bukkit.getPlayer(strings[1]))){
                teamExecuter.removeBluePlayer();
            }
            teamExecuter.JoinRed(Bukkit.getPlayer(strings[1]));
            setItems(Objects.requireNonNull(Bukkit.getPlayer(strings[1])));
            PlayerUtils.send(commandSender, commandSender.getName() + "#AQUA#加入队伍：#RED#Red #GREEN#成功");
        } else if (strings[0].equals("blue") || strings[0].equals("Blue")){
            if (teamExecuter.getRedPlayer() != null && teamExecuter.getRedPlayer().equals(Bukkit.getPlayer(strings[1]))){
                teamExecuter.removeRedPlayer();
            }
            teamExecuter.JoinBlue(Bukkit.getPlayer(strings[1]));
            setItems(Objects.requireNonNull(Bukkit.getPlayer(strings[1])));
            PlayerUtils.send(commandSender, "#AQUA#加入队伍：#BLUE#Blue #GREEN#成功");
        }
        if (teamExecuter.getRedPlayer() != null && teamExecuter.getBluePlayer() != null){
            // 游戏开启
            HighVersionQWQ.GameStart = true;
            // 设置BossBar
            ShowBossBar showBossBar = new ShowBossBar();
            showBossBar.createBossBar();
        }
        return true;
    }

    private void setItems(Player player){
        // 清空玩家背包
        player.getInventory().clear();
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
