package io.github.derexxd.safetrample.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {

    @Inject(method = "turnToDirt", at = @At("HEAD"), cancellable = true)
    private static void onTurnToDirt(Entity entity, BlockState state, Level level, BlockPos pos, CallbackInfo ci) {
        if (!(entity instanceof Player player)) return;

        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        Optional<Holder.Reference<Enchantment>> featherFalling = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .get(Enchantments.FEATHER_FALLING);
        if (featherFalling.isEmpty()) return;
        if (EnchantmentHelper.getItemEnchantmentLevel(featherFalling.get(), boots) <= 0) return;

        ci.cancel();
    }
}
