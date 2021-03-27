package oldmoon.dustw.tinkerdream.parts;

import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ToolPart;

import java.util.ArrayList;

/**
 * @author NmmOC7
 */
public class ModPartsList {
    public static final ArrayList<ToolPart> PARTS_LIST = new ArrayList<>();

    public static final ModPartsBase TEST_PART_A = new ModPartsBase("test_a", Material.VALUE_Ingot * 3, MaterialTypes.HEAD);
    public static final ModPartsBase HORSE_MEDAL_CORE = new ModPartsBase("horse_medal_core", Material.VALUE_Ingot * 5, MaterialTypes.HEAD);
}
