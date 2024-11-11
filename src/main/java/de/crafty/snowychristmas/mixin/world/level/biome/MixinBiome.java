package de.crafty.snowychristmas.mixin.world.level.biome;


import de.crafty.snowychristmas.config.SnowyChristmasConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class MixinBiome {


    @Redirect(method = "getPrecipitationAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;coldEnoughToSnow(Lnet/minecraft/core/BlockPos;)Z"))
    private boolean applyRainToSnowFeature$1(Biome instance, BlockPos blockPos) {
        if (!SnowyChristmasConfig.INSTANCE.changeRainToSnow())
            return instance.coldEnoughToSnow(blockPos);

        int chunkRadius = SnowyChristmasConfig.INSTANCE.rainSnowChangeRadius();
        if (chunkRadius == -1)
            return true;

        ChunkPos chunkPos = new ChunkPos(blockPos);
        return (chunkPos.x <= chunkRadius && chunkPos.x >= -chunkRadius && chunkPos.z <= chunkRadius && chunkPos.z >= -chunkRadius) || instance.coldEnoughToSnow(blockPos);
    }


    @Inject(method = "warmEnoughToRain", at = @At("HEAD"), cancellable = true)
    private void applyRainToSnowFeature$2(BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (!SnowyChristmasConfig.INSTANCE.changeRainToSnow())
            return;

        int chunkRadius = SnowyChristmasConfig.INSTANCE.rainSnowChangeRadius();
        if (chunkRadius == -1)
            cir.setReturnValue(false);

        ChunkPos chunkPos = new ChunkPos(blockPos);
        if(chunkPos.x <= chunkRadius && chunkPos.x >= -chunkRadius && chunkPos.z <= chunkRadius && chunkPos.z >= -chunkRadius)
            cir.setReturnValue(false);
    }
}
