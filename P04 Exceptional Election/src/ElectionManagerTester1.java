//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ElectionManagerTester1.java
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
import java.util.NoSuchElementException;

/**
 * A tester class for the Election Manager project. It contains various tests to check the
 * correctness of the Candidate, Election, and Ballot classes.
 *
 */
public class ElectionManagerTester1 {

  /**
   * Tests the Candidate constructor, toString(), and getter method for correctness. This test
   * accounts for the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Candidate c = new Candidate("lebron james", "basketball");

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      // 1) the constructor didn't initialize a data field correctly OR
      // 2) toString() doesn't return the correct value
      if (!c.toString().equals("lebron james (basketball): 0"))
        return false;

      // let's also verify the one getter method agrees with the toString() output:
      if (c.getNumVotes() != 0)
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Candidate constructor throws the correct type of exception(s) where an
   * exception is expected. See the Candidate documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorExceptions() {
    try {
      // test the 2-argument constructor with blank and null values
      Candidate c = new Candidate("", null);

    } catch (IllegalArgumentException e) {
      // we encountered an exception we should have, this is good
      e.printStackTrace();
      return true;
    }

    // either nor exception or incorrect exception thrown
    return false;
  }

  /**
   * Tests the Election constructor and associated getter methods for correctness. (Note that
   * SEAT_NAME is a publicly-accessible constant and can be verified directly.) This test accounts
   * for the fact a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */

  public static boolean testElectionConstructorAndGetters() {

    try {
      // test the 2-argument constructor
      Election e = new Election("King of Rats", 4);
      Candidate IShowSpeed = new Candidate("IShowSpeed", "Youtube");
      // add some candidates
      e.addCandidate(new Candidate("XQC", "Twitch"));
      e.addCandidate(new Candidate("Adin Ross", "Kick"));
      e.addCandidate(IShowSpeed);
      // add a vote in
      e.vote(IShowSpeed);

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      // 1) the constructor didn't initialize a data field correctly OR
      // 2) toString() doesn't return the correct value

      if (!e.toString().equals("King of Rats" + "\n" + "XQC (Twitch): 0" + "\n"
          + "Adin Ross (Kick): 0" + "\n" + "IShowSpeed (Youtube): 1"))
        return false;

      // verify the getNumCandidates getter method
      if (e.getNumCandidates() != 3)
        return false;

      // verify the capacity getter method
      if (e.capacity() != 4)
        return false;

      // verify the findWinner getter method
      if (!(e.findWinner().equals(IShowSpeed)))
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Election constructor throws the correct type of exception(s) in situations
   * where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testElectionConstructorExceptions() {
    try {
      // test the 2-argument constructor with a negative maximum candidate value
      Election e = new Election("King of Rats", -1);

    } catch (IllegalArgumentException e) {
      // we encountered an exception we should have, this is good
      e.printStackTrace();
      return true;
    }

    // either no exception or incorrect exception thrown
    return false;
  }

  /**
   * Tests the Election's addCandidate() method for correctness in non-Exception situations. This
   * test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidate() {
    try {
      Election e = new Election("King of Rats", 5);
      Candidate IShowSpeed = new Candidate("IShowSpeed", "Youtube");
      // add some candidates
      e.addCandidate(new Candidate("XQC", "Twitch"));
      e.addCandidate(new Candidate("Adin Ross", "Kick"));
      e.addCandidate(new Candidate("Caseoh", "Twitch"));
      e.addCandidate(IShowSpeed);

      if (!e.toString().equals("King of Rats" + "\n" + "XQC (Twitch): 0" + "\n"
          + "Adin Ross (Kick): 0" + "\n" + "Caseoh (Twitch): 0" + "\n" + "IShowSpeed (Youtube): 0"))
        return false;

      // verify the getNumCandidates getter method
      if (e.getNumCandidates() != 4)
        return false;

      // verify the capacity getter method
      if (e.capacity() != 5)
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Election's addCandidate() method throws the correct type of exception(s) in
   * situations where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidateExceptions() {
    try {
      Election e = new Election("King of Rats", 5);
      Candidate IShowSpeed = new Candidate("IShowSpeed", "Youtube");
      // add some candidates
      e.addCandidate(new Candidate("XQC", "Twitch"));
      e.addCandidate(new Candidate("Adin Ross", "Kick"));
      e.addCandidate(new Candidate("IShowSpeed", "Youtube")); // tests for exception
      e.addCandidate(IShowSpeed);

    } catch (IllegalArgumentException e) {
      // we encountered an exception which is good
      e.printStackTrace();
      return true;
    }

    // either no exception or incorrect exception thrown
    return false;
  }

  /**
   * Tests the Election's vote() method for correctness in non-Exception situations. This test
   * accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVote() {
    try {
      Election e = new Election("King of Rats", 4);
      Candidate IShowSpeed = new Candidate("IShowSpeed", "Youtube");
      Candidate AdinRoss = new Candidate("Adin Ross", "Kick");
      // add some candidates
      e.addCandidate(new Candidate("XQC", "Twitch"));
      e.addCandidate(new Candidate("Adin Ross", "Kick"));
      e.addCandidate(IShowSpeed);
      // add votes in
      e.vote(IShowSpeed);
      e.vote(AdinRoss);
      AdinRoss.addVote();
      e.vote(AdinRoss);
      AdinRoss.addVote();

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      // 1) the constructor didn't initialize a data field correctly OR
      // 2) toString() doesn't return the correct value

      if (!e.toString().equals("King of Rats" + "\n" + "XQC (Twitch): 0" + "\n"
          + "Adin Ross (Kick): 2" + "\n" + "IShowSpeed (Youtube): 1"))
        return false;

      // verify the getNumCandidates getter method
      if (e.getNumCandidates() != 3)
        return false;

      // verify the capacity getter method
      if (e.capacity() != 4)
        return false;

      // verify the findWinner getter method
      if (!(e.findWinner().equals(AdinRoss)))
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Election's vote() method throws the correct type of exception(s) in
   * situations where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVoteExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.

    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election.addCandidate(c1);
      election.addCandidate(c2);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // THIS part is what we are actually testing:

    try {
      election.vote(new Candidate("usain bolt", "athletics"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (NoSuchElementException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Tests the Election's removeCandidate() method for correctness. This test accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidate() {
    try {
      Election e = new Election("King of Rats", 4);
      Candidate IShowSpeed = new Candidate("IShowSpeed", "Youtube");
      Candidate AdinRoss = new Candidate("Adin Ross", "Kick");
      Candidate XQC = new Candidate("XQC", "Twitch");
      // add some candidates
      e.addCandidate(XQC);
      e.addCandidate(AdinRoss);
      e.addCandidate(IShowSpeed);
      // add votes in
      e.removeCandidate(XQC);
      e.removeCandidate(IShowSpeed);

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      // 1) the constructor didn't initialize a data field correctly OR
      // 2) toString() doesn't return the correct value

      if (!e.toString().equals("King of Rats" + "\n" + "Adin Ross (Kick): 0"))
        return false;

      // verify the getNumCandidates getter method
      if (e.getNumCandidates() != 1)
        return false;

      // verify the capacity getter method
      if (e.capacity() != 4)
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Election's removeCandidate() method throws the correct type of exception(s)
   * in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidateExceptions() {
    try {
      Election e = new Election("King of Rats", 5);
      Candidate IShowSpeed = new Candidate("IShowSpeed", "Youtube");
      e.removeCandidate(IShowSpeed);

    } catch (IllegalArgumentException e) {
      // we encountered an exception which is good
      e.printStackTrace();
    } catch (Exception f) {
      f.printStackTrace();
      return false;
    }

    try {
      Election k = new Election("King of Rats", 5);
      Candidate AdinRoss = new Candidate("Adin Ross", "Kick");
      Candidate XQC = new Candidate("XQC", "Twitch");
      // add some candidates
      k.addCandidate(AdinRoss);
      // add votes in
      k.removeCandidate(XQC);

    } catch (NoSuchElementException e) {
      // we encountered an exception which is good
      e.printStackTrace();
    } catch (Exception d) {

      d.printStackTrace();
      return false;
    }
    // all passes checked
    return true;
  }

  /**
   * Tests the Ballot two-phase setup process in non-Exception situations. This test accounts for
   * the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetup() {
    // Phase 1: add elections to the Ballot class
    Ballot.addElection(new Election("King of Rats", 4));
    Ballot.addElection(new Election("King of Cats", 3));
    Ballot.addElection(new Election("King of Bats", 5));
    // Phase 2: create a Ballot and verify that it has the correct number of elections
    // (hint: use toString())
    Ballot primaries = new Ballot();

    if (!primaries.toString().equals(
        "King of Rats: false" + "\n" + "King of Cats: false" + "\n" + "King of Bats: false")) {
      return false;
    }

    return true; // TODO
  }

  /**
   * Verifies that the Ballot two-phase setup process throws the correct type of exception(s) in
   * situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetupExceptions() {
    try {
      Ballot primaries = new Ballot();
      Ballot.addElection(new Election("King of Rats", 4));
      Ballot.addElection(new Election("King of Cats", 3));
      Ballot.addElection(new Election("King of Bats", 5));
    } catch (IllegalStateException e) {
      // we encountered an exception which is good
      e.printStackTrace();
    } catch (Exception e) {
      // we encountered an exception which is good
      e.printStackTrace();
      return false;
    }
    Ballot.clearElections();
    try {
      Ballot.addElection(new Election("King of Rats", 4));
      Ballot.addElection(new Election("King of Cats", 3));
      Ballot.addElection(new Election("King of Rats", 4));
      Ballot primaries = new Ballot();

    } catch (IllegalArgumentException e) {
      // we encountered an exception which is good
      e.printStackTrace();
    } catch (Exception e) {
      // we encountered an exception which is good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the Ballot vote() and hasVoted() methods in non-Exception situations. This test accounts
   * for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVote() {
    Ballot.clearElections();
    Election e = new Election("King of Rats", 4);
    e.addCandidate(new Candidate("XQC", "Twitch"));
    e.addCandidate(new Candidate("Adin Ross", "Kick"));

    Election e2 = new Election("Sportsball", 10);
    Candidate c1 = new Candidate("lebron james", "basketball");
    Candidate c2 = new Candidate("messi", "soccer");
    e2.addCandidate(c1);
    e2.addCandidate(c2);

    Ballot.addElection(e);
    Ballot.addElection(e2);

    Ballot primaries = new Ballot();
    primaries.vote("Sportsball", c2);

    if (!primaries.toString().equals("King of Rats: false" + "\n" + "Sportsball: true")) {
      return false;
    }

    return true; // TODO
  }

  /**
   * Verifies that the Ballot vote() and hasVoted() methods throw the correct type of exception(s)
   * in situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVoteExceptions() {
    try {

      Ballot.clearElections();
      Election e = new Election("King of Rats", 4);
      e.addCandidate(new Candidate("XQC", "Twitch"));
      e.addCandidate(new Candidate("Adin Ross", "Kick"));

      Election e2 = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      e2.addCandidate(c1);
      e2.addCandidate(c2);

      Ballot.addElection(e);
      Ballot.addElection(e2);

      Ballot primaries = new Ballot();
      primaries.vote("Sportsball", c2);
      primaries.vote("Sportsball", c1);

    } catch (IllegalStateException e) {
      // we encountered an exception which is good
      e.printStackTrace();
    } catch (Exception e) {
      // we encountered an exception which is good
      e.printStackTrace();
      return false;
    }

    try {

      Ballot.clearElections();
      Election e = new Election("King of Rats", 4);
      e.addCandidate(new Candidate("XQC", "Twitch"));
      e.addCandidate(new Candidate("Adin Ross", "Kick"));

      Election e2 = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      e2.addCandidate(c1);
      e2.addCandidate(c2);

      Ballot.addElection(e);
      Ballot.addElection(e2);

      Ballot primaries = new Ballot();
      primaries.vote("Sportsballeo", c2);

    } catch (NoSuchElementException e) {
      // we encountered an exception which is good
      e.printStackTrace();
    } catch (Exception e) {
      // we encountered an exception which is good
      e.printStackTrace();
      return false;
    }

    try {

      Ballot.clearElections();
      Election e = new Election("King of Rats", 4);
      e.addCandidate(new Candidate("XQC", "Twitch"));
      e.addCandidate(new Candidate("Adin Ross", "Kick"));

      Election e2 = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      e2.addCandidate(c1);
      e2.addCandidate(c2);

      Ballot.addElection(e);
      Ballot.addElection(e2);

      Ballot primaries = new Ballot();
      primaries.hasVoted("poop");

    } catch (NoSuchElementException e) {
      // we encountered an exception which is good
      e.printStackTrace();
    } catch (Exception e) {
      // we encountered an exception which is good
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Runs all testing methods and prints out their results.
   * 
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllRequiredTests() {

    boolean test1 = testCandidateConstructorAndGetters();
    System.out.println("testCandidateConstructorAndGetters(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testCandidateConstructorExceptions();
    System.out.println("testCandidateConstructorExceptions(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testElectionConstructorAndGetters();
    System.out.println("testElectionConstructorAndGetters(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testElectionConstructorExceptions();
    System.out.println("testElectionConstructorExceptions(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testAddCandidate();
    System.out.println("testAddCandidate(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testAddCandidateExceptions();
    System.out.println("testAddCandidateExceptions(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testVote();
    System.out.println("testVote(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testVoteExceptions();
    System.out.println("testVoteExceptions(): " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveCandidate();
    System.out.println("testRemoveCandidate(): " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testRemoveCandidateExceptions();
    System.out.println("testRemoveCandidateExceptions(): " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testBallotSetup();
    System.out.println("testBallotSetup(): " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testBallotSetupExceptions();
    System.out.println("testBallotSetupExceptions(): " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testBallotVote();
    System.out.println("testBallotVote(): " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testBallotVoteExceptions();
    System.out.println("testBallotVoteExceptions(): " + (test14 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14;
  }

  /**
   * Calls runAllRequiredTests and displays the output. If you add additional private testers, call
   * them directly in the main method rather than adding them to the previous method.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("runAllRequiredTests(): " + runAllRequiredTests());
  }


}
