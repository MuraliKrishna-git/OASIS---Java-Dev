import java.util.*;

class Question {
    String question;
    String[] options;
    char correctAnswer;

    public Question(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void display() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((char) (i + 'A') + ") " + options[i]);
        }
    }

    public boolean checkAnswer(char answer) {
        return answer == correctAnswer;
    }
}

class Candidate {
    String name;
    String password;
    int attempts = 0;
    List<Integer> scores = new ArrayList<>();
    List<Long> timeTakenList = new ArrayList<>();
    boolean hasAttended = false;

    public Candidate(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void updateProfile(String newPassword) {
        this.password = newPassword;
        System.out.println("Password updated successfully.");
    }

    public void addExamResult(int score, long timeTaken) {
        this.scores.add(score);
        this.timeTakenList.add(timeTaken);
        this.attempts++;
        this.hasAttended = true;
    }
}

class Admin {
    String username = "ADMIN";
    String password = "admin@123";
}

class Exam {
    private List<Question> questions = new ArrayList<>();
    private Map<String, Candidate> candidates = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public boolean adminLogin(String username, String password) {
        Admin admin = new Admin();
        return admin.username.equals(username) && admin.password.equals(password);
    }

    public void addCandidate() {
        System.out.println("Enter candidate name: ");
        String name = sc.next();
        System.out.println("Enter candidate password: ");
        String password = sc.next();
        candidates.put(name, new Candidate(name, password));
        System.out.println("Candidate added successfully!");
    }

    public void viewStatus() {
        System.out.println("Candidate Exam Status: ");
        for (Map.Entry<String, Candidate> entry : candidates.entrySet()) {
            Candidate candidate = entry.getValue();
            System.out.println("Name: " + candidate.name);
            System.out.println("Attempts: " + candidate.attempts);
            if (candidate.hasAttended) {
                for (int i = 0; i < candidate.attempts; i++) {
                    System.out.println("Attempt " + (i + 1) + ": Score = " + candidate.scores.get(i) + ", Time Taken = " + candidate.timeTakenList.get(i) + " seconds");
                }
            } else {
                System.out.println("Status: Not Attended");
            }
        }
    }

    public Candidate candidateLogin(String name, String password) {
        if (candidates.containsKey(name)) {
            Candidate candidate = candidates.get(name);
            if (candidate.password.equals(password)) {
                return candidate;
            }
        }
        return null;
    }

    public void updateProfile(Candidate candidate) {
        System.out.println("Enter new password: ");
        String newPassword = sc.next();
        candidate.updateProfile(newPassword);
    }

    public void setupQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 'C'));
        questions.add(new Question("Which is the largest planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 'C'));
        questions.add(new Question("Who developed Java?", new String[]{"James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Ken Thompson"}, 'A'));
    }

    public void addQuestion() {
        System.out.println("Enter the question: ");
        sc.nextLine();
        String questionText = sc.nextLine();

        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter option " + (char) ('A' + i) + ": ");
            options[i] = sc.nextLine();
        }

        System.out.println("Enter the correct answer (A/B/C/D): ");
        char correctAnswer = sc.next().charAt(0);

        questions.add(new Question(questionText, options, correctAnswer));
        System.out.println("Question added successfully!");
    }

    public void deleteQuestion() {
        System.out.println("Enter the question number to delete: ");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).question);
        }

        int questionIndex = sc.nextInt() - 1;
        if (questionIndex >= 0 && questionIndex < questions.size()) {
            questions.remove(questionIndex);
            System.out.println("Question deleted successfully!");
        } else {
            System.out.println("Invalid question number.");
        }
    }

    public void viewQuestions() {
        System.out.println("Current Questions:");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).question);
            for (int j = 0; j < questions.get(i).options.length; j++) {
                System.out.println((char) ('A' + j) + ") " + questions.get(i).options[j]);
            }
        }
    }

    public void startExam(Candidate candidate) {
        long startTime = System.currentTimeMillis();
        int score = 0;
        int questionIndex = 0;
        int examTimeLimit = 30; // Total exam time (seconds)
        long endTime = startTime + examTimeLimit * 1000;

        while (questionIndex < questions.size() && System.currentTimeMillis() < endTime) {
            Question q = questions.get(questionIndex);
            q.display();
            System.out.println("Time remaining: " + (endTime - System.currentTimeMillis()) / 1000 + " seconds");

            long questionStartTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - questionStartTime < 10000) {
                if (System.currentTimeMillis() >= endTime) {
                    System.out.println("Time's up!");
                    break;
                }

                System.out.print("Your answer (A/B/C/D): ");
                String input = sc.nextLine().trim();
                if (!input.isEmpty()) {
                    char answer = input.charAt(0);
                    if (q.checkAnswer(answer)) {
                        score++;
                    }
                    break;
                }
            }
            questionIndex++;
        }

        long totalTimeTaken = (System.currentTimeMillis() - startTime) / 1000;

        candidate.addExamResult(score, totalTimeTaken);

        System.out.println("Exam Finished! Your score: " + score);
        System.out.println("Total Time Taken: " + totalTimeTaken + " seconds");
    }
}

public class p4_Online_Examination {
    private static Scanner sc = new Scanner(System.in);
    private static Exam exam = new Exam();

    public static void main(String[] args) {
        exam.setupQuestions();
        boolean running = true;

        while (running) {
            System.out.println("1. Admin Login\n2. Candidate Login\n3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Username: ");
                    String adminUser = sc.next();
                    System.out.print("Enter Admin Password: ");
                    String adminPass = sc.next();

                    if (exam.adminLogin(adminUser, adminPass)) {
                        System.out.println("Admin Logged in Successfully!");
                        adminPanel();
                    } else {
                        System.out.println("Invalid Admin Credentials.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Candidate Name: ");
                    String candidateName = sc.next();
                    System.out.print("Enter Candidate Password: ");
                    String candidatePassword = sc.next();
                    Candidate candidate = exam.candidateLogin(candidateName, candidatePassword);

                    if (candidate != null) {
                        System.out.println("Candidate Logged in Successfully!");
                        candidatePanel(candidate);
                    } else {
                        System.out.println("Invalid Candidate Credentials.");
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void adminPanel() {
        boolean adminRunning = true;

        while (adminRunning) {
            System.out.println("\n1. Add Candidate\n2. View Candidate Status\n3. Manage Questions\n4. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    exam.addCandidate();
                    break;
                case 2:
                    exam.viewStatus();
                    break;
                case 3:
                    manageQuestions();
                    break;
                case 4:
                    adminRunning = false;
                    System.out.println("Logged out as Admin.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void manageQuestions() {
        boolean questionRunning = true;

        while (questionRunning) {
            System.out.println("\n1. Add Question\n2. Delete Question\n3. View Questions\n4. Back to Admin Menu");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    exam.addQuestion();
                    break;
                case 2:
                    exam.deleteQuestion();
                    break;
                case 3:
                    exam.viewQuestions();
                    break;
                case 4:
                    questionRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void candidatePanel(Candidate candidate) {
        boolean candidateRunning = true;

        while (candidateRunning) {
            System.out.println("\n1. Update Profile\n2. Start Exam\n3. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    exam.updateProfile(candidate);
                    break;
                case 2:
                    exam.startExam(candidate);
                    break;
                case 3:
                    candidateRunning = false;
                    System.out.println("Logged out as Candidate.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
