package oldmoon.dustw.tinkerdream.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import oldmoon.dustw.tinkerdream.TinkerDream;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.tools.modifiers.ModReinforced;

import java.util.ArrayList;

/**
 * @author NmmOC7, DustW
 */
public class Util {
    public static double radians = Math.PI / 180;

    public static IMaterialStats getStatsFromTool(ItemStack toolStack, String identifier) {
        NBTTagCompound tag = toolStack.getTagCompound();
        IMaterialStats stats;
        NBTTagList toolMaterials = tag.getCompoundTag("TinkerData").getTagList("Materials", 8);

        for (NBTBase material : toolMaterials) {
            stats = TinkerRegistry.getMaterial(material.toString().split("\"")[1]).getStats(identifier);

            if (stats != null) {
                return stats;
            }
        }

        return null;
    }

    public static boolean isExistForNbt(NBTTagCompound nbt, String... keys) {
        NBTTagCompound nbtTemp = nbt;

        for (String key : keys) {
            if (!nbtTemp.hasKey(key)) {
                return false;
            }
        }

        return true;
    }

    public static double pythagorasTheorem(boolean isLongestSide, double longestSideOrSideA, double sideB) {
        if (isLongestSide) {
            return Math.sqrt(longestSideOrSideA * longestSideOrSideA - sideB * sideB);
        }
        else {
            return Math.sqrt(longestSideOrSideA * longestSideOrSideA + sideB * sideB);
        }
    }

    public static void entitySprint(Entity entity, float speed, float motionY) {
        double xPlus = -MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI) *
                MathHelper.cos(entity.rotationPitch / 180.0F * (float) Math.PI) * speed;
        double zPlus = MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI) *
                MathHelper.cos(entity.rotationPitch / 180.0F * (float) Math.PI) * speed;

        entity.motionY += motionY;
        entity.motionX = xPlus;
        entity.motionZ = zPlus;
    }

    public static double getSprintDistance(Entity entity) {
        return Util.pythagorasTheorem(false, entity.motionX, entity.motionZ);
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int level, float seconds) {
        entity.addPotionEffect(new PotionEffect(potion, (int) (seconds * 20) + 1, level));
    }

    public static ResourceLocation getIcon(String name) {
        return new ResourceLocation(TinkerDream.MOD_ID, String.format("textures/potions/%s.png",name));
    }

    /**
     * 确定匠魂装备是否不毁
     */
    public static boolean isUnbreakable(ItemStack stack) {
        return TagUtil.getTagSafe(stack).getBoolean(ModReinforced.TAG_UNBREAKABLE);
    }

    public static Vec3d getLookFinishPos(EntityLivingBase entity, double distance) {
        return entity.getPositionEyes(1f).add(entity.getLookVec().scale(distance));
    }

    public static ItemStack[] getItemStackFromInventory(EntityPlayer player, Item item) {
        NonNullList<ItemStack> inventory = player.inventory.mainInventory;
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        for (ItemStack stack: inventory) {
            if (stack.getItem() == item) {
                itemStacks.add(stack);
            }
        }

        return itemStacks.toArray(new ItemStack[]{});
    }
}