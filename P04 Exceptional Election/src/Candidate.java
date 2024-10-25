//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Candidate.java
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

/**
 * This class can be instantiated to represent a candidate in an election
 */
public class Candidate {
  // initialize fields
  private String name;
  private int numVotes = 0;
  private String party;

  /**
   * Constructor that creates a new Candidate object with provided name and party
   * 
   * @param name  the of the candidate (cannot be null or blank)
   * @param party the candidate's party (cannot be null or blank)
   * @throws IllegalArgumentException if the name or party is null or blank
   */

  public Candidate(String name, String party) {
    if (name == null || name == "" || party == null || party == "") {
      throw new IllegalArgumentException("parameter(s) null or blank");
    }
    this.name = name;
    this.party = party;
  }

  /**
   * Accessor method for the candidate's number of votes
   * 
   * @return the number of votes for this candidate object
   */
  public int getNumVotes() {
    return this.numVotes;
  }

  /**
   * Mutator method to add 1 vote to candidate's numVotes
   */
  public void addVote() {
    this.numVotes++;
  }

  /**
   * Creates and returns a string for the candidate in the form of "name (party): numVotes"
   * 
   * @return a String that displays the candidate object's attributes
   */
  @Override
  public String toString() {
    return "" + this.name + " (" + this.party + "): " + this.numVotes;
  }

  /**
   * Method that decides whether this candidate object and a given object anObject are copies of
   * each other. If anObject is not a candidate object, they are not equal. If they are the same
   * object, they are equal iff they have the same name, party, and number of votes
   * 
   * @param anObject the object to be compared to this candidate
   * @return true if the passed object is a candidate object and equal to this candidate
   */
  @Override
  public boolean equals(Object anObject) {
    // Checks if the object is a Candidate object
    // If it is, downcast to Candidate to compare their attributes
    if (anObject instanceof Candidate) {
      Candidate otherCandidate = (Candidate) anObject;
      return this.name.equals(otherCandidate.name) && this.party.equals(otherCandidate.party)
          && this.numVotes == otherCandidate.numVotes;
    }
    return false;
  }

}
