package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class ProjectileHitEvent{

    public static Map<ArrowEntity, Integer> ricochets = new HashMap<>();

    @SubscribeEvent
    public static void onUpdate(ProjectileImpactEvent event) {
        Entity projectile = event.getEntity();
         if (event.getEntity() instanceof AbstractArrowEntity){
             ArrowEntity arrow = (ArrowEntity) projectile;

             if (ricochets.containsKey(arrow)) {
                 int level = ricochets.get(arrow);
                 if (level > 0) {
                     ricochets.replace(arrow, level - 1 );
                     if (event.getRayTraceResult().getType().equals(RayTraceResult.Type.BLOCK)) {

                         Vector3d direction = arrow.getMotion().mul(-.7, -.7, -.7);

                         event.setCanceled(true);

                         Vector3d teleport = direction.normalize();

                         Vector3d position = arrow.getPositionVec().add(teleport.getX(), teleport.getY(), teleport.getZ());

                         arrow.setPositionAndUpdate(position.getX(), position.getY(), position.getZ());

                         arrow.shoot(direction.getX(), direction.getY(), direction.getZ(), (float) direction.length(), 1);
//                         Vector3d vector3d = projectile.getMotion();
//                         arrow.setMotion(arrow.getMotion().add(vector3d.x, vector3d.y, vector3d.z));
                     }
                 }else{
                     ricochets.remove(arrow);
                 }
             }

         }
    }
}
