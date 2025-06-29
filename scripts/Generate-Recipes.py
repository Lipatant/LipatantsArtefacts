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

def get_color_dict() -> {str: str}:
    return {
        "black": "1908001",
        "blue": "3949738",
        "brown": "8606770",
        "cyan": "1481884",
        "gray": "4673362",
        "green": "6192150",
        "light_blue": "3847130",
        "light_gray": "10329495",
        "lime": "8439583",
        "magenta": "13061821",
        "orange": "16351261",
        "pink": "15961002",
        "purple": "8991416",
        "red": "11546150",
        "yellow": "16701501",
        "white": "16383998",
    }

DATAPACK_PATH = "../LipatantsArtefactsData/"
TEMPLATE_PATH = "./templates/"
RECIPE_PATH = DATAPACK_PATH + "data/lipartefacts/recipe/"

file_count = 0

goat_helmet_smithing_default_file = open(TEMPLATE_PATH + "goat_helmet_smithing_default.json", "r")
goat_helmet_smithing_default_source = goat_helmet_smithing_default_file.read()
goat_helmet_smithing_file = open(TEMPLATE_PATH + "goat_helmet_smithing.json", "r")
goat_helmet_smithing_source = goat_helmet_smithing_file.read()
goat_helmet_smithing_color_dict = get_color_dict()
for color in goat_helmet_smithing_color_dict:
    item_path = RECIPE_PATH + ("goat_helmet_smithing_%s_wool.json" % color)
    item_file = open(item_path, "w")
    item_source = goat_helmet_smithing_default_source if (color == "white") else goat_helmet_smithing_source
    item_file.write(item_source.replace("@1", color).replace("@2", goat_helmet_smithing_color_dict[color]))
    file_count += 1

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