import java.text.SimpleDateFormat;
import java.util.*;

public class p3_Online_Reservation {

    static class Station {
        private String name;
        private String type; // 's' for small, 'b' for big
        private double distanceFromStart;

        public Station(String name, String type, double distanceFromStart) {
            this.name = name;
            this.type = type;
            this.distanceFromStart = distanceFromStart;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public double getDistanceFromStart() {
            return distanceFromStart;
        }

        @Override
        public String toString() {
            return name + "(" + type + ")(" + distanceFromStart + "km)";
        }
    }

    static class Ticket {
        private String pnr;
        private String passengerName;
        private String fromStation;
        private String toStation;
        private String seatType;
        private double cost;
        private String date;
        private String travelDate;

        public Ticket(String pnr, String passengerName, String fromStation, String toStation, String seatType, double cost, String travelDate) {
            this.pnr = pnr;
            this.passengerName = passengerName;
            this.fromStation = fromStation;
            this.toStation = toStation;
            this.seatType = seatType;
            this.cost = cost;
            this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            this.travelDate = travelDate;
        }

        public String getPnr() {
            return pnr;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public String getFromStation() {
            return fromStation;
        }

        public String getToStation() {
            return toStation;
        }

        public String getSeatType() {
            return seatType;
        }

        public double getCost() {
            return cost;
        }

        public String getDate() {
            return date;
        }

        public String getTravelDate() {
            return travelDate;
        }

        @Override
        public String toString() {
            return "PNR: " + pnr + ", Passenger: " + passengerName + ", From: " + fromStation + ", To: " + toStation + ", Seat Type: " + seatType + ", Cost: " + cost + ", Booking Date: " + date + ", Travel Date: " + travelDate;
        }
    }

    static class Train {
        private String trainId;
        private String name;
        private String fromStation;
        private String toStation;
        private String type; // 'M', 'S', or 'B'
        private int sleeperSeats;
        private int thirdAcSeats;
        private int firstAcSeats;
        private double distance;
        private List<Station> stops;
        private List<Ticket> bookedTickets;
        private List<String> availableDates;

        public Train(String trainId, String name, String fromStation, String toStation, String type, int sleeperSeats, int thirdAcSeats, int firstAcSeats, double distance, List<Station> stops) {
            this.trainId = trainId;
            this.name = name;
            this.fromStation = fromStation;
            this.toStation = toStation;
            this.type = type;
            this.sleeperSeats = sleeperSeats;
            this.thirdAcSeats = thirdAcSeats;
            this.firstAcSeats = firstAcSeats;
            this.distance = distance;
            this.stops = stops;
            this.bookedTickets = new ArrayList<>();
            this.availableDates = generateAvailableDates();
        }

        public String getTrainId() {
            return trainId;
        }

        public String getName() {
            return name;
        }

        public String getFromStation() {
            return fromStation;
        }

        public String getToStation() {
            return toStation;
        }

        public String getType() {
            return type;
        }

        public int getSleeperSeats() {
            return sleeperSeats;
        }

        public int getThirdAcSeats() {
            return thirdAcSeats;
        }

        public int getFirstAcSeats() {
            return firstAcSeats;
        }

        public double getDistance() {
            return distance;
        }

        public List<Station> getStops() {
            return stops;
        }

        public List<Ticket> getBookedTickets() {
            return bookedTickets;
        }

        public List<String> getAvailableDates() {
            return availableDates;
        }

        public void bookTicket(Ticket ticket) {
            bookedTickets.add(ticket);
        }

        public int getTotalTicketsBooked() {
            return bookedTickets.size();
        }

        public void cancelTrainDate(String date) {
            changeValueToNull(availableDates, date);
        }

        public static void changeValueToNull(List<String> list, String value) {
            int index = list.indexOf(value);
            if (index != -1) {
                list.set(index, null);
            } else {
                System.out.println("Value not found in the list");
            }
        }

        private List<String> generateAvailableDates() {
            List<String> dates = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 1; i++) {
                dates.add(sdf.format(cal.getTime()));
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            return dates;
        }

        @Override
        public String toString() {
            return String.format("%-10s %-30s %-25s %-25s %-10s %-10d %-10d %-10d %-10.2f", trainId, name, fromStation, toStation, type, sleeperSeats, thirdAcSeats, firstAcSeats, distance);
        }
    }

    private static final List<Station> STATIONS = new ArrayList<>();
    private static final List<Train> TRAINS = new ArrayList<>();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static Calendar cal = Calendar.getInstance();
    static String travelDate = sdf.format(cal.getTime());

    static {
        int war = 637-295, del = war+437;
        STATIONS.add(new Station("MGR Chennai Central", "d", 0));
        STATIONS.add(new Station("Sulurupeta", "s", 82));
        STATIONS.add(new Station("Nayudupeta", "s", 109));
        STATIONS.add(new Station("Gudur Jn", "b", 137));
        STATIONS.add(new Station("Nellore", "b", 175));
        STATIONS.add(new Station("Bitragunta", "s", 210));
        STATIONS.add(new Station("Kavali", "s", 226));
        STATIONS.add(new Station("Singarayakonda", "s", 264));
        STATIONS.add(new Station("Ongole", "b", 292));
        STATIONS.add(new Station("Chirala", "b", 324));
        STATIONS.add(new Station("Bapatla", "b", 357));
        STATIONS.add(new Station("Nidubrolu", "s", 377));
        STATIONS.add(new Station("Tenali Jn", "s", 399));
        STATIONS.add(new Station("Vijayawada Jn", "d", 431));
        STATIONS.add(new Station("Khammam", "s", 530));
        STATIONS.add(new Station("Dornakal", "s", 553));
        STATIONS.add(new Station("Warangal", "b", 637));
        STATIONS.add(new Station("Kazipet Jn", "s", war+305));
        STATIONS.add(new Station("Secunderabad Jn", "d", war+437));
        STATIONS.add(new Station("Ramagudam", "s", del+224));
        STATIONS.add(new Station("Sirpur Kaghaznagar", "s", del+296));
        STATIONS.add(new Station("Balharshah", "d", del+366));
        STATIONS.add(new Station("Majri Jn", "s", del+414));
        STATIONS.add(new Station("Sewagram Jn", "b", del+498));
        STATIONS.add(new Station("Nagpur Jn", "d", del+575));
        STATIONS.add(new Station("Narkher Jn", "s", del+661));
        STATIONS.add(new Station("Amla Jn", "s", del+743));
        STATIONS.add(new Station("Itarsi Jn", "d", del+873));
        STATIONS.add(new Station("Narmadapuram", "s", del+891));
        STATIONS.add(new Station("Obaidulla Ganj", "s", del+929));
        STATIONS.add(new Station("Rani Kamalapati", "b", del+959));
        STATIONS.add(new Station("Bhopal Jn", "d", del+965));
        STATIONS.add(new Station("GulabGanj", "s", del+1039));
        STATIONS.add(new Station("Bina Jn", "b", del+1103));
        STATIONS.add(new Station("Lalitpur Jn", "s", del+1166));
        STATIONS.add(new Station("VGL Jhansi Junction", "d", del+1257));
        STATIONS.add(new Station("Simariatal", "s", del+1317));
        STATIONS.add(new Station("Gwalior Jn", "d", del+1354));
        STATIONS.add(new Station("Sikroda Kwanri", "s", del+1401));
        STATIONS.add(new Station("Dholpur Jn", "b", del+1462));
        STATIONS.add(new Station("Agra Cantt.", "d", del+1472));
        STATIONS.add(new Station("Deen Dayal Dham", "s", del+1505));
        STATIONS.add(new Station("Mathura Jn", "b", del+1526));
        STATIONS.add(new Station("Palwal", "s", del+1610));
        STATIONS.add(new Station("Faridabad", "s", del+1639));
        STATIONS.add(new Station("Tuglakabad", "s", del+1649));
        STATIONS.add(new Station("Hazrat Nizamuddin", "d", del+1660));
        STATIONS.add(new Station("New Delhi", "d", del+1667));
    }

    static {
        List<Station> stops = getStops("MGR Chennai Central", "Vijayawada Jn", "S");
        Train train = new Train("12712", "Pinakini Express", "MGR Chennai Central", "Vijayawada Jn", "S", getSleeperSeats("S"), getThirdAcSeats("S"), getFirstAcSeats("S"),getDistance("MGR Chennai Central", "Vijayawada Jn"), stops);
        TRAINS.add(train);
        stops = getStops("MGR Chennai Central", "Vijayawada Jn", "M");
        train = new Train("16745", "Jan Shatabdhi Express", "MGR Chennai Central", "Vijayawada Jn", "M", getSleeperSeats("M"), getThirdAcSeats("M"), getFirstAcSeats("M"), getDistance("MGR Chennai Central", "Vijayawada Jn"), stops);
        TRAINS.add(train);
        stops = getStops("MGR Chennai Central", "Hazrat Nizamuddin", "B");
        train = new Train("60254", "Rajadhani Express", "MGR Chennai Central", "Hazrat Nizamuddin", "B", getSleeperSeats("B"), getThirdAcSeats("B"), getFirstAcSeats("B"), getDistance("MGR Chennai Central", "Hazrat Nizamuddin"), stops);
        TRAINS.add(train);
        stops = getStops("Gudur Jn", "Secunderabad Jn", "M");
        train = new Train("16955", "Simhadhri Express", "Gudur Jn", "Secunderabad Jn", "M", getSleeperSeats("M"), getThirdAcSeats("M"), getFirstAcSeats("M"), getDistance("Gudur Jn", "Secunderabad Jn"), stops);
        TRAINS.add(train);
        stops = getStops("Vijayawada Jn", "Secunderabad Jn", "S");
        train = new Train("14799", "Vishaka Express", "Vijayawada Jn", "Secunderabad Jn", "S", getSleeperSeats("S"), getThirdAcSeats("S"), getFirstAcSeats("S"), getDistance("Vijayawada Jn", "Secunderabad Jn"), stops);
        TRAINS.add(train);
        stops = getStops("Bhopal Jn", "New Delhi", "B");
        train = new Train("17694", "Shatabdhi Express", "Bhopal Jn", "New Delhi", "B", getSleeperSeats("B"), getThirdAcSeats("B"), getFirstAcSeats("B"), getDistance("Bhopal Jn", "New Delhi"), stops);
        TRAINS.add(train);
        stops = getStops("MGR Chennai Central", "New Delhi", "S");
        train = new Train("16545", "Grand Trunk Express", "MGR Chennai Central", "New Delhi", "S", getSleeperSeats("S"), getThirdAcSeats("S"), getFirstAcSeats("S"),getDistance("MGR Chennai Central", "New Delhi"), stops);
        TRAINS.add(train);
//        stops = getStops("MGR Chennai Central", "New Delhi", "S");
//        train = new Train("16545", "Grand Trunk Express", "MGR Chennai Central", "New Delhi", "S", getSleeperSeats("S"), getThirdAcSeats("S"), getFirstAcSeats("S"),getDistance("MGR Chennai Central", "New Delhi"), stops);
//        TRAINS.add(train);
        stops = getStops("Kazipet Jn", "Hazrat Nizamuddin", "S");
        train = new Train("16545", "Dakshin SF Express", "Kazipet Jn", "Hazrat Nizamuddin", "S", getSleeperSeats("S"), getThirdAcSeats("S"), getFirstAcSeats("S"),getDistance("Kazipet Jn", "Hazrat Nizamuddin"), stops);
        TRAINS.add(train);
        stops = getStops("MGR Chennai Central", "VGL Jhansi Junction", "M");
        train = new Train("12512", "Rapti Sagar SF Express", "MGR Chennai Central", "VGL Jhansi Junction", "M", getSleeperSeats("M"), getThirdAcSeats("M"), getFirstAcSeats("M"), getDistance("MGR Chennai Central", "VGL Jhansi Junction"), stops);
        TRAINS.add(train);
        stops = getStops("Itarsi Jn", "New Delhi", "S");
        train = new Train("12715", "Sachkhand Express", "Itarsi Jn", "New Delhi", "S", getSleeperSeats("S"), getThirdAcSeats("S"), getFirstAcSeats("S"),getDistance("Itarsi Jn", "New Delhi"), stops);
        TRAINS.add(train);
        stops = getStops("Vijayawada Jn", "Hazrat Nizamuddin", "B");
        train = new Train("20805", "Andhra Pradesh Express", "Vijayawada Jn", "Hazrat Nizamuddin", "B", getSleeperSeats("B"), getThirdAcSeats("B"), getFirstAcSeats("B"), getDistance("Vijayawada Jn", "Hazrat Nizamuddin"), stops);
        TRAINS.add(train);
        stops = getStops("Secunderabad Jn", "Hazrat Nizamuddin", "B");
        train = new Train("12723", "Telangana Express", "Secunderabad Jn", "Hazrat Nizamuddin", "B", getSleeperSeats("B"), getThirdAcSeats("B"), getFirstAcSeats("B"), getDistance("Secunderabad Jn", "Hazrat Nizamuddin"), stops);
        TRAINS.add(train);
        stops = getStops("MGR Chennai Central", "Secunderabad Jn", "M");
        train = new Train("16955", "Charminar Express", "MGR Chennai Central", "Secunderabad Jn", "M", getSleeperSeats("M"), getThirdAcSeats("M"), getFirstAcSeats("M"), getDistance("MGR Chennai Central", "Secunderabad Jn"), stops);
        TRAINS.add(train);
    }

    private static void displayTrains(List<Train> trains) {
        System.out.printf("%-10s %-30s %-25s %-25s %-10s %-10s %-10s %-10s %-15s\n",
                "Train ID", "Train Name", "From", "To", "Type", "Sleeper", "3rd AC", "1st AC", "Distance");

        for (Train train : trains) {
            System.out.println(train);
        }
    }

    private static void displayTrains(List<Train> trains, boolean showAvailability) {
        System.out.printf("%-10s %-30s %-25s %-25s %-10s %-10s %-15s %-15s %-15s\n", "Train ID", "Train Name", "From", "To", "Type", "Sleeper", "3rd AC", "1st AC", "Distance");

        for (Train train : trains) {
            String sleeperStatus = train.getSleeperSeats() > 0 ? "Available" : "Not Available";
            String thirdAcStatus = train.getThirdAcSeats() > 0 ? "Available" : "Not Available";
            String firstAcStatus = train.getFirstAcSeats() > 0 ? "Available" : train.getType().equals("S") ? "Not Applicable" : "Not Available";

            System.out.printf("%-10s %-30s %-25s %-25s %-10s %-10s %-15s %-15s %-15s\n", train.getTrainId(), train.getName(), train.getFromStation(), train.getToStation(), train.getType(), sleeperStatus,  thirdAcStatus , firstAcStatus , train.getDistance());
        }
    }

    private static void displayAdminPanel() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. View Trains");
            System.out.println("2. Add New Train");
            System.out.println("3. Cancel Train Date");
            System.out.println("4. View Booked Tickets");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nAll Trains:");
                    displayTrains(TRAINS);
                    break;
                case 2:
                    addNewTrain();
                    break;
                case 3:
                    cancelTrainDate();
                    break;
                case 4:
                    viewBookedTickets();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewTrain() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add New Train:");
        System.out.print("Enter Train Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Train ID (5 digits): ");
        String trainId = scanner.nextLine();
        System.out.print("Enter From Station: ");
        String fromStation = scanner.nextLine();
        System.out.print("Enter To Station: ");
        String toStation = scanner.nextLine();
        System.out.print("Enter Train Type (M/S/B): ");
        String type = scanner.nextLine().toUpperCase();

        if (isValidStation(fromStation) && isValidStation(toStation)) {
            List<Station> stops = getStops(fromStation, toStation, type);
            if (stops != null) {
                Train newTrain = new Train(trainId, name, fromStation, toStation, type,getSleeperSeats(type), getThirdAcSeats(type), getFirstAcSeats(type),getDistance(fromStation, toStation), stops);
                TRAINS.add(newTrain);
                System.out.println("New train added successfully.");
            } else {
                System.out.println("Invalid route. Train stops not found.");
            }
        } else {
            System.out.println("Invalid stations. Please check the station names.");
        }
    }

    private static boolean isValidStation(String stationName) {
        return STATIONS.stream().anyMatch(station -> station.getName().equalsIgnoreCase(stationName));
    }

//    private static List<Station> getStops(String fromStation, String toStation, String type) {
//        boolean start = false;
//        List<Station> stops = new ArrayList<>();
//        for (Station station : STATIONS) {
//            if (station.getName().equalsIgnoreCase(fromStation)) {
//                start = true;
//            }
//            if (start) {
//                stops.add(station);
//                if (station.getName().equalsIgnoreCase(toStation)) {
//                    break;
//                }
//            }
//        }
//        return stops.size() > 0 ? stops : null;
//    }

    private static List<Station> getStops(String fromStation, String toStation, String type) {
        boolean start = false;
        List<Station> stops = new ArrayList<>();
        Set<String> allowedTypes = new HashSet<>();

        switch (type) {
            case "B":
                allowedTypes.add("d");
                break;
            case "M":
                allowedTypes.add("d");
                allowedTypes.add("b");
                break;
            case "S":
                allowedTypes.add("d");
                allowedTypes.add("b");
                allowedTypes.add("s");
                break;
            default:
                return null;
        }

        for (Station station : STATIONS) {
            if (station.getName().equalsIgnoreCase(fromStation)) {
                start = true;
            }
            if (start) {
                if (allowedTypes.contains(station.getType())) {
                    stops.add(station);
                }
                if (station.getName().equalsIgnoreCase(toStation)) {
                    break;
                }
            }
        }

        return stops.isEmpty() ? null : stops;
    }


    private static int getSleeperSeats(String type) {
        switch (type) {
            case "M": return 15;
            case "S": return 20;
            case "B": return 5;
            default: return 0;
        }
    }

    private static int getThirdAcSeats(String type) {
        switch (type) {
            case "M": return 10;
            case "S": return 10;
            case "B": return 10;
            default: return 0;
        }
    }

    private static int getFirstAcSeats(String type) {
        switch (type) {
            case "M": return 5;
            case "S": return 0;
            case "B": return 15;
            default: return 0;
        }
    }

    private static double getDistance(String fromStation, String toStation) {
        double fromDistance = STATIONS.stream()
                .filter(station -> station.getName().equalsIgnoreCase(fromStation))
                .findFirst().map(Station::getDistanceFromStart).orElse(0.0);
        double toDistance = STATIONS.stream()
                .filter(station -> station.getName().equalsIgnoreCase(toStation))
                .findFirst().map(Station::getDistanceFromStart).orElse(0.0);
        return Math.abs(toDistance - fromDistance);
    }

    private static void cancelTrainDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Train ID: ");
        String trainId = scanner.nextLine();
        System.out.print("Confirm the Date to Cancel (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        Train train = TRAINS.stream().filter(t -> t.getTrainId().equals(trainId)).findFirst().orElse(null);

        if (train != null) {
            train.cancelTrainDate(date);
            System.out.println("Train date canceled successfully.");
        } else {
            System.out.println("Train not found.");
        }
    }

    private static void viewBookedTickets() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Train ID\tTrain Name");
        for(Train train : TRAINS)
            System.out.println(train.getTrainId() + "\t\t" + train.getName());
        System.out.println();
        System.out.print("Enter Train ID: ");
        String trainId = scanner.nextLine();

        Train train = TRAINS.stream().filter(t -> t.getTrainId().equals(trainId)).findFirst().orElse(null);

        if (train != null) {
            System.out.println("\nBooked Tickets:");
            List<Ticket> bookedTickets = train.getBookedTickets();
            if (bookedTickets == null || bookedTickets.isEmpty()) {
                System.out.println("No booked tickets found.");
            } else {
                for (Ticket ticket : bookedTickets) {
                    System.out.println(ticket);
                }
            }
        } else {
            System.out.println("Train not found.");
        }
    }

    private static void displayCustomerPanel() {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("\n--- Customer Panel ---\n");
            System.out.println("\nAll Trains:");
            displayTrains(TRAINS, true);
            System.out.println();
            System.out.println("1. Book Ticket");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

//    private static void bookTicket() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter From Station: ");
//        String fromStation = scanner.nextLine();
//        System.out.print("Enter To Station: ");
//        String toStation = scanner.nextLine();
//
//        List<Train> availableTrains = findTrains(fromStation, toStation);
//
//        if (!availableTrains.isEmpty()) {
//            System.out.println("\nAvailable Trains:");
//            displayTrains(availableTrains);
//
//            System.out.print("\nEnter Train ID to Book Ticket: ");
//            String trainId = scanner.nextLine();
//
//            Train selectedTrain = TRAINS.stream()
//                    .filter(t -> t.getTrainId().equals(trainId))
//                    .findFirst()
//                    .orElse(null);
//
//            if (selectedTrain != null) {
//                System.out.print("Enter Passenger Name: ");
//                String passengerName = scanner.nextLine();
//                System.out.print("Enter Seat Type (Sleeper/3rd AC/1st AC): ");
//                String seatType = scanner.nextLine();
//                System.out.print("Enter Travel Date (yyyy-MM-dd, up to 10 days from today): ");
//                String travelDate = scanner.nextLine();
//
//                if (selectedTrain.getAvailableDates().contains(travelDate)) {
//                    if (isValidSeatType(seatType)) {
//                        double cost = calculateCost(seatType, selectedTrain.getDistance());
//                        String pnr = generatePNR();
//                        Ticket ticket = new Ticket(pnr, passengerName, fromStation, toStation, seatType, cost, travelDate);
//                        selectedTrain.bookTicket(ticket);
//                        System.out.println("Ticket booked successfully. PNR: " + pnr);
//                    } else {
//                        System.out.println("Invalid seat type.");
//                    }
//                } else if (selectedTrain.getAvailableDates().contains(null)) {
//                    System.out.println("Sorry for the inconvenience, the train has been cancelled on the selected date!!");
//                } else {
//                    System.out.println("Invalid travel date. Please choose a date within the next 10 days.");
//                }
//            } else {
//                System.out.println("Train not found.");
//            }
//        } else {
//            System.out.println("No trains available for the selected route.");
//        }
//    }

    private static void bookTicket() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter From Station: ");
        String fromStation = scanner.nextLine();
        System.out.print("Enter To Station: ");
        String toStation = scanner.nextLine();

        List<Train> availableTrains = findTrainsBasedOnStationType(fromStation, toStation);

        if (!availableTrains.isEmpty()) {
            System.out.println("\nAvailable Trains:");
            displayTrains(availableTrains);

            System.out.print("\nEnter Train ID to Book Ticket: ");
            String trainId = scanner.nextLine();

            Train selectedTrain = TRAINS.stream()
                    .filter(t -> t.getTrainId().equals(trainId))
                    .findFirst()
                    .orElse(null);

            if (selectedTrain != null) {
                System.out.print("Enter Passenger Name: ");
                String passengerName = scanner.nextLine();
                System.out.print("Enter Seat Type (Sleeper(sl)/3rd AC(3a)/1st AC(1a)): ");
                String seatType = scanner.nextLine();
//                System.out.print("Enter Travel Date (yyyy-MM-dd, up to 10 days from today): ");
//                String travelDate = scanner.nextLine();

                if (selectedTrain.getAvailableDates().contains(travelDate)) {
                    if (isValidSeatType(seatType)) {
                        if (hasAvailableSeats(selectedTrain, seatType, fromStation, toStation)) {  // Check seat availability between the stations
                            double cost = calculateCost(seatType, selectedTrain.getDistance());
                            String pnr = generatePNR();
                            Ticket ticket = new Ticket(pnr, passengerName, fromStation, toStation, seatType, cost, travelDate);
                            selectedTrain.bookTicket(ticket);
                            reduceSeatCount(selectedTrain, seatType, fromStation, toStation); // Reduce the seat count after booking
                            System.out.println("Ticket booked successfully. PNR: " + pnr);
                        } else {
                            System.out.println("No seats available for the selected seat type.");
                        }
                    } else {
                        System.out.println("Invalid seat type.");
                    }
                } else if (selectedTrain.getAvailableDates().contains(null)) {
                    System.out.println("Sorry for the inconvenience, the train has been cancelled on the selected date!!");
                } else {
                    System.out.println("Invalid travel date. Please choose a date within the next 10 days.");
                }
            } else {
                System.out.println("Train not found.");
            }
        } else {
            System.out.println("No trains available for the selected route.");
        }
    }

    private static List<Train> findTrainsBasedOnStationType(String fromStation, String toStation) {
        boolean isBigStationInRoute = isStationOfType(fromStation, "d") && isStationOfType(toStation, "d");
        boolean isSmallStationInRoute = isStationOfType(fromStation, "s") || isStationOfType(toStation, "s") || isStationOfType(fromStation, "b") || isStationOfType(toStation, "b") || isStationOfType(fromStation, "d") || isStationOfType(toStation, "d");
        boolean isMediumStationInRoute = isStationOfType(fromStation, "b") || isStationOfType(toStation, "b") || isStationOfType(fromStation, "d") || isStationOfType(toStation, "d");

        List<Train> availableTrains = new ArrayList<>();
        for (Train train : TRAINS) {
            if (isRouteAvailable(train, fromStation, toStation)) {
                if (isBigStationInRoute) {
                    if (train.getType().equals("B")) {
                        availableTrains.add(train);
                    }
                } if(isMediumStationInRoute) {
                    if (train.getType().equals("M")) {
                        availableTrains.add(train);
                    }
                } if(isSmallStationInRoute){
                    if (train.getType().equals("S")) {
                        availableTrains.add(train);
                    }
                }
            }
        }
        return availableTrains;
    }

    private static boolean isStationOfType(String stationName, String type) {
        return STATIONS.stream().anyMatch(station -> station.getName().equalsIgnoreCase(stationName) && station.getType().equalsIgnoreCase(type));
    }

    private static boolean hasAvailableSeats(Train train, String seatType, String fromStation, String toStation) {
        switch (seatType) {
            case "sl":
                return train.getSleeperSeats() > 0;
            case "3a":
                return train.getThirdAcSeats() > 0;
            case "1a":
                return train.getFirstAcSeats() > 0;
            default:
                return false;
        }
    }

    private static void reduceSeatCount(Train train, String seatType, String fromStation, String toStation) {
        switch (seatType) {
            case "sl":
                train.sleeperSeats--;
                break;
            case "3a":
                train.thirdAcSeats--;
                break;
            case "1a":
                train.firstAcSeats--;
                break;
        }
    }



    private static List<Train> findTrains(String fromStation, String toStation) {
        List<Train> availableTrains = new ArrayList<>();
        for (Train train : TRAINS) {
            if (isRouteAvailable(train, fromStation, toStation)) {
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }

    private static boolean isRouteAvailable(Train train, String fromStation, String toStation) {
        boolean start = false;
        for (Station station : train.getStops()) {
            if (station.getName().equalsIgnoreCase(fromStation)) {
                start = true;
            }
            if (start) {
                if (station.getName().equalsIgnoreCase(toStation)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidSeatType(String seatType) {
        return seatType.toLowerCase().equalsIgnoreCase("sl") || seatType.toLowerCase().equalsIgnoreCase("3a") || seatType.toLowerCase().equalsIgnoreCase("1a");
    }

    private static double calculateCost(String seatType, double distance) {
        double costPerKm;
        switch (seatType) {
            case "sl":
                costPerKm = 0.5;
                break;
            case "3a":
                costPerKm = 1.0;
                break;
            case "1a":
                costPerKm = 1.5;
                break;
            default:
                costPerKm = 0;
                break;
        }
        return costPerKm * distance;
    }

    private static String generatePNR() {
        String pnr = UUID.randomUUID().toString().toUpperCase().replaceAll("[^A-Z0-9]", "").substring(0, 10);
        return String.format("%s-%s-%s", pnr.substring(0, 4), pnr.substring(4, 6), pnr.substring(6));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Train Reservation System ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Password: ");
                    String adminPassword = scanner.nextLine();
                    if ("admin".equals(adminPassword)) {
                        displayAdminPanel();
                    } else {
                        System.out.println("Invalid password.");
                    }
                    break;
                case 2:
                    displayCustomerPanel();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
