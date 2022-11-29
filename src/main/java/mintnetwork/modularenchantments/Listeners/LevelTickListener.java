package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class LevelTickListener {

    @SubscribeEvent
    public static void onUpdate(TickEvent.LevelTickEvent event) {
        List<BlockPos> removeExplode = new ArrayList<>();
        for (BlockPos pos:BlockDestroyListener.explode) {
            DamageSource.explosion(event.level.explode(null,pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5, (float) (Math.random()*4F), Explosion.BlockInteraction.DESTROY));
            removeExplode.add(pos);
        }
        BlockDestroyListener.explode.removeAll(removeExplode);




    }
}
