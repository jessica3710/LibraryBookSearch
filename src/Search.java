
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Search Assignment
 * December 3, 2018 
 * Jessica Zheng
 * A program that allows the user to find a book through searches such as reference number, title and author
 */
public class Search extends javax.swing.JFrame {

    //ArrayList books to store the reference numbers, titles and authors
    private ArrayList<Book> books;

    /**
     * Creates new form Search
     */
    public Search() {
        initComponents();
        books = new ArrayList<>();
        displayArea.setEditable(false); //users can not write in the text box
        try {
            //reads the BookList - Copy.txt file
            FileReader fr = new FileReader("BookList - Copy.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            displayArea.setText("");
            while ((line = br.readLine()) != null) {
                //split the line of input into an array of strings 
                // the "\\s" splits on a space
                line = line.trim();
                System.out.println(line);
                Book book = new Book();
                //fill book out
                String[] lineSplit = line.split("\\t");
                book.setRefNumber(Integer.parseInt(lineSplit[0]));
                book.setTitle(lineSplit[1]);
                book.setAuthor(lineSplit[2]);
                //adds to arraylist books
                books.add(book);

                Collections.sort(books, new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        if (o1.getRefNumber() < o2.getRefNumber()) {
                            return -1;
                        } else if (o1.getRefNumber() == o2.getRefNumber()) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
            }
            br.close();
        } catch (IOException e) {
            //don't need message, just stop from crashing  
            //System.out.println("Import Failed");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBar = new javax.swing.JTextField();
        searchBttn = new javax.swing.JButton();
        dropDown = new javax.swing.JComboBox<>();
        reportArea = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchBttn.setText("Search");
        searchBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBttnActionPerformed(evt);
            }
        });

        dropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Number", "Title", "Author" }));

        reportArea.setFocusable(false);

        displayArea.setColumns(20);
        displayArea.setRows(5);
        reportArea.setViewportView(displayArea);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Book Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(471, 471, 471)
                        .addComponent(searchBttn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reportArea)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(searchBttn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(reportArea, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBttnActionPerformed

        String input = searchBar.getText().trim(); //initializes variable to the text from the user
        String dropDownInput = (String) dropDown.getSelectedItem(); //receives drop down input
        displayArea.setText("Reference #\tTitle\t\tAuthor\n"); //makes the format as a T-chart
        //switch statement to recognize which list it is either number, title or author
        switch (dropDownInput) {
            case "Number":
                
                int referenceNum = Integer.parseInt(input); //reference number is changed to an integer
                int position;
                //the search is true then finds the position with binary search
                if (BinarySearch(books, referenceNum, 0, books.size())) {
                    position = binarySearch(books, referenceNum, 0, books.size());
                    displayArea.append(books.get(position).getRefNumber() + "\t" + books.get(position).getTitle() + "\t"
                            + books.get(position).getAuthor() + "\n");
                } else if (!BinarySearch(books, referenceNum, 0, books.size())) {
                    //when false displays that it did not find the refernce number
                    displayArea.append("Did not find " + referenceNum);
                }
                break;
            case "Title":
                String title = input.toLowerCase();
                //looks through all the books in the books arraylist
                for (Book b : books) {
                    if (b.getTitle().toLowerCase().contains(title)) {
                        //if it is in the arraylist displays in the textbox
                        displayArea.append(b.getRefNumber() + "\t" + b.getTitle() + "\t" + b.getAuthor() + "\n");
                    }
                }
                break;
            case "Author":
                String author = input.toLowerCase();
                //looks through all the authors in the books arraylist
                for (Book b : books) {
                    if (b.getAuthor().toLowerCase().contains(author)) {
                        //if it is in the arraylist displays in the textbox
                        displayArea.append(b.getRefNumber() + "\t" + b.getTitle() + "\t" + b.getAuthor() + "\n");
                    }
                }
                break;
        }


    }//GEN-LAST:event_searchBttnActionPerformed
    //BinarySearch that returns true or false if found in the Arraylist
    public static Boolean BinarySearch(ArrayList<Book> b, int target, int left, int right) {
        int middle = left + (right - left) / 2;
        if (left > right) {
            return false;
        } else if (b.get(middle).getRefNumber() == target) {
            return true;
        } else if (b.get(middle).getRefNumber() > target) {
            return BinarySearch(b, target, left, middle - 1);
        } else if (b.get(middle).getRefNumber() < target) {
            return BinarySearch(b, target, middle + 1, right - 1);
        }
        return false;
    }
    //BinarySearch that returns the position if found in the Arraylist
    public static int binarySearch(ArrayList<Book> books, int target, int left, int right) {
        int middle = left + (right - left) / 2;
        if (left > right) {
            return -1;
        } else if (books.get(middle).getRefNumber() == target) {
            return middle;
        } else if (books.get(middle).getRefNumber() > target) {
            return binarySearch(books, target, left, middle - 1);
        } else if (books.get(middle).getRefNumber() < target) {
            return binarySearch(books, target, middle + 1, right);
        }
        return -1;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea displayArea;
    private javax.swing.JComboBox<String> dropDown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane reportArea;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchBttn;
    // End of variables declaration//GEN-END:variables
}
