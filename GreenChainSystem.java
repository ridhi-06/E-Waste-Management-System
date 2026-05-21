import java.util.*; 

// ========================================== 
// 1. INTERFACE (Polymorphism & Design) 
// ==========================================
interface Recyclable  
{ 
    void processForRecycling();        // Standard behavior for all waste  
} 

// ========================================== 
// 2. ABSTRACT CLASS (Inheritance Base)  
// ==========================================
abstract class ElectronicWaste implements Recyclable  
{ 
    protected String deviceID; 
    protected String roomLocation; 
    protected double weight; 

    public ElectronicWaste(String id, String room, double weight) 
    { 
        this.deviceID = id; 
        this.roomLocation = room; 
        this.weight = weight; 
    } 

    // Abstract method: Each subclass defines its own impact logic 
    public abstract double calculateImpactScore(); 

    public void displayBaseDetails()  
    {
        System.out.print("ID: " + deviceID + " | Room: " + roomLocation + " | Weight: " + weight + "kg ");
    }
} 

// ========================================== 
// 3. SUBCLASSES (Inheritance & Overriding)  
// ========================================== 

// Subclass for Laptops/PCs 
class ComputingDevice extends ElectronicWaste  
{ 
    private boolean isDataWiped; 

    public ComputingDevice(String id, String room, double weight, boolean wiped)  
    {
        super(id, room, weight); 
        this.isDataWiped = wiped; 
    } 

    @Override  // Redefining method from parent class or interface 
    public double calculateImpactScore() { 
        // Higher impact if rare metals like Gold/Copper can be recovered 
        return weight * 1.8; 
    } 

    @Override 
    public void processForRecycling() { 
        if (!isDataWiped) { 
            System.out.println(">>> ALERT: Data wipe required for " + deviceID); 
        } 
        System.out.println(">>> Action: Shredding circuit boards for metal recovery."); 
    } 
} 

// Subclass for Batteries/UPS 
class PowerUnit extends ElectronicWaste  
{ 
    private String chemicalType; 

    public PowerUnit(String id, String room, double weight, String chemical)  
    {
        super(id, room, weight); 
        this.chemicalType = chemical; 
    } 

    @Override 
    public double calculateImpactScore()  
    { 
        // High impact score due to chemical toxicity  
        return weight * 3.5; 
    } 

    @Override 
    public void processForRecycling()  
    { 
        System.out.println(">>> Action: Neutralizing " + chemicalType + " safely for " + deviceID); 
    } 
} 

// ========================================== 
// 4. MAIN SYSTEM (Controller Logic)  
// ========================================== 
public class GreenChainSystem  
{ 
    private static List<ElectronicWaste> inventory = new ArrayList<>(); 

    public static void main(String[] args)  
    {
        System.out.println("--- GreenChain: E-Waste Lifecycle Management ---"); 
        System.out.println("Target Institution: NMIMS 's MPSTME"); 
        System.out.println("------------------------------------------------"); 

        // Polymorphism in Action: Adding different objects to the same list 
        inventory.add(new ComputingDevice("CMP-101", "Lab 402", 2.5, false)); 
        inventory.add(new PowerUnit("PWR-909", "Server Room", 15.0, "Lithium-Ion")); 
        inventory.add(new ComputingDevice("CMP-105", "Faculty Office", 1.8, true)); 

        processInventory(); 
    } 

    public static void processInventory()  
    {
        double totalImpact = 0; 
        for (ElectronicWaste item : inventory)  
        {
            // Display common details via inheritance 
            item.displayBaseDetails();           
            // Execute polymorphic impact calculation 
            double score = item.calculateImpactScore(); 
            totalImpact += score;          
            System.out.println("| Impact Score: " + score); 
            // Execute polymorphic recycling protocol 
            item.processForRecycling(); 
            System.out.println("------------------------------------------------"); 
        } 
        System.out.println("Total Institutional Sustainability Score: " + totalImpact); 
        System.out.println("Status: Ready for Recycler Pickup [cite: 30]"); 
    } 
}