package de.crafty.snowychristmas.mixin.server.level;


import de.crafty.snowychristmas.config.SnowyChristmasConfig;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(ServerLevel.class)
public abstract class MixinServerLevel extends Level implements WorldGenLevel, AttachmentTarget {


    @Shadow @Final public static IntProvider RAIN_DURATION;

    @Shadow @Final public static IntProvider RAIN_DELAY;

    protected MixinServerLevel(WritableLevelData writableLevelData, ResourceKey<Level> resourceKey, RegistryAccess registryAccess, Holder<DimensionType> holder, Supplier<ProfilerFiller> supplier, boolean bl, boolean bl2, long l, int i) {
        super(writableLevelData, resourceKey, registryAccess, holder, supplier, bl, bl2, l, i);
    }


    @Redirect(method = "advanceWeatherCycle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/IntProvider;sample(Lnet/minecraft/util/RandomSource;)I", ordinal = 2))
    private int applyLongerSnowFeature(IntProvider instance, RandomSource randomSource){

        int longerDuration = (int) ((RAIN_DURATION.getMaxValue() - RAIN_DURATION.getMinValue()) * SnowyChristmasConfig.INSTANCE.longerSnowPeriods());
        UniformInt uniformInt = UniformInt.of(RAIN_DURATION.getMinValue() + longerDuration, RAIN_DURATION.getMaxValue() + longerDuration);

        return uniformInt.sample(randomSource);
    }

    @Redirect(method = "advanceWeatherCycle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/IntProvider;sample(Lnet/minecraft/util/RandomSource;)I", ordinal = 3))
    private int applyHigherSnowRateFeature(IntProvider instance, RandomSource randomSource){

        int higherRate = (int) ((RAIN_DELAY.getMaxValue() - RAIN_DELAY.getMinValue()) * SnowyChristmasConfig.INSTANCE.higherSnowRate());
        UniformInt uniformInt = UniformInt.of(RAIN_DELAY.getMinValue() - higherRate, RAIN_DELAY.getMaxValue() - higherRate);

        return uniformInt.sample(randomSource);
    }
}
