/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Final Project Milestone 1
 *
 * Description of assignment:
 * 		The first milestone for the final project is to set up the whole architecture,
 * 		including the list of classes, with all the fields and methods.
 *
 * Team: Spirit Coders 
 * 		Wing Hung Lau
 * 		Michael Tang
 * 		Donovan Gonzalez
 * 		Lynn Nguyen
 * 		Xinyuan Wang
 * 		Connor Chase
 */
package edu.cpp.cs.cs141.final_project;

/**
 * This is an interface called DynamicAgents, it is implemented by the {@link Spy} and {@link Ninja}
 * classes. The {@link DynamicAgents} has two methods that both the {@link Spy} and {@link Ninja}
 * share, and they are {@link #beAttacked()} and {@link #isDead()}.
 *
 * @author Wing Hung Lau
 *
 */
public interface DynamicAgents {
	
	/**
	 * This method is used to return the status of the {@link Spy} and the {@link Ninja}.
	 * @return  If the {@link Spy} and the {@link Ninja} is alive,
	 * 			return {@link #isDead} is {@code false},
	 * 		    if the {@link Spy} and the {@link Ninja} is dead,
	 * 			{@link #isDead} becomes {@code true}.
	 */
	public boolean isDead();
	
	/**
	 * This method is called when either the {@link Spy} or the {@link Ninja} is attacked
	 * by the other. This method will change the status of {@link #isDead}.
	 */
	public void beAttacked();
}
