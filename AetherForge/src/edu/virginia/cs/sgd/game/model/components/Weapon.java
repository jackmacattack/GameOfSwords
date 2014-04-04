package edu.virginia.cs.sgd.game.model.components;

import java.util.ArrayList;

import com.artemis.Component;

public class Weapon extends Component{
	private enum Property {
		POST_MOVEMENT, MAP_ATTACK, CHAIN_ATTACK
	}
	private int power, minRange, maxRange, accuracy, ammo, maxAmmo, critical;
	private ArrayList<Property> properties;
	
	public Weapon(boolean ranged){
		power = ranged ? 12 : 15;
		minRange = ranged ? 2 : 1;
		maxRange = ranged ? 2 : 1;
		accuracy = ranged ? 85 : 95;
		ammo = 6;
		maxAmmo = 20;
		critical = 5;
		properties = new ArrayList<Property>();
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getMinRange() {
		return minRange;
	}

	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public int getMaxAmmo() {
		return maxAmmo;
	}

	public void setMaxAmmo(int maxAmmo) {
		this.maxAmmo = maxAmmo;
	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}
	
	public boolean isRanged() {
		return this.minRange > 1;
	}
}
