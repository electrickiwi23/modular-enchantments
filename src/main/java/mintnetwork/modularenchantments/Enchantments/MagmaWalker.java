package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;

public class MagmaWalker extends Enchantment {

    public MagmaWalker() {

        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[] {EquipmentSlot.FEET});

    }

    public int getMinCost(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 15;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 2;
    }

    public static void freezeNearby(LivingEntity living, Level worldIn, BlockPos pos, int level) {
        if (living.isOnGround()) {
            BlockState blockstate = Registration.CRUSTEDMAGAMA.get().defaultBlockState();
            float f = (float)Math.min(16, 2 + level);
            BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset((-f), -1.0, (-f)), pos.offset(f, -1.0D, f))) {
                if (blockpos.closerToCenterThan(living.position(), f)) {
                    blockpos$mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = worldIn.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(LiquidBlock.LEVEL) == 0;
                        if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.canSurvive(worldIn, blockpos) && worldIn.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(living, net.minecraftforge.common.util.BlockSnapshot.create(worldIn.dimension(), worldIn, blockpos), net.minecraft.core.Direction.UP)) {
                            worldIn.setBlockAndUpdate(blockpos, blockstate);
                            worldIn.scheduleTick(blockpos, Registration.CRUSTEDMAGAMA.get(), Mth.nextInt(living.getRandom(), 60, 120));
                        }
                    }
                }
            }

        }
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench) && !(ench instanceof FrostWalkerEnchantment) && ench != Enchantments.DEPTH_STRIDER;
    }
}

