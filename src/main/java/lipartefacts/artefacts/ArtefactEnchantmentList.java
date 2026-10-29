package lipartefacts.artefacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lipartefacts.artefacts.enchantments.ArtefactEnchantment;
import lipartefacts.artefacts.enchantments.ArtefactEnchantmentCallToChaos;
import lipartefacts.artefacts.enchantments.ArtefactEnchantmentDoubleShot;
import lipartefacts.artefacts.enchantments.ArtefactEnchantmentFracture;
import lipartefacts.artefacts.enchantments.ArtefactEnchantmentIllagerBane;
import lipartefacts.artefacts.enchantments.ArtefactEnchantmentImperilment;
import lipartefacts.artefacts.enchantments.ArtefactEnchantmentVampirism;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class ArtefactEnchantmentList extends ArtefactListModule {

    public final ArtefactEnchantment CALL_TO_CHAOS = new ArtefactEnchantmentCallToChaos();
    public final ArtefactEnchantment DOUBLE_SHOT = new ArtefactEnchantmentDoubleShot();
    public final ArtefactEnchantment FRACTURE = new ArtefactEnchantmentFracture();
    public final ArtefactEnchantment ILLAGER_BANE = new ArtefactEnchantmentIllagerBane();
    public final ArtefactEnchantment IMPERILMENT = new ArtefactEnchantmentImperilment();
    public final ArtefactEnchantment VAMPIRISM = new ArtefactEnchantmentVampirism();

    public Map<ResourceKey<Enchantment>, Enchantment.Builder> getEnchantmentDefinitionList(BootstrapContext<Enchantment> context) {
        HashMap<ResourceKey<Enchantment>, Enchantment.Builder> list = new HashMap<ResourceKey<Enchantment>, Enchantment.Builder>();

        getEnchantmentList().forEach((e) -> {
            list.put(e.ENCHANTMENT_ID, e.getEnchantmentDefinition(context));
        });
        return list;
    }

    public Collection<ResourceKey<Enchantment>> getEnchantmentIdList() {
        ArrayList<ResourceKey<Enchantment>> list = new ArrayList<ResourceKey<Enchantment>>();

        getEnchantmentList().forEach((e) -> {
            list.add(e.ENCHANTMENT_ID);
        });
        return list;
    }

    protected List<ArtefactEnchantment> getEnchantmentList() {
        return List.of(
            CALL_TO_CHAOS,
            DOUBLE_SHOT,
            FRACTURE,
            ILLAGER_BANE,
            IMPERILMENT,
            VAMPIRISM
        );
    }

    public Map<TagKey<Enchantment>, Collection<ResourceKey<Enchantment>>> getEnchantmentTagList() {
        HashMap<TagKey<Enchantment>, Collection<ResourceKey<Enchantment>>> list = new HashMap<TagKey<Enchantment>, Collection<ResourceKey<Enchantment>>>();

        getEnchantmentList().forEach((e) -> {
            e.getEnchantmentTagList().forEach((t) -> {
                Collection<ResourceKey<Enchantment>> l = list.getOrDefault(t, null);
                if (l == null) {
                    l = new ArrayList<ResourceKey<Enchantment>>();
                    l.add(e.ENCHANTMENT_ID);
                    list.put(t, l);
                } else {
                    l.add(e.ENCHANTMENT_ID);
                }
            });
        });
        return list;
    }

    public Collection<ResourceKey<Item>> getIdList() {
        Collection<ResourceKey<Item>> list = super.getIdList();

        getEnchantmentList().forEach((e) -> {
            list.addAll(e.getIdList());
        });
        return list;
    }

    public Collection<ItemStack> getItemStackList(HolderLookup.Provider registries) {
        Collection<ItemStack> list = super.getItemStackList(registries);

        getEnchantmentList().forEach((e) -> {
            list.addAll(e.getItemStackList(registries));
        });
        return list;
    }

    public Collection<Item> getList() {
        Collection<Item> list = super.getList();

        getEnchantmentList().forEach((e) -> {
            list.addAll(e.getList());
        });
        return list;
    }

}
