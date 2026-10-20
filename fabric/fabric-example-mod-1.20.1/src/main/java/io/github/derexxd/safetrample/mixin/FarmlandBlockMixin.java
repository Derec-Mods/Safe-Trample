package io.github.derexxd.safetrample.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
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

    @Inject(method = "setToDirt", at = @At("HEAD"), cancellable = true)
    private static void onSetToDirt(Entity entity, BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        if (!(entity instanceof PlayerEntity player)) return;

        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        if (EnchantmentHelper.getLevel(Enchantments.FEATHER_FALLING, boots) <= 0) return;

        ci.cancel();
    }
}
