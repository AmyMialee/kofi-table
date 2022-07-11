package amymialee.kofitable.registry;

import amymialee.kofitable.KofiTable;
import amymialee.kofitable.items.SupporterStickItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class KofiTableItems {
    public static final ArrayList<Item> MOD_ITEMS = new ArrayList<>();

    public static final Item SUPPORTER_STICK = registerItem("supporter_stick", MOD_ITEMS, new SupporterStickItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).group(KofiTable.KOFI_GROUP)));

    public static void init() {}

    public static Item registerItem(String name, ArrayList<Item> list, Item item) {
        Registry.register(Registry.ITEM, KofiTable.id(name), item);
        list.add(item);
        return item;
    }

    public static ItemStack getRecipeKindIcon(ArrayList<Item> arrayList) {
        if (arrayList.size() == 1) {
            return arrayList.get(0).getDefaultStack();
        }
        return arrayList.get(KofiTable.RANDOM.nextInt(arrayList.size() - 1)).getDefaultStack();
    }
}