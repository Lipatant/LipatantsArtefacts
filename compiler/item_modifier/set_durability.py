def generate_data() -> dict:
    data = {
        "chainmail": {
            "base": 15,
        },
        "diamond": {
            "base": 33,
            "elytra": 432,
        },
        "golden": {
            "base": 7,
        },
        "iron": {
            "base": 15,
        },
        "leather": {
            "base": 5,
        },
        "netherite": {
            "base": 37,
            "elytra": 496,
        },
        "turtle": {
            "base": 25,
        },
    }
    data_generated_multiplicative = {
        "boots": 13,
        "chestplate": 16,
        "helmet": 11,
        "leggings": 15,
    }
    output = {}
    for tier in data:
        output[tier] = {}
        if "base" in data[tier]:
            for item in data_generated_multiplicative:
                if item in data[tier]:
                    continue
                output[tier][item] = data[tier]["base"] * data_generated_multiplicative[item]
        for item in data[tier]:
            if item == "base":
                continue
            output[tier][item] = data[tier][item]
    return output