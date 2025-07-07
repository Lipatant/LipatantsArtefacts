import json

def format_added_entry(data: dict) -> dict:
    DEFAULT_VALUES = {
        "type": "minecraft:loot_table"
    }
    output = data.copy()
    for key in DEFAULT_VALUES:
        if key not in output:
            output[key] = DEFAULT_VALUES[key]
    output = {key: value for key, value in sorted(output.items())}
    return {"type": output.pop("type"), **output}

def format_added_pool(data: dict) -> dict:
    DEFAULT_VALUES = {
        "bonus_rolls": 0.0,
        "rolls": 1.0,
    }
    output = data.copy()
    for key in DEFAULT_VALUES:
        if key not in output:
            output[key] = DEFAULT_VALUES[key]
    output["entries"] = [format_added_entry(entry) for entry in output["entries"]]
    output = {key: value for key, value in sorted(output.items())}
    return output

def generate_loot_table(loot_table: str, data: dict) -> bool:
    global file_count
    # Load source file.
    source_file_path = SOURCE_DATA_PACK_PATH + LOOT_TABLE_PATH + loot_table + ".json"
    source_file = open(source_file_path, "r")
    source = json.load(source_file)
    # Load loot table file.
    loot_table_file_path = DATA_PACK_PATH + LOOT_TABLE_PATH + loot_table + ".json"
    loot_table_file = open(loot_table_file_path, "w")
    # Insert data.
    data_added_entry_default_values = {
        "bonus_rolls": 0.0,
        "rolls": 1.0,
    }
    data_added_pool_default_values = {
        "bonus_rolls": 0.0,
        "rolls": 1.0,
    }
    data_added_pools = data.get("added_pools", [])
    for added_pool in data_added_pools:
        source["pools"].append(format_added_pool(added_pool))
    data_added_entries = data.get("added_entries", [])
    for added_entry in data_added_entries:
        source["pools"][0]["entries"].append(format_added_entry(added_entry))
    # Write data.
    print(source_file_path, "->", loot_table_file_path)
    json.dump(source, loot_table_file, indent=4)
    # Close files.
    loot_table_file.close()
    source_file.close()
    file_count += 1
    return True

# Path of the output data pack.
DATA_PACK_PATH = "../LipatantsArtefactsData/"
# Path of the location containing the loot tables from a data pack.
LOOT_TABLE_PATH = "data/minecraft/loot_table/"
# Location of the default data pack of Minecraft.
SOURCE_DATA_PACK_PATH = "D:/Minecraft/"
# Source path for the script.
SOURCE_PATH = "./sources/"

# File count generated.
file_count = 0

loot_table_source_file = open(SOURCE_PATH + "loot_tables.json", "r")
loot_table_source = json.load(loot_table_source_file)
loot_table_source_file.close()

for loot_table in loot_table_source:
    generate_loot_table(loot_table, loot_table_source[loot_table])

print("Job done for %s files." % file_count)