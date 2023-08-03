package top.mpt.huihui.highversionqwq.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Item工具类
 * @author WindLeaf_qwq
 */
public class ItemUtils {
    /**
     * 新增物品
     * @param type 物品类型
     * @param displayName 显示名称
     * @param amount 物品数目
     * @param potionEffect 药水效果
     * @return 物品
     */
    public static ItemStack newItem(Material type, String displayName, int amount, PotionEffect potionEffect) {
        ItemStack myItem = new ItemStack(type, amount);
        if (type.equals(Material.POTION) || type.equals(Material.TIPPED_ARROW)){
            PotionMeta im = (PotionMeta) myItem.getItemMeta();
            assert im != null;
            im.addCustomEffect(potionEffect, true);
            im.setDisplayName(ChatUtils.translateColor(displayName));
            im.setUnbreakable(true);
            myItem.setItemMeta(im);
        } else {
            ItemMeta im = myItem.getItemMeta();
            assert im != null;
            im.setDisplayName(ChatUtils.translateColor(displayName));
            im.setUnbreakable(true);
            myItem.setItemMeta(im);
        }

        return myItem;
    }
}
