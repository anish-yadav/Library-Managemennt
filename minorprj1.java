

	import java.io.*;
class Vcd
{
   public void Clear()
   {
      for (int i=1;i<=50;i++)
         System.out.println();
   }
   public void NewBook()throws IOException
   {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      File fil=new File("MyLibra.dat");
      DataOutputStream dos=new DataOutputStream(new FileOutputStream(fil,true));
      Clear();
      System.out.println("          Please Fill the following INFORMATION for your New BOOK...");
      System.out.println("\n\n*********************************************************************");
      System.out.print("Enter Book Name:-> ");
      String bname=br.readLine( );
      System.out.print("Enter Purchase Date (DD/MM/YYYY):-> ");
      String tdate=br.readLine();
      System.out.print("Enter Number of Copies :-> ");
      int amt=Integer.parseInt(br.readLine( ));
      System.out.print("Enter Author of the Book:-> ");
      String bauth=br.readLine( );
      System.out.print("Enter COST of the Book:-> ");
      double bcost=Double.parseDouble(br.readLine());
      System.out.println();
      dos.writeUTF(bname);
      dos.writeUTF(tdate);
      dos.writeInt(amt);
      dos.writeUTF(bauth);
      dos.writeDouble(bcost);
      System.out.println("\n\n******************************************************");
      System.out.println("\nBOOK added to Database....");
      System.out.print("Press ENTER to Back to WORKING MENU....");
      br.read();
      dos.close();
   }
   public void ChangeBook() throws IOException
   {
      Clear();
      BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br2=new BufferedReader(new InputStreamReader(System.in));
      DataOutputStream ds1 = new DataOutputStream(new FileOutputStream("temp.dat"));
      DataInputStream di = new DataInputStream(new FileInputStream("MyLibra.dat"));
      String booknm,book,bauth,tdate,newbn,nauth,pdate;
      int nos,found=0; double ncost,bcost;
      System.out.print("Enter the Book Name :-> ");
      booknm=br1.readLine();
      try
      {
         while(true)
         {
            book=di.readUTF();
            pdate=di.readUTF();
            nos=di.readInt();
            bauth=di.readUTF();
            bcost=di.readDouble();
            if( (booknm.equalsIgnoreCase(book)) && (found==0) )
            {
               System.out.println("Present Data on BOOK Database");
               System.out.println("*****************************");
               System.out.println("Book Name :-> "+book);
               System.out.println("Purchase Date :-> "+pdate);
               System.out.println("No. of Copies Avail :-> "+nos);
               System.out.println("Author Name:-> "+bauth);
               System.out.println("Book Costs :-> "+bcost);
               System.out.println("*****************************");
               System.out.print("\n\n ??? Is the Data to Change [Y/N] :-> ");
               String op=br1.readLine();
               if (op.equalsIgnoreCase("Y"))
               {
                  found=1;
                  System.out.println("\n\n\nEnter Correct Data to Change");
                  System.out.println("****************************");
                  System.out.print("\nEnter Correct Book Name :-> ");
                  String nm=br1.readLine();
                  System.out.print("Enter Correct Purchase Date :-> ");
                  String td=br1.readLine();
                  System.out.print("Enter Correct No. of Copies :-> ");
                  int no=Integer.parseInt(br1.readLine());
                  System.out.print("Enter Correct Author Name :-> ");
                  String anm=br1.readLine();
                  System.out.print("Enter Correct Book Cost :-> ");
                  ncost=Double.parseDouble(br1.readLine());
                  System.out.println("****************************");
                  ds1.writeUTF(nm); 
                  ds1.writeUTF(td); 
                  ds1.writeInt(no); 
                  ds1.writeUTF(anm);
                  ds1.writeDouble(ncost);
                  System.out.println("\n\n Database Updated.....");
               }
               else
               {
                  ds1.writeUTF(book);
                  ds1.writeUTF(pdate);
                  ds1.writeInt(nos);
                  ds1.writeUTF(bauth);
                  ds1.writeDouble(bcost);
               }   
            }
            else
            {
               ds1.writeUTF(book);
               ds1.writeUTF(pdate);
               ds1.writeInt(nos);
               ds1.writeUTF(bauth);
               ds1.writeDouble(bcost);
            }
        }
     }
     catch(EOFException e){}
     if(found==0)
        System.out.println("\nSorry!! The Name is not in Database...");
     ds1.close();
     di.close();
     File f1=new File("MyLibra.dat");
     f1.delete();
     File f2 = new File("temp.dat");
     File f3 = new File("MyLibra.dat");
     f2.renameTo(f3);
     System.out.print("Press ENTER to go back to WORKING MENU....");
     br1.read();
   }
   public void DeleteBook() throws IOException
   {
      Clear();
      BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br2=new BufferedReader(new InputStreamReader(System.in));
      DataOutputStream ds1 = new DataOutputStream(new FileOutputStream("temp.dat"));
      DataInputStream di = new DataInputStream(new FileInputStream("MyLibra.dat"));
      String booknm,book,bauth,pdate,auth;
      int nos,found=0; double bcost;
      System.out.print("Enter the BOOK's NAME :-> ");
      booknm=br1.readLine();
      System.out.print("Enter AUTHOR's NAME :-> ");
      auth=br1.readLine();
      try
      {
         while(true)
         {
            book=di.readUTF();
            pdate=di.readUTF();
            nos=di.readInt();
            bauth=di.readUTF();
            bcost=di.readDouble();
            if( (booknm.equalsIgnoreCase(book)) && (found==0) && (auth.equalsIgnoreCase(bauth)) )
            {
               System.out.println("Present Data on BOOK Database");
               System.out.println("*****************************");
               System.out.println("Book Name :-> "+book);
               System.out.println("Purchase Date :-> "+pdate);
               System.out.println("No. of Copies Avail :-> "+nos);
               System.out.println("Author Name:-> "+bauth);
               System.out.println("Book Costs :-> "+bcost);
               System.out.println("****************************");
               System.out.print("\n\n ??? Is the Data to DELETE [Y/N] :-> ");
               String op=br1.readLine();
               if (op.equalsIgnoreCase("Y"))
               {
                  found=1;
                  System.out.println("****************************");
                  System.out.print("\n\nEnter the Number of BOOKS Damaged or Lost :-> ");
                  int n=Integer.parseInt(br1.readLine());
                  if(n<1 || n>nos)
                  {
                    ds1.writeUTF(book);
                    ds1.writeUTF(pdate);
                    ds1.writeInt(nos);
                    ds1.writeUTF(bauth);
                    ds1.writeDouble(bcost);
                    System.out.println("\n\nSorry Wrong Entry !!!");
                  }
                  else
                  {
                    if (nos-n !=0)
                    {
                      ds1.writeUTF(book);
                      ds1.writeUTF(pdate);
                      ds1.writeInt(nos-n);
                      ds1.writeUTF(bauth);
                      ds1.writeDouble(bcost);
                    }
                    else
                    {
                      System.out.println("The Book is Completely Removed from Database...");
                      System.out.println("Though the number of Books is ZERO...");
                    }
                  }
                  System.out.println("\n\n Database Updated.....");
               }
               else
               {
                  ds1.writeUTF(book);
                  ds1.writeUTF(pdate);
                  ds1.writeInt(nos);
                  ds1.writeUTF(bauth);
                  ds1.writeDouble(bcost);
               }   
            }
            else
            {
               ds1.writeUTF(book);
               ds1.writeUTF(pdate);
               ds1.writeInt(nos);
               ds1.writeUTF(bauth);
               ds1.writeDouble(bcost);
            }
        }
     }
     catch(EOFException e){}
     if(found==0)
        System.out.println("\nSorry!! The Name is not in Database...");
     ds1.close();
     di.close();
     File f1=new File("MyLibra.dat");
     f1.delete();
     File f2 = new File("temp.dat");
     File f3 = new File("MyLibra.dat");
     f2.renameTo(f3);
     System.out.print("Press ENTER to go back to WORKING MENU....");
     br1.read();
   }
   public void NewMember() throws IOException
   {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      File fil=new File("LibrMemb.dat");
      DataOutputStream dos1=new DataOutputStream(new FileOutputStream(fil,true));
      dos1.close();
      File fil1=new File("LibrMemb.dat");
      DataInputStream dis=new DataInputStream(new FileInputStream(fil1));
      String opdate,mname,madd1,madd2,mtel,mstat,x;
      char renpay;
      double amount;
      int num=1;
      try
      {
         while(true)
         {
            mname=dis.readUTF();
            madd1=dis.readUTF();
            madd2=dis.readUTF();
            mtel=dis.readUTF();
            x=dis.readUTF();
            num=Integer.parseInt(x.substring(4,x.length()))+1;
            opdate=dis.readUTF();
            mstat=dis.readUTF();
            renpay=dis.readChar();
            amount=dis.readDouble();
         }
      }
      catch(EOFException e){}
      dis.close();
      File fil2=new File("LibrMemb.dat");
      DataOutputStream dos=new DataOutputStream(new FileOutputStream(fil2,true));
      Clear();
      char ans;
      System.out.println("\t\tษอออออออออออออออออออออออออออออออออออออออออออออออออออป");
      System.out.println("\t\tบ ษอออออออออออออออออออออออออออออออออออออออออออออออป บ");
      System.out.println("\t\tบ บ  I N F O R M A T I O N   F O R   M E M B E R  บ บ");
      System.out.println("\t\tบ ฬอออออออออออออออออออออออออออออออออออออออออออออออน บ");
      System.out.println("\t\tบ บ        TO BE A MEMBER OF THIS LIBRARY         บ บ");
      System.out.println("\t\tบ บ                                               บ บ");
      System.out.println("\t\tบ บ        EACH MEMBER HAVE TO PAY A  SUM         บ บ");
      System.out.println("\t\tบ บ                                               บ บ");
      System.out.println("\t\tบ บ        OF Rs.500/-    AS JOINING FEES         บ บ");
      System.out.println("\t\tบ บ                                               บ บ");
      System.out.println("\t\tบ บ        AND A RENEWAL AMOUNT OF Rs.200         บ บ");
      System.out.println("\t\tบ บ                                               บ บ");
      System.out.println("\t\tบ บ        EACH MONTH. IN DEFAULT OF SUCH         บ บ");
      System.out.println("\t\tบ บ                                               บ บ");
      System.out.println("\t\tบ บ        CONDITION THE MEMBER  WILL  BE         บ บ");
      System.out.println("\t\tบ บ                                               บ บ");
      System.out.println("\t\tบ บ        REMOVED & MAY REJOIN WITH SAME         บ บ");
      System.out.println("\t\tบ บ                                               บ บ");
      System.out.println("\t\tบ บ        RULE STATED ABOVE.............         บ บ");
      System.out.println("\t\tบ ศอออออออออออออออออออออออออออออออออออออออออออออออผ บ");
      System.out.println("\t\tศออออออออออออออออออออออออออออออออออออออออออออออออออผ");
      System.out.print("\t\t           Are U agree [Y/N] :-> ");
      ans=br.readLine().toUpperCase().charAt(0);
      if(ans=='Y')
      {
        Clear();
        System.out.println("          Please Fill the following INFORMATION of New MEMBER...");
        System.out.println("\n\n*********************************************************************");
        System.out.print("Enter Member's Name:-> ");
        mname=br.readLine();
        System.out.print("Enter Primary Address:-> ");
        madd1=br.readLine();                     
        System.out.print("Enter Secondary Address:-> ");
        madd2=br.readLine();
        System.out.print("Enter Telephone Number:-> ");
        mtel=br.readLine();
        System.out.print("Enter Today's Date (dd/mm/yyyy):-> ");
        opdate=br.readLine();
        mstat="Avail";
        System.out.print("\n\nHave U Collected Rs.500 from NEW MEMBER ??? [Y/N] :-> ");
        char op=(br.readLine().toUpperCase()).charAt(0);
        if (op=='Y')
        {
          amount=500;
          renpay='Y';
          System.out.println("Your Membershop Value is :-> BSHS"+num);
          x="BSHS"+Integer.toString(num);
          dos.writeUTF(mname);
          dos.writeUTF(madd1);
          dos.writeUTF(madd2);
          dos.writeUTF(mtel);
          dos.writeUTF(x);
          dos.writeUTF(opdate);
          dos.writeUTF(mstat);
          dos.writeChar(renpay);
          dos.writeDouble(amount);
          System.out.println("\n\n******************************************************");
          System.out.println("\nMEMBER added to Database....");
        }  
      }
      System.out.print("Press ENTER to Back to WORKING MENU....");
      br.read();
      dos.close();
   }
   public void ChangeMember() throws IOException
   {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      File fil=new File("LibrMemb.dat");
      File file=new File("Duplicat.dat");
      DataInputStream dis=new DataInputStream(new FileInputStream(fil));
      DataOutputStream dos=new DataOutputStream(new FileOutputStream(file));
      String opdate,mname,madd1,madd2,mtel,mstat,x;
      Clear();
      System.out.println("\t\t\t******************************");
      System.out.println("\t\t\t*    C O R R E C T I O N     *");
      System.out.println("\t\t\t******************************");
      System.out.print("\n\n\nEnter the Library Membership Number :- ");
      String mbc=br.readLine();
      char renpay;
      double amount;
      int found=0;
      try
      {
         while(true)
         {
            mname=dis.readUTF();
            madd1=dis.readUTF();
            madd2=dis.readUTF();
            mtel=dis.readUTF();
            x=dis.readUTF();
            opdate=dis.readUTF();
            mstat=dis.readUTF();
            renpay=dis.readChar();
            amount=dis.readDouble();
            if (mbc.equalsIgnoreCase(x) && found==0)
            {
              Clear();
              System.out.println("*******************************************");
              System.out.println("*  P R E S E N T   I N F O R M A T I O N  *");
              System.out.println("*******************************************\n\n");
              System.out.println("MEMBER NAME :- "+mname);
              System.out.println("MEMBER ADDRESS :- "+madd1);
              System.out.println("                  "+madd2);
              System.out.println("MEMBER TEL. NO. :- "+mtel);
              System.out.println("MEMBER MEMBERSHIP NO. :- "+x);
              System.out.println("MEMBERSHIP DATE :- "+opdate);
              System.out.println("MEMBER STATUS :- "+mstat);
              System.out.println("MEMBER RENEWAL STATUS :- "+renpay);
              System.out.print("\n\nAre these INFORMATION CORRECT ??? [Y/N] :-> ");
              char ans=br.readLine().toUpperCase().charAt(0);
              if(ans=='Y')
              {
                found=1;
                System.out.println("\n\n    Please Fill the CORRECT INFORMATION of MEMBER except MEMBERSHIP NO...");
                System.out.println("*********************************************************************");
                System.out.print("Enter Member's Name:-> ");
                mname=br.readLine();
                System.out.print("Enter Primary Address:-> ");
                madd1=br.readLine();                     
                System.out.print("Enter Secondary Address:-> ");
                madd2=br.readLine();
                System.out.print("Enter Telephone Number:-> ");
                mtel=br.readLine();
                System.out.print("Enter Joining Date (dd/mm/yyyy):-> ");
                opdate=br.readLine();
                System.out.print("Enter Member Status:-> ");
                mstat=br.readLine();
                System.out.print("Enter Member Renewal Status:-> ");
                renpay=br.readLine().toUpperCase().charAt(0);
                dos.writeUTF(mname);
                dos.writeUTF(madd1);
                dos.writeUTF(madd2);
                dos.writeUTF(mtel);
                dos.writeUTF(x);
                dos.writeUTF(opdate);
                dos.writeUTF(mstat);
                dos.writeChar(renpay);
                dos.writeDouble(amount);
                System.out.println("\n\n******************************************************");
                System.out.println("\nMEMBER updated in Database....");
              }
              else
              {
                dos.writeUTF(mname);
                dos.writeUTF(madd1);
                dos.writeUTF(madd2);
                dos.writeUTF(mtel);
                dos.writeUTF(x);
                dos.writeUTF(opdate);
                dos.writeUTF(mstat);
                dos.writeChar(renpay);
                dos.writeDouble(amount);
              }
            }
            else
            {
              dos.writeUTF(mname);
              dos.writeUTF(madd1);
              dos.writeUTF(madd2);
              dos.writeUTF(mtel);
              dos.writeUTF(x);
              dos.writeUTF(opdate);
              dos.writeUTF(mstat);
              dos.writeChar(renpay);
              dos.writeDouble(amount);
            }
         }
      }
      catch (EOFException e1){}
      if (found==0)
      {
        System.out.println("\n\n\nSorry... Not Wrong MEMBERSHIP NUMBER");
        System.out.println("Try next time...");
      }
      dos.close();
      dis.close();
      File f1=new File("LibrMemb.dat");
      f1.delete();
      File f2 = new File("Duplicat.dat");
      File f3 = new File("LibrMemb.dat");
      f2.renameTo(f3);
      System.out.print("Press ENTER to Back to WORKING MENU....");
      br.read();
   }
//*******
   public void DeleteMember() throws IOException
   {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      File fil=new File("LibrMemb.dat");
      File file=new File("Duplicat.dat");
      DataInputStream dis=new DataInputStream(new FileInputStream(fil));
      DataOutputStream dos=new DataOutputStream(new FileOutputStream(file));
      String opdate,mname,madd1,madd2,mtel,mstat,x;
      Clear();
      System.out.println("\t\t\t******************************");
      System.out.println("\t\t\t*      D E L E T I O N       *");
      System.out.println("\t\t\t******************************");
      System.out.print("\n\n\nEnter the Library Membership Number :- ");
      String mbc=br.readLine();
      char renpay;
      double amount;
      int found=0;
      try
      {
         while(true)
         {
            mname=dis.readUTF();
            madd1=dis.readUTF();
            madd2=dis.readUTF();
            mtel=dis.readUTF();
            x=dis.readUTF();
            opdate=dis.readUTF();
            mstat=dis.readUTF();
            renpay=dis.readChar();
            amount=dis.readDouble();
            if (mbc.equalsIgnoreCase(x) && found==0)
            {
              Clear();
              System.out.println("*******************************************");
              System.out.println("*  P R E S E N T   I N F O R M A T I O N  *");
              System.out.println("*******************************************\n\n");
              System.out.println("MEMBER NAME :- "+mname);
              System.out.println("MEMBER ADDRESS :- "+madd1);
              System.out.println("                  "+madd2);
              System.out.println("MEMBER TEL. NO. :- "+mtel);
              System.out.println("MEMBER MEMBERSHIP NO. :- "+x);
              System.out.println("MEMBERSHIP DATE :- "+opdate);
              System.out.println("MEMBER STATUS :- "+mstat);
              System.out.println("MEMBER RENEWAL STATUS :- "+renpay);
              System.out.print("\n\nAre these INFORMATION CORRECT to DELETE ??? [Y/N] :-> ");
              char ans=br.readLine().toUpperCase().charAt(0);
              if(ans=='Y')
              {
                found=1;
                System.out.println("\n\n******************************************************");
                System.out.println("\nMEMBER updated in Database....");
              }
              else
              {
                dos.writeUTF(mname);
                dos.writeUTF(madd1);
                dos.writeUTF(madd2);
                dos.writeUTF(mtel);
                dos.writeUTF(x);
                dos.writeUTF(opdate);
                dos.writeUTF(mstat);
                dos.writeChar(renpay);
                dos.writeDouble(amount);
              }
            }
            else
            {
              dos.writeUTF(mname);
              dos.writeUTF(madd1);
              dos.writeUTF(madd2);
              dos.writeUTF(mtel);
              dos.writeUTF(x);
              dos.writeUTF(opdate);
              dos.writeUTF(mstat);
              dos.writeChar(renpay);
              dos.writeDouble(amount);
            }
         }
      }
      catch (EOFException e1){}
      if (found==0)
      {
        System.out.println("\n\n\nSorry... Not Wrong MEMBERSHIP NUMBER");
        System.out.println("Try next time...");
      }
      dos.close();
      dis.close();
      File f1=new File("LibrMemb.dat");
      f1.delete();
      File f2 = new File("Duplicat.dat");
      File f3 = new File("LibrMemb.dat");
      f2.renameTo(f3);
      System.out.print("Press ENTER to Back to WORKING MENU....");
      br.read();
   }

//*******
   public void VcdDisp( )throws IOException
   {
      Clear();
      BufferedReader br3=new BufferedReader(new InputStreamReader(System.in));
      DataInputStream di2 = new DataInputStream(new FileInputStream("Videocd.dat"));
      String vcd,name,tdate;
      int noc,found=0;
      System.out.print("Please enter the VCD Name : ");
      name=br3.readLine();
      try
      {
         while(true)
         {
            vcd=di2.readUTF();
            tdate=di2.readUTF();
            noc=di2.readInt();
            if(vcd.equalsIgnoreCase(name))
            {
               found++; Clear();
               System.out.println("\n*********************************************");
               System.out.println("            Name :-> "+vcd);
               System.out.println("  Date Purchased :-> "+tdate);
               System.out.println("Available Copies :-> "+noc);
               System.out.println("*********************************************");
               System.out.print("\n\nPress ENTER to NEXT >>>");
               String xx=br3.readLine(); 
            }
         }
      }
      catch(EOFException e1){}
      di2.close();
      if (found==0)
         System.out.println("\nEntered Name is UNAVAILABLE in Database.");
      System.out.println("\n\nDisplaying is over....");
      System.out.print("\n\nPress ENTER to go to main MENU....");
      br3.read();
      Clear();
   }
   public void DatabaseDisp( )throws IOException
   {
      Clear();
      BufferedReader br3=new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br4=new BufferedReader(new InputStreamReader(System.in));
      DataInputStream di2 = new DataInputStream(new FileInputStream("Videocd.dat"));
      String vcd,name,tdate;
      int noc,c=1;
      try
      {
        while(true)
        {
           vcd=di2.readUTF();
           tdate=di2.readUTF();
           noc=di2.readInt();
           if (c==1)
           {
              System.out.println("     VCD Name\t\t    Date Purchased   Quantity Avail");
              System.out.println("****************************************************************");
           }
           System.out.print("\n"+vcd);
           for(int i=30;i>vcd.length();i--)
              System.out.print(" ");
           System.out.print(tdate+"\t   "+noc);
           c++;
           if (c>15)
           {
              System.out.println("\n\n****************************************************************");
              System.out.print("\n\n\t\t\tPress ENTER to NEXT >>>");
              br3.read();
              Clear();
              c=1;
            }
        }
      }
      catch(EOFException e1){}
      di2.close();
      System.out.println("\n\n****************************************************************");
      System.out.println("\n\nDisplaying is over....");
      System.out.print("\n\nPress ENTER to go to main MENU....");
      br4.read();
      Clear();
   }

   public void IssueBook( )throws IOException
   {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      File fil=new File("LibrMemb.dat");
      File file=new File("Duplicat.dat");
      DataInputStream dis=new DataInputStream(new FileInputStream(fil));
      DataOutputStream dos=new DataOutputStream(new FileOutputStream(file));
      String opdate,mname,madd1,madd2,mtel,mstat,x;
      Clear();
      System.out.println("\t\t\t******************************");
      System.out.println("\t\t\t*    B O O K   I S S U E     *");
      System.out.println("\t\t\t******************************");
      System.out.print("\n\n\nEnter the Library Membership Number :- ");
      String mbc=br.readLine();
      char renpay;
      double amount;
      int found=0;
      try
      {
         while(true)
         {
            mname=dis.readUTF();
            madd1=dis.readUTF();
            madd2=dis.readUTF();
            mtel=dis.readUTF();
            x=dis.readUTF();
            opdate=dis.readUTF();
            mstat=dis.readUTF();
            renpay=dis.readChar();
            amount=dis.readDouble();
            if (mbc.equalsIgnoreCase(x) && found==0)
            {
              Clear();
              System.out.println("*******************************************");
              System.out.println("*  P R E S E N T   I N F O R M A T I O N  *");
              System.out.println("*******************************************\n\n");
              System.out.println("MEMBER NAME :- "+mname);
              System.out.println("MEMBER ADDRESS :- "+madd1);
              System.out.println("                  "+madd2);
              System.out.println("MEMBER TEL. NO. :- "+mtel);
              System.out.println("MEMBER MEMBERSHIP NO. :- "+x);
              System.out.println("MEMBERSHIP DATE :- "+opdate);
              System.out.println("MEMBER STATUS :- "+mstat);
              System.out.println("MEMBER RENEWAL STATUS :- "+renpay);
              System.out.print("\n\nAre these INFORMATION CORRECT ??? [Y/N] :-> ");
              char ans=br.readLine().toUpperCase().charAt(0);
              if(mstat.equalsIgnoreCase("Avail") && renpay=='Y')
              {
                found=1;
                dos.writeUTF(mname);
                dos.writeUTF(madd1);
                dos.writeUTF(madd2);
                dos.writeUTF(mtel);
                dos.writeUTF(x);
                dos.writeUTF(opdate);
                dos.writeUTF("Not Avail");
                dos.writeChar(renpay);
                dos.writeDouble(amount);
                System.out.println("\n\n******************************************************");
                System.out.println("\nMEMBER updated in Database....");
              }
              else
              {
                dos.writeUTF(mname);
                dos.writeUTF(madd1);
                dos.writeUTF(madd2);
                dos.writeUTF(mtel);
                dos.writeUTF(x);
                dos.writeUTF(opdate);
                dos.writeUTF(mstat);
                dos.writeChar(renpay);
                dos.writeDouble(amount);
              }
            }
            else
            {
              dos.writeUTF(mname);
              dos.writeUTF(madd1);
              dos.writeUTF(madd2);
              dos.writeUTF(mtel);
              dos.writeUTF(x);
              dos.writeUTF(opdate);
              dos.writeUTF(mstat);
              dos.writeChar(renpay);
              dos.writeDouble(amount);
            }
         }
      }
      catch (EOFException e1){}
      if (found==0)
      {
        System.out.println("\n\n\nSorry... Not Wrong MEMBERSHIP NUMBER");
        System.out.println("Try next time...");
      }
      dos.close();
      dis.close();
      File f1=new File("LibrMemb.dat");
      f1.delete();
      File f2 = new File("Duplicat.dat");
      File f3 = new File("LibrMemb.dat");
      f2.renameTo(f3);
      System.out.print("Press ENTER to Back to WORKING MENU....");
      br.read();

   }
   public void ReturnVcd( )throws IOException
   {
      BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
      DataInputStream di2=new DataInputStream(new FileInputStream("Customer.dat"));
      DataInputStream di1 = new DataInputStream(new FileInputStream("Videocd.dat"));
      DataOutputStream ds1 = new DataOutputStream(new FileOutputStream("temp.dat"));
      DataOutputStream ds2 = new DataOutputStream(new FileOutputStream("temp1.dat"));
      Clear();
      System.out.println("          Please Fill the following ........");
      System.out.println("\n\n**********************************************************");
      System.out.print("Enter Customer Name:-> ");
      String cname=br1.readLine( );
      System.out.print("Issued ??? FILM Name  :-> ");
      String fname=br1.readLine();
      System.out.println();
      String cnm,cadr,tdate,vcd,pdate,dpdate=""; int nos,found=0;
      try
      {
         while(true)
         {
            cnm=di2.readUTF();
            cadr=di2.readUTF();
            tdate=di2.readUTF();
            vcd=di2.readUTF();
            pdate=di2.readUTF();
            if( (fname.equalsIgnoreCase(vcd)) && (found==0) && (cnm.equalsIgnoreCase(cname)) )
            {
               System.out.println("Present Data on CUSTOMER Database");
               System.out.println("*********************************");
               System.out.println("Film Name :-> "+vcd);
               System.out.println("Issued Date :-> "+tdate);
               System.out.println("****************************");
               System.out.print("\n\n ??? Is the VCD to RETURN [Y/N] :-> ");
               String op=br1.readLine();
               if (op.equalsIgnoreCase("Y"))
               {
                  found=1; dpdate=pdate;
                  System.out.println("CD... Returned...");
                  System.out.println("Updating all Databases...");
	       }
	       else
	       {
                  ds2.writeUTF(cnm);
		  ds2.writeUTF(cadr);
		  ds2.writeUTF(tdate);
		  ds2.writeUTF(vcd);
		  ds2.writeUTF(pdate);
               }
            }
            else
	    {
                  ds2.writeUTF(cnm);
		  ds2.writeUTF(cadr);
		  ds2.writeUTF(tdate);
		  ds2.writeUTF(vcd);
		  ds2.writeUTF(pdate);
            }
         }
     }
     catch(EOFException e){}
     if(found==0)
        System.out.println("\nSorry!! The Name is not in Database...");
     ds2.close();
     di2.close();
     File f1=new File("Customer.dat");
     f1.delete();
     File f2 = new File("temp1.dat");
     File f3 = new File("Customer.dat");
     f2.renameTo(f3);
     int foundd=0;
     if(found==1)
     {
      try
      {
         while(true)
         {
            vcd=di1.readUTF();
            pdate=di1.readUTF();
            nos=di1.readInt();
            if( (fname.equalsIgnoreCase(vcd)) && (foundd==0) && (pdate.equalsIgnoreCase(dpdate) ) )
            {
                  ds1.writeUTF(vcd);
                  ds1.writeUTF(pdate);
                  ds1.writeInt(nos+1);
		  foundd+=1;
            }
            else
            {
               ds1.writeUTF(vcd);
               ds1.writeUTF(pdate);
               ds1.writeInt(nos);
            }  
         }
       } 
       catch(EOFException e){}
       if (foundd==0)
       {
               ds1.writeUTF(fname);
               ds1.writeUTF(dpdate);
               ds1.writeInt(1);
       }
     }
     ds1.close();
     di1.close();
     File f4=new File("Videocd.dat");
     f4.delete();
     File f5 = new File("temp.dat");
     File f6 = new File("Videocd.dat");
     f5.renameTo(f6);
     System.out.print("Press ENTER to go to main MENU....");
     br1.read(); Clear();
  }

}
class LibMain
{
   public static void main(String arg[ ])throws IOException
   {
      BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
      BufferedReader b1=new BufferedReader(new InputStreamReader(System.in));
      Vcd a=new Vcd();
      int  chc=1;
      while (chc != 4)
      {
         a.Clear();
         System.out.println("\t\t                  ~\\\\|//~");
         System.out.println("\t\t                  -(o o)-");
         System.out.println("\t\t                    (ง)");
         System.out.println();
         System.out.println("\t   ษออออออออออออออออออออออออออออออออออออออออออออออออออป");
         System.out.println("\t   บ ษออออออออออออออออออออออออออออออออออออออออออออออป บ");
         System.out.println("\t   บ บ               M A I N    M E N U             บ บ");
         System.out.println("\t   บ ฬออออออออออออออออออออออออออออออออออออออออออออออน บ");
         System.out.println("\t   บ บ      1. B O O K   M A N A G E M E N T        บ บ");
         System.out.println("\t   บ บ                                              บ บ");
         System.out.println("\t   บ บ      2. M E M B E R   M A N A G E M E N T    บ บ");
         System.out.println("\t   บ บ                                              บ บ");
         System.out.println("\t   บ บ      3. L I B R A R Y   P R O C E S S        บ บ");
         System.out.println("\t   บ บ                                              บ บ");
         System.out.println("\t   บ บ      4. E  X  I  T                           บ บ");
         System.out.println("\t   บ ศออออออออออออออออออออออออออออออออออออออออออออออผ บ");
         System.out.println("\t   ศออออออออออออออออออออออออออออออออออออออออออออออออออผ");
         System.out.print("\t\t        Enter your Choice Please: ");
         chc=Integer.parseInt(b.readLine( ));
         switch(chc)
         {
            case 1:
              int ch1=0;
              while (ch1 != 4)
              {
                 a.Clear();
                 System.out.println();
                 System.out.println("\t\tษออออออออออออออออออออออออออออออออออออออออออออออออออป");
                 System.out.println("\t\tบ ษออออออออออออออออออออออออออออออออออออออออออออออป บ");
                 System.out.println("\t\tบ บ        B O O K   M A N A G E M E N T         บ บ");
                 System.out.println("\t\tบ ฬออออออออออออออออออออออออออออออออออออออออออออออน บ");
                 System.out.println("\t\tบ บ   1. INSERT NEW BOOKS IN LIBRARY             บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   2. MODIFYING IFORMATION OF EXISTING BOOK   บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   3. DELETION OF LOST OR DAMAGED BOOKS       บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   4. P R E V I O U S   O P T I O N S         บ บ");
                 System.out.println("\t\tบ ศออออออออออออออออออออออออออออออออออออออออออออออผ บ");
                 System.out.println("\t\tศออออออออออออออออออออออออออออออออออออออออออออออออออผ");
                 System.out.print("        Enter your Choice Please: ");
                 ch1=Integer.parseInt(b.readLine( ));
                 switch(ch1)
                 {
                    case 1:
                      a.NewBook( );
                      break;
                    case 2:
                      a.ChangeBook();
                      break;
                    case 3:
                      a.DeleteBook();
                      break;
                    case 4:
                      break;
                    default: 
                      System.out.println("Invalid Entry.. Press Enter & Enter Option Again..");
                      b1.read();
                  } 
              }
              break;
            case 2:
              int ch11=0;
              while (ch11 != 4)
              {
                 a.Clear();
                 System.out.println();
                 System.out.println("\t\tษออออออออออออออออออออออออออออออออออออออออออออออออออป");
                 System.out.println("\t\tบ ษออออออออออออออออออออออออออออออออออออออออออออออป บ");
                 System.out.println("\t\tบ บ      M E M B E R   M A N A G E M E N T       บ บ");
                 System.out.println("\t\tบ ฬออออออออออออออออออออออออออออออออออออออออออออออน บ");
                 System.out.println("\t\tบ บ   1. NEW MEMBER INTRODUCTION                 บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   2. MODIFICATION OF MEMBER IFORMATION       บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   3. DELETION OF UNPAID MEMBERS              บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   4. P R E V I O U S   O P T I O N S         บ บ");
                 System.out.println("\t\tบ ศออออออออออออออออออออออออออออออออออออออออออออออผ บ");
                 System.out.println("\t\tศออออออออออออออออออออออออออออออออออออออออออออออออออผ");
                 System.out.print("        Enter your Choice Please: ");
                 ch11=Integer.parseInt(b.readLine( ));
                 switch(ch11)
                 {
                    case 1:
                      a.NewMember( );
                      break;
                    case 2:
                      a.ChangeMember();
                      break;
                    case 3:
                      a.DeleteMember();
                      break;
                    case 4:
                      break;
                    default: 
                      System.out.println("Invalid Entry.. Press Enter & Enter Option Again..");
                      b1.read();
                 } 
              }
              break;
            case 3:
              int ch2=0;
              a.Clear();
              while (ch2 != 4)
              {
                 System.out.println();
                 System.out.println("\t\tษออออออออออออออออออออออออออออออออออออออออออออออออออป");
                 System.out.println("\t\tบ ษออออออออออออออออออออออออออออออออออออออออออออออป บ");
                 System.out.println("\t\tบ บ      L I B R A R Y   P R O C E S S E S       บ บ");
                 System.out.println("\t\tบ ฬออออออออออออออออออออออออออออออออออออออออออออออน บ");
                 System.out.println("\t\tบ บ   1. ISSUE BOOKS TO MEMBERS                  บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   2. RECEIVE ON RETURN FROM MEMBERS          บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   3. QUERY BOOKS FOR AVAILABILITY            บ บ");
                 System.out.println("\t\tบ บ                                              บ บ");
                 System.out.println("\t\tบ บ   4. P R E V I O U S   O P T I O N S         บ บ");
                 System.out.println("\t\tบ ศออออออออออออออออออออออออออออออออออออออออออออออผ บ");
                 System.out.println("\t\tศออออออออออออออออออออออออออออออออออออออออออออออออออผ");
                 System.out.print("        Enter your Choice Please: ");
                 ch2=Integer.parseInt(b.readLine( ));
                 switch(ch2)
                 {
                    case 1:
                      a.IssueBook( ); 
                      break;
                    case 2:
                      a.ReturnVcd( ); 
                      break;
                    case 3:
                      break;
                    case 4:
                      break;
                    default: 
                      System.out.println("Invalid Entry.. Press Enter & Enter Option Again..");
                      b1.read();
                      break;
                 }
              }
              break;
            case 4:
              System.out.println("The System is Developed By");
              System.out.println("Sweta Snigdha Sahoo");
              break;
            default :
              System.out.println("Invalid Entry.. Press Enter & Enter Option Again..");
              b1.read();
         }
      }
   }
}

