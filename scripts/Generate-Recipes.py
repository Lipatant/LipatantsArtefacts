def combine_lists(material_list: [str], piece_list: [str]) -> [str]:
    output = []
    for material in material_list:
        for piece in piece_list:
            output.append(material + "_" + piece)
    return output

def get_item_list_armors(tags: [str] = []) -> [str]:
    output = ["elytra", "turtle_helmet"] + combine_lists(
        ["leather", "chainmail", "iron", "golden", "diamond", "netherite"],
        ["helmet", "chestplate", "leggings", "boots"],
    )
    if "durability" not in tags:
        output.append("carved_pumpkin")
    return output

def get_item_list_tools() -> [str]:
    return [
            "brush",
            "bow",
            "carrot_on_a_stick",
            "crossbow",
            "fishing_rod",
            "flint_and_steel",
            "mace",
            "shears",
            "shield",
            "trident",
            "warped_fungus_on_a_stick",
        ] + combine_lists(
        ["wooden", "stone", "iron", "golden", "diamond", "netherite"],
        ["axe", "hoe", "pickaxe", "shovel", "sword"],
    )

DATAPACK_PATH = "../LipatantsArtefactsData/"
TEMPLATE_PATH = "./templates/"
RECIPE_PATH = DATAPACK_PATH + "data/lipartefacts/recipe/"

file_count = 0

origin_crystal_source_file = open(TEMPLATE_PATH + "origin_crystal.json", "r")
origin_crystal_source = origin_crystal_source_file.read()
origin_crystal_item_list = get_item_list_armors() + get_item_list_tools()
for item in origin_crystal_item_list:
    item_path = RECIPE_PATH + ("origin_crystal_on_%s.json" % item)
    item_file = open(item_path, "w")
    item_file.write(origin_crystal_source.replace("@1", "minecraft:" + item))
    file_count += 1

repair_crystal_source_file = open(TEMPLATE_PATH + "repair_crystal.json", "r")
repair_crystal_source = repair_crystal_source_file.read()
repair_crystal_item_list = get_item_list_armors(["durability"]) + get_item_list_tools()
for item in repair_crystal_item_list:
    item_path = RECIPE_PATH + ("repair_crystal_on_%s.json" % item)
    item_file = open(item_path, "w")
    item_file.write(repair_crystal_source.replace("@1", "minecraft:" + item))
    file_count += 1

print("Job done for %s files." % file_count)