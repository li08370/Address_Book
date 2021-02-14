//import java.util.;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Note will find objects that no longer exist
public class addressBook {
    private static ArrayList<Address> addressList = new ArrayList<>();


    public static void main(String[] args) {
        Address big = new Address("c", "three", 765);
        Address the = new Address("b", "hell", 415);
        Address he = new Address("a", "big", 654);

        addressList.add(big);//index = 0
        addressList.add(the);
        addressList.add(he);

        int index;
        int action = 0;
        System.out.println(addressList.size());
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("""
                        What would you like to do with your address book?\s
                         1) add\s
                         2) update existing contact\s
                         3) delete a contact\s
                         4) search\s
                         5) print all\s
                         6) quit""");
                action = input.nextInt();
            } catch (InputMismatchException exp) {
                System.out.println("Please enter an action listed by the number");
            }
            switch (action) {
                case 1:
                    addingAddress();
                    break;
                case 2:
                    index = search(0, false);
                    if (index != -1) {
                        update(index);
                    }
                    break;
                case 3:
                    index = search(1, false);
                    if (index != -1) {
                        addressList.remove(index);
                    }
                    break;
                case 4:
                    search(2, false);
                    break;
                case 5:
                    print();
                    break;
                case 6:
                   System.out.println("Loading");
                    break;
            }
            addressList.sort(Address::compareTo);
        } while (action != 5) ;
            for (Address a : addressList) {
                System.out.println(a);
            }

    }

    private static void update(int index) {
        String name;
        int pNumber;
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to change? \n 1) first name \n 2) last name \n3) phone number");
        try {
            int action = sc.nextInt();
            switch (action) {
                case 1:
                    System.out.println("What is the new first name?");
                    name = sc.next();
                    addressList.get(index).setFirst_name(name);
                    break;
                case 2:
                    System.out.println("What is the new last name?");
                    name = sc.next();
                    addressList.get(index).setLast_name(name);
                    break;
                case 3:
                    System.out.println("What is the new Phone number?");
                    pNumber = sc.nextInt();
                    addressList.get(index).setPhone_number(pNumber);
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter an appropriate response to the prompts");
        }
        System.out.println(addressList.get(index));
        addressList.sort(Address::compareTo);
    }

    private static void addingAddress() {
        Scanner sc = new Scanner(System.in);
        String firstName = null;
        String lastName = null;
        int phoneNumber = 0;
        try {
            System.out.println("Enter there first name, last name and phone number ");
            firstName = sc.next();
            lastName = sc.next();
            phoneNumber = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter an appropriate input");
        }
        Address a = new Address(firstName, lastName, phoneNumber);
        addressList.add(a);
    }

    private static int search(int type, boolean sName) {
        int pNumber;
        String name;
        int index = -1;
        Address a = null;
        ArrayList<Integer> found = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        if(!addressList.isEmpty()) {
            //User Input  used to search for an Address
            System.out.println("What address are you looking for? \n You can search by first name, last name or phone number");
            if (sc.hasNextInt()) {
                pNumber = sc.nextInt();
                a = new Address("n","n", pNumber);

            } else if (sc.hasNext()) {
                name = sc.next();
                a = new Address(name, name);
            }
            //loop used to travers the address and find any address's that apply to the search
            for (int i = 0; i < addressList.size(); i++) {
                assert a != null;

                if (sName == false) {
                    if (addressList.get(i).equals(a)) {
                        found.add(i);
                        System.out.println(addressList.get(i));
                    }
                } else {
                    if (addressList.get(i).equal(a)){
                        found.add(i);
                        System.out.println(addressList.get(i));
                    }
                }
            }
        }
        //determines whether there are any address that were added to the found array
        if(found.size() == 0) {
            System.out.println("There is not contact by that name or number");
        }else if (type != 2 && (found.size() > 1)) {
                System.out.println("There are multiple contacts. Please use another attribute of the address?");
                index = search(type, true);
            } else {
                index = found.get(0);
            }
        return index;
        }

        public static void print(){
            addressList.sort(Address::compareTo);
            for (Address a : addressList) {
                System.out.println(a);
            }
        }

    }






