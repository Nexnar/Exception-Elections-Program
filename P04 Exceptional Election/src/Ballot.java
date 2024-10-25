//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Ballot.java
// Course: CS 300 Fall 2024
//
// Author: Tristin Yun
// Email: tyun7@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NOBODY
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class can be instantiated to represent a ballot. This class stores a list of election
 * objects. Every ballot instance is allowed one vote per election.
 */
public class Ballot {
  // initialize fields
  private static boolean ballotsCreated = false; // tracks if ballots have been created. Once the
                                                 // first is
  // created
  // no more elections can be added to the elections
  // ArrayList
  private static ArrayList<Election> elections = new ArrayList<>(); // contains the active elections
                                                                    // eligible for
  // ballots
  private boolean[] hasVoted; // keeps track of which elections the ballot has voted for

  /**
   * Constructor method that initializes a new ballot which has the number of elections present in
   * the elections ArrayList
   * 
   * @throws IllegalStateException if user tries to create a ballot when there are not elections to
   *                               vote
   */
  public Ballot() {
    if (!(elections.size() > 0)) {
      throw new IllegalStateException("There are no elections to create a ballot for");
    }
    ballotsCreated = true;
    this.hasVoted = new boolean[elections.size()];
  }

  /**
   * Method that adds an election to the end of the elections ArrayList if this election is not
   * already added
   * 
   * @param election the election to be added to the ArrayList
   * @throws IllegalStateException    if a ballot has already been created (cannot add more
   *                                  elections)
   * @throws IllegalArgumentException if the election is already in the ArrayList
   */
  public static void addElection(Election election) {
    if (ballotsCreated == true) {
      throw new IllegalStateException("a ballot has already been created");
    }
    for (Election e : elections) {
      if (e.equals(election)) {
        throw new IllegalArgumentException("the election is already in this list");
      }
    }
    elections.add(election);
  }

  /**
   * Method that adds a vote to the given candidate running in the election of the given position
   * seatName, as long as this ballot has not already voted in that election. Also changes hasVoted
   * to true for that election.
   * 
   * @param seatName  the name of the seat of the election to vote in
   * @param Candidate the candidate to add a vote for
   * @throws IllegalStateException  if this ballot has already voted in the given election
   * @throws NoSuchElementException if the seatName isn't a valid election on the ballot, or if the
   *                                given candidate is not running in that election
   */
  public void vote(String seatName, Candidate candidate) {

    for (int i = 0; i < elections.size(); i++) {
      if (elections.get(i).SEAT_NAME.equals(seatName)) {
        if (this.hasVoted[i] == false) {
          elections.get(i).vote(candidate);
          this.hasVoted[i] = true;
          return;
        } else {
          throw new IllegalStateException("this ballot has already voted in the election");
        }
      }
    }
    throw new NoSuchElementException("invalid seatname");
  }

  /**
   * Method that determines if this ballot has already voted in an an election with the given
   * seatName
   * 
   * @param seatName the name of the seat of the election to vote in
   * @return true if this ballot has already sent a vote for the given election
   * @throws NoSuchElementException if the given seat name does not match with an election on this
   *                                ballot
   */
  public boolean hasVoted(String seatName) {
    for (int i = 0; i < elections.size(); i++) {
      if (elections.get(i).SEAT_NAME.equals(seatName)) {
        return hasVoted[i];
      }
    }
    throw new NoSuchElementException("the given seat name does not match an election");
  }

  /**
   * Creates and returns a String representation of this ballot's voter state as follows: lists the
   * seatName of the election from the ArrayList and whether this ballot has cast a vote in that
   * election.
   * 
   * @return the String representation of the current ballot, NOT ending with a new line
   */
  @Override
  public String toString() {
    String thisBallot = elections.get(0).SEAT_NAME + ": " + hasVoted[0];
    for (int i = 1; i < elections.size(); i++) {
      thisBallot += "\n" + elections.get(i).SEAT_NAME + ": " + hasVoted[i];
    }
    return thisBallot;
  }

  /**
   * Empties the elections ArrayList and resets ballotsCreated, for testing purposes only
   */
  public static void clearElections() {
    elections.clear();
    ballotsCreated = false;
  }


}
