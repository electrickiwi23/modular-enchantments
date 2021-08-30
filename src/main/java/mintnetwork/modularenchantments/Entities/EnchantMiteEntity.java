package mintnetwork.modularenchantments.Entities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SilverfishBlock;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Predicate;


public class EnchantMiteEntity extends MonsterEntity {
    private EnchantMiteEntity.EnchantMiteGoal summonSilverfish;

    LivingEntity attackerTarget = null;
    LivingEntity owner = null;

    public EnchantMiteEntity(EntityType<? extends EnchantMiteEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    public EnchantMiteEntity(EntityType<? extends EnchantMiteEntity> typeIn, World worldIn,LivingEntity owner) {
        super(typeIn, worldIn);
        this.owner = owner;
    }

    protected void registerGoals() {
        this.summonSilverfish = new EnchantMiteEntity.EnchantMiteGoal(this);
        this.goalSelector.addGoal(1, new SwimGoal(this));
//        this.goalSelector.addGoal(3, this.summonSilverfish);
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
//        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class,100, true,false,new Predicate<LivingEntity>() {
            @Override
            public boolean test(LivingEntity livingEntity) {

                if (attackerTarget!=null) return livingEntity == attackerTarget;
                if (owner !=null) return livingEntity!=owner;
                return true;
            }
        } ));
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset() {
        return 0.1D;
    }

    public void setTarget(LivingEntity living){
        attackerTarget = living;
        this.setAttackTarget(living);

    }


    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.13F;
    }

    public static AttributeModifierMap.MutableAttribute func_234301_m_() {
        return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MAX_HEALTH, 4.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D);
    }



        protected boolean canTriggerWalking() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15F, 1.0F);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            if ((source instanceof EntityDamageSource || source == DamageSource.MAGIC) && this.summonSilverfish != null) {
                this.summonSilverfish.notifyHurt();
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        this.renderYawOffset = this.rotationYaw;
        super.tick();
        if (this.ticksExisted>=300){
            for (int i = 0; i < 5; i++) {
                this.getEntityWorld().addParticle(ParticleTypes.WITCH,this.getPosXRandom(.1),this.getPosY(),this.getPosZRandom(.1),.01,.01,.01);
            }
            this.remove();
        }
    }

    /**
     * Set the render yaw offset
     */
    public void setRenderYawOffset(float offset) {
        this.rotationYaw = offset;
        super.setRenderYawOffset(offset);
    }

    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return SilverfishBlock.canContainSilverfish(worldIn.getBlockState(pos.down())) ? 10.0F : super.getBlockPathWeight(pos, worldIn);
    }

    public static boolean func_223331_b(EntityType<SilverfishEntity> p_223331_0_, IWorld p_223331_1_, SpawnReason reason, BlockPos p_223331_3_, Random p_223331_4_) {
        if (canMonsterSpawn(p_223331_0_, p_223331_1_, reason, p_223331_3_, p_223331_4_)) {
            PlayerEntity playerentity = p_223331_1_.getClosestPlayer((double)p_223331_3_.getX() + 0.5D, (double)p_223331_3_.getY() + 0.5D, (double)p_223331_3_.getZ() + 0.5D, 5.0D, true);
            return playerentity == null;
        } else {
            return false;
        }
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }


    static class EnchantMiteGoal extends Goal {
        private final EnchantMiteEntity enchantMite;
        private int lookForFriends;

        public EnchantMiteGoal(EnchantMiteEntity silverfishIn) {
            this.enchantMite = silverfishIn;
        }

        public void notifyHurt() {
            if (this.lookForFriends == 0) {
                this.lookForFriends = 20;
            }

        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return this.lookForFriends > 0;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            --this.lookForFriends;
            if (this.lookForFriends <= 0) {
                World world = this.enchantMite.world;
                Random random = this.enchantMite.getRNG();
                BlockPos blockpos = this.enchantMite.getPosition();

                for(int i = 0; i <= 5 && i >= -5; i = (i <= 0 ? 1 : 0) - i) {
                    for(int j = 0; j <= 10 && j >= -10; j = (j <= 0 ? 1 : 0) - j) {
                        for(int k = 0; k <= 10 && k >= -10; k = (k <= 0 ? 1 : 0) - k) {
                            BlockPos blockpos1 = blockpos.add(j, i, k);
                            BlockState blockstate = world.getBlockState(blockpos1);
                            Block block = blockstate.getBlock();
                            if (block instanceof SilverfishBlock) {
                                if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, this.enchantMite)) {
                                    world.destroyBlock(blockpos1, true, this.enchantMite);
                                } else {
                                    world.setBlockState(blockpos1, ((SilverfishBlock)block).getMimickedBlock().getDefaultState(), 3);
                                }

                                if (random.nextBoolean()) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
