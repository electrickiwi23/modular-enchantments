package mintnetwork.modularenchantments.Events;

import mintnetwork.modularenchantments.Enchantments.DestructionCurse;
import mintnetwork.modularenchantments.Enchantments.ExperienceEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class CommonEvents {

    public static List<BlockPos> destructionExplotions = new ArrayList<>();

    @SubscribeEvent
    public static void LevelTick(TickEvent.LevelTickEvent event) {
        List<BlockPos> removeExplode = new ArrayList<>();
        for (BlockPos pos: destructionExplotions) {
            DamageSource.explosion(event.level.explode(null,pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5, (float) (Math.random()*4F), Explosion.BlockInteraction.DESTROY));
            removeExplode.add(pos);
        }
        destructionExplotions.removeAll(removeExplode);
    }

    @SubscribeEvent
    public static void BlockBreakPost(final BlockEvent.BreakEvent event) {
        LivingEntity entity = event.getPlayer();
        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(entity.getMainHandItem());
        for (Enchantment e : enchants.keySet()) {
            if (e instanceof DestructionCurse) {
                BlockPos pos = event.getPos();
                destructionExplotions.add(pos);
            }
            if (e instanceof ExperienceEnchantment){
                event.setExpToDrop(ExperienceEnchantment.calculateExp(event.getExpToDrop(), enchants.get(e)));
            }
        }
    }

    @SubscribeEvent (priority = EventPriority.HIGHEST)
    public static void ArrowShoot(ArrowLooseEvent event) {
        int VolleyLevel = EnchantmentHelper.getEnchantmentLevel(Registration.VOLLEY.get(),event.getEntity());
        ItemStack itemStack = event.getEntity().getProjectile(event.getBow());
        if (event.hasAmmo()) {
            if (itemStack.isEmpty()) {
                itemStack = new ItemStack(Items.ARROW);
            }
            if (!event.getLevel().isClientSide) {
                ArrowItem arrowitem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                float f = BowItem.getPowerForTime(event.getCharge());
                if ((double) f >= 0.1D) {
                    for (int v = 0; v < VolleyLevel * 3; v++) {
                        AbstractArrow abstractarrow = arrowitem.createArrow(event.getLevel(), itemStack, event.getEntity());
                        abstractarrow = ((BowItem) event.getBow().getItem()).customArrow(abstractarrow);
                        abstractarrow.shootFromRotation(event.getEntity(), event.getEntity().getXRot(), event.getEntity().getYRot(), 0.0F, f * 3.0F, 1.0F + 1.5F * VolleyLevel + 2F);

                        abstractarrow.setBaseDamage(abstractarrow.getBaseDamage()/2.6);

                        if (f == 1.0F) {
                            abstractarrow.setCritArrow(true);
                        }

                        int j = event.getBow().getEnchantmentLevel(Enchantments.POWER_ARROWS);
                        if (j > 0) {
                            abstractarrow.setBaseDamage((abstractarrow.getBaseDamage() + (double)j * 0.5D + 0.5D));
                        }

                        int k = event.getBow().getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
                        if (k > 0) {
                            abstractarrow.setKnockback(k);
                        }

                        if (event.getBow().getEnchantmentLevel(Enchantments.FLAMING_ARROWS) > 0) {
                            abstractarrow.setSecondsOnFire(100);
                        }
                        abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

                        abstractarrow.tick();
                        abstractarrow.addTag("volley_arrow");
                        event.getLevel().addFreshEntity(abstractarrow);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void EntitySpawn(final EntityJoinLevelEvent event){
        if (event.getEntity() instanceof AbstractArrow arrow){
            if (arrow.getOwner()!=null&& arrow.getOwner() instanceof LivingEntity owner){
                event.getEntity().sendSystemMessage(Component.literal("Test shot"));
                int VolleyLevel = EnchantmentHelper.getEnchantmentLevel(Registration.VOLLEY.get(),owner);
                boolean Sniper = EnchantmentHelper.getEnchantmentLevel(Registration.SNIPER.get(),owner)> 0;
                if (VolleyLevel > 0) {
                    if (!arrow.getTags().contains("volley_arrow")){
                        arrow.addTag("volley_arrow");
                        arrow.setBaseDamage(arrow.getBaseDamage()/2.5);
                    }
                }
                if (Sniper) {
                    arrow.setDeltaMovement(arrow.getDeltaMovement().scale(1.3f));
                    arrow.setBaseDamage(arrow.getBaseDamage()/1.3);

                }
            }
        }
    }

    @SubscribeEvent
    public static void HarvestCheck(PlayerEvent.HarvestCheck event) {

        ItemStack stack = event.getEntity().getMainHandItem();
        if (stack.getEnchantmentLevel(Registration.UTILITY.get())>0) {
            Item item = stack.getItem();
            if (!(item instanceof DiggerItem)) {
                return;
            }
            boolean tier = TierSortingRegistry.isCorrectTierForDrops(((DiggerItem) item).getTier(), event.getTargetBlock());

            if (tier) {
                event.setCanHarvest(true);
            }
        }
    }
}