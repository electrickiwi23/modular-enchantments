package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.HeavyCurse;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class PlayerSwimListener {

    private static List<Entity> hasSniped = new ArrayList<>();

    @SubscribeEvent
    public static void tickPlayer(TickEvent.PlayerTickEvent event) {

        PlayerEntity player = event.player;

        if (event.phase.equals(TickEvent.Phase.START)){


        for (ArrowEntity arrow: player.world.getLoadedEntitiesWithinAABB(ArrowEntity.class,new AxisAlignedBB(player.getPosX()+4,player.getPosY()+6,player.getPosZ()+4,player.getPosX()-4,player.getPosY()-2,player.getPosZ()-4))){
            if (!arrow.isOnGround()) {
                if (arrow.ticksExisted==1) {

//                    if (BowShootListener.sniperLevel.containsKey(arrow.getShooter())) {
//
//                        System.out.println(event.phase);
//
//
//                        System.out.println("side: " + event.side);
//
//                        Vector3d direction = arrow.getMotion().mul(2, 2, 2);
//                        if (event.side.isServer()) {
//                            if(!hasSniped.contains(arrow)) {
//                                hasSniped.add(arrow);
//                                arrow.setDamage(arrow.getDamage()/2);
//                                arrow.shoot((float) direction.getX(), (float) direction.getY(), (float) direction.getZ(), (float) direction.length(), 0);
//                                BowShootListener.sniperLevel.remove(player);
//                            }
//                        }
////
//                        if (event.side.isClient()){
//                            arrow.shoot((float) direction.getX(), (float) direction.getY(), (float) direction.getZ(), (float) direction.length(), 0);
//                            BowShootListener.sniperLevel.remove(player);
//                        }
//
//
//                    }

                    if (BowShootListener.ricochetLevel.containsKey(arrow.getShooter())) {
                        ProjectileHitEvent.ricochets.put(arrow, BowShootListener.ricochetLevel.get(arrow.getShooter()));
                        BowShootListener.ricochetLevel.remove(arrow.getShooter());
                    }
                }
            }
        }

        if (event.side.isServer()) {
            if (player.getCooldownTracker().hasCooldown(Items.SHIELD)) {
                int level = Math.max(EnchantmentHelper.getEnchantmentLevel(Registration.Recovery.get(), player.getHeldItemMainhand()), Math.max(EnchantmentHelper.getEnchantmentLevel(Registration.Recovery.get(), player.getHeldItemOffhand()), 0));
                int create = player.getCooldownTracker().cooldowns.get(Items.SHIELD).createTicks;
                int expire = player.getCooldownTracker().cooldowns.get(Items.SHIELD).expireTicks;
                if (player.getCooldownTracker().ticks == create) {
                    player.getCooldownTracker().setCooldown(Items.SHIELD, (int) ((expire - create) * (1 - .12 * level)));
                }
            }
        }


        if (event.player.isInWater()){
            for (ItemStack item: player.getArmorInventoryList()                 ) {
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(item);
                for (Enchantment e : enchants.keySet()) {
                    if (e instanceof HeavyCurse){
                        player.setJumping(false);
                        if(player.getMotion().getY()>0){
                            player.setMotion(player.getMotion().add(0,player.getMotion().getY()/-2,0));
                        } else{
                            player.setMotion(player.getMotion().add(0,-.06,0));
                        }
                    }
                }
            }
        }
    }
    }

}
