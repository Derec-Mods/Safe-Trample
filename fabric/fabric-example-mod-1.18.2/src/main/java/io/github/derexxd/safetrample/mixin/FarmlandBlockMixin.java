package io.github.derexxd.safetrample.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {

    @Inject(method = "onLandedUpon", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FarmlandBlock;setToDirt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"), cancellable = true)
    private void onLand(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (!(entity instanceof PlayerEntity player)) return;

        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        if (EnchantmentHelper.getLevel(Enchantments.FEATHER_FALLING, boots) <= 0) return;

        entity.handleFallDamage(fallDistance, 1.0F, DamageSource.FALL);
        ci.cancel();
    }
}
