package amymialee.kofitable.items;

import amymialee.kofitable.KofiTable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SupporterStickItem extends Item {
    public SupporterStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (KofiTable.CONTRIBUTORS.get(user.getUuid()).supporter) {
            user.sendMessage(Text.of("SUPPORTER!"), true);
        } else {
            user.sendMessage(Text.of("NOT SUPPORTER!"), true);
        }
        return super.use(world, user, hand);
    }
}