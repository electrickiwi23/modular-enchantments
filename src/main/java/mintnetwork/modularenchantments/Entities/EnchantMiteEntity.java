package mintnetwork.modularenchantments.Entities;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Predicate;


public class EnchantMiteEntity extends Monster {


    LivingEntity attackerTarget = null;
    LivingEntity owner = null;


    public EnchantMiteEntity(EntityType<? extends EnchantMiteEntity> typeIn, Level worldIn) {
        super(typeIn, worldIn);
    }

    public EnchantMiteEntity(EntityType<? extends EnchantMiteEntity> typeIn, Level worldIn,LivingEntity owner) {
        super(typeIn, worldIn);
        this.owner = owner;
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class,100, true,false,new Predicate<LivingEntity>() {
            @Override
            public boolean test(LivingEntity livingEntity) {

                if (attackerTarget!=null) return livingEntity == attackerTarget;
                if (owner !=null) return livingEntity!=owner;
                return !(livingEntity instanceof EnchantMiteEntity);
            }
        } ));
    }

    /**
     * Returns the Y Offset of this entity.
     */

    public double getMyRidingOffset() {
        return 0.1D;
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.13F;
    }


    public static AttributeSupplier.Builder createMonsterAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }



   protected boolean canTriggerWalking() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SILVERFISH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SILVERFISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SILVERFISH_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SILVERFISH_STEP, 0.15F, 1.0F);
    }



    /**
     * Called to update the entity's position/logic.
     */

    public void tick() {
        this.yBodyRot = this.getYRot();
        if (tickCount>=600){
            remove(RemovalReason.KILLED);
            this.level.addParticle(ParticleTypes.POOF,position().x, position().y,position().z,0,0,0);

        }
        super.tick();
    }

    public void setYBodyRot(float p_33553_) {
        this.setYRot(p_33553_);
        super.setYBodyRot(p_33553_);
    }

    /**
     * Set the render yaw offset
     */


    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

}
