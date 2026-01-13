import java.util.*;
import java.time.LocalDate;
import java.io.*;

// DONE
class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;
    
    public StudyLog(LocalDate A, String B, double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
    }

    @Override
    public String toString()
    {
        return Date+" | "+Subject+" | "+ Duration+" | "+Description;
    }

    // geter methods  4 pan
    public LocalDate getDate()
    {
        return Date;
    }

    public String getSubject()
    {
        return Subject;
    }

    public double getDuration()
    {
        return Duration;
    }

    public String getDescription()
    {
        return Description;
    }
}

class StudyTracker
{
    // Data structure to hold the data about study
    private ArrayList <StudyLog> Database = new ArrayList <StudyLog> ();

    public void InsertLog()
    {
        Scanner ScannerObj = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------Please enter the valid details of your study------------");
        System.out.println("--------------------------------------------------------------------");

        LocalDate DateObj = LocalDate.now();

        System.out.println("Please provide the name of subject like C/C++/Java/OS/DS");
        String sub = ScannerObj.nextLine();

        System.out.println("Enter the time period of your study in hours");
        double dur = ScannerObj.nextDouble();
        ScannerObj.nextLine();  // flush 

        System.out.println("Please provide the description about the study for future refernce");
        String desc = ScannerObj.nextLine();

        StudyLog StudyObj = new StudyLog(DateObj,sub,dur,desc);

        Database.add(StudyObj);

        System.out.println("Study Log gets stored succesfully");
        System.out.println("--------------------------------------------------------------------");
    }

    public void DisplayLog()
    {
        System.out.println("--------------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("---------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println("---------------Log Report from Marvellous Study Tracker-------------");
        System.out.println("--------------------------------------------------------------------");

        for(StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public void ExportCSV()
    {
        if(Database.isEmpty())
        {
            System.out.println("Nothing to Export as database is empty");
            System.out.println("---------------------------------------");
            return;
        }

        String FileName = "MarvellousStudy.csv";

        // Create
        try(FileWriter fwobj = new FileWriter(FileName))
        {
            // Write CSV header
            fwobj.write("Date,Subject,Duration,Description\n");

            // Travel database
            for(StudyLog sobj : Database)
            {
                // Write each record in CSV
                fwobj.write(sobj.getDate() + ","+
                            sobj.getSubject().replace(","," ") + ","+
                            sobj.getDuration() + ","+
                            sobj.getDescription().replace(","," ") + "\n"
                );
            }

            System.out.println("Log created Succesfully");

        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured while creating the CSV.");
            System.out.println("Report this to Marvellous Infosystem");
        }
    }

    public void SummaryByDate()
    {
        System.out.println("--------------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("---------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println("-------------Summary by date fromMarvellous Study Tracker-----------");
        System.out.println("--------------------------------------------------------------------");

        TreeMap <LocalDate,Double> tobj = new TreeMap <LocalDate,Double> ();
        
        LocalDate lobj = null;
        double d, old;

        for(StudyLog sobj : Database)
        {
            lobj = sobj.getDate();
            d = sobj.getDuration();

            if(tobj.containsKey(lobj))
            {   
                old = tobj.get(lobj);
                tobj.put(lobj,d+old);
            }
            else
            {
                tobj.put(lobj,d);    
            }
        }
        // Display the details as per date
        for(LocalDate ldbj : tobj.keySet())
        {
            System.out.println("Date : "+ldbj+" Toatal Study "+tobj.get(ldbj));
        }

        System.out.println("--------------------------------------------------------------------");
    }

    public void SummaryBySubject()
    {
        System.out.println("--------------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("---------------------------------------");
            return;
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println("----------Summary by subject fromMarvellous Study Tracker-----------");
        System.out.println("--------------------------------------------------------------------");

        TreeMap <String,Double> tobj = new TreeMap <String,Double> ();
        
        String s;
        double d, old;

        for(StudyLog sobj : Database)
        {
            s = sobj.getSubject();
            d = sobj.getDuration();

            if(tobj.containsKey(s))
            {   
                old = tobj.get(s);
                tobj.put(s,d+old);
            }
            else
            {
                tobj.put(s,d);    
            }
        }
        // Display the details as per subject
        for(String str : tobj.keySet())
        {
            System.out.println("Subject : "+str+" Toatal Study "+tobj.get(str));
        }

        System.out.println("--------------------------------------------------------------------");
    }

}

class program558           // StudyTrackerStarter
{
    public static void main(String A[])
    {
        StudyTracker stobj = new StudyTracker();

        Scanner ScannerObj = new Scanner(System.in);    
        
        int iChoise = 0;

        System.out.println("--------------------------------------------------------------------");
        System.out.println("---------- Welcome to marvellous Study tracker Application ---------");
        System.out.println("--------------------------------------------------------------------");

        do
        {
            System.out.println("Please select the appropriate option");
            System.out.println("1 : Insert new Study Log into Database");
            System.out.println("2 : View All Study Log");
            System.out.println("3 : Summary of Study Log by Date");
            System.out.println("4 : Summary of Study Log by Student");
            System.out.println("5 : Export Study Log to CSV file");
            System.out.println("6 : Exit the Application");

            iChoise = ScannerObj.nextInt();

            switch(iChoise)
            {
                case 1:         //Insert new Study Log into Database
                    stobj.InsertLog();
                    break;

                case 2:         //View All Study Log
                    stobj.DisplayLog();
                    break;

                case 3:         // Summary of Study Log by Date
                    stobj.SummaryByDate();
                    break;
                
                case 4:         // Summary of Study Log by Student
                    stobj.SummaryBySubject();
                    break;
                
                case 5:          // Export Study Log to CSV file
                    stobj.ExportCSV();
                    break;
                
                case 6:          //Exit the Application
                    System.out.println("Thank you for using Marvellous Study Log Application");
                    break;
                
                default:  
                    System.out.println("Please enter the valid option");                      
            }
        }while(iChoise != 6);
        
    }        
    
}