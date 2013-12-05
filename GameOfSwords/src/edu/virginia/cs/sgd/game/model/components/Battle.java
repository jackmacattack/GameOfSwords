package edu.virginia.cs.sgd.game.model.components;

import java.util.ArrayList;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

public class Battle {

	@Mapper static ComponentMapper<Stats> statsMapper;
	@Mapper static ComponentMapper<Weapon> weaponMapper;
	@Mapper static ComponentMapper<MapPosition> positionMapper;
	@Mapper static ComponentMapper<HP> hpMapper;
	
	public static boolean OneOnOneFight(Entity attacker, Entity defender) {	
		boolean attackLands = calculateFightDoesHit(attacker, defender);
		boolean killedTarget = false;
		if(attackLands) {
			System.out.println("Attack hit");
			int damage = calculateFightDamage(attacker, defender);
			HP defenderHP = defender.getComponent(HP.class);//hpMapper.get(defender);
			int newHP = defenderHP.getHP() - damage;
			if(newHP <= 0) {
				newHP = 0;
				killedTarget = true;
			}
			defenderHP.setHP(newHP);
		}
		attacker.changedInWorld();
		defender.changedInWorld();
		return attackLands;
	}
	
	public static void AreaOfEffectFight(Entity attacker, ArrayList<Entity> defenders) {
		
	}
	
	private static boolean calculateFightDoesHit(Entity attacker, Entity defender) {
		Stats attackerStats = attacker.getComponent(Stats.class);//statsMapper.get(attacker);
		Weapon attackerWeapon = attacker.getComponent(Weapon.class);//weaponMapper.get(attacker);
		MapPosition attackerPosition = attacker.getComponent(MapPosition.class);//positionMapper.get(attacker);		
		
		Stats defenderStats = defender.getComponent(Stats.class);//statsMapper.get(defender);	
		MapPosition defenderPosition = defender.getComponent(MapPosition.class);//positionMapper.get(defender);
		
		int range = MapPosition.calculateDistance(attackerPosition, defenderPosition);
		
		int chance = attackerWeapon.getAccuracy() + 10 * range - defenderStats.getDexterity();
		System.out.println("Chance of attack landing: " + chance);
		return (Math.random()*(100-0)) < chance;
	}
	
	private static int calculateFightDamage(Entity attacker, Entity defender) {
		Stats attackerStats = attacker.getComponent(Stats.class);//statsMapper.get(attacker);
		Weapon attackerWeapon = attacker.getComponent(Weapon.class);//weaponMapper.get(attacker);
		
		Stats defenderStats = defender.getComponent(Stats.class);//statsMapper.get(defender);	

		int damage  = attackerStats.getStrength() + attackerWeapon.getPower() - defenderStats.getDefense();
		return damage;
	}

	
}