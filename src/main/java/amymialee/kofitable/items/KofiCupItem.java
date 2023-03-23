package amymialee.kofitable.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class KofiCupItem extends Item {
    public KofiCupItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 40 * 20, 0, true, false));
            user.getItemCooldownManager().set(this, 30 * 20);
            world.playSoundFromEntity(null, user, SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS, 1, 1);
        }
        return super.use(world, user, hand);
    }
}