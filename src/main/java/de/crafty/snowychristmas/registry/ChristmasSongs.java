package de.crafty.snowychristmas.registry;

import de.crafty.snowychristmas.SnowyChristmas;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public class ChristmasSongs {


    //Last Christmas - Wham
    public static final ResourceKey<JukeboxSong> LAST_CHRISTMAS = registerSong("last_christmas");

    //All I want for Christmas is you - Mariah Carey
    public static final ResourceKey<JukeboxSong> AIWFCIY = registerSong("aiwfciy");

    //Driving Home for Christmas - Chris Rea
    public static final ResourceKey<JukeboxSong> DHFC = registerSong("dhfc");

    //It's beginning to look a lot like Christmas - Michael Bublé
    public static final ResourceKey<JukeboxSong> IBTLALLC = registerSong("ibtlallc");

    //Underneath the Tree - Kelly Clarkson
    public static final ResourceKey<JukeboxSong> UTT = registerSong("utt");

    public static final ResourceKey<JukeboxSong> GZUZ = registerSong("gzuz");

    private static ResourceKey<JukeboxSong> registerSong(String id){
        ResourceLocation soundLocation = ResourceLocation.fromNamespaceAndPath(SnowyChristmas.MODID, "christmas_disc." + id);
        Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, soundLocation, SoundEvent.createVariableRangeEvent(soundLocation));

        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(SnowyChristmas.MODID, id));
    }

}
