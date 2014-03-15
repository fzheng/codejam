interface Work {
    void logHours(int hours);
}

class Job {
    protected float rate = 10.1f;
    private boolean isManager;
    boolean isManager() {
        return isManager;
    }
    void doOne() {
        System.out.println("One");
    }
    int getStoryPoint() {
        return 1;
    }
    static int getWorkHours() {
        return 11;
    }
}

class Programming extends Job implements Work{
    // must public, as interface is implicitly public abstract
    public void logHours(int hours) {
        System.out.println("Programming logs " + hours + " hours");
    }
    void doTwo() {
        System.out.println("Two");
    }
    int getStoryPoint() {
        return 2;
    }
    static int getWorkHours() {
        return 12;
    }
}

public class ClassInheritance {
    public static void main(String[] args) {
        Job a = new Job();
        Programming b = new Programming();
        Job c = new Programming();
        Work d = new Programming();
        
        // inheritance and casting
        ((Programming) c).doTwo();
        ((Work) c).logHours(5);
        ((Programming) d).doTwo();
        d.logHours(3);
        
        // polymorphism
        System.out.println(a.getStoryPoint());
        System.out.println(b.getStoryPoint());
        System.out.println(c.getStoryPoint());
        
        // redefine (static method)
        System.out.println(a.getWorkHours());
        System.out.println(b.getWorkHours());
        System.out.println(c.getWorkHours());
        
        System.out.println(a.isManager()); // instance variable default value
        boolean isLocalManager = true;  // local variable must be initialized
        System.out.println(isLocalManager);
    }
}