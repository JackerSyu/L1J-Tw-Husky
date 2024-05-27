
-- 修改炎雙數值
update weapon
set dmg_small = '34', dmg_large = '37', hitmodifier = '8', dmgmodifier='26'
where item_id='203'

-- 新增炎雙改
INSERT INTO `weapon` (`item_id`, `name`, `unidentified_name_id`, `identified_name_id`, `type`, `material`, `weight`, `invgfx`, `grdgfx`, `itemdesc_id`, `dmg_small`, `dmg_large`, `range`, `safenchant`, `use_royal`, `use_knight`, `use_mage`, `use_elf`, `use_darkelf`, `use_dragonknight`, `use_illusionist`, `hitmodifier`, `dmgmodifier`, `add_str`, `add_con`, `add_dex`, `add_int`, `add_wis`, `add_cha`, `add_hp`, `add_mp`, `add_hpr`, `add_mpr`, `add_sp`, `m_def`, `haste_item`, `double_dmg_chance`, `magicdmgmodifier`, `canbedmg`, `min_lvl`, `max_lvl`, `bless`, `trade`, `cant_delete`, `max_use_time`) 
 VALUES (200200, '炎魔雙手劍【★】', '炎魔雙手劍【★】', '炎魔雙手劍【★】', 'tohandsword', 'blackmithril', 60000, 2268, 5426, 0, 30, 33, 1, 0, 1, 1, 0, 0, 0, 1, 0, 8, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0);

INSERT INTO `weapon_skill` (`weapon_id`, `note`, `probability`, `fix_damage`, `random_damage`, `area`, `skill_id`, `skill_time`, `effect_id`, `effect_target`, `arrow_type`, `attr`, `gfx_id`, `gfx_id_target`) VALUES (200200, '炎魔雙手劍【★】', 15, 90, 90, 2, 0, 0, 762, 0, 0, 2, 0, 1);


-- 新增死騎劍改
INSERT INTO `weapon` (`item_id`, `name`, `unidentified_name_id`, `identified_name_id`, `type`, `material`, `weight`, `invgfx`, `grdgfx`, `itemdesc_id`, `dmg_small`, `dmg_large`, `range`, `safenchant`, `use_royal`, `use_knight`, `use_mage`, `use_elf`, `use_darkelf`, `use_dragonknight`, `use_illusionist`, `hitmodifier`, `dmgmodifier`, `add_str`, `add_con`, `add_dex`, `add_int`, `add_wis`, `add_cha`, `add_hp`, `add_mp`, `add_hpr`, `add_mpr`, `add_sp`, `m_def`, `haste_item`, `double_dmg_chance`, `magicdmgmodifier`, `canbedmg`, `min_lvl`, `max_lvl`, `bless`, `trade`, `cant_delete`, `max_use_time`) VALUES (200201, '死亡騎士的烈炎之劍【★】', '死亡騎士的烈炎之劍【★】', '死亡騎士的烈炎之劍【★】', 'sword', 'iron', 40000, 1259, 3304, 992, 16, 10, 1, 6, 0, 1, 0, 1, 0, 1, 0, 5, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0);


INSERT INTO `weapon_skill` (`weapon_id`, `note`, `probability`, `fix_damage`, `random_damage`, `area`, `skill_id`, `skill_time`, `effect_id`, `effect_target`, `arrow_type`, `attr`, `gfx_id`, `gfx_id_target`) VALUES (200201, '死亡騎士烈炎之劍【★】', 15, 90, 90, 2, 0, 0, 245, 0, 0, 2, 0, 1);

--新增真弓
INSERT INTO `weapon` (`item_id`, `name`, `unidentified_name_id`, `identified_name_id`, `type`, `material`, `weight`, `invgfx`, `grdgfx`, `itemdesc_id`, `dmg_small`, `dmg_large`, `range`, `safenchant`, `use_royal`, `use_knight`, `use_mage`, `use_elf`, `use_darkelf`, `use_dragonknight`, `use_illusionist`, `hitmodifier`, `dmgmodifier`, `add_str`, `add_con`, `add_dex`, `add_int`, `add_wis`, `add_cha`, `add_hp`, `add_mp`, `add_hpr`, `add_mpr`, `add_sp`, `m_def`, `haste_item`, `double_dmg_chance`, `magicdmgmodifier`, `canbedmg`, `min_lvl`, `max_lvl`, `bless`, `trade`, `cant_delete`, `max_use_time`) VALUES (200202, '真冥皇熾天使弓', '真冥皇熾天使弓', '真冥皇熾天使弓', 'bow', 'wood', 25000, 2168, 5402, 0, 5, 5, -1, 6, 0, 0, 0, 1, 0, 0, 0, 12, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

INSERT INTO `weapon_skill` (`weapon_id`, `note`, `probability`, `fix_damage`, `random_damage`, `area`, `skill_id`, `skill_time`, `effect_id`, `effect_target`, `arrow_type`, `attr`, `gfx_id`, `gfx_id_target`) VALUES (200202, '真冥皇熾天使弓', 5, 80, 0, 0, 0, 0, 6288, 0, 1, 0, 0, 1);

-- 真‧冥皇執行劍修正新版數值
UPDATE weapon 
SET dmg_small = '34', dmg_large = '37', hitmodifier = '8', dmgmodifier= '26'
WHERE item_id = '61'

-- 聖經修正新版數值
UPDATE weapon
SET dmg_small = '5', dmg_large = '5', hitmodifier = '15', dmgmodifier = '10', add_sp = '10', add_wis = '0', add_int='2'
WHERE item_id = '134'

-- 紅影雙刀修正新版數值
UPDATE weapon
SET dmg_small = '30', dmg_large = '20', hitmodifier = '10', dmgmodifier = '18', add_wis = '0'
WHERE item_id = '86'

-- 風刃短劍修正新版數值 
UPDATE weapon
SET dmg_small = '23', dmg_large = '15', hitmodifier = '15', dmgmodifier = '16', add_hp='100', add_dex = '0'
WHERE item_id = '12'